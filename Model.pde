interface Model{
  interface Ipresenter{
    void calculateRoute(String d,City c);
    String showArrayString(ArrayList<City> a);
  }
  interface Iview{
    void getData ();
    City getCityforName(String c);
    void setResult(String r);
    void txtResult();
  }
}
