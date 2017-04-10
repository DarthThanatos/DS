/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.doctor;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert
 */
public class Doctor {
    public static void main(String[] argv) throws Exception{
        String EXCHANGE_NAME = "hospital";
        String[] conditions = {"ankle","knee","elbow"};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localHost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        //queue for msgs coming from Techs
        String diagnosisQueueName = channel.queueDeclare().getQueue();
        
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String adminInfoQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(adminInfoQueueName, EXCHANGE_NAME, "info");
        
        channel.basicConsume(adminInfoQueueName, true, new DefaultConsumer(channel){
           @Override
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]body){
               System.out.println("Got a message from admin");
               System.out.println(new String(body));
           }
                   
        });
        
        channel.basicConsume(diagnosisQueueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body){
                System.out.println("Received dignosis: " + new String(body));
            }
        });
        
        while(true){
            System.out.println("Hello from Doctor");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Type your name: ");
            String patientName = br.readLine();
            System.out.println("Type your condition:\n1-ankle\n2-knee\n3-elbow");
            int patientConditionNumber = 0;
            try{
                patientConditionNumber = Integer.parseInt(br.readLine());
                if (patientConditionNumber > 3 || patientConditionNumber < 1) {
                    System.out.println("Number not in range");
                    continue;
                }
            }catch(Exception e){
                System.out.println("Not a valid number");
                continue;
            }
            String corrId = UUID.randomUUID().toString();
            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(corrId)
                    .replyTo(diagnosisQueueName)
                    .build();
            String patientCondition = conditions[patientConditionNumber-1];
            channel.basicPublish("", patientCondition, props, (patientName + " " + patientCondition).getBytes() );
            channel.basicPublish(EXCHANGE_NAME, "log", null, ("Sent request for diagnosis: " + patientName + " " + patientCondition).getBytes());
        }
    }
}
