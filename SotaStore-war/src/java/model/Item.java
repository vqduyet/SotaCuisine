/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.Products;

/**
 *
 * @author Duyet
 */
public class Item {
    private Products p;
    private int quantity;

    public Products getP() {
        return p;
    }

    public void setP(Products p) {
        this.p = p;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item() {
    }

    public Item(Products p, int quantity) {
        this.p = p;
        this.quantity = quantity;
    }
    
    
    
}
