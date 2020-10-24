package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        JSON json = new JSON();
        JSON.URL = "https://api.opendota.com/api/heroStats";
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
        System.out.println(heroesDB);

        System.out.println("Enter the name of hero;");
        Scanner scanner = new Scanner(System.in);
        String search = scanner.nextLine();
        HeroesDB nameHero = heroesDB.filterByLocalizedName(search);
        System.out.println(nameHero);
        
    }
}