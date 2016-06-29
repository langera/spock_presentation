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
        subscribers.forEach(new Consumer<Subscriber>() {
            @Override
            public void accept(Subscriber subscriber) {
                subscriber.receive(event);
            }
        });
    }
}
