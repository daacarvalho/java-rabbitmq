import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws TimeoutException, IOException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String message = "Hello World";
            channel.basicPublish("",QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '"+message+"'");

        }
    }
}
