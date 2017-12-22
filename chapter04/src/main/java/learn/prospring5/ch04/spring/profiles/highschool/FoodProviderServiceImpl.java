package learn.prospring5.ch04.spring.profiles.highschool;

import learn.prospring5.ch04.spring.profiles.Food;
import learn.prospring5.ch04.spring.profiles.FoodProviderService;

import java.util.ArrayList;
import java.util.List;



public class FoodProviderServiceImpl implements FoodProviderService {

    @Override
    public List<Food> provideLunchSet() {
        List<Food> lunchSet = new ArrayList<>();
        lunchSet.add(new Food("Coke"));
        lunchSet.add(new Food("Hamburger"));
        lunchSet.add(new Food("French Fries"));
        return lunchSet;
    }
}