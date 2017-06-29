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
import com.anicol.messageprocessor.utility.Serializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alan.nicol
 */
public class MessageProcessor {

    private static final int TOTAL_SALES_PRODUCT_COUNT = 10;
    private static final int TOTAL_ADJUSTMENT_REPORT_COUNT = 50;

    private int messageCount = 0;

    private final SaleMap sales = new SaleMap();
    private final List<Adjustment> adjustments = new ArrayList();

    public void process(String data) throws Exception {
        Gson gson;
        Object object;
        Class clazz;

        if ((data != null) && (!data.isEmpty())) {
            gson = new GsonBuilder().registerTypeHierarchyAdapter(Object.class, new Serializer()).create();
            object = gson.fromJson(data, Object.class);
            clazz = getClass(object);

            if (clazz != null) {
                if (clazz.isAssignableFrom(Sale.class)) {
                    processSale(data);
                    messageCount++;
                } else if ((clazz.isAssignableFrom(Adjustment.class)) && (!sales.getMap().isEmpty())) {
                    processAdjustment(data);
                    messageCount++;
                }
            }

            if ((messageCount % TOTAL_SALES_PRODUCT_COUNT == 0)) {
                MessageOutputter.displaySales(sales);
            }

            if ((messageCount % TOTAL_ADJUSTMENT_REPORT_COUNT == 0)) {
                MessageOutputter.displayAdjustmentReport(adjustments);
            }
        } else {
            throw new IllegalArgumentException("No parameter data entered or missing");
        }

    }

    private void processSale(String data) {
        Gson gson;
        Sale sale;

        gson = new GsonBuilder().registerTypeHierarchyAdapter(Sale.class, new Serializer()).create();
        sale = gson.fromJson(data, Sale.class);

        sales.put(sale);
        MessageOutputter.displaySale(sale);
    }

    private void processAdjustment(String data) {
        Gson gson;
        Adjustment adjustment;

        gson = new GsonBuilder().registerTypeHierarchyAdapter(Adjustment.class, new Serializer()).create();
        adjustment = gson.fromJson(data, Adjustment.class);

        adjustments.add(adjustment);
        adjustSalesCosts(adjustment);
        MessageOutputter.displayAdjustment(adjustment);
    }

    private void adjustSalesCosts(Adjustment adjustment) {
        List<Sale> itemsSold;

        itemsSold = sales.get(adjustment.getProduct().getId());

        if (itemsSold != null) {

            for (Sale sale : itemsSold) {
                switch (adjustment.getAdjustmentType()) {
                    case ADD:
                        sale.setValue(sale.getValue() + adjustment.getValue());
                        break;
                    case SUBTRACT:
                        sale.setValue(sale.getValue() - adjustment.getValue());
                        break;
                    case MULTIPLY:
                        sale.setValue(sale.getValue() * adjustment.getValue());
                        break;
                }
            }
        }
    }

    private Class getClass(Object object) throws Exception {
        LinkedTreeMap linkedTreeMap;
        String className;

        if (object instanceof LinkedTreeMap) {
            linkedTreeMap = (LinkedTreeMap) object;

            className = (String) linkedTreeMap.get("class");

            return Class.forName(className);
        }

        return null;
    }

    public SaleMap getSales() {
        return sales;
    }

    public List<Adjustment> getAdjustments() {
        return adjustments;
    }

    public int getMessageCount() {
        return messageCount;
    }

}
