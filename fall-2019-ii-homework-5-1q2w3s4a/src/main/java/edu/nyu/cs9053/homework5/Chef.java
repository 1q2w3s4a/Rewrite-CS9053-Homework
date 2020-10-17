package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 */
public class Chef {

    private final SousChef sousChef;
    private final Oven oven;

    public Chef(SousChef sc, Oven oven) {
        this.sousChef = sc;
        this.oven = oven;
    }

    public static void main(String[] args) {
	// TODO - create an instance of Chef and cook the proper recipes according to the instructions.
        Oven oven = new Oven(300);
        SousChef sousChef = new SousChef(oven);
        Chef chef = new Chef(sousChef, oven);

        chef.prepareToCook(new RoastedSweetPotato());
        chef.prepareToCook(new PotRoast());

    }

    private void prepareToCook(Recipe recipe) {
	    sousChef.prepare(recipe, new RecipeReadyCallback() {
            @Override
            public void recipeReadyToCook(Recipe recipe) {
                recipe.initializeFromOven(oven);

                cookInOven(recipe, true);
            }
        });
    }

    private void cookInOven(Recipe recipe, boolean initialPutInOven) {

        oven.cook(recipe, new Timer() {
            @Override
            public void update(Time unit, int value, int ovenTemperature) {
                recipe.adjust(unit, value, ovenTemperature);
                if(recipe.isRecipeDone()) {
                    oven.takeOut(recipe);
                }
                else  {
                    oven.cook(recipe,this, false);
                }
            }
        }, initialPutInOven);

    }
}
