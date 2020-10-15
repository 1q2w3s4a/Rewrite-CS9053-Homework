package edu.nyu.cs9053.homework5;

public class Baguette implements Recipe, Timer{
	private static final int volume = 2000;
	private static final double DEFAULT_initialRemainingSec = 0.0;
	private final Oven oven;
	private double initialRemainingSec;
	private double remainingSec;

	public Baguette(Oven oven) {
		this.oven = oven;
		this.initialRemainingSec = DEFAULT_initialRemainingSec;
	}


	public void initializeFromOven(Oven oven){
    	int temp = oven.getSetTemperature();
    }

	public int getVolumeCubicInches(){
		return volume;
	}

	public Double getRemainingSecondsUntilDone(){
		return remainingSec;
	}

	public void adjust(Time unit, int amount, int ovenTemperature){

	}

	public boolean isRecipeDone(){
		return remainingSec == 0;
	}

	@Override
	public void update(Time unit, int value, int ovenTemperature) {

	}
}