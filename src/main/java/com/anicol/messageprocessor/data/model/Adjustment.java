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
public class Adjustment {
    
    public enum AdjustmentType{ADD,SUBTRACT,MULTIPLY};
    
    private Product product;
    private AdjustmentType adjustmentType;
    private double value;

    public Adjustment(Product product, AdjustmentType adjustmentType, double value) {
        this.product = product;
        this.adjustmentType = adjustmentType;
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AdjustmentType getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(AdjustmentType adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}
