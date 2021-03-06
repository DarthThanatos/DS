package zad1d;

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

public class Z1d_Consumer {

    public static void main(String[] argv) throws Exception {
        // przy wlaczonym load balancingu i dwoch konsumentach, po wyslaniu trzech cykli 10 sekund 1 sekund
        // kazdy otrzymal na przemian 10 sekundowe zlecenie i 1 sekundowe zlecenie
        // info
        System.out.println("Z1d CONSUMER");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // queue
        String QUEUE_NAME = "queue1d";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicQos(1);
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
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // start listening
        System.out.println("Waiting for messages...");
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // close
        //channel.close();
        //connection.close();
    }
}
