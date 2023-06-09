package com.ayc.simplecoffeeorder;

import com.ayc.simplecoffeeorder.model.Coffee;
import com.ayc.simplecoffeeorder.model.CoffeeRecipe;
import com.ayc.simplecoffeeorder.model.Ingredient;
import com.ayc.simplecoffeeorder.service.CoffeeRecipeService;
import com.ayc.simplecoffeeorder.service.CoffeeService;
import com.ayc.simplecoffeeorder.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Log4j2
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    private final CoffeeService coffeeService;

    private final IngredientService ingredientService;

    private final CoffeeRecipeService coffeeRecipeService;

    @Override
    public void run(String... args) {
        saveDefaultValues();
        userInteractive();
    }

    private void userInteractive() {
        menu();
    }

    private void menu() {
        List<Coffee> coffees = coffeeService.findAll();
        System.out.println("Menü:");
        for (int i = 0; i < coffees.size(); i++) {
            System.out.println((i + 1) + ". " + coffees.get(i).getName() + " - " + coffees.get(i).getPrice() + " TL");
        }
        System.out.println("0. Çıkış");
        System.out.println("Lütfen içmek istediğiniz kahvenin numarasını giriniz:");
        int coffeeNumber = getValidInput(coffees.size());
        System.out.println("Teşekkürler kahveniz hazırlanıyor...");
        List<CoffeeRecipe> coffeeRecipes = coffeeRecipeService.findByCoffee(coffees.get(coffeeNumber - 1));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(coffees.get(coffeeNumber - 1).getName());
        stringBuilder.append(" seçtiniz. Bu içeceğimiz ");
        for (int i = 0; i < coffeeRecipes.size(); i++) {
            stringBuilder.append(coffeeRecipes.get(i).getQuantity());
            stringBuilder.append(" doz ");
            stringBuilder.append(coffeeRecipes.get(i).getIngredient().getName());
            stringBuilder.append(" ");
            if (i != coffeeRecipes.size() - 1) {
                stringBuilder.append("ve ");
            }
        }
        stringBuilder.append("içermektedir. Afiyet Olsun.");
        System.out.println(stringBuilder.toString());
        menu();
    }

    private int getValidInput(int size) {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Geçersiz giriş! Lütfen bir sayı girin.");
            scanner.next();
        }
        int coffeeNumber = scanner.nextInt();
        if (coffeeNumber < 0 || coffeeNumber > size) {
            System.out.println("Geçersiz giriş! Lütfen menüdeki bir sayı girin.");
            menu();
        }
        if (coffeeNumber == 0) {
            System.out.println("Güle güle!");
            System.exit(0);
        }
        return coffeeNumber;
    }

    private void saveDefaultValues() {
        if (coffeeService.count() > 0) {
            log.info("Coffee list is already filled");
        } else {
            saveDefaultCoffees();
        }

        if (ingredientService.count() > 0) {
            log.info("Ingredient list is already filled");
        } else {
            saveDefaultIngredients();
        }

        if (coffeeRecipeService.count() > 0) {
            log.info("Coffee recipe list is already filled");
        } else {
            saveDefaultCoffeeRecipes();
        }
    }

    private void saveDefaultCoffeeRecipes() {
        List<Coffee> coffees = coffeeService.findAll();
        List<Ingredient> ingredients = ingredientService.findAll();
        List<CoffeeRecipe> coffeeRecipes = new ArrayList<>();
        fillCoffeeRecipes(coffees, ingredients, coffeeRecipes);
        coffeeRecipeService.saveAll(coffeeRecipes);
        log.info("Coffee recipe list is filled");
    }

    private void fillCoffeeRecipes(List<Coffee> coffees, List<Ingredient> ingredients, List<CoffeeRecipe> coffeeRecipes) {
        Map<String, Integer> ingredientMap = new HashMap<>();
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientMap.put(ingredients.get(i).getName(), i);
        }
        for (Coffee coffee : coffees) {
            switch (coffee.getName()) {
                case "Espresso":
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Espresso")), 1.0));
                    break;
                case "Double Espresso":
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Espresso")), 2.0));
                    break;
                case "Cappuccino":
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Espresso")), 1.0));
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Milk Foam")), 2.0));
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Steamed Milk")), 2.0));
                    break;
                case "Caffe Latte":
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Espresso")), 1.0));
                    coffeeRecipes.add
                            (new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Steamed Milk")), 3.0));
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Milk Foam")), 1.0));
                    break;
                case "Mocha":
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Espresso")), 1.0));
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Hot Chocolate")), 2.0));
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Milk Foam")), 1.0));
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Steamed Milk")), 1.0));
                    break;
                case "Americano":
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Espresso")), 1.0));
                    coffeeRecipes.add(
                            new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Hot Water")), 4.0));
                    break;
                case "Hot Water":
                    coffeeRecipes.add(new CoffeeRecipe(coffee, ingredients.get(ingredientMap.get("Hot Water")), 5.0));
                    break;
                default:
                    break;
            }
        }
    }

    private void saveDefaultIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        fillIngredients(ingredients);
        ingredientService.saveAll(ingredients);
        log.info("Ingredient list is filled");
    }

    private void fillIngredients(List<Ingredient> ingredients) {
        ingredients.add(new Ingredient("Espresso"));
        ingredients.add(new Ingredient("Hot Water"));
        ingredients.add(new Ingredient("Steamed Milk"));
        ingredients.add(new Ingredient("Milk Foam"));
        ingredients.add(new Ingredient("Hot Chocolate"));
    }

    private void saveDefaultCoffees() {
        List<Coffee> coffees = new ArrayList<>();
        fillCoffees(coffees);
        coffeeService.saveAll(coffees);
        log.info("Coffee list is filled");
    }

    private void fillCoffees(List<Coffee> coffees) {
        coffees.add(new Coffee("Espresso", 20.0));
        coffees.add(new Coffee("Double Espresso", 29.0));
        coffees.add(new Coffee("Cappuccino", 27.0));
        coffees.add(new Coffee("Caffe Latte", 27.0));
        coffees.add(new Coffee("Mocha", 32.0));
        coffees.add(new Coffee("Americano", 25.0));
        coffees.add(new Coffee("Hot Water", 5.0));
    }
}
