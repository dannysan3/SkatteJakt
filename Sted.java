class Sted {

  private String beskrivelse;
  private Skattekiste skatt;
  private Sted utgang;

  public Sted(String b) {
    beskrivelse = b;
  }


  public void settSkatt(Skattekiste s) {
    skatt = s;
  }

  public Skattekiste hentSkatt() {
    return skatt;
  }

  public void settNeste(Sted s) {
    utgang = s;
  }

  public Sted gaaNeste() {
    return utgang;
  }


  public String toString() {

    return beskrivelse;
  }


}
