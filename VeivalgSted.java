class VeivalgSted extends Sted {


  Sted[] stederVidere = new Sted[3];

  public VeivalgSted(String b) {
    super(b);


  }


  public String[] beskrivelse() {

    String[] beskrivelser = {"Hoyre: " + stederVidere[0].toString(), "Venstre: " + stederVidere[1].toString(), "Rett fram: " + stederVidere[2].toString()};
    return beskrivelser;
  }

  public Sted[] hentSteder() {
    return stederVidere;
  }


  public void skrivValgSteder() {
    System.out.println(this);
    System.out.println();
    for(Sted v: stederVidere) {
      System.out.println(v);
    }
  }




  }
