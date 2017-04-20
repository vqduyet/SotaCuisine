/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Admin;
import entity.AdminGroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lenovo
 */
@Local
public interface AdminFacadeLocal {

    void create(Admin admin);

    void edit(Admin admin);

    void remove(Admin admin);

    Admin find(Object id);

    List<Admin> findAll();

    List<Admin> findRange(int[] range);

    int count();

    boolean login(String username, String password);

    Admin findByName(String username);

    List<Admin> findByGroup(AdminGroup group);

    boolean isUsernameAvailable(String username);

    boolean isGroupHasMember(AdminGroup group);

    
}
