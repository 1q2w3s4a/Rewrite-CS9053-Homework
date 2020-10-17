package edu.nyu.cs9053.homework5;

public class PotRoast implements Recipe {
    private static final int DEFAULT_VOLUME = 10000;

    private double remainingSec;
    private int volume;
    private int temperature;

    public PotRoast() {
        this(DEFAULT_VOLUME);
    }

    public PotRoast(int volume) {
        this.volume = volume;
        this.remainingSec = Double.MAX_VALUE;
        this.temperature = 0;
    }

    @Override
    public void initializeFromOven(Oven oven) {
        temperature = oven.getSetTemperature();
        remainingSec = temperature / 5 * 60;
    }

    @Override
    public int getVolumeCubicInches() {
        return volume;
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
            System.out.println("Recipe is done! " );
        }
    }

    @Override
    public boolean isRecipeDone() {
        return remainingSec==0;
    }
}
