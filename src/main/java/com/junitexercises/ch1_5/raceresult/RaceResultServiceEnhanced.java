package com.junitexercises.ch1_5.raceresult;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RaceResultServiceEnhanced {

    Map<String, Collection<Client>> categoryClientsMap = new HashMap<>();
    Logger logger;

    public RaceResultServiceEnhanced(Logger logger) {
        this.logger = logger;
    }

    public void addSubscriber(Client client, String category) {
        categoryClientsMap.computeIfAbsent(category, k -> new HashSet<>()).add(client);
    }

    public void send(Message message, String category) {
        logger.log(message, LocalDateTime.now());
        categoryClientsMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(category))
                .forEach(entry -> entry.getValue().forEach(c -> c.receive(message)));
    }

    public void removeSubscriber(Client client, String category) {
        categoryClientsMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(category))
                .forEach(entry -> entry.getValue().remove(client));
    }
}