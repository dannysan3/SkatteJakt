import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;


class Terreng {

  protected Sted start;
  protected ArrayList<Gjenstand> liste = new ArrayList<Gjenstand>();
  protected ArrayList<Sted> stedListe = new ArrayList<Sted>();




  public Terreng(File fil) throws FileNotFoundException { //vi oppretter terrenget i konstruktør
    File minFil = fil;
    Scanner lesFil = new Scanner(minFil);

    String linje;

      while (lesFil.hasNext()) {
      linje = lesFil.nextLine();
      Sted ny = new VeivalgSted(linje);
      if (start == null) {
        start = ny;

      }

      else {
        Sted temp = start;
        while (temp.gaaNeste() !=null) {
          temp = temp.gaaNeste();
        }

        temp.settNeste(ny);
      }

    }

    Sted temp2 = start;
    while (temp2!= null) {
      stedListe.add(temp2);
      temp2 = temp2.gaaNeste();

    }

  }

  public void plasserSkatter(File gjenstandFil) throws FileNotFoundException {
    File minFil = gjenstandFil;
    Scanner lesFil = new Scanner(minFil);
    String linje;

    int teller = 0;
    Sted temp = start;
    Skattekiste skatt;



    while (lesFil.hasNext()) {
      linje = lesFil.nextLine();
      String[] biter = linje.split(" ");
      Gjenstand g1 = new Gjenstand(biter[0],Integer.parseInt(biter[1]));
      liste.add(g1);

    }


    while (temp !=null &&temp.hentSkatt()==null) {
      skatt = new Skattekiste();



      if (teller == liste.size() || teller+1 ==liste.size()) {
        teller =0;
      }
      skatt.leggGjenstand(liste.get(teller));
      teller ++;
      skatt.leggGjenstand(liste.get(teller));
      teller ++;
      temp.settSkatt(skatt);
      temp = temp.gaaNeste();

    }



  }

  public Sted hentStart() {
    return start;
  }

  public void print(){ //hjelpe metode

        Sted gjeldende = start;
        int teller = 0;

        while (gjeldende != null){
            System.out.println("Sted " + teller + ": " + gjeldende);
            gjeldende.hentSkatt().skrivGjenstander();
            gjeldende = gjeldende.gaaNeste();
            teller++;
        }
    }


    public void skrivSteder() {
      for (Sted s: stedListe) {
        System.out.println(s);
      }
    }


    public void skriv() {
      for (Gjenstand g: liste) {
        System.out.println(g);
      }
    }




}
