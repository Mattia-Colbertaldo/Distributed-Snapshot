package org.project;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

class MessageBuffer
{
    private Map<String, List<Byte>> incomingMessages;  //class used as implementation: AbstractQueue

    /*
     *   MessageBuffer(List<String> names)
     *   {
     *       incomingMessages = new HashMap<>();
     *       for (String name : names)
     *           incomingMessages.put(name, new AbstractQueue<Message>());
     *   }
     * // unused constructor
     */
    MessageBuffer()
    {
        incomingMessages = new HashMap<>();
    }

    synchronized void addClient(String name)
    {
        incomingMessages.put(name, new ArrayList<>());
    }

    synchronized void addMessage(String name, List<Byte> message)
    {
        incomingMessages.get(name).addAll(message);
    }

    /*
    public List<Byte> retrieveMessage(String name)
    {
        List<Byte> message = incomingMessages.get(name);
        incomingMessages.remove(name);
        return message;

    }
    */


    synchronized InputStream getInputStream(String name)
    {

        byte[] input = new byte[incomingMessages.get(name).size()];
        for(int i=0; i<incomingMessages.get(name).size(); i++)
            input[i] = incomingMessages.get(name).get(i);


        ///////////////////////////////////////////////////

        //todo
        incomingMessages.get(name).clear(); //MA COSI PULISCI SOLO LA PRIMA VOLTA CHE UNO RICHIEDE L'INPUT STREAM
        //DOVRESTI PULIRE OGNI VOLTA CHE UNO LEGGE IL MESSAGGIO
        return new ByteArrayInputStream(input);
    }

}
