package teszt;

import modell.Auto;

public class TesztAuto {
    public static void main(String[] args) {
        new TesztAuto().tesztesetek();
    }

    private void tesztesetek() {
        letrehozasTeszt();
        haladasTeszt();
        tankolasTeszt();
        potkerekekTeszt();
        kerekCsereTeszt();
    }

    private void letrehozasTeszt() {
        System.out.println("- Létrehozás (Konstruktor) tesztek");
        
        System.out.println("-- Auto() -> NINCS beindítva, VAN üzemanyag");
        Auto auto = new Auto();
        assert !auto.isBeinditva() : "inicializációs hiba";
        assert auto.isUzemanyag() : "inicializációs hiba";
        
        System.out.println("-- Auto(true) -> be VAN indítva, NINCS üzemanyag");
        auto = new Auto(true);
        assert auto.isBeinditva() : "inicializációs hiba";
        assert !auto.isUzemanyag() : "inicializációs hiba";
        
        System.out.println("-- Auto(false) -> NINCS beindítva, NINCS üzemanyag");
        auto = new Auto(false);
        assert !auto.isBeinditva() : "inicializációs hiba";
        assert !auto.isUzemanyag() : "inicializációs hiba";
        
        System.out.println("-- Auto(true, true) -> be VAN indítva, VAN üzemanyag");
        auto = new Auto(true, true);
        assert auto.isBeinditva() : "inicializációs hiba";
        assert auto.isUzemanyag() : "inicializációs hiba";
        
        System.out.println("-- Auto(true, false) -> NINCS beindítva, VAN üzemanyag");
        auto = new Auto(true, false);
        assert !auto.isBeinditva() : "inicializációs hiba";
        assert auto.isUzemanyag() : "inicializációs hiba";
        
        System.out.println("-- Auto(false, true) -> be VAN indítva, NINCS üzemanyag");
        auto = new Auto(false, true);
        assert auto.isBeinditva() : "inicializációs hiba";
        assert !auto.isUzemanyag(): "inicializációs hiba";
        
        System.out.println("-- Auto(false, false) -> NINCS beindítva, NINCS üzemanyag");
        auto = new Auto(false, false);
        assert !auto.isBeinditva() : "inicializációs hiba";
        assert !auto.isUzemanyag() : "inicializációs hiba";
        
        System.out.println("-- NINCS beindítva, NINCS üzemanyag, 2 pótkerék");
        auto = new Auto(false, false, 2);
        assert !auto.isBeinditva() : "inicializációs hiba";
        assert !auto.isUzemanyag() : "inicializációs hiba";
        assert auto.getPotkerek() == 2 : "inicializációs hiba";
        
        System.out.println("-- be VAN indítva, VAN üzemanyag, 2 pótkerék");
        auto = new Auto(true, true, 2);
        assert auto.isBeinditva() : "inicializációs hiba";
        assert auto.isUzemanyag() : "inicializációs hiba";
        assert auto.getPotkerek() == 2 : "inicializációs hiba";
    }
    
    private void haladasTeszt() {
        System.out.println("- haladás tesztek:");
        
        System.out.println("-- ha megérkeztünk, ELFOGYOTT az üzemanyag");
        Auto auto = new Auto(true, true);
        auto.megy();
        boolean kapott = auto.isUzemanyag();
        boolean vart = false;
        assert kapott == vart : "haladási hiba";
        
        /* egyszerűbb, könnyebb: */
        //assert !auto.isUzemanyag() : "haladási hiba";
        
        System.out.println("-- ha defekt, akkor MARAD üzemanyag");
        auto = new Auto(true, true);
        auto.defektetKap();
        auto.megy();
        assert auto.isDefekt() : "haladási hiba";
        assert auto.isUzemanyag() : "haladási hiba";
        
        System.out.println("-- ha nincs beindítva, akkor VAN üzemanyag");
        auto = new Auto(true, false);
        auto.megy();
        assert auto.isUzemanyag() : "haladási hiba";
        
        System.out.println("-- BIZTOS LESZ defekt haladáskor, MARAD üzemanyag");
        auto = new Auto(true, true);
        auto.megyEselyDefektre(1);
        assert auto.isDefekt() : "haladási defekt hiba";
        assert auto.isUzemanyag() : "haladási defekt hiba";
        
        System.out.println("-- BIZTOS NEM LESZ defekt haladáskor, ÜRES a tank");
        auto = new Auto(true, true);
        auto.megyEselyDefektre(0);
        assert !auto.isDefekt() : "haladási defekt hiba"; 
        assert !auto.isUzemanyag() : "haladási defekt hiba"; 
    }
    
     private void tankolasTeszt() {
        System.out.println("- Tankolás tesztek: ");
       
        System.out.println("-- VAN üzemanyag, LEállítva -> marad üzemanyag");
        Auto auto = new Auto(true, false);
        auto.tankol();
        boolean kapott = auto.isUzemanyag();
        boolean vart = true;
        assert kapott == vart : " tankolási hiba";
        /* a könnyebb olvashatóságért: */
        //assert auto.isUzemanyag() : " tankolási hiba";
        
        System.out.println("-- VAN üzemanyag, BEindítva -> marad üzemanyag");
        auto = new Auto(true, true);
        auto.tankol();
        assert auto.isUzemanyag() : " tankolási hiba";
        
        System.out.println("-- NINCS üzemanyag, BEindítva -> nem lehet tankolni");
        auto = new Auto(false, true);
        auto.tankol();
        assert !auto.isUzemanyag() : " tankolási hiba";
        
        System.out.println("-- NINCS üzemanyag, LEállítva -> lehet tankolni");
        auto = new Auto(false, false);
        auto.tankol();
        assert auto.isUzemanyag() : " tankolási hiba";  
    }
     
    private void potkerekekTeszt(){
        System.out.println("- Pótkerék tesztek (csere nélkül): ");
        Auto[] tesztAutok = new Auto[4];
        tesztAutok[0] = new Auto(false, false, 0);
        tesztAutok[1] = new Auto();
        tesztAutok[2] = new Auto(false, false, 3);
        tesztAutok[3] = new Auto(false, false, -2);
        
        int[] vartErtekek = {0, 1, 3, 0};
        for (int i = 0; i < tesztAutok.length; i++) {
            int vart = vartErtekek[i];
            System.out.printf("-- %d. eset: %d pótkerék\n".formatted(i, vart));
            Auto auto = tesztAutok[i];
            int kapott = auto.getPotkerek();
            assert kapott == vart : "pótkerék hiba";
        }
        
        
//        System.out.println("-- alapértelmezett 0 pótkerék");
//        Auto auto = new Auto(false, false, 0);
//        int kapott = auto.getPotkerek();
//        int vart = 0;
//        assert kapott == vart : "pótkerék hiba";
//        
//        System.out.println("-- alapértelmezett 1 pótkerék");
//        auto = new Auto();
//        kapott = auto.getPotkerek();
//        vart = 1;
//        assert kapott == vart : "pótkerék hiba";
//        
//        System.out.println("-- alapértelmezett 3 pótkerék");
//        auto = new Auto(false, false, 3);
//        kapott = auto.getPotkerek();
//        vart = 3;
//        assert kapott == vart : "pótkerék hiba";
//        
//        System.out.println("-- negatív értékű pótkerék");
//        auto = new Auto(false, false, -2);
//        kapott = auto.getPotkerek();
//        vart = 0;
//        assert kapott == vart : "pótkerék hiba";
    }
    
    private void kerekCsereTeszt() {
        System.out.println("- Kerékcsere tesztek: ");
       
        System.out.println("-- nem defektest cserélünk");
        Auto auto = new Auto();
        auto.kerekCsere();
        int kapott = auto.getPotkerek();
        int vart = 1;
        assert kapott == vart : "kerékcsere hiba";
        
        System.out.println("-- nem defektest cserélünk, többször");
        auto = new Auto();
        auto.kerekCsere();
        auto.kerekCsere();
        auto.kerekCsere();
        kapott = auto.getPotkerek();
        vart = 1;
        assert kapott == vart : "kerékcsere hiba";
        
        System.out.println("-- defektest cserélünk");
        auto = new Auto();
        auto.defektetKap();
        auto.kerekCsere();
        kapott = auto.getPotkerek();
        vart = 0;
        assert kapott == vart : "kerékcsere hiba";
        
        System.out.println("-- defektest cserélünk, többszöt");
        auto = new Auto();
        auto.defektetKap();
        auto.kerekCsere();
        auto.kerekCsere();
        auto.kerekCsere();
        auto.kerekCsere();
        kapott = auto.getPotkerek();
        vart = 0;
        assert kapott == vart : "kerékcsere hiba";
        
        System.out.println("-- defektest cserélünk, marad még pótkerék");
        auto = new Auto(true, true, 2);
        auto.defektetKap();
        auto.kerekCsere();
        kapott = auto.getPotkerek();
        vart = 1;
        assert kapott == vart : "kerékcsere hiba";
        
        System.out.println("-- defektest cserélünk többször, nincs több pótkerék");
        auto = new Auto(true, true, 2);
        auto.defektetKap();
        auto.kerekCsere();
        auto.defektetKap();
        auto.kerekCsere();
        kapott = auto.getPotkerek();
        vart = 0;
        assert kapott == vart : "kerékcsere hiba";
    }
    
   
}
