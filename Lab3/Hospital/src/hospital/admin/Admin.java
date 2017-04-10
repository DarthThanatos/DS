/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.admin;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

/**
 *
 * @author Robert
 */
public class Admin{
    
    static String logHistory = "";
    static String EXCHANGE_NAME = "hospital";
    private static void sendHistory(Channel channel) throws Exception{
        channel.basicPublish(EXCHANGE_NAME, "info", null, logHistory.getBytes());
        System.out.println("History sent");
    }
    
    private static void sendCustomMsg(Channel channel) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type the msg u want to send to everyone");
        String msgToSend = br.readLine();
        channel.basicPublish(EXCHANGE_NAME, "info", null, msgToSend.getBytes());
        System.out.println("Msg sent");
    }
    
    private static void listHistory(){
        System.out.println(logHistory);
    }
    
    public static void main(String[] argv) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection("localhost");
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String logQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(logQueueName, EXCHANGE_NAME, "log");
        
        
        channel.basicConsume(logQueueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties props, byte[] body){
                String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
                System.out.println("Got log entry: " + new String(body) + " timestamp: " + timeStamp);
                logHistory += "[" + timeStamp+ "] " + new String(body) + "\n" ;
            }
        });
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Type 1, 2 or 3:\n1 - list logging history\n2 - send logging history to everybody\n3 - Send custom msg to everybody:");
            int mode = -1;
            try{
                mode = Integer.parseInt(br.readLine());
            }catch(Exception e){
                System.out.println("Not a valid number");
                continue;
            }
            switch(mode){
                case 1:
                    listHistory();
                    break;
                case 2:
                    sendHistory(channel);
                    break;
                case 3:
                    sendCustomMsg(channel);
                    break;
                default: 
                    System.out.println("Not a valid mode");
                    break;
            }
        }
        
    }
}
