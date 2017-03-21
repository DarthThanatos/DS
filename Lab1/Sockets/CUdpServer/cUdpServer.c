#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>

int main(int argc, char **argv) {

    printf("C UDP SERVER\n");
    
    // config
    int portNb = 12345;
    int recvBuffLen = 1000;
    
    // prepare variables
    struct sockaddr_in serv_addr, cli_addr;         // sockaddr_in struct for server and client
    int fd;                                         // fd (file descriptor)
    socklen_t cli_len;                              // used when receiving data
    int len;                                        // used for send/recv
    char recvBuffer[recvBuffLen];                   // buffer for message from client
    
    // create server socket
    fd = socket(AF_INET, SOCK_DGRAM, 0);            // address family Internet, stream, auto choose protocol (dgam -> UDP)
    if (fd < 0) {
        perror("socket error");
        exit(EXIT_FAILURE);
    }
    
    // fill sockaddr_in struct for server
    bzero((char*)&serv_addr, sizeof(serv_addr));    // clear
    serv_addr.sin_family = AF_INET;                 // address family Internet
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);  // any address 
    serv_addr.sin_port = htons(portNb);             // port number (host to network byte order)    
    
    // bind socket to address
    if (bind(fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0){
        perror("bind error");
        exit(EXIT_FAILURE);
    };

    // loop
    while (1) {

        // receive msg
        len = recvfrom(fd, recvBuffer, recvBuffLen, 0, (struct sockaddr*)&cli_addr, &cli_len);
        recvBuffer[len] = 0;
        printf("received msg: %s\n", recvBuffer);
    }
    
    // close
    close(fd);
}