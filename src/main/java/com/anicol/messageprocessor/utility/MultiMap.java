/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alan.nicol
 */
public class MultiMap<K, V> {
    
    private final HashMap<K, List<V>> hashMap;

    public MultiMap() {
        this.hashMap = new HashMap();
    }

    public boolean put(K key, V value) {
        if (hashMap.containsKey(key)) {
            hashMap.get(key).add(value);
            return true;
        } else {
            List<V> values = new ArrayList<>();
            values.add(value);
            hashMap.put(key, values);
            return false;
        }
    }
    
    public List<V> get(K key) {
        return hashMap.get(key);
    }

    public Map<K, List<V>> getMap() {
        return hashMap;
    }
    
   
}
