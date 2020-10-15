package edu.nyu.cs9053.homework5;

import edu.nyu.cs9053.homework5.*;

public class PotRoast implements Recipe{
	private static final int DEFAULT_VOLUME = 10000; 

	private final int volume;

	private int curTemperature;
	private Double remainSeconds;

	public PotRoast() {
		this(DEFAULT_VOLUME);
	}

	public PotRoast(int volume){
		this.volume = volume;
		curTemperature = 0;
		remainSeconds = Double.MAX_VALUE;
	}


    @Override
    public void initializeFromOven(Oven oven){
    	curTemperature = oven.getSetTemperature();
    	remainSeconds = (curTemperature / 5.0) * 60;
    }

    @Override
	public int getVolumeCubicInches(){
		return volume;
	}

	@Override
	public Double getRemainingSecondsUntilDone(){
		return remainSeconds;
	}

	@Override
	public void adjust(Time unit, int amount, int ovenTemperature){
		if (isRecipeDone()) {
			System.out.printf("Recipe already done!%n");
			return;
		}

		if (unit == Time.Minutes) {
			remainSeconds -= amount * 60;
		} else if (unit == Time.Seconds) {
			remainSeconds -= amount;
		}
		remainSeconds = remainSeconds < 0 ? 0 : remainSeconds;

		// update time based on temperature changes
		double ratio = curTemperature / ovenTemperature;
		remainSeconds = remainSeconds * ratio;
		curTemperature = ovenTemperature;

		if(isRecipeDone()) {
			System.out.printf("Recipe done!%n");
		}

	}

	@Override
	public boolean isRecipeDone(){
		return remainSeconds == 0;
	}

}