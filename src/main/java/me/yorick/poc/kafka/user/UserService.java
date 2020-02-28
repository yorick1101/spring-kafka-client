package me.yorick.poc.kafka.user;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    @Autowired
    private KafkaTemplate<String, Object> template;

    private static final String topicName = "Users";
    private User latestUser = null;

    public String publishMessage(User user) throws ExecutionException, InterruptedException {
        SendResult<String, Object> result = template.send(topicName, user).get();
        template.flush();
        ProducerRecord record = result.getProducerRecord();
        return record.topic() + ':' + record.partition()+':'+((User)record.value()).getName();
    }

    @KafkaListener(topics = topicName)
    public void consume(ConsumerRecord<String, Object> message){
        User user = (User)message.value();
        System.out.println("Received from "+message.topic()+" "+user.getName()+"  "+user.getId());
        latestUser = user;
    }

    public User getOne(){
        System.out.println("latestUser"+(latestUser==null));
        return latestUser;
    }


}
