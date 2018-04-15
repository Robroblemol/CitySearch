class Presenter implements Model.Ipresenter{

  @Override
  ArrayList<City> calculateRoute(String d,City c){
    ArrayList<City> r = null;
    r = c.findShortRoute(d,c);
    return r;
  }
}
