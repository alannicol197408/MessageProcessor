/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.data.data;

/**
 *
 * @author alan.nicol
 */
public enum ProductType {
    
    APPLE(1,"Apple"),BANANA(2,"Banana"),GRAPEFRUIT(3,"Grapefruit"),LIME(4,"Lime"),ORANGE(5,"Orange"),MANGO(6,"Mango"),
    PAPAYA(7,"Papaya"),PEACH(8,"Peach"),PEAR(9,"Pear"),PINEAPPLE(10,"Pineapple"), RASPBERRY(11,"Raspberry"),STRAWBERRY(12,"Strawberry");
    
    private final int id;
    private final String name;
    
    ProductType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
