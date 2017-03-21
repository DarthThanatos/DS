#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>

int main(int argc, char **argv) {
    
    printf("C TCP CLIENT\n");
    
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
    printf("connected to server\n");
        
    // send message  
    len = send(fd, sendBuffer, strlen(sendBuffer), 0);    
    
    // receive response
    len = recv(fd, recvBuffer, recvBuffLen, 0);    
    recvBuffer[len] = 0;
    printf("received response: %s\n", recvBuffer);

    // close
    close(fd);
    
    // exit
    return EXIT_SUCCESS;
}
