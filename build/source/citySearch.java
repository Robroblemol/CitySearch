import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

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
