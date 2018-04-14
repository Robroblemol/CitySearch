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
  ArrayList<City> getCities(){
    return cities;
  }

}
