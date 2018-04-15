class Presenter implements Model.Ipresenter{

  private View v;

  Presenter(View v){
    this.v=v;
  }

  @Override
  void calculateRoute(String d,City c){
    ArrayList<City> r = null;
    r = c.findShortRoute(d,c);
    v.setResult(showArrayString(r));

  }
  String showArrayString (ArrayList<City> a){
    String c = "" ;
    for(int i = 0; i < a.size(); i++){
      if(i >= a.size()-1)
        c = c + a.get(i).getName();
      else
        c = c + a.get(i).getName()+" + ";
    }
    return c;
  }
}
