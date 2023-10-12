package mod.cons;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	
	@KafkaListener(
			topics ="wikimedia_recentchange",
			groupId = "myGroup")
	public void consume(String message) {
		
		System.out.println("###  " + message + "  ###");
		
	}
}
