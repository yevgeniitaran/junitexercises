package com.junitexercises.ch7_powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
public class MailClientTestMockito {

    @Test
    public void sendMailTest() throws Exception {
        MailClient client = new MailClient();
        Email mail = mock(Email.class);
        whenNew(Email.class).withAnyArguments().thenReturn(mail);
        PowerMockito.mockStatic(EmailServer.class);
        EmailServer.sendEmail(mail);
        PowerMockito.verifyStatic();
        EmailServer.sendEmail(eq(mail));

    }
}

class MailClient {
    public void sendEmail(String address, String title, String body) {
        Email email = new Email(address, title, body);
        EmailServer.sendEmail(email);
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
    public static void sendEmail(Email email) {

    }
}