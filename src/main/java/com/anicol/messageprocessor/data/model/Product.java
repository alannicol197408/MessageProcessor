/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.data.model;

import com.anicol.messageprocessor.data.data.ProductType;

/**
 *
 * @author alan.nicol
 */
public class Product {

    private int id;
    private String name;
    
    public Product(ProductType productType) {
        this.id = productType.getId();
        this.name = productType.getName();
    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
