package main;

import modell.Auto;

public class KonzolAutosProgram {
    /* ADATTAGOK, a konstruktor inicializálja, 
    ha static az adattag, akkor lehet helyben is inicializálni
    Az adattagok minden metódusban elérhetőek
    */
    
    /* itt most:
    static, mert a main csak így látja
    adhatunk neki értéket, mert static, osztályhoz tartozik, 
    nem az objektum konstruktora inicializálja
    */
    private static KonzolAutosProgram prg = new KonzolAutosProgram();
    
    /* TAGFÜGGVÉNYEK */
    
    public static void main(String[] args) {
        /* ha STATIC lenne a bemutatok(), akkor az OSZHTÁLYhoz tartozna: */
//        AutosProgram.bemutatok();
        
        /* ha NINCS static, akkor a PÉLDÁNYhoz (példány=objektum) tartozik: */
        /* referencia nélkül, azaz nincs hivatkozás a memóriabeli objektumra: */
//        new AutosProgram().bemutatok();
        
        /* referenciával, azaz tudunk hivatkozni a memóriabeli objektumra: */
//        AutosProgram prg = new AutosProgram();
//        prg.bemutato_1();
//        prg.bemutato_2();
        
        /* csak 1 objektum legyen az AutosProgram osztályból, 
        ezt már fent példányosítottuk, a main csak 1 soros
        */
        prg.bemutatok();
    }

    private void bemutatok(){
        prg.bemutato_0();
        prg.bemutato_1();
        prg.bemutato_2();
    }
    
    private void bemutato_0() {
        //System.out.println("1. bemutató: ");
        Auto auto = new Auto();
        auto.megy();
        auto.setBeinditva(true);
        auto.megy();
        auto.megy();
        auto.tankol();
        auto.setBeinditva(false);
        auto.tankol();
        auto.setBeinditva(true);
        auto.megy();
        auto.megy();
    }
    
    private void bemutato_1() {
        //System.out.println("2. bemutató: ");
        Auto auto = new Auto(true);
        auto.tankol();
    }
    
    private void bemutato_2() {
        //System.out.println("3. bemutató: ");
        Auto auto = new Auto(true, true);
    }
    
}
