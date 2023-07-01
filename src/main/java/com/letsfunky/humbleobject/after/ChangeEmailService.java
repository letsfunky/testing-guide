package com.letsfunky.humbleobject.after;

public class ChangeEmailService {
    private final MessageBus messageBus;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public ChangeEmailService(MessageBus messageBus, CompanyRepository companyRepository, UserRepository userRepository) {
        this.messageBus = messageBus;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public void changeEmail(int userId, String newEmail) {
        var user = userRepository.findById(userId);
        var company = companyRepository.findById(user.companyId());

        user.changeEmail(newEmail, company);

        companyRepository.save(company);
        userRepository.save(user);
        user.emailChangedEvents().forEach(
                event -> messageBus.sendEmailChangedMessage(event.userId(), event.newEmail())
        );
    }
}