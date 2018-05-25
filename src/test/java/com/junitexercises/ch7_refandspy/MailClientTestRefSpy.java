package com.junitexercises.ch7_refandspy;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class MailClientTestRefSpy {

    private Email email;

    @Test
    public void sendEmailTest() {
        EmailServer emailServer = mock(EmailServer.class);
        MailClient mailClient = spy(new MailClient(emailServer));
        email = mock(Email.class);
        doReturn(email).when(mailClient).createEmail("address", "title", "body");
        mailClient.sendEmail("address", "title", "body");
        verify(emailServer).sendEmail(email);
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

    public Email createEmail(String address, String title, String body) {
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