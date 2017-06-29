/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.app;

import com.anicol.messageprocessor.utility.Serializer;
import com.anicol.messageprocessor.data.model.Adjustment;
import com.anicol.messageprocessor.data.model.Adjustment.AdjustmentType;
import static com.anicol.messageprocessor.data.model.Adjustment.AdjustmentType.*;
import com.anicol.messageprocessor.data.model.Product;
import static com.anicol.messageprocessor.data.data.ProductType.*;
import com.anicol.messageprocessor.data.model.Sale;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alan.nicol
 */
public class Server {

    private static final int PORT = 9090;

    public Server() {

        Socket socket = null;
        ServerSocket serverSocket = createServerSocket();

        try {

            if (serverSocket != null) {
                while (true) {
                    try {
                        System.out.println("Waiting for client...");
                        socket = acceptSocketRequest(serverSocket);
                        System.out.println("Client connected...");
                        if (socket != null) {
                            sendData(socket);
                        }
                    } finally {
                        closeSocket(socket);
                    }
                }
            }
        } finally {
            closeSocket(serverSocket);
        }
    }

    private ServerSocket createServerSocket() {
        try {
            return new ServerSocket(PORT);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        return null;
    }

    private Socket acceptSocketRequest(ServerSocket serverSocket) {
        try {
            return serverSocket.accept();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        return null;
    }

    private void sendData(Socket socket) {

        PrintWriter printWriter;
        Gson gson;

        try {
            System.out.println("Sending data...");
            printWriter = new PrintWriter(socket.getOutputStream());
            gson = new GsonBuilder().registerTypeHierarchyAdapter(Object.class, new Serializer()).create();

            sendAppleData(printWriter, gson);
            sendBananaData(printWriter, gson);
            sendGrapefruitData(printWriter, gson);
            sendLimeData(printWriter, gson);
            sendOrangeData(printWriter, gson);
            sendMangoData(printWriter, gson);
            sendPapayaData(printWriter, gson);
            sendPeachData(printWriter, gson);
            sendPearData(printWriter, gson);
            sendPineappleData(printWriter, gson);
            sendRaspberryData(printWriter, gson);
            sendStrawberryData(printWriter, gson);
            printWriter.flush();
            System.out.println("Data sent...");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }

    private void sendAppleData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.10;

        Product apple = new Product(APPLE);

        sendSale(printWriter, gson, apple, COST);
        sendSale(printWriter, gson, apple, COST, 20);
        sendSale(printWriter, gson, apple, COST, 30);
        sendSale(printWriter, gson, apple, COST, 40);
        sendAdjustment(printWriter, gson, apple, ADD, 0.20);
    }

    private void sendBananaData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.50;

        Product banana = new Product(BANANA);

        sendSale(printWriter, gson, banana, COST);
        sendSale(printWriter, gson, banana, COST, 10);
        sendSale(printWriter, gson, banana, COST, 20);
        sendSale(printWriter, gson, banana, COST, 20);
        sendAdjustment(printWriter, gson, banana, SUBTRACT, 0.10);
    }

    private void sendGrapefruitData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.30;

        Product grapefruit = new Product(GRAPEFRUIT);

        sendSale(printWriter, gson, grapefruit, COST);
        sendSale(printWriter, gson, grapefruit, COST, 20);
        sendSale(printWriter, gson, grapefruit, COST, 10);
        sendSale(printWriter, gson, grapefruit, COST, 30);
        sendAdjustment(printWriter, gson, grapefruit, MULTIPLY, 2.0);
    }

    private void sendOrangeData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.20;

        Product orange = new Product(ORANGE);

        sendSale(printWriter, gson, orange, COST);
        sendSale(printWriter, gson, orange, COST, 10);
        sendSale(printWriter, gson, orange, COST, 15);
        sendSale(printWriter, gson, orange, COST, 20);
        sendAdjustment(printWriter, gson, orange, ADD, 0.10);
    }

    private void sendLimeData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.10;

        Product lime = new Product(LIME);

        sendSale(printWriter, gson, lime, COST);
        sendSale(printWriter, gson, lime, COST, 12);
        sendSale(printWriter, gson, lime, COST, 23);
        sendSale(printWriter, gson, lime, COST, 45);
        sendAdjustment(printWriter, gson, lime, SUBTRACT, 0.10);
    }

    private void sendMangoData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.80;

        Product mango = new Product(MANGO);

        sendSale(printWriter, gson, mango, COST);
        sendSale(printWriter, gson, mango, COST, 10);
        sendSale(printWriter, gson, mango, COST, 16);
        sendSale(printWriter, gson, mango, COST, 24);
        sendAdjustment(printWriter, gson, mango, MULTIPLY, 3.0);
    }

    private void sendPapayaData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.70;

        Product papaya = new Product(PAPAYA);

        sendSale(printWriter, gson, papaya, COST);
        sendSale(printWriter, gson, papaya, COST, 8);
        sendSale(printWriter, gson, papaya, COST, 22);
        sendSale(printWriter, gson, papaya, COST, 35);
        sendAdjustment(printWriter, gson, papaya, ADD, 0.20);
    }

    private void sendPeachData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.60;

        Product peach = new Product(PEACH);

        sendSale(printWriter, gson, peach, COST);
        sendSale(printWriter, gson, peach, COST, 10);
        sendSale(printWriter, gson, peach, COST, 20);
        sendSale(printWriter, gson, peach, COST, 10);
        sendAdjustment(printWriter, gson, peach, SUBTRACT, 0.30);
    }

    private void sendPearData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.20;

        Product pear = new Product(PEAR);

        sendSale(printWriter, gson, pear, COST);
        sendSale(printWriter, gson, pear, COST, 5);
        sendSale(printWriter, gson, pear, COST, 10);
        sendSale(printWriter, gson, pear, COST, 12);
        sendAdjustment(printWriter, gson, pear, MULTIPLY, 2.0);
    }

    private void sendPineappleData(PrintWriter printWriter, Gson gson) {
        final double COST = 1.10;

        Product pineapple = new Product(PINEAPPLE);

        sendSale(printWriter, gson, pineapple, COST);
        sendSale(printWriter, gson, pineapple, COST, 8);
        sendSale(printWriter, gson, pineapple, COST, 23);
        sendSale(printWriter, gson, pineapple, COST, 15);
        sendAdjustment(printWriter, gson, pineapple, ADD, 0.30);
    }

    private void sendRaspberryData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.05;

        Product raspberry = new Product(RASPBERRY);

        sendSale(printWriter, gson, raspberry, COST);
        sendSale(printWriter, gson, raspberry, COST, 20);
        sendSale(printWriter, gson, raspberry, COST, 120);
        sendSale(printWriter, gson, raspberry, COST, 170);
        sendAdjustment(printWriter, gson, raspberry, SUBTRACT, 0.01);
    }

    private void sendStrawberryData(PrintWriter printWriter, Gson gson) {
        final double COST = 0.05;

        Product strawberry = new Product(STRAWBERRY);

        sendSale(printWriter, gson, strawberry, COST);
        sendSale(printWriter, gson, strawberry, COST, 20);
        sendSale(printWriter, gson, strawberry, COST, 200);
        sendSale(printWriter, gson, strawberry, COST, 180);
        sendAdjustment(printWriter, gson, strawberry, MULTIPLY, 4.0);
    }

    private void sendSale(PrintWriter printWriter, Gson gson, Product product, double value) {
        printWriter.println(gson.toJson(new Sale(product, value)));
    }

    private void sendSale(PrintWriter printWriter, Gson gson, Product product, double value, int quantity) {
        printWriter.println(gson.toJson(new Sale(product, value, quantity)));
    }

    private void sendAdjustment(PrintWriter printWriter, Gson gson, Product product, AdjustmentType adjustmentType, double value) {
        printWriter.println(gson.toJson(new Adjustment(product, adjustmentType, value)));
    }

    private void closeSocket(Closeable socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Server();
    }
}
