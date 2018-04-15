import g4p_controls.*;//importamos libreria
class View implements Model.Iview {

  private GTextField txfCTarger;
  private GTextField txfCOrigin;
  GButton btnCalculate;
  Presenter presenter = new Presenter();
  ArrayList<City> cities = null;
  String r = "";
  citySearch citySearch = null;

  private Message m = new Message();
  
  View(ArrayList<City> cities){
    G4P.setGlobalColorScheme(GCScheme.RED_SCHEME);
    G4P.setCursor(ARROW);
    surface.setTitle(m.titleApp);

    this.cities = cities;
    this.citySearch = citySearch;
    //surface.setTitle(m.getTitleApp());
    btnCalculate = new GButton (citySearch,70,125,100,35,m.btnCalculate);
    btnCalculate.fireAllEvents(true);

    txfCTarger = new GTextField(citySearch, 70, 80, 100, 20);
    txfCOrigin = new GTextField(citySearch, 70, 80, 100, 20);
  }
  public void handleButtonEvents(GButton button, GEvent event) {
    if(button==btnCalculate&&event==GEvent.PRESSED){
        getData();
      }
  }

  @Override
  void getData() {
    presenter.calculateRoute(txfCOrigin.getText(),
              getCityforName(txfCTarger.getText()));
  }
  @Override
  City getCityforName(String c){
    for(int i = 0;i < cities.size(); i++){
      if(cities.get(i).getName().equals(c))
        return cities.get(i);
    }
    return null;
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
