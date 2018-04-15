interface Model{
  interface Ipresenter{
    ArrayList<City> calculateRoute(String d,City c);
  }
  interface Iview{
    void getData ();
    City getCityforName(String c);
    void setResult(String r);
    void txtResult();
  }
}
