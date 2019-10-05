/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenprojectjqm.ApiInfos;
import java.util.List;


public interface InterfModeleInfos {
    
    List<ApiInfos> all();

    void close();

    void eff(ApiInfos inf) throws Exception;

    void modif(ApiInfos inf) throws Exception;

    void nouv(ApiInfos inf) throws Exception;

    ApiInfos rech(int nr) throws Exception;
    
}
