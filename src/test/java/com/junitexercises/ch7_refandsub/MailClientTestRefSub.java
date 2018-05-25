package com.junitexercises.ch7_refandsub;


import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MailClientTestRefSub {

    private Email email;

    @Test
    public void sendEmailTest() {
        EmailServer emailServer = mock(EmailServer.class);
        MailClient mailClient = new MailClientExt(emailServer);
        email = mock(Email.class);
        mailClient.sendEmail("address", "title", "body");
        verify(emailServer).sendEmail(email);
    }

    class MailClientExt extends MailClient {

        public MailClientExt(EmailServer emailServer) {
            super(emailServer);
        }

        @Override
        protected Email createEmail(String address, String title, String body) {
            return email;
        }

    }

}

class MailClient {

    private final EmailServer emailServer;

    public MailClient(EmailServer emailServer) {
        this.emailServer = emailServer;
    }

    public void sendEmail(String address, String title, String body) {
        Email email = createEmail(address, title, body);
        emailServer.sendEmail(email);
    }

    protected Email createEmail(String address, String title, String body) {
        return new Email(address, title, body);
    }
}

class Email {
    String address;
    String title;
    String body;

    public Email(String address, String title, String body) {
        this.address = address;
        this.title = title;
        this.body = body;
    }
}

class EmailServer {
    public void sendEmail(Email email) {

    }
}