/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.AdminGroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lenovo
 */
@Local
public interface AdminGroupFacadeLocal {

    void create(AdminGroup adminGroup);

    void edit(AdminGroup adminGroup);

    void remove(AdminGroup adminGroup);

    AdminGroup find(Object id);

    List<AdminGroup> findAll();

    List<AdminGroup> findRange(int[] range);

    int count();

    List<AdminGroup> findNormal();
    
}
