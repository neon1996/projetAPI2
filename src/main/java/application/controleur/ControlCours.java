/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controleur;

import application.General;
import application.modele.InterfModeleCours;
import be.condorcet.mavenprojectjqm.ApiCours;
import be.condorcet.mavenprojectjqm.ApiSessioncours;

import java.util.*;
import org.hibernate.Transaction;

public class ControlCours {

    Scanner sc = new Scanner(System.in);
    InterfModeleCours mc;

    public ControlCours() {
        mc = (InterfModeleCours) General.getElement("modelecours");
    }

    public void menu() {
        int ch;
        do {
            System.out.println("\n1.Nouveau cours: ");
            System.out.println("2.Trouver cours sur base de son id: ");
            System.out.println("3.Modifier cours: ");
            System.out.println("4.Effacer un cours: ");
            System.out.println("5.Tous les cours: ");
            System.out.println("6.Fin.");

            System.out.println("Choix : ");
            ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    nouvcours();
                    break;
                case 2:
                    rechNum();
                    break;
                case 3:
                    modifcours();
                    break;
                case 4:
                    effcours();
                    break;
                case 5:
                    allcours();
                    break;
                case 6:
                    return;

                default:
                    System.out.println("Choix invalide");
            }
        } while (ch != 6);
    }

    public void quitter() {
        System.out.println("Deconnexion cours.");
        mc.close();

    }

    public void nouvcours() {

        try {
            System.out.println("Matière : ");
            String matiere = sc.nextLine();
            System.out.println("Nbres heures : ");
            Short heures = sc.nextShort();
            sc.skip("\n");

            ApiCours cl = new ApiCours(0, matiere, heures);
            cl.setMatiere(matiere);
            cl.setHeures(heures);

            mc.nouv(cl);
            System.out.println("Cours créé idcours : " + cl.getIdcours());
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }

    public ApiCours rechNum() {
        try {
            ApiCours cl = null;
            System.out.println("Identifiant du cours recherche : ");
            int nr = sc.nextInt();
            sc.skip("\n");
            cl = mc.rech(nr);
            System.out.println("Numero de cours : " + cl.getIdcours() + " - ");
            System.out.println("Matiere : " + cl.getMatiere() + " - ");
            System.out.println("Nbre heures : " + cl.getHeures());

            
        Set<ApiSessioncours> scf = cl.getApiSessioncourses();
        for (ApiSessioncours cf : scf) {
            System.out.println(cf.getIdsesscours());
        }
            return cl;
        } catch (Exception e) {
            System.out.println("Aucun cours trouvé." + e);
            return null;
        }

    }

    protected void modifcours() {
        try {
            ApiCours cl = rechNum();
            if (cl == null) {
                return;
            }
            System.out.println(cl.getMatiere() + " " + cl.getHeures());

            System.out.println("Nouvelle matière :");
            String matiere = sc.nextLine();
            cl.setMatiere(matiere);

            System.out.println("Nouveau nbre d'heures : ");
            Short heures = sc.nextShort();
            cl.setHeures(heures);

            mc.modif(cl);
            System.out.println("Cours modifié.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e);
        }
    }

    protected void effcours() {

        try {
            ApiCours cl = rechNum();
            if (cl == null) {
                return;
            }
            mc.eff(cl);
            System.out.println("Cours effacé.");
        } catch (Exception e) {
            System.out.println("Erreur " + e);
        }
    }

    protected void allcours() {

        List<ApiCours> liste = mc.all();
        for (ApiCours cl : liste) {
            System.out.print("\nNumero de cours :" + cl.getIdcours() + " - ");
            System.out.print("Matière :" + cl.getMatiere() + " - ");
            System.out.print("Heures :" + cl.getHeures());
        }

    }
}
