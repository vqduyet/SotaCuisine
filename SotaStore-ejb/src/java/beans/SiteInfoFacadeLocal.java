/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.SiteInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Duyet
 */
@Local
public interface SiteInfoFacadeLocal {

    void create(SiteInfo siteInfo);

    void edit(SiteInfo siteInfo);

    void remove(SiteInfo siteInfo);

    SiteInfo find(Object id);

    List<SiteInfo> findAll();

    List<SiteInfo> findRange(int[] range);

    int count();
    
}
