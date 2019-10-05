/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.modele;

import be.condorcet.mavenproject1.NewHibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Michel
 */
public class GestSession {
    private static Session s=null;
    
    public static Session getSession(){
        if (s==null)   s = NewHibernateUtil.getSessionFactory().openSession();
        return s;
    }
    
    public static void close(){
        if(s!=null) s.close();
        s=null;
    }
}
