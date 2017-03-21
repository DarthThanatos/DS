#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>

int main(int argc, char **argv) {
    
    printf("C UDP CLIENT\n");
    
     // config
    int portNb = 12345;    
    
    // prepare variables
    struct sockaddr_in serv_addr, cli_addr;         // sockaddr_in struct for server and client
    int fd;                                         // fd (file descriptor)   
    int len;                                        // used for send/recv
    char sendBuffer[] = "Ping C Udp";               // message to send    
    
    // create server socket
    fd = socket(AF_INET, SOCK_DGRAM, 0);            // address family Internet, stream, auto choose protocol (dgram -> UDP)        
    
    // fill sockaddr_in struct for server
    bzero((char*)&serv_addr, sizeof(serv_addr));            // clear
    serv_addr.sin_family = AF_INET;                         // address family Internet
    serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1");     // localhost
    serv_addr.sin_port = htons(portNb);                     // port number (host to network byte order)

    // fill sockaddr_in struct for client
    bzero((char*)&cli_addr, sizeof(cli_addr));              // clear
    cli_addr.sin_family = AF_INET;                          // address family Internet
    cli_addr.sin_addr.s_addr = inet_addr("127.0.0.1");      // localhost
    cli_addr.sin_port = htons(0);                           // port number (host to network byte order)
    
    // bind
    if (bind(fd, (struct sockaddr*)&cli_addr, sizeof(cli_addr)) < 0){
        perror("bind error");
        exit(EXIT_FAILURE);
    }
    printf("bind ok\n");
    
    // send message
    len = sendto(fd, sendBuffer, strlen(sendBuffer), 0, (struct sockaddr*)&serv_addr, sizeof(serv_addr));    
    printf("msg sent");
    
    // close
    close(fd);
    
    // exit
    return EXIT_SUCCESS;
}
