/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.app;

import com.anicol.messageprocessor.service.MessageProcessor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author alan.nicol
 */
public class Client {

    private static final int PORT = 9090;
    private static final String ADDRESS = "localhost";
    
    private int messageCount = 0;
    
    private MessageProcessor messageProcessorService = new MessageProcessor();

    public Client() {

        String inputLine;
        try {
            Socket socket = new Socket(ADDRESS, PORT);
            System.out.println("Connected to server...");
            System.out.println();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while ((inputLine = bufferedReader.readLine()) != null) {

                messageCount++;
                messageProcessorService.process(inputLine);

                if (messageCount == 50) {
                    break;
                }
            }

            System.exit(0);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Client();
    }

}
