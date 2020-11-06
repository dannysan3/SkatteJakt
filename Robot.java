import java.util.Random;

class Robot implements Brukergrensesnitt {

  public void giStatus(String status) {
    System.out.println(status);
  }

  public int beOmKommando(String spoersmaal, String[] alternativer) {
    Random rand = new Random();
    int r = rand.nextInt(alternativer.length);
    int teller = 0;
    System.out.println("Roboten sine alternativer: ");
    for (String s: alternativer) {
      System.out.println(teller + " : " + s);
      teller ++;
    }
    teller = 0;

    System.out.println("Robot velger alternativ : " + r + " : " + alternativer[r]);
    return r;

  }
}
