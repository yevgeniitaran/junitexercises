package com.junitexercises.ch1_5.raceresult;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RaceResultServiceEnhancedTest {

    private Logger logger = mock(Logger.class);
    private RaceResultServiceEnhanced raceResultServiceEnhanced = new RaceResultServiceEnhanced(logger);
    private Client clientA = mock(Client.class, "clientA");
    private Client clientB = mock(Client.class, "clientB");
    private Message message = mock(Message.class);
    private String categoryA = "categoryA";
    private String categoryB = "categoryB";

    private ArgumentCaptor<LocalDateTime> argument = ArgumentCaptor.forClass(LocalDateTime.class);

    @Test
    public void messageShouldBeSentToAllSubscribedClients() {
        raceResultServiceEnhanced.send(message, categoryA);
        verify(clientA, never()).receive(message);
        verify(clientB, never()).receive(message);
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.send(message, categoryA);
        verify(clientA).receive(message);
    }

    @Test
    public void allSubscribedClientsShouldReceiveMessages() {
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.addSubscriber(clientB, categoryA);
        raceResultServiceEnhanced.send(message, categoryA);
        verify(clientA).receive(message);
        verify(clientB).receive(message);
    }

    @Test
    public void notSubscribedClientShouldNotReceiveMessage() {
        raceResultServiceEnhanced.send(message, categoryA);
        verify(clientA, never()).receive(message);
        verify(clientB, never()).receive(message);
    }

    @Test
    public void shouldSendOnlyOneMessageToMultiSubscriber() {
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.send(message, categoryA);
        verify(clientA).receive(message);
    }

    @Test
    public void unsubscribedClientShouldNotReceiveMessages() {
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.removeSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.send(message, categoryA);
        verify(clientA, never()).receive(message);
    }

    @Test
    public void subscribe_ForDifferentCategory_NotifySpecificCategories() {
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.addSubscriber(clientB, categoryB);
        raceResultServiceEnhanced.send(message, categoryA);
        verify(clientA).receive(message);
        verify(clientB, never()).receive(message);
    }

    @Test
    public void subscribe_ForSeveralCategories_NotifyForBothCategories() {
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.addSubscriber(clientA, categoryB);
        raceResultServiceEnhanced.send(message, categoryA);
        verify(clientA).receive(message);
        raceResultServiceEnhanced.send(message, categoryB);
        verify(clientA, times(2)).receive(message);
    }

    @Test
    public void send_ForUsualCase_MakeLogging() {
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.send(message, categoryA);
        verify(logger).log(eq(message), argument.capture());
        assertEquals(argument.getValue().getSecond(), LocalDateTime.now().getSecond());
        verify(clientA).receive(message);
    }

    @Test
    public void send_ForHundredTimes_CorrectlyReceived() {
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        for (int i = 0; i < 100; i++) {
            raceResultServiceEnhanced.send(message, categoryA);
        }
        verify(clientA,times(100)).receive(message);
    }

    @Test
    public void unsubscribe_ForUnsubscribedClient_ProcessedCorrectly() {
        raceResultServiceEnhanced.addSubscriber(clientA, categoryA);
        raceResultServiceEnhanced.addSubscriber(clientB, categoryA);
        raceResultServiceEnhanced.removeSubscriber(clientB, categoryB);
        raceResultServiceEnhanced.send(message, categoryA);
        verify(clientA).receive(message);
        verify(clientB).receive(message);
    }
}