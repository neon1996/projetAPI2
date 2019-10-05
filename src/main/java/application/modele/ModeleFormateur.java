/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenprojectjqm.ApiFormateur;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author gregj
 */
public class ModeleFormateur implements InterfModeleFormateur {

    Session s;

    public ModeleFormateur() {
        s = GestSession.getSession();
    }

    @Override
    public List<ApiFormateur> all() {
        Query q = s.createQuery("from ApiFormateur");
        List<ApiFormateur> liste = (List<ApiFormateur>) q.list();
        return liste;
    }

    @Override
    public void close() {
        GestSession.close();
    }

    @Override
    public void eff(ApiFormateur fo) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.delete(fo);
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
    public void modif(ApiFormateur fo) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(fo);
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
    public void nouv(ApiFormateur fo) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(fo);
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
    public ApiFormateur rech(int nr) throws Exception {
        try {
            ApiFormateur form = null;
            Query q = s.createQuery("from ApiFormateur where idform= :numrech");
            q.setInteger("numrech", nr);
            form = (ApiFormateur) q.uniqueResult();
            return form;
        } catch (Exception e) {
            s.clear();
            throw new Exception("Aucun formateur trouvé : " + e.getMessage());
        }
    }

}
