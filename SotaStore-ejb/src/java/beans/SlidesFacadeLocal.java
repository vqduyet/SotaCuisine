/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Slides;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Duyet
 */
@Local
public interface SlidesFacadeLocal {

    void create(Slides slides);

    void edit(Slides slides);

    void remove(Slides slides);

    Slides find(Object id);

    List<Slides> findAll();

    List<Slides> findRange(int[] range);

    int count();

    List<Slides> getSortedSlideList();
    
}
