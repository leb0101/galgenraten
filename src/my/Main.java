package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
// Alternativ kann mit dem Scanner-Objekt aus dem java.util package gearbeitet werden
// (für die User-Eingabe)
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tries = LsgWort.ANZ;
    static String eingabe, endString;
    public static String[] lsgChars = new String[LsgWort.LSGWORT.length()];
    public static String[] lsgWortChars = new String[LsgWort.LSGWORT.length()];


    public static void main(String[] args) {
        initArrays();
        showMenue();
    }

// Methode zum anlegen des Lösungswortes in einem String-Array
    public static void initArrays(){
        endString = " ";
        for(int i=0; i < LsgWort.LSGWORT.length();i++){
            lsgWortChars[i] = LsgWort.LSGWORT.substring(i,i+1);
            lsgChars[i] = "_";
            endString += lsgChars[i];
        }
    }

// Methode zum anzeigen des Hauptmenues
    public static void showMenue(){
        if (tries != 0) {
            System.out.println("Galgenraten");
            System.out.println("----------------------------------");
            System.out.println("Lösungswort: " + endString);
            System.out.println("Versuche: " + tries + " von " + LsgWort.ANZ);
            System.out.println("1.) Lösungswort raten");
            System.out.println("2.) Buchstabe raten");
            System.out.println("0.) Programm verlassen");
            userChoice();
        }else{
            System.out.println("Leider verloren !");
            fin();
        }
    }

    // Methode um das Lösungswort anzuzeigen
    public static void endStringBuild(String eingabe) {
        endString = " ";

        for (int i = 0; i < LsgWort.LSGWORT.length(); i++) {
            if (LsgWort.LSGWORT.contains(eingabe) && LsgWort.LSGWORT.indexOf(eingabe, i) != -1) {
                i = LsgWort.LSGWORT.indexOf(eingabe, i);
                lsgChars[i] = lsgWortChars[i];
            }
        }

        for (int i=0; i< LsgWort.LSGWORT.length(); i++){
            endString += lsgChars[i];
        }
    }

    /**
     * Methode um die Benutzereingabe zu streuern
     */
    public static void userChoice(){
        try {
            eingabe = br.readLine();
            switch (eingabe){
                case "1": userInput(1);break;
                case "2": userInput(2);break;
                case "0": fin();break;
                default:
                    System.out.println("Ungültige Eingabe");
                    showMenue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void userInput(int userChoice){
        System.out.println("Ihre Eingabe:");
        try{
            eingabe = br.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (eingabe != null){
            check(eingabe, userChoice);
        }else
        {
            System.out.println("Ungültige Eingabe");
            userInput(userChoice);
        }
    }

    /**
     *
     * @param eingabe -Typ String
     */
    public static void check(String eingabe,int userChoice){
       if (userChoice==1){
           if (LsgWort.LSGWORT.matches(eingabe)){
               System.out.println("Richtig!");
           }
           else {
               System.out.println("Leider falsch ! Sie haben verloren");
           }
           fin();
       }

       else if (userChoice==2)
       {
           if (LsgWort.LSGWORT.contains(eingabe)) {
               tries--;
               System.out.println("Ihr Buchstabe " + eingabe + " ist enthalten.");
               endStringBuild(eingabe);

               if (endString.strip().equals(LsgWort.LSGWORT)){
                   System.out.println(" Sie haben alle Buchstaben erraten !");
                   fin();
               }
               else {
                   showMenue();
               }

           } else {
               tries--;
               System.out.println("Leider nicht Richtig !");
               showMenue();
           }
       }



    }
// Methode um das Programm zu beenden
        public static void fin(){
            System.out.println("Good Bye");
            System.exit(0);
        }

}
