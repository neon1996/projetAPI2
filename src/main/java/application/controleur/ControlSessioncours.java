package application.controleur;

import application.General;

import application.modele.InterfModeleSessioncours;
import be.condorcet.mavenprojectjqm.ApiCours;
import be.condorcet.mavenprojectjqm.ApiLocal;

import be.condorcet.mavenprojectjqm.ApiSessioncours;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.hibernate.Transaction;

public class ControlSessioncours {

    Scanner sc = new Scanner(System.in);
    InterfModeleSessioncours mssc;

    public ControlSessioncours() {
        mssc = (InterfModeleSessioncours) General.getElement("modelesesscours");
    }

    public void menu() {
        int ch;
        do {
            System.out.println("\n1.Nouveau session cours :");
            System.out.println("2.Trouver session cours sur base de l'id :");
            System.out.println("3.Modifier session cours :");
            System.out.println("4.Effacer un session cours :");
            System.out.println("5.Toutes les session cours :");
            System.out.println("6.Fin.");

            System.out.println("Choix :");
            ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    nouvsesscours();
                    break;
                case 2:
                    rechNum();
                    break;
                case 3:
                    modifsesscours();
                    break;
                case 4:
                    effsesscours();
                    break;
                case 5:
                    allsesscours();
                    break;
                case 6:
                    return;

                default:
                    System.out.println("Choix invalide");
            }
        } while (ch != 6);
    }

    public void quitter() {
        System.out.println("Deconnexion sessioncours ...");
        mssc.close();

    }

    public void nouvsesscours() {

        Transaction t = null;
        try {
            ControlCours cc = (ControlCours) General.getElement("controlcours");
            ApiCours cours = cc.rechNum();

            ControlLocal cl = (ControlLocal) General.getElement("controllocal");
            ApiLocal local = cl.rechNum();

            ApiSessioncours sess = new ApiSessioncours();

            sess.setApiCours(cours);
            sess.setApiLocal(local);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            System.out.println("Date debut (dd-MM-yyyy) : ");
            String datedebut = sc.nextLine();
            Date dateDebut = formatter.parse(datedebut);

            System.out.println("Date fin (dd-MM-yyyy) : ");
            String datefin = sc.nextLine();
            Date dateFin = formatter.parse(datefin);

            sess.setDatedebut(dateDebut);
            sess.setDatefin(dateFin);

            System.out.println("Nbre étudiants inscrits : ");
            short nbreinscrits = sc.nextShort();
            sess.setNbreinscrits(nbreinscrits);

            mssc.nouv(sess);

            System.out.println("Session de cours créée idsess = " + sess.getIdsesscours());
        } catch (Exception e) {
            System.out.println("Erreur " + e);
        }
    }

    public ApiSessioncours rechNum() {
        try {
            ApiSessioncours sess = null;
            System.out.println("Numero de la session de cours recherchée : ");
            int nr = sc.nextInt();
            sc.skip("\n");
            sess = mssc.rech(nr);
            System.out.println("Numero de cours : " + sess.getIdsesscours() + " - ");
            System.out.println("Date début : " + sess.getDatedebut() + " ,Date Fin : " + sess.getDatefin() + " - ");
            System.out.println("Nbre inscrits : " + sess.getNbreinscrits() + " - ");
            System.out.println("Cours : " + sess.getApiCours().getMatiere() + " - ");
            System.out.println("Local : " + sess.getApiLocal().getSigle());

            return sess;
        } catch (Exception e) {
            System.out.println("Aucune session de cours trouvée pour cet id." + e);
            return null;
        }

    }

    protected void modifsesscours() {
        try {
            ApiSessioncours sess = rechNum();
            if (sess == null) {
                return;
            }
            System.out.println(" Id : " + sess.getIdsesscours() + " , Nbre inscrits : " + sess.getNbreinscrits());

            System.out.println("Nouveau nbre d'inscrits : ");
            Short nbreinscrits = sc.nextShort();
            sess.setNbreinscrits(nbreinscrits);
            sc.skip("\n");
            mssc.modif(sess);
            System.out.println("Session cours modifiée.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e);
        }
    }

    protected void effsesscours() {

        try {
            ApiSessioncours sess = rechNum();
            if (sess == null) {
                return;
            }
            mssc.eff(sess);
            System.out.println("Session cours effacé.");
        } catch (Exception e) {
            System.out.println("Erreur." + e);
        }
    }

    protected void allsesscours() {

        List<ApiSessioncours> liste = mssc.all();
        for (ApiSessioncours sess : liste) {
            System.out.print("Numero de session cours : " + sess.getIdsesscours() + " - ");
            System.out.print("Date debut : " + sess.getDatedebut() + ", Date fin : " + sess.getDatefin() + " - ");
            System.out.print("Nbre inscrits : " + sess.getNbreinscrits() + " - ");
            System.out.println("Cours : " + sess.getApiCours().getMatiere() + " - ");
            System.out.print("Local : " + sess.getApiLocal().getSigle());

        }

    }

}
