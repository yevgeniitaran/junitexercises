package com.junitexercises.ch7_redesign;

import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MailClientTestRedesign {

    @Test
    public void sendEmailTest() {
        EmailServer emailServer = mock(EmailServer.class);
        MailClient mailClient = new MailClient(emailServer);
        Email email = new Email("address", "title", "body");
        mailClient.sendEmail(email);
        verify(emailServer).sendEmail(eq(email));
    }

}

class MailClient {

    private final EmailServer emailServer;

    public MailClient(EmailServer emailServer) {
        this.emailServer = emailServer;
    }

    public void sendEmail(Email email) {
        emailServer.sendEmail(email);
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