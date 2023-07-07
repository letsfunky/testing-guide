package com.letsfunky.testingstyle;

public class GreetingService {
    private final EmailGateway emailGateway;

    public GreetingService(EmailGateway emailGateway) {
        this.emailGateway = emailGateway;
    }

    public void greetUser(String user) {
        emailGateway.sendGreetings();
    }

    public static class EmailGateway {
        public void sendGreetings() {
            // empty intended
        }
    }
}
