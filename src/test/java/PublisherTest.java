import org.junit.Test;
import org.junit.runner.RunWith;
import org.langera.spock.Publisher;
import org.langera.spock.Subscriber;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class PublisherTest {

    private Publisher publisher = new Publisher();

    @Test
    public void eventsArePublishedToAllSubscribers() {
        final Subscriber subscriber1 = mock(Subscriber.class);
        final Subscriber subscriber2 = mock(Subscriber.class);
        final Subscriber subscriber3 = mock(Subscriber.class);

        publisher.add(subscriber1);
        publisher.add(subscriber2);

        publisher.publish("hello");

        Mockito.verify(subscriber1, times(1)).receive("hello");
        Mockito.verify(subscriber2, times(1)).receive("hello");
        Mockito.verify(subscriber3, times(0)).receive("hello");
    }

    @Test
    public void publisherRecoversSubscriberExceptionMockAndStub() {
        final Subscriber subscriber1 = mock(Subscriber.class);
        final Subscriber subscriber2 = mock(Subscriber.class);
        Mockito.doThrow(new RuntimeException()).when(subscriber1).receive("hello");
        publisher.add(subscriber1);
        publisher.add(subscriber2);

        publisher.publish("hello");

        Mockito.verify(subscriber1, times(1)).receive("hello");
        Mockito.verify(subscriber2, times(1)).receive("hello");
    }
}
