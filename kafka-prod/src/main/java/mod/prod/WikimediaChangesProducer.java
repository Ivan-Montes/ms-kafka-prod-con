package mod.prod;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import java.util.concurrent.TimeUnit;


@Service
public class WikimediaChangesProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	private String  url = "https://stream.wikimedia.org/v2/stream/recentchange";
	private String topic = "wikimedia_recentchange";
	
	public void sendMessage() throws InterruptedException {
		
		EventHandler eventHand = new WikimediaChangesHandler(kafkaTemplate, topic);
		EventSource eventSource = (new EventSource.Builder(eventHand, URI.create(url))).build();
		eventSource.start();
		TimeUnit.MINUTES.sleep(10);
	}
}
