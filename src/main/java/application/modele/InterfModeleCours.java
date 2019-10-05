
package application.modele;
import be.condorcet.mavenprojectjqm.ApiCours;
import java.util.List;

public interface InterfModeleCours {
    
    List<ApiCours> all();

    void close();

    void eff(ApiCours co) throws Exception;

    void modif(ApiCours co) throws Exception;

    void nouv(ApiCours co) throws Exception;

    ApiCours rech(int nr) throws Exception;
    
}
