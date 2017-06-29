/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.data.model;

/**
 *
 * @author alan.nicol
 */
public class Sale {

    private Product product;
    private double value;
    private int quantity;

    public Sale(Product product, double value) {
        this.product = product;
        this.value = value;
        this.quantity = 1;
    }
    
    public Sale(Product product, double value, int quantity) {
        this.product = product;
        this.value = value;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
