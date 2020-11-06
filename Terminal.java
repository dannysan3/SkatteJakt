import java.util.Scanner;

class Terminal implements Brukergrensesnitt {

  private Scanner skan;



  public Terminal(Scanner skan) {
    this.skan = skan;

  }

  public void giStatus(String status) {

    System.out.println(status);
  }



  public int beOmKommando(String spoersmaal, String[] alternativer) {
    System.out.println();
    System.out.println();
    System.out.println(spoersmaal);

    int teller = 0;
    System.out.println("Skriv inn index paa ditt valg");
    for (String s: alternativer) {
      System.out.println(teller + " : " + s);
      teller ++;
    }
    teller = 0;

    int svar = skan.nextInt();
    return svar;


  }
}
