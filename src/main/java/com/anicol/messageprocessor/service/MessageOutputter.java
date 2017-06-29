/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.service;

import com.anicol.messageprocessor.data.model.Adjustment;
import static com.anicol.messageprocessor.data.model.Adjustment.AdjustmentType.MULTIPLY;
import com.anicol.messageprocessor.data.model.Sale;
import com.anicol.messageprocessor.data.utility.SaleMap;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alan.nicol
 */
public class MessageOutputter {

    public static void displaySale(Sale sale) {
        System.out.println("New Sale");
        System.out.println("--------");
        System.out.format("Quantity: %d%n", sale.getQuantity());
        System.out.format("Item: %s(s)%n", sale.getProduct().getName());
        System.out.format("Cost Per Item: £%s%n", formatValue(sale.getValue()));
        System.out.format("Total Cost: £%s%n", getTotalSale(sale));
        System.out.println();
    }

    public static void displayAdjustment(Adjustment adjustment) {

        System.out.println("New Adjustment");
        System.out.println("----------");
        System.out.format("Item: %s(s)%n", adjustment.getProduct().getName());
        System.out.format("Type: %s%n", adjustment.getAdjustmentType());
        if (adjustment.getAdjustmentType() == MULTIPLY) {
            System.out.format("Value: %s%n", formatValue(adjustment.getValue()));
        } else {
            System.out.format("Cost: £%s%n", formatValue(adjustment.getValue()));
        }

        System.out.println();
    }

    public static void displaySales(SaleMap sales) {

        for (Map.Entry<Integer, List<Sale>> entry : sales.getMap().entrySet()) {
            displayTotal(entry.getValue());
        }

        System.out.println();
    }
    
    public static void displayAdjustmentReport(List<Adjustment> adjustments) {

        System.out.println("Client paused...");
        System.out.println();
        System.out.println("Record of Adjustments");
        System.out.println("---------------------");
        for (Adjustment adjustment : adjustments) {
            System.out.println("Adjustment");
            displayAdjustment(adjustment);
        }
    }

    private static String formatValue(double value) {
        DecimalFormat decimalFormat;

        decimalFormat = new DecimalFormat("##0.00");
        return decimalFormat.format(value);
    }

    private static String getTotalSale(Sale sale) {
        return formatValue(sale.getValue() * sale.getQuantity());
    }

    private static void displayTotal(List<Sale> sales) {
        double total = 0.0;
        int quantity = 0;

        for (Sale sale : sales) {
            total += sale.getValue() * sale.getQuantity();
            quantity += sale.getQuantity();
        }

        System.out.format("Total Sales Quantity For %s(s): %s%n", sales.get(0).getProduct().getName(), quantity);
        System.out.format("Total Sales Cost For %s(s): £%s%n", sales.get(0).getProduct().getName(), formatValue(total));
        System.out.println();
    }

}
