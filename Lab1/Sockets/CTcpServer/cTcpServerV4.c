#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>

int reverseNumber(int nb){
	unsigned char bytes[4];
	printf("nb: %u %x\n", nb, nb);
	bytes[0] = (nb>>24) & 0xff;
	bytes[1] = (nb>>16) & 0xff;
	bytes[2] = (nb>>8) & 0xff;
	bytes[3] = (nb>>0) & 0xff;
	printf("byte0=%x byte1=%x byte2=%x byte3=%x \n",bytes[0], bytes[1],bytes[2],bytes[3]);
	return (bytes[3]<<24) | (bytes[2]<<16) | (bytes[1]<<8) | (bytes[0]<<0);
}

int main(int argc, char** argv) {

    printf("C TCP SERVER\n");
    
    // config
    int portNb = 12345;
    int recvBuffLen = 1000;
    
    // prepare variables
    struct sockaddr_in serv_addr, cli_addr;         // sockaddr_in struct for server and client
    int serv_fd, cli_fd;                            // fd (file descriptor) for server and client
    socklen_t cli_len;                              // used when accepting clients
    int len;                                        // used for send/recv    
    char sendBuffer[] = "Pong C Tcp";               // response message from server
    char recvBuffer[recvBuffLen];                   // buffer for message from client
    
    // create server socket
    serv_fd = socket(AF_INET, SOCK_STREAM, 0);      // address family Internet, stream, auto choose protocol (stream -> TCP)    
    
    // fill sockaddr_in struct for server
    bzero((char*)&serv_addr, sizeof(serv_addr));    // clear
    serv_addr.sin_family = AF_INET;                 // address family Internet
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);  // any address 
    serv_addr.sin_port = htons(portNb);             // port number (host to network byte order)                                                    
    
    // bind socket to address    
    if (bind(serv_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0){
        perror("bind error");
        exit(EXIT_FAILURE);
    };
    
    // start listening
    listen(serv_fd, 5);
    printf("listening for clients\n");
    
    // loop
    while (1) {
		int nb = 0;
        
        // accept client
        cli_fd = accept(serv_fd, (struct sockaddr*)&cli_addr, &cli_len);        
        if(cli_fd==-1) perror("Error");
		printf("client accepted\n");
        
        // receive msg
        len = recv(cli_fd,  &nb, 4, MSG_WAITALL);
		if (len == -1)perror("recv error:");
		
        nb = reverseNumber(nb);
        printf("received msg: %d %x\n", nb,nb);
		nb = reverseNumber(nb + 1);
		
        // send response
        len = send(cli_fd, &nb, 4, 0);
		if (len == -1)perror("send error:");
        
        // close client
        if (close(cli_fd) == -1 ) perror("close error:");
    }
    
    // close server
    close(serv_fd);
}