/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenprojectjqm.ApiLocal;
import java.util.List;

/**
 *
 * @author gregj
 */
public interface InterfModeleLocal {
    
    List<ApiLocal> all();

    void close();

    void eff(ApiLocal lo) throws Exception;

    void modif(ApiLocal lo) throws Exception;

    void nouv(ApiLocal lo) throws Exception;

    ApiLocal rech(int nr) throws Exception;
    
}
