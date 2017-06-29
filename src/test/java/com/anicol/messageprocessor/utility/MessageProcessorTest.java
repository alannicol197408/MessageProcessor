/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.utility;

import com.anicol.messageprocessor.data.data.ProductType;
import com.anicol.messageprocessor.data.model.Adjustment;
import com.anicol.messageprocessor.data.model.Product;
import com.anicol.messageprocessor.data.model.Sale;
import com.anicol.messageprocessor.service.MessageProcessor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author alan.nicol
 */
public class MessageProcessorTest {
    
    private static final String INCORRECT_DATA = "dfdsfdsf";
    private MessageProcessor messageProcessor;
    
    private Product product;
    
    @Before
    public void initialize() {
        messageProcessor = new MessageProcessor();
        product = new Product(ProductType.BANANA);
    }
    
    @Test
    public void testWithMissingData() {
        try {
            messageProcessor.process("");
            fail("Exception should be raised");
        } catch(Exception exception) {
            
        }
        
    }
    
    @Test
    public void testWithIncorrectData() {
        try {
            messageProcessor.process(INCORRECT_DATA);
            assertTrue(messageProcessor.getMessageCount() == 0);
        } catch(Exception exception) {
            fail("Not expecting error");
        }
        
    }
    
    @Test
    public void testWithSaleData() {
        Gson gson;
        
        try {
            gson = new GsonBuilder().registerTypeHierarchyAdapter(Sale.class, new Serializer()).create();
            
            messageProcessor.process(gson.toJson(new Sale(product,10.0,10)));
            assertFalse(messageProcessor.getSales().getMap().isEmpty());
            assertTrue(messageProcessor.getMessageCount() == 1);
            messageProcessor.process(gson.toJson(new Sale(product,10.0,10)));
            messageProcessor.process(gson.toJson(new Sale(product,10.0,10)));
            assertTrue(messageProcessor.getMessageCount() == 3);
        } catch(Exception exception) {
            fail("Not expecting error");
        }
    }
    
    @Test
    public void testWithAdjustmentData() {
        Gson saleGson;
        Gson adjustmentGson;
        
        try {
            saleGson = new GsonBuilder().registerTypeHierarchyAdapter(Sale.class, new Serializer()).create();
            adjustmentGson = new GsonBuilder().registerTypeHierarchyAdapter(Adjustment.class, new Serializer()).create();
            
            messageProcessor.process(adjustmentGson.toJson(new Adjustment(product,Adjustment.AdjustmentType.ADD,10)));
            assertTrue(messageProcessor.getAdjustments().isEmpty());
            assertTrue(messageProcessor.getMessageCount() == 0);
            messageProcessor.process(saleGson.toJson(new Sale(product,10.0,10)));
            messageProcessor.process(adjustmentGson.toJson(new Adjustment(product,Adjustment.AdjustmentType.ADD,10)));
            assertFalse(messageProcessor.getAdjustments().isEmpty());
            assertTrue(messageProcessor.getMessageCount() == 2);
            
        } catch(Exception exception) {
            fail("Not expecting error");
        }
    }
    
}
