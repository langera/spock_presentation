package org.langera.spock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Publisher {

    private final List<Subscriber> subscribers = new ArrayList<>();

    public void add(final Subscriber element) {
        subscribers.add(element);
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void fire(Object event) {
        subscribers.forEach(subscriber -> {
            try {
                subscriber.receive(event);
            } catch (Exception e) {
                System.err.println("something better");
            }
        });
    }
}
