/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenprojectjqm.ApiFormateur;
import java.util.List;

/**
 *
 * @author gregj
 */
public interface InterfModeleFormateur {
    
    List<ApiFormateur> all();

    void close();

    void eff(ApiFormateur fo) throws Exception;

    void modif(ApiFormateur fo) throws Exception;

    void nouv(ApiFormateur fo) throws Exception;

    ApiFormateur rech(int nr) throws Exception;
    
}
