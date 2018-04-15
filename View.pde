import g4p_controls.*;//importamos libreria


GButton btnCalculate;
GDropList selecTarget;
GDropList selecOrigin;

class View implements Model.Iview {

  private Presenter presenter = new Presenter(this);
  private ArrayList<City> cities = null;
  private String r = "Null";
  private citySearch citySearch = null;

  Message msg = new Message();


  View(citySearch citySearch,ArrayList<City> cities){
    G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
    G4P.setCursor(ARROW);
    surface.setTitle(msg.titleApp);

    this.cities = cities;
    this.citySearch = citySearch;
    //surface.setTitle(m.getTitleApp());
    btnCalculate = new GButton (citySearch,270,100,100,35,msg.btnCalculate);
    btnCalculate.fireAllEvents(true);

    selecOrigin = new GDropList(citySearch,270,50 , 120, 90, 5);
    selecOrigin.setItems(nameCity,0);
    selecOrigin.tag = msg.selecOrigin;
    selecTarget = new GDropList(citySearch,270,70 , 120, 90, 5);
    selecTarget.setItems(nameCity,0);
    selecTarget.tag = msg.selecTarget;

  }

  @Override
  void getData() {
  //  println("origen: "+selecOrigin.getSelectedText()+
  //  " target: "+getCityforName(selecTarget.getSelectedText()).getName());
     presenter.calculateRoute(selecOrigin.getSelectedText(),
               getCityforName(selecTarget.getSelectedText()));
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
      fill(0);
      textSize(14);
      text("Ruta: "+ r,210, 35);
    }
}
public void handleButtonEvents(GButton button, GEvent event) {
  if(button==btnCalculate&&event==GEvent.PRESSED){
      println("me precionaron!");
      v.getData();
    }
}
