package com.example.zuzia.cookbook.recipesManager;

import com.example.zuzia.cookbook.R;

import java.util.ArrayList;
import java.util.List;

public class RecipesManager {
    private List<Recipe> recipeList = new ArrayList<>();
    private static RecipesManager recipesManager = new RecipesManager();

    private RecipesManager() {
        this.initListWithSampleData();
    }

    public static RecipesManager getInstance() {
        return recipesManager;
    }

    private void initListWithSampleData() {
        Recipe recipe1 = new Recipe();
        recipe1.setTitle("Czekoladowe ciasto z truskawkami");
        recipe1.setDescription("Pyszne czekoladowe ciasto, które po upieczeniu opada i tworzy miejsce na warstwę puszystego kremu mascarpone z owocami.");
        List<String>  ingredients1 = new ArrayList<>();
        ingredients1.add("czekolada 300g");
        ingredients1.add("masło 150g");
        ingredients1.add("jajka 6");
        ingredients1.add("cukier 100g");
        ingredients1.add("truskawki");
        ingredients1.add("śmietanka 250ml");
        ingredients1.add("mascarpone 250g");
        recipe1.setIngredients(ingredients1);
        recipe1.setInstruction(" Piekarnik nagrzać do 175 stopni C. Przygotować tortownicę o średnicy 24 cm.\n" +
                "Czekoladę połamać na kosteczki i rozpuścić razem z masłem nie podgrzewając za bardzo składników (np. w rondelku na minimalnym ogniu ciągle mieszając). Odstawić z ognia.\n" +
                "Do czystej miski wbić 2 jajka oraz dodać żółtka z pozostałych 4 jajek (białka zachować). Żółtka wymieszać rózgą, następnie wlać do nich masę czekoladową z rondelka i wymieszać do uzyskania gładkiej masy.\n" +
                "Ubić pozostałe 4 biała na sztywną pianę, następnie stopniowo dodawać cukier cały czas dokładnie ubijając, aż piana będzie sztywna i gęsta.\n" +
                "Pianę dodać w dwóch partiach do masy czekoladowej i mieszać delikatnie łyżką do połączenia się składników.\n" +
                "Wyłożyć do formy i wstawić do piekarnika. Piec przez ok. 35 minut (można sprawdzić patyczkiem czy mokre ciasto nie przykleja się do niego).\n" +
                "Wyjąć ciasto z piekarnika i całkowicie ostudzić (podczas studzenia środek ciasta zapadnie się tworząc miejsce na krem i owoce).");
        recipe1.setImageId(R.drawable.czekoladowe_ciasto);
        recipeList.add(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setTitle("Naleśniki");
        recipe2.setDescription("Najlepszy przepis na naleśniki");
        List<String>  ingredients2 = new ArrayList<>();
        ingredients2.add("mąka 1 szkl");
        ingredients2.add("jajka 2");
        ingredients2.add("woda gazowana 3/4 szklanki");
        ingredients2.add("mleko 1 szkl");
        recipe2.setIngredients(ingredients2);
        recipe2.setInstruction("Mąkę wsypać do miski, dodać jajka, mleko, wodę i sól. Zmiksować na gładkie ciasto. Dodać roztopione masło lub olej roślinny i razem zmiksować (lub wykorzystać tłuszcz do smarowania patelni przed smażeniem każdego naleśnika).\n" +
                "Naleśniki smażyć na dobrze rozgrzanej patelni z cienkim dnem np. naleśnikowej. Przewrócić na drugą stronę gdy spód naleśnika będzie już ładnie zrumieniony i ścięty.");
        recipe2.setImageId(R.drawable.nalesniki);
        recipeList.add(recipe2);

        Recipe recipe3 = new Recipe();
        recipe3.setTitle("Herbata z rozmarynem i grejpfrutem");
        recipe3.setDescription("Rozgrzewająca herbata na zimę");
        List<String> ingredients3 =  new ArrayList<>();
        ingredients3.add("zaparzona herbata 1 kubek");
        ingredients3.add("rozmaryn ałązka");
        ingredients3.add("grejpfrut plasterek");
        ingredients3.add("syrop malinowy 3 łyżki");
        recipe3.setIngredients(ingredients3);
        recipe3.setInstruction("Herbatę zaparzyć razem z rozmarynem i rozgniecionym w moździerzu pieprzem. Dodać grejpfruta i cytrynę, sok malinowy i maliny. Herbatę można dosłodzić wedle uznania.");
        recipe3.setImageId(R.drawable.herbata);
        recipeList.add(recipe3);

        Recipe recipe4 = new Recipe();
        recipe4.setTitle("Szklanka wody");
        List<String> ingredients4 = new ArrayList<>();
        ingredients4.add("woda");
        recipe4.setIngredients(ingredients4);
        recipe4.setInstruction("Wodę wlać do szklanki i wypić");
        recipe4.setImageId(R.drawable.woda);
        recipeList.add(recipe4);

       /* Recipe recipe5 = new Recipe();
        recipe5.setTitle("Szklanka ");
        recipe5.setInstruction("Szklanka bez wody :( ");
        recipe5.setImageId(R.drawable.woda);
        recipeList.add(recipe5);*/
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
