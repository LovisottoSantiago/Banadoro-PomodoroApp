package com.banapomodoro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomSelector {

  private final Random randomnizer;
  private String randomKey;
  private String randomValue;
  private final Map<String, String> myMap;

  public RandomSelector() {
    randomnizer = new Random();
    myMap = new HashMap<>();
    myMap.put("/assets/img/bana.png", "cdt-el-bananero.wav"); // Bana 
    myMap.put("/assets/img/panchito.png", "panchito.wav"); // Momo

  }

  public void getRandom() {
    List<String> keys = new ArrayList<>(myMap.keySet());
    int randomIndex = randomnizer.nextInt(keys.size());

    randomKey = keys.get(randomIndex);
    randomValue = myMap.get(randomKey);
  }



  public String getRandomName() {
    return randomKey;
  }

  public String getRandomSong() {
    return randomValue;
  }

}
