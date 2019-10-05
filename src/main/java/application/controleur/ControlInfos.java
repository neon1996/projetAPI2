/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controleur;

import application.General;
import application.modele.InterfModeleCours;
import application.modele.InterfModeleInfos;
import be.condorcet.mavenprojectjqm.ApiFormateur;
import be.condorcet.mavenprojectjqm.ApiInfos;
import be.condorcet.mavenprojectjqm.ApiSessioncours;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author gregj
 */
public class ControlInfos {

    Scanner sc = new Scanner(System.in);
    InterfModeleInfos mi;

    public ControlInfos() {
        mi = (InterfModeleInfos) General.getElement("modeleinfos");
    }

    public void menu() {
        int ch;
        do {
            System.out.println("\n1.Nouveau infos : ");
            System.out.println("2.Trouver infos sur base de id : ");
            System.out.println("3.Modifier infos : ");
            System.out.println("4.Effacer un infos : ");
            System.out.println("5.Tous les infos : ");
            System.out.println("6.Fin.");

            System.out.println("Choix : ");
            ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    nouvinfos();
                    break;
                case 2:
                    rechNum();
                    break;
                case 3:
                    modifinfos();
                    break;
                case 4:
                    effinfos();
                    break;
                case 5:
                    allinfos();
                    break;
                case 6:
                    return;

                default:
                    System.out.println("choix invalide");
            }
        } while (ch != 6);
    }

    public void quitter() {
        System.out.println("Deconnexion infos.");
        mi.close();

    }

    public void nouvinfos() {

        try {

            ControlFormateur cf = (ControlFormateur) General.getElement("controlformateur");
            ApiFormateur form = cf.rechNum();

            ControlSessioncours ssc = (ControlSessioncours) General.getElement("controlsessioncours");
            ApiSessioncours sess = ssc.rechNum();

            ApiInfos inf = new ApiInfos();

            inf.setApiFormateur(form);
            inf.setApiSessioncours(sess);

            System.out.println("Nbre heures :");
            Short nh = sc.nextShort();

            sc.skip("\n");

            mi.nouv(inf);
            System.out.println("Infos créé idinfos : " + inf.getIdInfos());
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }

    public ApiInfos rechNum() {
        try {
            ApiInfos inf = null;
            System.out.println("Identifiant infos : ");
            int nr = sc.nextInt();
            sc.skip("\n");
            inf = mi.rech(nr);
            System.out.println("Numero infos : " + inf.getIdInfos() + "-");
            System.out.println("Matiere : " + inf.getNh() + "-");
            System.out.println("Nbre heures : " + inf.getApiFormateur().getMatricule() + "-");
            System.out.println("Nbre heures : " + inf.getApiSessioncours().getIdsesscours() + "-");

            return inf;
        } catch (Exception e) {
            System.out.println("Aucun infos trouvé :" + e);
            return null;
        }

    }

    protected void modifinfos() {
        try {
            ApiInfos inf = rechNum();
            if (inf == null) {
                return;
            }
            System.out.println(inf.getIdInfos() + " " + inf.getNh() + " "
                    + inf.getApiFormateur().getMatricule() + " " + inf.getApiSessioncours().getIdsesscours());

            System.out.println("Nouveau nbre d'heures : ");
            Short nh = sc.nextShort();
            inf.setNh(nh);

            sc.skip("\n");

            mi.modif(inf);
            System.out.println("Infos modifié.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e);
        }
    }

    protected void effinfos() {

        try {
            ApiInfos inf = rechNum();
            if (inf == null) {
                return;
            }
            mi.eff(inf);
            System.out.println("Infos effacé.");
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }

    protected void allinfos() {

        List<ApiInfos> liste = mi.all();
        for (ApiInfos inf : liste) {
            System.out.print("\nNumero infos : " + inf.getIdInfos() + "-");
            System.out.print("Nombre d'heures : " + inf.getNh() + "-");
            System.out.print("Formateur : " + inf.getApiFormateur() + "-");
            System.out.println("Session de cours : " + inf.getApiSessioncours());
        }

    }

}
