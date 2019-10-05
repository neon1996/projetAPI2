/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenprojectjqm.ApiCours;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author gregj
 */
public class ModeleCours implements InterfModeleCours {

    Session s;

    public ModeleCours() {
        s = GestSession.getSession();
    }

    @Override
    public List<ApiCours> all() {
      Query q = s.createQuery("from ApiCours");
        List<ApiCours> liste = (List<ApiCours>) q.list();
        return liste;
      }

    @Override
    public void close() {
        GestSession.close();
    }

    @Override
    public void eff(ApiCours co) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.delete(co);
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
    public void modif(ApiCours co) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(co);
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
    public void nouv(ApiCours co) throws Exception {

        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(co);
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
    public ApiCours rech(int nr) throws Exception {
        try {
            ApiCours cours = null;
            Query q = s.createQuery("from ApiCours where idcours = :numrech");
            q.setInteger("numrech", nr);
            cours = (ApiCours) q.uniqueResult();
            return cours;
        } catch (Exception e) {
            s.clear();
            throw new Exception("Aucun résultat : " + e.getMessage());
        }
    }

}
