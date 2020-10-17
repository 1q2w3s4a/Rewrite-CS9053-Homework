package edu.nyu.cs9053.homework5;

public class RoastedSweetPotato implements Recipe {

    private static final int DEFAULT_VOLUME = 6000;
    //private double cookingTime;
    private double remainingSec;
    private int volume;
    private int temperature;

    public RoastedSweetPotato() {
        this(DEFAULT_VOLUME);
    }

    public RoastedSweetPotato(int volume) {
        this.volume = volume;
        this.temperature = 0;
        this.remainingSec = Double.MAX_VALUE;
    }

    @Override
    public void initializeFromOven(Oven oven) {
        temperature = oven.getSetTemperature();
        remainingSec = this.temperature / 10 * 60;
    }

    @Override
    public int getVolumeCubicInches() {
        return this.volume;
    }

    @Override
    public Double getRemainingSecondsUntilDone() {
        return remainingSec;
    }

    @Override
    public void adjust(Time unit, int amount, int ovenTemperature) {
        if(isRecipeDone()) {
            System.out.println("Recipe is done! ");
            return;
        }

        if(unit == Time.Minutes) {
            remainingSec -= amount * 60;
        } else if(unit == Time.Seconds) {
            remainingSec -= amount;
        }
        remainingSec = remainingSec < 0 ? 0 : remainingSec;

        double ratio = temperature / ovenTemperature;
        remainingSec = remainingSec * ratio;
        temperature = ovenTemperature;

        if(isRecipeDone()) {
            System.out.println("Recipe is done! ");
        }


    }

    @Override
    public boolean isRecipeDone() {
        return remainingSec == 0;
    }
}
