package zad1b;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Z1b_Consumer {

    public static void main(String[] argv) throws Exception {
        // po ponownym odpaleniu wiadomosc nie zostala ponownie odczytana; to samo w trakcie
        // info
        System.out.println("Z1a CONSUMER");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // queue
        String QUEUE_NAME = "queue1b";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // consumer (handle msg)
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message + "; sleeping " + message + " seconds");
                try {
                    Thread.sleep(Integer.parseInt(message) * 1000);
                } catch (InterruptedException ex) {
                    
                }
                System.out.println("End of processing");
            }
        };

        // start listening
        System.out.println("Waiting for messages...");
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // close
        //channel.close();
        //connection.close();
    }
}
