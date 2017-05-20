import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * Created by bubal on 20.05.2017.
 */
public class Main {

    public static final String QUEUE_TO = "to_group2";
    public static final String QUEUE = "group2";

    public static void main(String[] args) throws MalformedURLException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://group2:tvqNMo@91.241.45.69/debug");

        try(Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_TO, false, false, true, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            channel.queueBind(QUEUE_TO, QUEUE_TO, QUEUE_TO);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };
            channel.basicPublish(QUEUE, QUEUE, null, "Hello, dude".getBytes());
            channel.basicConsume(QUEUE_TO, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
