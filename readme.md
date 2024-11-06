# SimpleSockets

Java Socket Communication with Wrapper Classes

> **Author:** @Danny-Wits

`SimpleSocket` is a Java library that provides two wrapper classes for `Socket` and `ServerSocket` to enhance functionality and simplify socket-based client-server communication. The goal of this project is to abstract the low-level details of socket programming while still giving developers control over client-server interactions.

## Features

- **Enhanced Server**: Simplifies accepting client connections and handling communication.
- **Enhanced Client**: Eases connecting to the server and sending/receiving messages.
- **Bidirectional Communication**: Easily send and receive messages between the server and client.
- **Error Handling**: Basic error handling to ensure reliability and smooth debugging.

## Class Overview

### Server

The `Server` class is a wrapper around `ServerSocket` to handle client connections and facilitate communication.

#### Key Methods:

- `waitForConnection()`: Listens for client connections and initializes communication when a client connects.
- `sendToClient(String message)`: Sends a message to the connected client.
- `readFromClient()`: Reads a message sent by the client.

### Client

The `Client` class is a wrapper around `Socket` that simplifies the process of connecting to the server and exchanging messages.

#### Key Methods:

- `readFromServer()`: Reads a message from the server.
- `sendToServer(String message)`: Sends a message to the server.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- Basic understanding of Java socket programming

### Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/Danny-Wits/SimpleSockets-JAVA.git
   cd SimpleSockets-JAVA
   ```

2. Compile the classes:
   ```bash
   javac Server.java
   javac Client.java
   ```

## Usage

### Starting the Server

1. Run the `Server` on a specified port (e.g., 1234):

   ```java
   Server server = new Server(1234);  // Server on port 1234
   server.waitForConnection();
   ```

2. The server will now wait for a client to connect and handle communication.

### Running the Client

1. Run the `Client` to connect to the server:

   ```java
   Client client = new Client("localhost", 1234);  // Connect to server on port 1234
   client.sendToServer("Hello Server!");            // Send a message to the server
   ```

2. The client can now send messages to the server and receive responses.

### Example Interaction

1. **Start the server**:

   ```bash
   java Server 1234
   ```

   The server will output:

   ```
   Server waiting on port: 1234
   ```

2. **Start the client**:

   ```bash
   java Client 1234
   ```

   The client will output:

   ```
   Connected to the server on port: 1234
   ```

3. **Message Exchange**: Once connected, the client sends a message, and the server reads and responds.
