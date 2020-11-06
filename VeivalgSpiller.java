class VeivalgSpiller extends Spiller {

  public VeivalgSpiller(VeivalgSted start, Brukergrensesnitt r, Skattekiste s,String nmr) {
    super(start,r,s,nmr);

  }



  public void velgVei() {
    super.nyttTrekk();


  }


  @Override
  public void nesteSted() {
    VeivalgSted startSted2 = (VeivalgSted)  startSted;
    int svar2 = ref.beOmKommando("Hvor vil du gaa videre ", startSted2.beskrivelse() );

    startSted = startSted2.hentSteder()[svar2];

  }
}
