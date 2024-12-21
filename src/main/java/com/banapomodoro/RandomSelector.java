package com.banapomodoro;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomSelector {

  private Random randomnizer;

  private Map<String, String> myMap = new HashMap<>();

  public RandomSelector() {
    myMap.put("/assets/img/bana.png", "cdt-el-bananero.wav"); // Bana 
    myMap.put("/assets/img/bana.png", "cdt-el-bananero.wav"); // Momo
    myMap.put("/assets/img/bana.png", "cdt-el-bananero.wav"); // Bana 
    myMap.put("/assets/img/bana.png", "cdt-el-bananero.wav"); // Bana 
  }

  public void getRandom() {

  }



  public String getRandomName() {
    return "Hola";
  }

  public String getRandomSong() {
    return "Hola";
  }

}
