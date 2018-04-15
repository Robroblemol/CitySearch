class City{
  private String name = null; // name of city
  private ArrayList<City> nextCities;
  private Boolean  parent = false;

  City(String name){
    this.name = name;
    this.nextCities  = new ArrayList <City>();
  }
  City getNextCities(int i){
    return nextCities.get(i);
  }
  void addNextNode(City c){
    nextCities.add(c);
  }
  String getName(){
    return name;
  }
  int size(){
    return nextCities.size();
  }
  boolean getParent(){
    return parent;
  }
  void setParent(Boolean p) {
    parent = p;
  }
  ArrayList<City> findShortRoute (String destination, City parentNode){
    ArrayList<City> route = new ArrayList<City>();
    if(this.getName().equals(destination)){
      route.add(this);
      return route;
    }else{
      for(int i = 0; i < nextCities.size(); i++)
       if(nextCities.get(i).getParent() == false){
          parentNode.setParent(true);
          ArrayList<City> newRoute = nextCities.get(i).
          findShortRoute(destination,this);
          if(!newRoute.isEmpty() &&
          (newRoute.size() < route.size() || route.isEmpty())){
            route = newRoute;
          }
        }
        this.parent = false;
        if(!route.isEmpty())
            route.add(this);
          return route;
    }
  }

}
