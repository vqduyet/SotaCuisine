/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.CatalogsFacadeLocal;
import beans.ProductsFacadeLocal;
import entity.Products;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

/**
 *
 * @author phuon
 */
@ManagedBean
@SessionScoped
public class adminProductMB {

    @EJB
    private CatalogsFacadeLocal catalogsFacade;
    @EJB
    private ProductsFacadeLocal productsFacade;
    private int currentRate;
    private int tempCatalogId;

    public int getTempCatalogId() {
        return tempCatalogId;
    }

    public void setTempCatalogId(int tempCatalogId) {
        this.tempCatalogId = tempCatalogId;
    }
    private Products productNew = new Products();
    private Products selectedProduct;

    public Products getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Products selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    private Part imageFile;
    private Part imageDetailFile;
    private int catalogId;
    private String defaultStatus = "Normal";
    private int rating;

    public int getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(int currentRate) {
        this.currentRate = currentRate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(String defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public Part getImageDetailFile() {
        return imageDetailFile;
    }

    public void setImageDetailFile(Part imageDetailFile) {
        this.imageDetailFile = imageDetailFile;
    }

    public Part getImageFile() {
        return imageFile;
    }

    public void setImageFile(Part imageFile) {
        this.imageFile = imageFile;
    }

    public Products getProductNew() {
        return productNew;
    }

    public void setProductNew(Products productNew) {
        this.productNew = productNew;
    }

    /**
     * Creates a new instance of adminProductMB
     */
    public adminProductMB() {
    }

    public List<Products> fetchAllProductList() {
        return productsFacade.findAll();
    }

    public List<Products> fetchGetActiveProductList() {
        return productsFacade.getActiveProductList();
    }

    public List<Products> fetchGetInactiveProductList() {
        return productsFacade.getInactiveProductList();
    }

    public void createProduct() {
        if (productsFacade.checkProductName(productNew.getName().trim())) {
            FacesContext.getCurrentInstance().addMessage("addproductform", new FacesMessage("Product's name already exist please choose new name"));
        }
        uploadImage(imageFile);
        uploadImage(imageDetailFile);
        productNew.setStatus(defaultStatus);
        productNew.setCatalogId(catalogsFacade.find(catalogId));
        productNew.setImageLink("images/food/" + imageFile.getSubmittedFileName());
        productNew.setImageLinkDetail("images/food/" + imageDetailFile.getSubmittedFileName());
        productNew.setDiscount(BigDecimal.ZERO);
        productsFacade.create(productNew);
        productNew = new Products();
        FacesContext.getCurrentInstance().addMessage("addproductform", new FacesMessage("New Product has been added sucessfull"));
        //return "/admin/product/product-list?faces-redirect=true";
    }

    public void save() {

    }

    public void uploadImage(Part filePart) {
        if (filePart != null) {
            //get path which contain file uploaded
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources\\images\\food\\");
            System.out.println(path);
            //write file to path
            try {
                InputStream in = filePart.getInputStream();
                byte[] data = new byte[in.available()];
                in.read(data);
                FileOutputStream out = new FileOutputStream(new File(path + "\\" + filePart.getSubmittedFileName()));
                out.write(data);
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    public String gotoEdit(int id) {
        selectedProduct = productsFacade.find(id);
        currentRate = selectedProduct.getRateTotal();
        return "/admin/product/product-edit?faces-redirect=true";
    }

    public void updateProduct() {
        boolean flag = false;
        List<Products> tempList = productsFacade.checkProductNameNotIn(selectedProduct.getId());
        if (!tempList.isEmpty()) {
            for (Products tempProduct : tempList) {
                if (tempProduct.getName().equalsIgnoreCase(selectedProduct.getName())) {
                    flag = true;
                }
            }
            if (flag) {
                FacesContext.getCurrentInstance().addMessage("editProductForm", new FacesMessage("Product's name already exist please choose new name"));
            } else {
                if (!imageFile.getSubmittedFileName().isEmpty()) {
                    validateEditFile(imageFile);
                    uploadImage(imageFile);
                    selectedProduct.setImageLink("images/food/" + imageFile.getSubmittedFileName());
                }
                if (!imageDetailFile.getSubmittedFileName().isEmpty()) {
                    validateEditFile(imageDetailFile);
                    uploadImage(imageDetailFile);
                    selectedProduct.setImageLink("images/food/" + imageDetailFile.getSubmittedFileName());
                }
                selectedProduct.setCatalogId(catalogsFacade.find(this.tempCatalogId));
                productsFacade.edit(selectedProduct);
                FacesContext.getCurrentInstance().addMessage("editProductForm", new FacesMessage("New Product has been edited sucessfull"));
            }
        }
    }

    public void updateProductRating(int rate, int Id) {
        selectedProduct = productsFacade.find(Id);
        int oldRate = selectedProduct.getRateTotal();
        selectedProduct.setRateTotal(oldRate + rate);
        int count = selectedProduct.getRateCount() + 1;
        selectedProduct.setRateCount(count);
        productsFacade.edit(selectedProduct);
    }

    public void validateFile(FacesContext ctx,
            UIComponent comp,
            Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;
        if (file.getSize() > 2 * 1024 * 1024) {
            System.out.println(file.getSize());
            msgs.add(new FacesMessage("File too big!"));
        }
        if (!"image/gif".equals(file.getContentType()) && !"image/png".equals(file.getContentType()) && !"image/jpeg".equals(file.getContentType()) && !"image/jpg".equals(file.getContentType())) {
            System.out.println(file.getContentType());
            msgs.add(new FacesMessage("Not an image file!"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }

    }

    public void validateEditFile(Part file) {
        if (file != null) {
            List<FacesMessage> msgs = new ArrayList<FacesMessage>();

            if (file.getSize() > 2 * 1024 * 1024) {
                System.out.println(file.getSize());
                msgs.add(new FacesMessage("File too big!"));
            }
            if (!"image/gif".equals(file.getContentType()) && !"image/png".equals(file.getContentType()) && !"image/jpeg".equals(file.getContentType()) && !"image/jpg".equals(file.getContentType())) {
                System.out.println(file.getContentType());
                msgs.add(new FacesMessage("Not an image file!"));
            }
            if (!msgs.isEmpty()) {
                throw new ValidatorException(msgs);
            }
        }

    }
}
