package com.junitexercises.ch1_5.raceresult;

import java.util.Collection;
import java.util.HashSet;

public class RaceResultsService {

    private Collection<Client> clients = new HashSet<>();

    public void addSubscriber(Client client) {
        clients.add(client);
    }

    public void send(Message message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }

    public void removeSubscriber(Client client) {
        clients.remove(client);
    }
}