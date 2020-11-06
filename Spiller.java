class Spiller {

  protected Sted startSted;

  protected Brukergrensesnitt ref;

  protected Skattekiste sekk;
  protected int formue = 0;

  protected String spillerNmr;

  public Spiller(Sted start, Brukergrensesnitt r, Skattekiste s,String nmr) {
    startSted = start;
    ref = r;
    sekk = s;
    spillerNmr = nmr;
  }

  public String toString() {
    return "Spiller " + spillerNmr;
  }


  public int hentFormue() {
    return formue;
  }

  public Sted hentStartSted(){
    return startSted;
  }



  public void nyttTrekk() {
    System.out.println();
    System.out.println();
    ref.giStatus(startSted.toString());
    int plass = 10- sekk.hentStoerrelse(); //variabel som sjekker hvor mye plass som er i sekke, sekken sin maks er 10
    int selgesFor;
    int svar;


    String antall = "Du har : " + plass + "/10 ledige plasser  i sekken din";
    System.out.println(antall);

    if (plass ==0) { //kommer vi inn hit, har brukeren brukt opp alle sine plasser
      String[] alternativer = {"Selg gjenstand ", "Gaa videre"};
       svar = ref.beOmKommando("Du kan bare selge gjenstander i sekken din eller gaa videre ", alternativer );
      if (svar ==0) {
        selgesFor = startSted.hentSkatt().leggGjenstand(sekk.hentGjenstand());
        formue += selgesFor;
      }


    }

    else if (plass==10) { //kommer vi inn hit, har brukeren ingen gjenstander i sekken
      String[] alternativer3 = {"Plukk opp en gjenstand", "Gaa videre"};
      svar = ref.beOmKommando("Du har ingenting i sekken din ",alternativer3);

      if (svar==0) {
        sekk.leggGjenstand(startSted.hentSkatt().hentGjenstand());
      }

    }

    else { //her har vi minst en gjenstand i sekken
      String[] alternativer2 = {"Selg gjenstand", "Plukk opp en gjenstand ", "Selg og plukk opp en gjenstand ", "Gaa videre"};
      svar = ref.beOmKommando("Du har plass i sekken din, alle alternativer aapne ", alternativer2);
      if (svar ==0) {

        selgesFor = startSted.hentSkatt().leggGjenstand(sekk.hentGjenstand());
        formue += selgesFor;
      }
      else if(svar == 1) {
        sekk.leggGjenstand(startSted.hentSkatt().hentGjenstand());

      }

      else if(svar == 2) {
        sekk.leggGjenstand(startSted.hentSkatt().hentGjenstand());
        selgesFor = startSted.hentSkatt().leggGjenstand(sekk.hentGjenstand());
        formue += selgesFor;

      }

    }

    nesteSted();


  }


  public void nesteSted() {
    startSted = startSted.gaaNeste();
  }




}
