package plyrData;

import java.util.Random;

public class Dice {
	public int throwDice()
	{
		Random rand=new Random();
		int r=rand.nextInt(6)+1;
		return r;	
	}
}
