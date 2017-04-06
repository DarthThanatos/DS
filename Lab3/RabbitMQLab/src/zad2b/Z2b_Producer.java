package zad2b;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Z2b_Producer {

    public static void main(String[] argv) throws Exception {
       
        //lazy.orange.rabbit
        //lazy.black.rabbit
        
        // info
        System.out.println("Z2b PRODUCER");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchange
        String EXCHANGE_NAME = "exchange2b";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
       
        while (true) {

            // read msg
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter message: ");
            String message = br.readLine();
            
            // break condition
            if ("exit".equals(message)) {
                break;
            }

            // publish
            System.out.println("Type routing key:");
            String routing_key = br.readLine();
            channel.basicPublish(EXCHANGE_NAME, routing_key, null,(message + " key: " + routing_key).getBytes("UTF-8"));
            System.out.println("Sent: " + message);
        }
    }
}
