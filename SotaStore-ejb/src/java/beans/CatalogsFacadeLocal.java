/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Catalogs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Duyet
 */
@Local
public interface CatalogsFacadeLocal {

    void create(Catalogs catalogs);

    void edit(Catalogs catalogs);

    void remove(Catalogs catalogs);

    Catalogs find(Object id);

    List<Catalogs> findAll();

    List<Catalogs> findRange(int[] range);

    int count();

    List<Catalogs> getCatalogByParentId(int parentId);

    List<Catalogs> getMenuCatalogs();
    
    List<Catalogs> getParentCatalogList();  

    List<Catalogs> getActiveCatalogsList();

    List<Catalogs> getInactiveCatalogsList();

    List<Catalogs> getSubCatalogList();

    boolean checkCatalogName(String catalogName);

    List<Catalogs> checkCatalogNameNotIn(int catalogId);
    
}
