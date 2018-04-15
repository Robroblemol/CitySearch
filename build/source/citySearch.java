import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import g4p_controls.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class citySearch extends PApplet {

Map m;
City c;
ArrayList<City> r = null;
ArrayList<City> cities = null;
PImage mapImage;
public void setup(){
    
    m = new Map();
    mapImage = loadImage("mapBucharest.png");
    cities = m.getCities();
    r = cities.get(15).findShortRoute("Craiova",cities.get(15));
    println(">> " + showArrayString(r));
}
public void draw() {
  background(0);
  image(mapImage,0,0);

}
public String showArrayString (ArrayList<City> a){
  String c = null;
  for(int i = 0; i < a.size(); i++){
    c = c+" + "+a.get(i).getName();
  }
  return c;
}
class City{
  private String name = null; // name of city
  private ArrayList<City> nextCities;
  private Boolean  parent = false;

  City(String name){
    this.name = name;
    this.nextCities  = new ArrayList <City>();
  }
  public City getNextCities(int i){
    return nextCities.get(i);
  }
  public void addNextNode(City c){
    nextCities.add(c);
  }
  public String getName(){
    return name;
  }
  public int size(){
    return nextCities.size();
  }
  public boolean getParent(){
    return parent;
  }
  public void setParent(Boolean p) {
    parent = p;
  }
  public ArrayList<City> findShortRoute (String destination, City parentNode){
    ArrayList<City> route = new ArrayList<City>();
    // println("destination: "+destination+
    //   " parentNode.Name: "+parentNode.getName()+
    //   " parentNode.parent: "+parentNode.getParent());
    //if(parentNode.getName().equals(destination)){
    if(this.getName().equals(destination)){
      route.add(this);
      return route;
    }else{
      // for(int i = 0; i < parentNode.size(); i++)
      //   if(parentNode.getNextCities(i).getParent() == false){
      for(int i = 0; i < nextCities.size(); i++)
       if(nextCities.get(i).getParent() == false){
          //parentNode.getNextCities(i).setParent(true);
          parentNode.setParent(true);
          //ArrayList<City> newRoute = parentNode.getNextCities(i).
          ArrayList<City> newRoute = nextCities.get(i).
          //findShortRoute(destination,parentNode.getNextCities(i));
          findShortRoute(destination,this);
          if(!newRoute.isEmpty() &&
          (newRoute.size() < route.size() || route.isEmpty())){
            route = newRoute;
          }
        }
        //parentNode.setParent(false);
        this.parent = false;
        if(!route.isEmpty())
            route.add(this);
          return route;


    }
  }

}
class Map{
  ArrayList <City> cities = new ArrayList<City>();
  String[] nameCity = {
    "Oredea","Zerind","Arad","Timisoara","Lugoj","Mehadia","Dobreta",
    "Sibu","Rimnicu Vilcea","Craiova","Fagaras","Pitesti","Bucharest",
    "Giurgiu","Urziceni","Neamt","Iasi","Vaslui","Hirsova","Eforie"
  };

  Map(){
    for (int i = 0;i < nameCity.length; i++)
      {try
        {cities.add (new City(nameCity[i]));
        }
        catch (Exception e){
          println(">>"+e);
        }
      }
      //agregamos los caminos

    cities.get(0).addNextNode(cities.get(7));//Sibus
    cities.get(0).addNextNode(cities.get(1));//Zerind

    cities.get(1).addNextNode(cities.get(0));
    cities.get(1).addNextNode(cities.get(2));

    cities.get(2).addNextNode(cities.get(1));
    cities.get(2).addNextNode(cities.get(7));
    cities.get(2).addNextNode(cities.get(3));

    cities.get(3).addNextNode(cities.get(2));
    cities.get(3).addNextNode(cities.get(4));

    cities.get(4).addNextNode(cities.get(3));
    cities.get(4).addNextNode(cities.get(5));

    cities.get(5).addNextNode(cities.get(4));
    cities.get(5).addNextNode(cities.get(6));

    cities.get(6).addNextNode(cities.get(5));
    cities.get(6).addNextNode(cities.get(9));

    cities.get(7).addNextNode(cities.get(0));
    cities.get(7).addNextNode(cities.get(2));
    cities.get(7).addNextNode(cities.get(10));
    cities.get(7).addNextNode(cities.get(8));

    cities.get(8).addNextNode(cities.get(7));
    cities.get(8).addNextNode(cities.get(9));
    cities.get(8).addNextNode(cities.get(11));

    cities.get(9).addNextNode(cities.get(8));
    cities.get(9).addNextNode(cities.get(11));
    cities.get(9).addNextNode(cities.get(6));

    cities.get(10).addNextNode(cities.get(7));
    cities.get(10).addNextNode(cities.get(12));

    cities.get(11).addNextNode(cities.get(9));
    cities.get(11).addNextNode(cities.get(12));

    cities.get(12).addNextNode(cities.get(10));
    cities.get(12).addNextNode(cities.get(11));
    cities.get(12).addNextNode(cities.get(14));
    cities.get(12).addNextNode(cities.get(13));

    cities.get(13).addNextNode(cities.get(12));

    cities.get(14).addNextNode(cities.get(12));
    cities.get(14).addNextNode(cities.get(17));
    cities.get(14).addNextNode(cities.get(18));

    cities.get(15).addNextNode(cities.get(16));

    cities.get(16).addNextNode(cities.get(17));

    cities.get(17).addNextNode(cities.get(16));
    cities.get(17).addNextNode(cities.get(14));

    cities.get(18).addNextNode(cities.get(14));
    cities.get(18).addNextNode(cities.get(19));

    cities.get(19).addNextNode(cities.get(18));

  }
  public ArrayList<City> getCities(){
    return cities;
  }

}
class Message{
  String titleApp = "Busqueda camino mas corto";
  String btnCalculate = "Calcular!";
  public String getTitleApp(){
    return "Busqueda camino mas corto";
  }
}
interface Model{
  interface Ipresenter{
    public ArrayList<City> calculateRoute(String d,City c);
  }
  interface Iview{
    public void getData ();
    public City getCityforName(String c);
    public void setResult(String r);
    public void txtResult();
  }
}
class Presenter implements Model.Ipresenter{

  public @Override
  ArrayList<City> calculateRoute(String d,City c){
    ArrayList<City> r = null;
    r = c.findShortRoute(d,c);
    return r;
  }
}
//importamos libreria
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

  public @Override
  void getData() {
    presenter.calculateRoute(txfCOrigin.getText(),
              getCityforName(txfCTarger.getText()));
  }
  public @Override
  City getCityforName(String c){
    for(int i = 0;i < cities.size(); i++){
      if(cities.get(i).getName().equals(c))
        return cities.get(i);
    }
    return null;
  }
  public @Override
    void setResult(String r) {
      this.r =r;
    }
  public @Override
    void txtResult() {
      fill(255);
      textSize(25);
      text(r,250, 50);
    }
}
  public void settings() {  size(840, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "citySearch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
