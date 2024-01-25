# SDCC Homework

Transparency to replication mechanism implemented in a client-server application communicating though rpc.

## Table of Contents

- [Explanation](#explanation)
- [Usage](#usage)

## Explanation

There is a server program which runs multiple goroutines to let them listening requests from different ports, specified in the config.json file, about ordering 2 list, the first in ascending order and the second in descending order.
The proxy program accepts requests for the described task and works as a load balancer to spread them among the server goroutines.
The client program allows to specify the 2 lists and get the ordered communicating with the proxy

## Usage

Server and Proxy have to be executed before the clinet, so that when they are both up and running the client can execute and communicate with them.

0. Make sure to have Go language properly installed on your computer.

1. To run the server:
   go run server/server.go

2. To run the load balancer:
   go run proxy/proxy.go

3. To run the client:
   go run client/client

4. Once the client is running, specify the elements of the list separated by a blankspace and press enter when the list is finished.
   > Enter the first list: 1 2 3 4 5
   > Enter the second list: 1 2 3 4
