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
import com.anicol.messageprocessor.data.utility.SaleMap;
import com.anicol.messageprocessor.service.MessageOutputter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author alan.nicol
 */
public class MessageOutputterTest {
    
    private Product product;
    
    @Before
    public void initialize() {
        product = new Product(ProductType.BANANA);
    }
    
    @Test
    public void testDisplaySale() {
        MessageOutputter.displaySale(new Sale(product,10.0,10));
    }
    
    @Test
    public void testDisplayAdjustment() {
        MessageOutputter.displayAdjustment(new Adjustment(product,AdjustmentType.ADD,10));
    }
    
    @Test
    public void testDisplaySales() {
        SaleMap sales;
        
        sales = new SaleMap();
        sales.put(new Sale(product,10,10));
        sales.put(new Sale(product,10,20));
        sales.put(new Sale(product,10,5));
        MessageOutputter.displaySales(sales);
    }
    
    @Test
    public void testDisplayAdjustmentReport() {
        List<Adjustment> adjustments;
        
        adjustments = new ArrayList();
        adjustments.add(new Adjustment(product,AdjustmentType.ADD,10));
        adjustments.add(new Adjustment(product,AdjustmentType.MULTIPLY,2));
        adjustments.add(new Adjustment(product,AdjustmentType.SUBTRACT,5));
        adjustments.add(new Adjustment(product,AdjustmentType.ADD,5));
        MessageOutputter.displayAdjustmentReport(adjustments);
    }
}
