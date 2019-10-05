/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenprojectjqm.ApiSessioncours;
import java.util.List;

/**
 *
 * @author gregj
 */
public interface InterfModeleSessioncours {
    
   List<ApiSessioncours> all();

    void close();

    void eff(ApiSessioncours ssc) throws Exception;

    void modif(ApiSessioncours ssc) throws Exception;

    void nouv(ApiSessioncours ssc) throws Exception;

    ApiSessioncours rech(int nr) throws Exception;
    
}
