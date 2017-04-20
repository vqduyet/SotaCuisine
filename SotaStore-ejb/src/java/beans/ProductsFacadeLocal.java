/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Products;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Duyet
 */
@Local
public interface ProductsFacadeLocal {

    void create(Products products);

    void edit(Products products);

    void remove(Products products);

    Products find(Object id);

    List<Products> findAll();

    List<Products> findRange(int[] range);

    int count();
    
    List<Products> getProductsByName(String name);

    List<Products> getProductsByCatalog(int id);
    
    List<Products> getActiveProductList();

    List<Products> getInactiveProductList(); 
    
    List<Products> getSpecialDishes();

    boolean checkProductName(String productName);

    List<Products> checkProductNameNotIn(int productId);

    List<Products> getMostViewProductList();

    List<Products> getMostBoughtProductList();

    List<Products> getTodaySpecialLimitList();

   
    
}
