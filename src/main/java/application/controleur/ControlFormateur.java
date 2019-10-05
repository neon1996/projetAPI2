/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controleur;

import application.General;
import application.modele.InterfModeleFormateur;
import be.condorcet.mavenprojectjqm.ApiFormateur;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author gregj
 */
public class ControlFormateur {

    Scanner sc = new Scanner(System.in);
    InterfModeleFormateur mf;

    public ControlFormateur() {
        mf = (InterfModeleFormateur) General.getElement("modeleformateur");
    }

    public void menu() {
        int ch;
        do {
            
            System.out.println("\n1.Nouveau formateur: ");
            System.out.println("2.Trouver formateur sur base de son matricule: ");
            System.out.println("3.Modifier formateur: ");
            System.out.println("4.Effacer un formateur: ");
            System.out.println("5.Tous les formateurs: ");
            System.out.println("6.Fin.  ");

            System.out.println("Choix :");
            ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    nouvform();
                    break;
                case 2:
                    rechNum();
                    break;
                case 3:
                    modifform();
                    break;
                case 4:
                    effform();
                    break;
                case 5:
                    allform();
                    break;
                case 6:
                    return;

                default:
                    System.out.println("choix invalide");
            }
        } while (ch != 6);
    }

    public void quitter() {
        System.out.println("Deconnexion formateur.");
        mf.close();

    }

    public void nouvform() {

        try {
            System.out.println("Matricule :");
            String matricule = sc.nextLine();
           
            System.out.println("Nom :");
            String nom = sc.nextLine();
            
            System.out.println("Prenom :");
            String prenom = sc.nextLine();
            
            System.out.println("Numero :");
            String numero = sc.nextLine();
            
            System.out.println("Rue :");
            String rue = sc.nextLine();
            
            System.out.println("Localite :");
            String localite = sc.nextLine();

            System.out.println("Code postal :");
            Short cp = sc.nextShort();
            sc.skip("\n");

            System.out.println("Telephone :");
            String tel = sc.nextLine();


            ApiFormateur form = new ApiFormateur(0, matricule, nom, prenom, numero, rue, localite, cp, tel);
            form.setMatricule(matricule);
            form.setNom(nom);
            form.setPrenom(prenom);
            form.setNumero(numero);
            form.setRue(rue);
            form.setLocalite(localite);
            form.setCp(cp);
            form.setTel(tel);

            mf.nouv(form);
            
            System.out.println("Formateur créé idform : " + form.getIdform());
        } catch (Exception e) {
            System.out.println("erreur " + e);
        }
    }

    public ApiFormateur rechNum() {
        try {
            ApiFormateur form = null;
            System.out.println("Matricule du formateur recherché :");
            int nr = sc.nextInt();
            sc.skip("\n");
            form = mf.rech(nr);
            System.out.println("\nNom : " + form.getNom() + " Prenom: " + form.getPrenom() + " - ");
            System.out.println("Adresse : " + form.getNumero()+ " , " + form.getRue() + " , " + form.getLocalite() + " - ");
            System.out.println("Telephone : " + form.getTel());
            return form;
        } catch (Exception e) {
            System.out.println("Aucun formateur trouvé pour ce matricule : " + e);
            return null;
        }

    }

    protected void modifform() {
        try {
            ApiFormateur form = rechNum();
            if (form == null) {
                return;
            }
            
            System.out.println(form.getMatricule()+ " " + form.getNom() + " " + form.getPrenom());
            
            System.out.println("Nouveau matricule :");
            String matricule = sc.nextLine();
            form.setMatricule(matricule);
            
            System.out.println("Nouveau nom :");
            String nom = sc.nextLine();
            form.setNom(nom);
            
            System.out.println("Nouveau Prenom :");
            String prenom = sc.nextLine();
            form.setMatricule(prenom);
            
            System.out.println("Nouveau numero :");
            String numero = sc.nextLine();
            form.setNumero(numero);
            
            System.out.println("Nouvelle rue :");
            String rue = sc.nextLine();
            form.setMatricule(rue);
            
            System.out.println("Nouvelle localité :");
            String localite = sc.nextLine();
            form.setMatricule(localite);
            
            System.out.println("Nouveau code postal : ");
            Short cp = sc.nextShort();
            form.setCp(cp);
            sc.skip("\n");
            
            System.out.println("Nouveau telephone :");
            String tel = sc.nextLine();
            form.setMatricule(tel);

            mf.modif(form);
            System.out.println("Formateur modifié.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e);
        }
    }

    protected void effform() {

        try {
            ApiFormateur form = rechNum();
            if (form == null) {
                return;
            }
            mf.eff(form);
            System.out.println("Formateur effacé.");
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }

    protected void allform() {

        List<ApiFormateur> liste = mf.all();
        for (ApiFormateur form : liste) {
            System.out.print("\nNumero du formateur : " + form.getIdform() + "-");
            System.out.print("Nom : " + form.getNom() + " Prenom : " + form.getPrenom());
        }

    }

}
