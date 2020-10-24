package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        JSON json = new JSON();
        JSON.URL = "https://api.opendota.com/api/heroStats"; //Dota 2 heroes stats
        json.run();

        String jsonString = json.jsonIn;
        ObjectMapper mapper = new ObjectMapper();

        HeroesDB heroesDB = new HeroesDB();

        ArrayList<Heroes> heroes = mapper.reader()
                .forType(new TypeReference<ArrayList<Heroes>>() {
                })
                .readValue(jsonString);

        for(Heroes hero : heroes){
            heroesDB.add(hero);
        }
        heroesDB.getHeroesArrayList().sort(Heroes.byBaseArmorDesc);
        System.out.println("Result of sorting by base armor value:\n" + heroesDB);


        System.out.println("Enter the name of hero;");
        Scanner scanner = new Scanner(System.in);
        String search = scanner.nextLine();
        HeroesDB nameHero = heroesDB.filterByLocalizedName(search);
        System.out.println(nameHero);

    }
}
