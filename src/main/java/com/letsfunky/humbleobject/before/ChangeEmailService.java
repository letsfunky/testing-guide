package com.letsfunky.humbleobject.before;

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
        if (user.email().equals(newEmail)) {
            return;
        }

        var company = companyRepository.findById(user.companyId());
        var companyDomainName = company.domainName();
        var noOfEmployees = company.noOfEmployees();

        var emailDomain = newEmail.split("@")[1];
        var isEmailCorporate = emailDomain.equals(companyDomainName);

        var newUserType = isEmailCorporate ? UserType.Employee : UserType.Customer;
        if (user.userType() != newUserType) {
            var delta = newUserType == UserType.Employee ? 1 : -1;
            var newNumbers = noOfEmployees + delta;
            company.noOfEmployees(newNumbers);
            companyRepository.save(company);
        }

        user.email(newEmail);
        user.userType(newUserType);

        userRepository.save(user);
        messageBus.sendEmailChangedMessage(userId, newEmail);
    }
}