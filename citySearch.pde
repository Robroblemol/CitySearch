Map m;
City c;
ArrayList<City> r = null;
ArrayList<City> cities = null;
PImage mapImage;
void setup(){
    size(840, 500);
    m = new Map();
    mapImage = loadImage("mapBucharest.png");
    cities = m.getCities();
    r = cities.get(15).findShortRoute("Craiova",cities.get(15));
    println(">> " + showArrayString(r));
}
void draw() {
  background(0);
  image(mapImage,0,0);

}
String showArrayString (ArrayList<City> a){
  String c = null;
  for(int i = 0; i < a.size(); i++){
    c = c+" + "+a.get(i).getName();
  }
  return c;
}
