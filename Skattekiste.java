import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Skattekiste {

  private Gjenstand[] gjenstander = new Gjenstand[10];
  private Random r = new Random();
  private Lock laas = new ReentrantLock();
  //added comment


  public Gjenstand hentGjenstand() {
    laas.lock();

    try {

      int sjekkes[] = gyldigeIndexer();

      if (sjekkes.length ==1) {
        int index = sjekkes[0];
        Gjenstand eneste = gjenstander[index];
        gjenstander[index] = null;
        return eneste;
      }




      try {
        int rand = r.nextInt(hentStoerrelse());

        int randomIndex = sjekkes[rand];
        Gjenstand returneres = gjenstander[randomIndex];



        gjenstander[randomIndex] = null;
        return returneres;
      }
      catch(IllegalArgumentException e) {
        System.out.println("Ingen flere gjenstander aa plukke opp!");
        return null;
      }

    }

    finally{
      laas.unlock();
    }

  }

  public int leggGjenstand(Gjenstand g) { //antar at det må være plass i Skattekisten, for å selge

    laas.lock();
    try {
      if(g==null) {
        return 0;
      }

      int pris;
      int nyPris;
      for (int i=0; i<gjenstander.length; i++) {
        if (gjenstander[i]==null) {
          gjenstander[i] = g;
          pris = g.hentPris();
          nyPris = pris + r.nextInt(100);
          return nyPris;
        }
      }

        System.out.println("skattekiste full, kan ikke legge til " + g);
        return 0;
      }

    finally{
      laas.unlock();
    }

    }







    public int hentStoerrelse() {
      int teller = 0;
      for (Gjenstand g: gjenstander) {
        if (g!=null) {
          teller ++;
        }
      }
      return teller;
    }

    public int[] gyldigeIndexer() { //for å finne ut hvor i gjenstandlisten det er objekter
      int array[] = new int[hentStoerrelse()];
      int teller = 0;
      int index = 0;
      for (Gjenstand g: gjenstander) {
        if (g!=null) {
          array[index] = teller;
          index ++;
        }
        teller ++;
      }

      return array;


    }







  public void skrivGjenstander() {
    for (Gjenstand g: gjenstander) {
      System.out.println(g);
    }
  }





}
