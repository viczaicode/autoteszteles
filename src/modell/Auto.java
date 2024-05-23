package modell;


public class Auto {
    /* ADATTAGOK */
    private static int objektumDb = 0; //osztály adattagja, lehet itt inicializálni
    
    /* objektum adattagokat a konstruktor inicializál: */
    private boolean uzemanyag; //példány v. másnéven az objektum adattagja
    private boolean beinditva; //példány v. másnéven az objektum adattagja

    private boolean defekt;
    private int potkerek;
    
    /* TAGFÜGGVÉNYEK */
    /* kstr hívási lánc: túlterhelt kstr hívja a másik kstr-t */
    public Auto(){
        /* semmi nem lehet a kstr. hívás előtt, mert nem fordul le! */
        /* kstr csak kstr-ból hívunk, this kulcsszóval, nem a nevével */
        this(true, false);
    }
    
    public Auto(boolean beinditva){
        this(false, beinditva);
    }
    
    public Auto(boolean uzemanyag, boolean beinditva){
        this(uzemanyag, beinditva, 1);
    }
    
    public Auto(boolean uzemanyag, boolean beinditva, int potkerek){
        Auto.objektumDb++;
        this.uzemanyag = uzemanyag;
        this.beinditva = beinditva;
        this.potkerek = potkerek;
        this.defekt = false;
    }
    
    public void setBeinditva(boolean beinditva){
        this.beinditva = beinditva;
    }
    
     public void megyEselyDefektre(){
        megyEselyDefektre(.2);
    }
    
    public void megyEselyDefektre(double esely){
        if (beinditva && uzemanyag && !defekt) {
            if(Math.random() < esely){
                defektetKap();
            }else{
                megy();
            }
        }
    }
    
    //setUzemanyag(false)
    public void megy(){
        if (beinditva && uzemanyag && !defekt) {
           this.uzemanyag = false;
        }
    }
    
    //setUzemanyag(true)
    public void tankol(){
        if (!beinditva) {
            this.uzemanyag = true;
        }
    }
    
    public void kerekCsere(){
        if (defekt && potkerek > 0) {
            this.defekt = false;
            potkerek--;
        }
    }
    
    public void defektetKap(){
        this.defekt = true;
    }

    public boolean isUzemanyag() {
        return uzemanyag;
    }

    public boolean isDefekt() {
        return defekt;
    }

    public int getPotkerek() {
        return potkerek;
    }

    public boolean isBeinditva() {
        return beinditva;
    }
}

