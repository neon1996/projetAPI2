/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenprojectjqm.ApiInfos;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author gregj
 */
public class ModeleInfos implements InterfModeleInfos {

    Session s;

    public ModeleInfos() {
        s = GestSession.getSession();
    }

    @Override
    public List<ApiInfos> all() {
        Query q = s.createQuery("from ApiInfos");
        List<ApiInfos> liste = (List<ApiInfos>) q.list();
        return liste;
    }

    @Override
    public void close() {

        GestSession.close();
    }

    @Override
    public void eff(ApiInfos inf) throws Exception {

        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.delete(inf);
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
    public void modif(ApiInfos inf) throws Exception {
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(inf);
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
    public void nouv(ApiInfos inf) throws Exception {
        
         Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(inf);
            t.commit();
        } catch (Exception e) {
            if(t!=null) t.rollback();
             s.clear();
            throw new Exception("Erreur de création :"+e.getMessage());
        }
       }

    @Override
    public ApiInfos rech(int nr) throws Exception {
        
        try{
        ApiInfos cf = null;
         Query q = s.createQuery("from ApiInfos where idinfos = :numrech");
        q.setInteger("numrech", nr);
        cf = (ApiInfos) q.uniqueResult();
        return cf;
        }
        catch (Exception e){
             s.clear();
             throw new Exception("Aucun résultat trouvé : "+e.getMessage());
        }
      }

}
