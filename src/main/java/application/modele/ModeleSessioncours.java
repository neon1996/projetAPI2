/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenprojectjqm.ApiSessioncours;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author gregj
 */
public class ModeleSessioncours implements InterfModeleSessioncours {

    Session s;

    public ModeleSessioncours() {
        s = GestSession.getSession();
    }

    @Override
    public List<ApiSessioncours> all() {

        Query q = s.createQuery("from ApiSessioncours");
        List<ApiSessioncours> liste = (List<ApiSessioncours>) q.list();
        return liste;

    }

    @Override
    public void close() {
        GestSession.close();
    }

    @Override
    public void eff(ApiSessioncours ssc) throws Exception {
         Transaction t = null;
        try {
            t = s.beginTransaction();
            s.delete(ssc);
            t.commit();
        } catch (Exception e) {
            if(t!=null)   t.rollback();
             s.clear();
            throw new Exception("Erreur d'effacement : "+e.getMessage());
        }
    }

    @Override
    public void modif(ApiSessioncours ssc) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(ssc);
            t.commit();
        } catch (Exception e) {
            if(t!=null)t.rollback();
             s.clear();
            throw new Exception("Erreur de mise à jour : "+e.getMessage());
            }
    }

    @Override
    public void nouv(ApiSessioncours ssc) throws Exception {

        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(ssc);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            s.clear();
            throw new Exception("Erreur de création :" + e.getMessage());
        }
    }

    @Override
    public ApiSessioncours rech(int nr) throws Exception {

        try {
            ApiSessioncours sess = null;
            Query q = s.createQuery("from ApiSessioncours where idsesscours = :numrech");
            q.setInteger("numrech", nr);
            sess = (ApiSessioncours) q.uniqueResult();
            return sess;
        } catch (Exception e) {
            s.clear();
            throw new Exception("Aucun résultat trouvé : " + e.getMessage());
        }

    }

}
