import g4p_controls.*;//importamos libreria


GButton btnCalculate;
GDropList selecTarget;
GDropList selecOrigin;

class View implements Model.Iview {

  private Presenter presenter = new Presenter();
  private ArrayList<City> cities = null;
  private String r = "";
  private citySearch citySearch = null;

  Message msg = new Message();


  View(citySearch citySearch,ArrayList<City> cities){
    G4P.setGlobalColorScheme(GCScheme.RED_SCHEME);
    G4P.setCursor(ARROW);
    surface.setTitle(msg.titleApp);

    this.cities = cities;
    this.citySearch = citySearch;
    //surface.setTitle(m.getTitleApp());
    btnCalculate = new GButton (citySearch,270,100,100,35,msg.btnCalculate);
    btnCalculate.fireAllEvents(true);

    selecOrigin = new GDropList(citySearch,270,50 , 80, 90, 4);
    selecOrigin.setItems(nameCity,0);
    selecOrigin.tag = msg.selecOrigin;
    selecTarget = new GDropList(citySearch,270,20 , 80, 90, 4);
    selecTarget.setItems(nameCity,0);
    selecTarget.tag = msg.selecTarget;

  }

  @Override
  void getData() {
    println("origen: "+selecOrigin.getSelectedText()+
    " target: "+getCityforName(selecTarget.getSelectedText()).getName());
    // presenter.calculateRoute(txfCOrigin.getText(),
    //           getCityforName(txfCTarger.getText()));
  }
  @Override
  City getCityforName(String c){
    City cc = null;
    for(int i = 0;i < cities.size(); i++){
      //println("nameCity: "+cities.get(i).getName()+" c: "+c);
      if(cities.get(i).getName().equals(c)){
        println("macth");
        cc = cities.get(i);
      }
    }
    return cc;
  }
  @Override
    void setResult(String r) {
      this.r =r;
    }
  @Override
    void txtResult() {
      fill(255);
      textSize(25);
      text(r,250, 50);
    }
}
public void handleButtonEvents(GButton button, GEvent event) {
  if(button==btnCalculate&&event==GEvent.PRESSED){
      println("me precionaron!");
      v.getData();
    }
}
