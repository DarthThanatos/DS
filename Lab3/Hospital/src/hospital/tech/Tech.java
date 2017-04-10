/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.tech;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Robert
 */
public class Tech {
    
    public static boolean argsCorrect(String[] argv){
        if (argv.length != 2) {
            System.out.println("Not enough arguments");
            return false;
        }
        if(argv[0].equals(argv[1])){
            System.out.println("names of conditions should be different");
            return false;
        }
        String[] validConditionsNamesArray = {"ankle", "knee", "elbow"};
        List<String> validConditionsNamesList = Arrays.asList(validConditionsNamesArray);
        if (!validConditionsNamesList.contains(argv[0])){
            System.out.println("Argument: " + argv[0] + " does not describe a condtion!");
            return false;
        }
        if (!validConditionsNamesList.contains(argv[1])){
            System.out.println("Argument: " + argv[1] + " does not describe a condtion!");
            return false;
        }
        return true;
    }
    
    public static void main(String [] argv) throws Exception{
        String EXCHANGE_NAME = "hospital";
        System.out.println("Hello from Tech");
        
        //checking correctness of the input
        if(! argsCorrect(argv)) return;
        
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.DIRECT);
        String adminInfoQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(adminInfoQueueName, EXCHANGE_NAME, "info");
        
        channel.basicConsume(adminInfoQueueName, true, new DefaultConsumer(channel){
           @Override
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]body){
               System.out.println("Got a message from admin");
               System.out.println(new String(body));
           }
                   
        });
        
        for(String queueBinding: argv){
            channel.queueDeclare(queueBinding, false, false, false, null);
                   
            channel.basicConsume(queueBinding, true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties props,  byte[] body){
                     try{
                        //channel.basicAck(envelope.getDeliveryTag(), false);
                        System.out.println("Got: " + new String(body));
                        String[] body_parts = new String(body).split(" ");
                        AMQP.BasicProperties replyProps = new AMQP
                            .BasicProperties
                            .Builder()
                            .correlationId(props.getCorrelationId())
                            .build();
                        channel.basicPublish("", props.getReplyTo(), replyProps, (body_parts[0] + " badanie " + body_parts[1] + " pomyslnie zakonczone").getBytes());
                        channel.basicPublish(EXCHANGE_NAME, "log", null, ("Sent reply for diagnosis: " +  body_parts[0] + " badanie " + body_parts[1] + " pomyslnie zakonczone").getBytes());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
            
            System.out.println("Bound queue to the name: " + queueBinding );
        }
        

       
    }
}
