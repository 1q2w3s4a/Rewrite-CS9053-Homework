package edu.nyu.cs9053.homework5;

public class RoastedSweetPotato implements Recipe{
	private static final int DEAFULT_VOLUME = 6000;

	private int temp;
	private Double remainSec;
	private final int volume;

	public RoastedSweetPotato() {
		this(DEAFULT_VOLUME);
	}

	public RoastedSweetPotato(int volume) {
		this.volume = volume;
		temp = 0;
		remainSec = Double.MAX_VALUE;
	}

	@Override
	public void initializeFromOven(Oven oven) {
		temp = oven.getSetTemperature();
		remainSec = (temp / 10.0) * 60;
	}

	@Override
	public int getVolumeCubicInches() {
		return volume;
	}

	@Override
	public Double getRemainingSecondsUntilDone() {
		return remainSec;
	}

	@Override
	public void adjust(Time unit, int amount, int ovenTemperature) {
		if (isRecipeDone()) {
			System.out.printf("Recipe already done!%n");
			return;
		}
		
		if (unit == Time.Minutes) {
			remainSec -= amount * 60;
		} else if (unit == Time.Seconds) {
			remainSec -= amount;
		}
		remainSec = remainSec < 0 ? 0 : remainSec;
		
		double ratio = temp / ovenTemperature;
		remainSec = remainSec * ratio;
		temp = ovenTemperature;
		
		if (isRecipeDone()) {
			System.out.printf("Recipe done!%n");
		}
	}

	@Override
	public boolean isRecipeDone() {
		return remainSec == 0;
	}
}