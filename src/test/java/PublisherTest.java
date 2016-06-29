import org.junit.Test;
import org.junit.runner.RunWith;
import org.langera.spock.Publisher;
import org.langera.spock.Subscriber;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.mock;

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

        publisher.fire("hello");

        Mockito.verify(subscriber1, calls(1)).receive("hello");
        Mockito.verify(subscriber2, calls(1)).receive("hello");
        Mockito.verify(subscriber3, calls(0)).receive("hello");
    }
}
