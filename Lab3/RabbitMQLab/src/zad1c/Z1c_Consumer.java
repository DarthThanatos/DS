package zad1c;

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

public class Z1c_Consumer {

    public static void main(String[] argv) throws Exception {
        // Po skonczeniu - nie odczyta wiadomosci ponownie
        // po wlaczeniu w trakcie (tzn. ubijamy zanim skonczy spac i wlaczamy znowu): odczytuje do momentu w którym dostanie ack
        // gdy mamy uruchomionych dwu konsumerow, jeden otrzyma spanie 20 sekund, a drugi stoi bezczynny, w momencie ubicia tego pierwszego w trakcie spania, drugi przejmuje zadanie, bo nie zostalo ono potwierdzone
        // Jak uruchamiamy 2 consumerow i wysylamy na przemian 10 i 1 sekundowe zlecenia na przemian, to jeden otrzymuje 10s zlecenia a drugi 1 sekundowe
        
        // info
        System.out.println("Z1c CONSUMER");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // queue
        String QUEUE_NAME = "queue1c";
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
