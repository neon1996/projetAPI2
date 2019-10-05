/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controleur;

import application.General;
import application.modele.InterfModeleLocal;
import be.condorcet.mavenprojectjqm.ApiLocal;
import be.condorcet.mavenprojectjqm.ApiSessioncours;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author gregj
 */
public class ControlLocal {

    Scanner sc = new Scanner(System.in);
    InterfModeleLocal ml;

    public ControlLocal() {
        ml = (InterfModeleLocal) General.getElement("modelelocal");
    }

    public void menu() {
        int ch;
        do {
            System.out.println("\n1.Nouveau local: ");
            System.out.println("2.Trouver local sur base de l'identifiant: ");
            System.out.println("3.Modifier local: ");
            System.out.println("4.Effacer un local: ");
            System.out.println("5.Tous les locaux: ");
            System.out.println("6.Fin.");

            System.out.println("Choix :");
            ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    nouvlocal();
                    break;
                case 2:
                    rechNum();
                    break;
                case 3:
                    modiflocal();
                    break;
                case 4:
                    efflocal();
                    break;
                case 5:
                    alllocal();
                    break;
                case 6:
                    return;

                default:
                    System.out.println("choix invalide");
            }
        } while (ch != 6);
    }

    public void quitter() {
        System.out.println("Deconnexion local ...");
        ml.close();

    }

    public void nouvlocal() {

        try {
            System.out.println("Sigle du local :");
            String sigle = sc.nextLine();
            System.out.println("Nbres de place :");
            Short places = sc.nextShort();

            sc.skip("\n");

            System.out.println("Description :");
            String desc = sc.nextLine();

            ApiLocal loc = new ApiLocal(0, sigle, places, desc);
            loc.setSigle(sigle);
            loc.setPlaces(places);
            loc.setDescription(desc);

            ml.nouv(loc);
            System.out.println("Local créé idlocal= " + loc.getIdlocal());
        } catch (Exception e) {
            System.out.println("erreur " + e);
        }
    }

    public ApiLocal rechNum() {
        try {
            ApiLocal loc = null;
            System.out.println("Sigle du local recherché ");
            int nr = sc.nextInt();
            //sc.skip("\n");
            loc = ml.rech(nr);

            System.out.println("Numero du local :" + loc.getIdlocal() + "-");
            System.out.println("Nbre de places :" + loc.getPlaces() + "-");
            System.out.println("Description:" + loc.getDescription());

            Set<ApiSessioncours> scf = loc.getApiSessioncourses();
        for (ApiSessioncours cf : scf) {
            System.out.println(cf.getIdsesscours());
        }
            return loc;
        } catch (Exception e) {
            System.out.println("Aucun local trouvé :" + e);
            return null;
        }

    }

    protected void modiflocal() {
        try {
            ApiLocal loc = rechNum();
            if (loc == null) {
                return;
            }
            System.out.println(loc.getSigle() + " " + loc.getPlaces() + " " + loc.getDescription());
            
            System.out.println("Nouveau sigle :");
            String matiere = sc.nextLine();
            loc.setSigle(matiere);
            
            System.out.println("Nouveau nbre de places : ");
            Short places = sc.nextShort();
            loc.setPlaces(places);
            
            System.out.print("Nouvelle description : ");
            String desc = sc.nextLine();
            loc.setDescription(desc);

            ml.modif(loc);
            System.out.println("Local modifié ");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour :" + e);
        }
    }

    protected void efflocal() {

        try {
            ApiLocal loc = rechNum();
            if (loc == null) {
                return;
            }
            ml.eff(loc);
            System.out.println("Local effacé");
        } catch (Exception e) {
            System.out.println("Erreur " + e);
        }
    }

    protected void alllocal() {

        List<ApiLocal> liste = ml.all();
        for (ApiLocal loc : liste) {
            System.out.print("Numero du local :" + loc.getIdlocal() + "-");
            System.out.print("Sigle du local : " + loc.getSigle() + "-");
            System.out.print("Nbre de places :" + loc.getPlaces() + "-");
            System.out.print("Description :" + loc.getDescription());
        }

    }

}
