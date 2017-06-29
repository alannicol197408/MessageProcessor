/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.utility;

import com.anicol.messageprocessor.data.data.ProductType;
import com.anicol.messageprocessor.data.model.Adjustment;
import com.anicol.messageprocessor.data.model.Adjustment.AdjustmentType;
import com.anicol.messageprocessor.data.model.Product;
import com.anicol.messageprocessor.data.model.Sale;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author alan.nicol
 */
public class SerializerTest {
    
    private Gson gson;
    private Product product;
    private Sale sale;
    private Adjustment adjustment;
    
    @Before
    public void initialize() {
        gson = new GsonBuilder().registerTypeHierarchyAdapter(Object.class, new Serializer()).create();
    
        product = new Product(ProductType.BANANA);
        sale = new Sale(product,10);
        adjustment = new Adjustment(product, AdjustmentType.ADD,20);
    }
    
    @Test
    public void testSerialize() {
        
        testSerializeObject(product);
        testSerializeObject(sale);
        testSerializeObject(adjustment);
    }
    
    private void testSerializeObject(Object object) {
        String json;
        
        json = gson.toJson(object);
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }
    
    @Test
    public void testDeserialize() {
        testDeserializeObject(gson.toJson(product),Product.class);
        testDeserializeObject(gson.toJson(sale),Sale.class);
        testDeserializeObject(gson.toJson(adjustment),Adjustment.class);
    }
    
    private void testDeserializeObject(String data, Class clazz) {
        Object object;
        
        object = gson.fromJson(data, clazz);
    
        assertNotNull(object);
        assertTrue(object.getClass().isAssignableFrom(clazz));
    }
}
