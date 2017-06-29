/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.utility;

import com.anicol.messageprocessor.data.data.ProductType;
import com.anicol.messageprocessor.data.model.Product;
import com.anicol.messageprocessor.data.model.Sale;
import com.anicol.messageprocessor.data.utility.SaleMap;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author alan.nicol
 */
public class SaleMapTest {
    
    private Product banana;
    private Product apple;
    
    @Before
    public void initialize() {
      
        banana = new Product(ProductType.BANANA);
        apple = new Product(ProductType.APPLE);
    }
     
    @Test
    public void testMultiMap() {
        
        SaleMap sales;
        
        sales = new SaleMap();
        
        sales.put(new Sale(banana,10));
        assertTrue(sales.getMap().size()==1);
        sales.put(new Sale(banana,20));
        assertTrue(sales.getMap().size()==1);
        sales.put(new Sale(banana,12));
        assertTrue(sales.getMap().size()==1);
        sales.put(new Sale(apple,30));
        assertTrue(sales.getMap().size()==2);
    }
}
