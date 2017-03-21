#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>

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
        
        // accept client
        cli_fd = accept(serv_fd, (struct sockaddr*)&cli_addr, &cli_len);        
        printf("client accepted\n");
        
        // receive msg
        len = recv(cli_fd, recvBuffer, recvBuffLen, 0);
        recvBuffer[len] = 0;
        printf("received msg: %s\n", recvBuffer);
        
        // send response
        len = send(cli_fd, sendBuffer, strlen(sendBuffer), 0);
        
        // close client
        close(cli_fd);
    }
    
    // close server
    close(serv_fd);
}