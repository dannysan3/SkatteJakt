import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class Spill {
  static String stedFil = null;
  static String gjenstandsFil = null;
  static int trekk = 0;
  static Terreng t = null;
  static VeivalgTerreng te = null;

  public static void main(String[] args) {


    if (args.length>2) {
      stedFil = args[0];
      gjenstandsFil = args[1];
      trekk = Integer.parseInt(args[2]);
    }
    else {
      System.out.println("Feil bruk av programmet: "
      + "spill.java <stedFil> <gjenstandFil> <antallTrekk>");
      return;
    }



    System.out.println("Vil du ha:  0: enkelt terreng eller 1: VeivalgTerreng");

    Scanner skan = new Scanner(System.in);

    String svar = skan.nextLine();

    System.out.println("Skriv antall spillere: ");

    Scanner skan2 = new Scanner(System.in);

    int antallSpillere = skan2.nextInt();

    if (svar.equals("0")) {


      try {
        t = new Terreng(new File(stedFil));
        t.plasserSkatter(new File(gjenstandsFil));
      } catch(FileNotFoundException e) {
        System.out.println("finner ikke fil");
      }


      VanligTraad[] vanligeTraader = new VanligTraad[antallSpillere];

      for (int i =0; i<antallSpillere; i++) { //her oppretter vi de vanlige traadene, utifra hvor mange spillere som er
        VanligTraad traad = new VanligTraad(Integer.toString(i));
        vanligeTraader[i] = traad;


      }



      for (int tr=0;tr<trekk;tr++) {

        for (int i2=0; i2<antallSpillere; i2++) {
          Thread ny = new Thread(vanligeTraader[i2]);

          ny.start();
          try {
            ny.join();
          }catch(InterruptedException e) {

          }

        }
/*her oppretter jeg selve traadene, med de vanligeTraadene og legger dem i lista. Etter de er lagt til så starter jeg dem
for å sørge for at bare en og en tråd kommuniserer med terminal, så bruker jeg join rett etter.
Det jeg gjorde for å løse oppgaven var å opprette traader basert på antall spillere. Så kjører jeg en while løkke
med en for loop som kaller på start på hver traad, Dette for at traadene skal gjøre et nyttt trekk, siden traadene har sin egen spiller. */


      }
      int mest = 0;
      VanligTraad vinner = null;

      for (VanligTraad vt: vanligeTraader){
        if (vt.hentTraadFormue()>mest) {
          mest = vt.hentTraadFormue();
          vinner = vt;
        }
      }

      System.out.println("------------------------");
      System.out.println("Spill ferdig, Resultat: ");

      for (VanligTraad vt: vanligeTraader) {
        System.out.println(vt + " formue " + vt.hentTraadFormue());
      }


      System.out.println("Vinner er " + vinner + " med formue " + mest);
    }










  else {

    try {
      te = new VeivalgTerreng(new File(stedFil));
      te.plasserSkatter(new File(gjenstandsFil));
    } catch(FileNotFoundException e) {
      System.out.println("finner ikke fil");
    }

    VeiTraad[] veiTraader = new VeiTraad[antallSpillere];


    for (int i = 0; i<antallSpillere; i++) {
      VeiTraad traad = new VeiTraad(Integer.toString(i));
      veiTraader[i] = traad;
    }


    for (int tr=0;tr<trekk;tr++) {

      for (int i2 =0;i2<trekk;i2++){
        Thread ny = new Thread(veiTraader[i2]);
        ny.start();
        try {
          ny.join();
        }catch(InterruptedException e) {

        }

      }


    }
    int mest = 0;
    VeiTraad vinner = null;

    for (VeiTraad vt: veiTraader){
      if (vt.hentTraadFormue()>mest) {
        mest = vt.hentTraadFormue();
        vinner = vt;
      }
    }


    System.out.println("------------------------");
    System.out.println("Spill ferdig, Resultat: ");

    for (VeiTraad vt: veiTraader) {
      System.out.println(vt + " formue " + vt.hentTraadFormue());
    }

    System.out.println("Vinner er " + vinner + " med formue " + mest);









  }






  }



  static class VanligTraad implements Runnable{


    Skattekiste skatten = new Skattekiste();
    Spiller main;
    String nmr;


    public VanligTraad(String nmr) {
      this.nmr = nmr;



      Sted start = t.hentStart();
      Robot forste = new Robot(); //til terminal kjoring
      main = new Spiller(start,forste,skatten,nmr); //til terminal kjoring


    }

    public int hentTraadFormue() {
      return main.hentFormue();
    }



    public void run() {
      System.out.println("--------------------------");
      System.out.println(main + " sin tur");
      main.nyttTrekk();
    }

    public String toString() {
      return main.toString();
    }
  }


  static class VeiTraad implements Runnable {


    Skattekiste skatten  = new Skattekiste();
    VeivalgSpiller main;
    String nmr;

    public VeiTraad(String nmr) {
      this.nmr = nmr;



      VeivalgSted start = (VeivalgSted) te.hentStart();
      Robot forste = new Robot(); //til terminal kjoring
      main = new VeivalgSpiller(start,forste,skatten,nmr); //til terminal kjoring


    }

    public int hentTraadFormue() {
      return main.hentFormue();
    }

    public void run() {
      System.out.println("--------------------------");
      System.out.println(main + " sin tur");
      main.velgVei();


    }

    public String toString() {
      return main.toString();
    }

  }



}
