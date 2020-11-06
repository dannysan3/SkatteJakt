import java.io.FileNotFoundException;
import java.io.File;
import java.util.Random;
class VeivalgTerreng extends Terreng {

  VeivalgSted start;
  Random rand = new Random();


  public VeivalgTerreng(File fil) throws FileNotFoundException {
    super(fil);
    for (Sted s: stedListe) {



      Sted hoyre =  stedListe.get(rand.nextInt(stedListe.size())); //her gir jeg 3 tilfeldige steder videre
      Sted venstre =  stedListe.get(rand.nextInt(stedListe.size()));
      Sted rettFram = stedListe.get(rand.nextInt(stedListe.size()));


      while (s.equals(hoyre)||s.equals(venstre)||s.equals(rettFram)) {
         hoyre =  stedListe.get(rand.nextInt(stedListe.size()));
         venstre =  stedListe.get(rand.nextInt(stedListe.size()));
         rettFram = stedListe.get(rand.nextInt(stedListe.size()));
      }

      VeivalgSted valgt = (VeivalgSted) s;

      valgt.hentSteder()[0] = hoyre;
      valgt.hentSteder()[1] = venstre;
      valgt.hentSteder()[2] = rettFram;




    }


  }




  public void test() {
    for (Sted s: stedListe) {
      VeivalgSted valgt =  (VeivalgSted) s;


      valgt.skrivValgSteder();

    }

  }



}
