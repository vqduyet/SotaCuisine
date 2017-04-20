/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ProductsFacadeLocal;
import entity.Products;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author phuon
 */
@ManagedBean
@SessionScoped
public class productMB {

    @EJB
    private ProductsFacadeLocal productsFacade;
    private String searchStr;
    private List<Products> searchList = new ArrayList<Products>();
    private List<Products> productList;
    private int currentRate;

    public int getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(int currentRate) {
        this.currentRate = currentRate;
    }
    
    public List<Products> getProductList() {
        return productList;
    }

    public void setProductList(List<Products> productList) {
        this.productList = productList;
    }
    

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public List<Products> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Products> searchList) {
        this.searchList = searchList;
    }

    public Products getProductNew() {
        return productNew;
    }

    public void setProductNew(Products productNew) {
        this.productNew = productNew;
    }

    public Products getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Products selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    private Products productNew = new Products();
    private Products selectedProduct;

    /**
     * Creates a new instance of productMB
     */
    public productMB() {
    }

    public String searchProducts(){
        searchList = productsFacade.getProductsByName(searchStr);
        return "/product/search?faces-redirect=true";
    }
    
    public List<Products> fetchGetActiveProductList() {
        return productsFacade.getActiveProductList();
    }
    
    public List<Products> fetchProductsByCatalogId(int id){
        return productsFacade.getProductsByCatalog(id);
    }
    
    public String gotoCatalogPage(int id){
        productList  = productsFacade.getProductsByCatalog(id);
        return "/product/gallery?faces-redirect=true";
    }
    
    public String gotoProductDetail(int id){
        selectedProduct  = productsFacade.find(id);
        int oldView = selectedProduct.getViewNo();
        selectedProduct.setViewNo(oldView + 1);
        productsFacade.edit(selectedProduct);
        currentRate = selectedProduct.getRateTotal();
        if (selectedProduct.getRateCount()!=0)
            currentRate = (int)(currentRate / selectedProduct.getRateCount());
        return "/product/productDetail?faces-redirect=true";
    }
    
    public List<Products> fetchSpecialDishesToday(){
        return productsFacade.getSpecialDishes();
    }
    
    /* lists of index */
    public List<Products> fetchMostViewList(){
        return productsFacade.getMostViewProductList();
    }
    public List<Products> fetchMostBoughtList(){
        return productsFacade.getMostBoughtProductList();
    }
    public List<Products> fetchTodaySpecialLimit(){
        return productsFacade.getTodaySpecialLimitList();
    }

}
