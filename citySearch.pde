Map m;
City c;
ArrayList<City> cities = null;
View v;

void setup(){
    size(840, 500);
    m = new Map();
    cities = m.getCities();
    v = new View(this,cities);
}
void draw() {
  background(0);
  v.txtResult();
}
