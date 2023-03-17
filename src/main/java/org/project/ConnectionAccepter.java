package org.project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Thread class used to handle incoming connections
 */
class ConnectionAccepter extends Thread
{
    private final ServerSocket serverSocket;
    private final SnapshotCreator snapC;

    /**
     * @param snap parameter used in order to add a new socket (a connection accepted) to che SnapshotCreator
     * @throws IOException
     */
    ConnectionAccepter(SnapshotCreator snap) throws IOException
    {
        serverSocket = new ServerSocket(SnapshotCreator.serverPort);
        snapC = snap;
    }

    @Override
    public void run()
    {
        Socket connection;
        System.out.println("Ready to accept on port " + SnapshotCreator.serverPort);
        System.out.println(serverSocket);
        while (true)
        {
            try {
                connection = serverSocket.accept();
                snapC.connectionAccepted(connection);
            } catch (IOException ignored) {}
        }
    }
}
