/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import beans.SlidesFacadeLocal;
import entity.Slides;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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

/**
 *
 * @author Duyet
 */
@ManagedBean
@SessionScoped
public class SlideMB {
    @EJB
    private SlidesFacadeLocal slidesFacade;
    private Slides selectedSlide;
    private Part imgFile = null;
    private Part imgEditFile = null;
    private Slides newSlide = new Slides();

    public Part getImgEditFile() {
        return imgEditFile;
    }

    public void setImgEditFile(Part imgEditFile) {
        this.imgEditFile = imgEditFile;
    }

    public Slides getNewSlide() {
        return newSlide;
    }

    public void setNewSlide(Slides newSlide) {
        this.newSlide = newSlide;
    }

    
    public Part getImgFile() {
        return imgFile;
    }

    public void setImgFile(Part imgFile) {
        this.imgFile = imgFile;
    }
    

    public Slides getSelectedSlide() {
        return selectedSlide;
    }

    public void setSelectedSlide(Slides selectedSlide) {
        this.selectedSlide = selectedSlide;
    }
    

    /**
     * Creates a new instance of SlideMB
     */
    public SlideMB() {
    }
    
    public List<Slides> fetchSortedSlideList(){
        return slidesFacade.getSortedSlideList();
    }
    
    public List<Slides> getAllSlides(){
        return slidesFacade.findAll();
    }
    
    
    
    public String gotoEdit(int id){
        selectedSlide = slidesFacade.find(id);
        return "/admin/slide/slide-edit?faces-redirect=true";
    }
    
    public void delete(int id){
        Slides deleteSlide = slidesFacade.find(id);
        slidesFacade.remove(deleteSlide);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Delete successfully."));
    }
    
    public void updateSlide(){
        try {
            if(!imgEditFile.getSubmittedFileName().isEmpty()){
                System.out.println(imgEditFile.getSubmittedFileName());
                validateEditFile(imgEditFile);
                uploadImage(imgEditFile);                
                selectedSlide.setImageLink("images/slides/"+imgEditFile.getSubmittedFileName());
                selectedSlide.setImageName(imgEditFile.getSubmittedFileName());
            }            
            slidesFacade.edit(selectedSlide);
            imgEditFile = null;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Update slide successfully."));
        } 
        catch(ValidatorException e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(e.getFacesMessage().getSummary()));
            return;
        }
        catch (Exception e) {
            e.printStackTrace();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Image hhh."));
            return;
        }        
    }
    
    //create new slide
    public void addSlide(){
        try {
            uploadImage(imgFile);
            newSlide.setImageLink("images/slides/"+imgFile.getSubmittedFileName());
            newSlide.setImageName(imgFile.getSubmittedFileName());
            slidesFacade.create(newSlide);                        
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Image cannot be blank."));        
        }
        newSlide = new Slides();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Insert slide successfully."));
    }
    
    //upload hinh
    public void uploadImage(Part file){
        if(file!=null){ 
            //get path which contain file uploaded
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources\\images\\slides\\");
            System.out.println(path);
            //write file to path
            try {
                InputStream in = file.getInputStream();
                byte[] data = new byte[in.available()];
                in.read(data);
                FileOutputStream out = new FileOutputStream(new File(path+"\\" + file.getSubmittedFileName()));
                out.write(data);
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }
    }
    
    public void validateFile(FacesContext ctx,
                         UIComponent comp,
                         Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part)value;
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
    /*
    public void validateEditFile(FacesContext ctx,
                         UIComponent comp,
                         Object value) {
        if(value!=null){
            List<FacesMessage> msgs = new ArrayList<FacesMessage>();
            Part file = (Part)value;

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
   */ 
    public void validateEditFile(Part file) {
        if(file!=null){
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
