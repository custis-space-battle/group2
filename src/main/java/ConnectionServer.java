import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class ConnectionServer {
    private final static String QUEUE_TO = "to_group2";
    public static final String QUEUE = "group2";
    private static final Logger log = LoggerFactory.getLogger(ConnectionServer.class);

    public static void main(String[] argv) throws MalformedFrameException, NoSuchAlgorithmException,
            KeyManagementException, URISyntaxException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://group2:tvqNMo@91.241.45.69/debug");
        try {
            final Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            final WarField warField = new WarField();
            System.out.println(channel);
            channel.queueDeclare(QUEUE_TO, false, false, true, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            channel.queueBind(QUEUE_TO, QUEUE_TO, QUEUE_TO);
            System.out.println(channel);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public  void handleDelivery(String consumerTag, Envelope envelope,
                                            AMQP.BasicProperties properties, byte[] body)
                        throws IOException {

                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                    Game game = new Game();
                    message = game.compare(message, warField);
                    System.out.println();

                    Channel channel1 = connection.createChannel();
                    if(message != null){
                        channel1.basicPublish(QUEUE, QUEUE, null, message.getBytes());
                    }
                }
            };
            channel.basicPublish(QUEUE, QUEUE, null, "start: USUAL".getBytes());
            channel.basicConsume(QUEUE_TO, true, consumer);

        } catch (TimeoutException e) {
            log.trace("время ожидания");

        } catch (InterruptedIOException e) {
            log.trace("ошибка в потоках");

        } catch (IOException e) {
            log.trace("общее исключение");

        }
    }
}