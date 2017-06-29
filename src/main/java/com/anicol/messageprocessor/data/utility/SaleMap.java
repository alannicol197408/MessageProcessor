/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.data.utility;

import com.anicol.messageprocessor.data.model.Sale;
import com.anicol.messageprocessor.utility.MultiMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alan.nicol
 */
public class SaleMap {
    
    private MultiMap<Integer, Sale> multiMap;

    public SaleMap() {
        multiMap = new MultiMap();
    }
    
    public boolean put(Sale sale) {
        return multiMap.put(sale.getProduct().getId(), sale);
    }
    
    public Map<Integer, List<Sale>> getMap() {
        return multiMap.getMap();
    }
    
    public List<Sale> get(Integer key) {
        return multiMap.get(key);
    }
}
