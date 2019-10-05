package application;

import application.controleur.ControlCours;
import application.controleur.ControlLocal;
import application.controleur.ControlFormateur;
import application.controleur.ControlInfos;
import application.controleur.ControlSessioncours;

import application.modele.InterfModeleCours;
import application.modele.InterfModeleLocal;
import application.modele.InterfModeleInfos;
import application.modele.InterfModeleSessioncours;
import application.modele.InterfModeleFormateur;

import application.modele.ModeleCours;
import application.modele.ModeleLocal;
import application.modele.ModeleFormateur;
import application.modele.ModeleSessioncours;
import application.modele.ModeleInfos;
import java.util.*;

public class General {

    private static Map<String, Object> elements = new HashMap<>();

    public General() {
        InterfModeleCours imcours = new ModeleCours();
        setElement("modelecours", imcours);

        ControlCours ccours = new ControlCours();
        setElement("controlcours", ccours);

        InterfModeleLocal imlocal = new ModeleLocal();
        setElement("modelelocal", imlocal);

        ControlLocal clocal = new ControlLocal();
        setElement("controllocal", clocal);

        InterfModeleFormateur imform = new ModeleFormateur();
        setElement("modeleformateur", imform);

        ControlFormateur cform = new ControlFormateur();
        setElement("controlformateur", cform);

        InterfModeleSessioncours imsesscours = new ModeleSessioncours();
        setElement("modelesesscours", imsesscours);

        ControlSessioncours csesscours = new ControlSessioncours();
        setElement("controlsesscours", csesscours);

        InterfModeleInfos imc = new ModeleInfos();
        setElement("modeleinfos", imc);

        ControlInfos cinfos = new ControlInfos();
        setElement("controlinfos", cinfos);
    }

    public static void setElement(String cle, Object elt) {
        elements.put(cle, elt);
    }

    public static Object getElement(String cle) {
        return elements.get(cle);
    }

    public void gestion() {
        ControlCours cc = (ControlCours) getElement("controlcours");
        ControlLocal cl = (ControlLocal) getElement("controllocal");
        ControlFormateur cf = (ControlFormateur) getElement("controlformateur");
        ControlInfos ci = (ControlInfos) getElement("controlinfos");
        ControlSessioncours csc = (ControlSessioncours) getElement("controlsesscours");
        int ch = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(""
                    + "\n1.Cours"
                    + "\n2.Local"
                    + "\n3.Formateur"
                    + "\n4.Sessioncours"
                    + "\n5.Infos"
                    + "\n6.Fin");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    cc.menu();
                    break;
                case 2:
                    cl.menu();
                    break;
                case 3 :
                    cf.menu();
                    break;
                case 4 : 
                    ci.menu();
                    break;
                case 5 :
                    csc.menu();
                    break;
                case 6:break;
                default:
                    System.out.println("choix invalide");
            }
        } while (ch != 6);
        cc.quitter();
        cl.quitter();
        cf.quitter();
        ci.quitter();
        csc.quitter();
    }

    public static void main(String[] args) {
        General pgm = new General();
        pgm.gestion();
    }

}
