/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenprojectjqm.ApiLocal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ModeleLocal implements InterfModeleLocal {

    Session s;

    public ModeleLocal() {
        s = GestSession.getSession();
    }

    @Override
    public List<ApiLocal> all() {
        Query q = s.createQuery("from ApiLocal");
        List<ApiLocal> liste = (List<ApiLocal>) q.list();
        return liste;
    }

    @Override
    public void close() {
        GestSession.close();
    }

    @Override
    public void eff(ApiLocal lo) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.delete(lo);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            s.clear();
            throw new Exception("Erreur d'effacement : " + e.getMessage());
        }
    }

    @Override
    public void modif(ApiLocal lo) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(lo);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            s.clear();
            throw new Exception("Erreur de mise à jour : " + e.getMessage());
        }
    }

    @Override
    public void nouv(ApiLocal lo) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(lo);
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
    public ApiLocal rech(int nr) throws Exception {
        try {
            ApiLocal loc = null;
            Query q = s.createQuery("from ApiLocal where idlocal= :numrech");
            q.setInteger("numrech", nr);
            loc = (ApiLocal) q.uniqueResult();
            return loc;
        } catch (Exception e) {
            s.clear();
            throw new Exception("Aucun local trouvé : " + e.getMessage());
        }
    }

}
