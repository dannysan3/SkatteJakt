class Gjenstand {

  private String navn;
  private int pris;

  public Gjenstand(String n, int p) {
    navn = n;
    pris = p;
  }


  public String hentNavn() {
    return navn;
  }

  public int hentPris() {
    return pris;
  }

  public String toString() {
    return  navn + " koster " + pris;
  }

}
