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
	bytes[0] = (nb>>24) & 0xff;
	bytes[1] = (nb>>16) & 0xff;
	bytes[2] = (nb>>8) & 0xff;
	bytes[3] = (nb>>0) & 0xff;
	return (bytes[3]<<24) | (bytes[2]<<16) | (bytes[1]<<8) | (bytes[0]<<0);
}

int main(int argc, char **argv) {
    
    printf("C TCP CLIENT\n");
    int nb = argc == 2 ? atoi(argv[1]) : 9;
     // config
    int portNb = 12345;
    int recvBuffLen = 1000;
    
    // prepare variables
    struct sockaddr_in serv_addr, cli_addr;         // sockaddr_in struct for server
    int fd;                                         // fd (file descriptor) for client (our) socket    
    int len;                                        // used for send/recv    
    char sendBuffer[] = "Ping C Tcp";               // message to send
    char recvBuffer[recvBuffLen];                   // buffer for message from server
    
    // create server socket
    fd = socket(AF_INET, SOCK_STREAM, 0);           // address family Internet, stream, auto choose protocol (stream -> TCP)    
    
    // fill sockaddr_in struct for server
    bzero((char*)&serv_addr, sizeof(serv_addr));            // clear
    serv_addr.sin_family = AF_INET;                         // address family Internet
    serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1");     // localhost
    serv_addr.sin_port = htons(portNb);                     // port number (host to network byte order)
    
    // establish connection 
    if (connect(fd, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) < 0){
        perror("connect error");
        exit(EXIT_FAILURE);
    }
    printf("connected to server %x\n",nb);
    nb = reverseNumber(nb);    
    // send message  
    len = send(fd, &nb, 4, 0);    
    
    // receive response
	nb = 0;
    len = recv(fd, &nb, 4, MSG_WAITALL);
    nb = reverseNumber(nb);    
    printf("received response: %d = %x\n", nb,nb);

    // close
    close(fd);
    
    // exit
    return EXIT_SUCCESS;
}
