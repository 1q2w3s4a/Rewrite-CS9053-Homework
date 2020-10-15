package edu.nyu.cs9053.homework5;



/**
 * User: blangel
 */
public class Chef {

	private final SousChef sous;
	private final Oven oven;

	public Chef(SousChef sous, Oven oven) {
		this.sous = sous;
		this.oven = oven;
	}

    public static void main(String[] args) {
    	Oven oven  = new Oven(300);
    	SousChef sc = new SousChef(oven);
    	Chef c = new Chef(sc, oven);

    	c.prepareToCook(new RoastedSweetPotato());
    }

    private void prepareToCook(Recipe recipe) {
    	sous.prepare(recipe, new RecipeReadyCallback(){
    		@Override
    		public void recipeReadyToCook(Recipe recipe) {
    			recipe.initializeFromOven(oven);

    			cookInOven(recipe, true);
    		}
    	});
    }

    private void cookInOven(Recipe recipe, boolean initialPutInOven) {
    	oven.cook(recipe, new Timer(){
    		@Override
    		public void update(Time unit, int value, int ovenTemperature) {
    			recipe.adjust(unit, value, ovenTemperature);
    			if(recipe.isRecipeDone()) {
    				oven.takeOut(recipe);
    			} else {
    				oven.cook(recipe, this, false);
    			}
    		}
    	}, initialPutInOven);
    }
}
