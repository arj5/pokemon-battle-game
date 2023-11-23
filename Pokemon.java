/*Pokemon.java 
 * April 14, 2022
 * ICS4U1 Arjun Sharma
 */
package Pokemon;
import java.util.Random;

public class Pokemon {
	//random num generator
	Random rand = new Random();

	//instance variables
    String name;
	private String type;
	private int healthTotal;
	private int healthLeft;
	private int energy;
	private int charge=2;
	private int heal=1;
	private int energyBoost=1;
	private int damageTaken;
	private boolean dodge;
	
	
	//constructor 
	public Pokemon() {
		name="";
		type="";
		healthTotal = 1;
		healthLeft = 0;
		energy = 100;
	}
	//constructor that inputs givens
	public Pokemon (String name, String type, int healthTotal, int healthLeft) {
		this.name=name;
		this.type=type;
		this.healthTotal=healthTotal;
		this.healthLeft=healthLeft;
		//energy always set to 100
		energy = 100;
	}
	
	
	
	
	//Decreases pokemon HP with given num
	public void damageTaken (int hp) {
		if(hp<0) {
			damageTaken=0;
		}
		
		else {
		damageTaken = hp;
		}
		
		
		if(healthLeft-damageTaken<0) {
			healthLeft = 0;
		}
		else {
		healthLeft -=damageTaken;
		}
	}
	//returns most recent amount of damage taken
	public int getDamageTaken() {
		return damageTaken;
	}
	
	
	//fast attack does damage with any num from 1-50
	//decrease energy by 10
	public int fastAttack() {
		
		if(energy>5) {
		int cap = 50;
		int random = rand.nextInt(cap)+1;
		energy -=10;
		return random;
		}
		else {
			return 0;
			
		}
		
	}
	//charged attack does damage with any num from 50-100
	//decreasing 20 energy and amount of charges
	public int chargeAttack() {
		if(charge>0 && energy>20) {
		int cap = 50;
		int random = rand.nextInt(cap)+51;
		charge-=1;
		energy-=20;
		return random;
		}
		return 0;
	
		
		
		
	}
	
	public void dodge() {
		//they can only dodge basic attacks, make it based off luck with 1/6 chances
		int random = rand.nextInt(6)+1;
	
		
		if(random==2) {
			dodge= true;
			
		}
		else {
			dodge= false;
		}
		
		
	}
	
	

	
	//can only heal once - +50HP
	public void heal() {
		heal=0;
		healthLeft+=50;

		
	}
	//can only use energy boost once - +20%
	public void energyBoost() {
			energyBoost=0;
			energy+=20;
		
	}
	
	
	
	//Prints out pokemon information + stats in correct formatting
	public String toString() {
		return name + " (" + type + ")\t" + healthLeft + "/" + healthTotal + " HP" + "\t" + energy + "% energy"; 
	}
	
		
	//Accessors and Mutators
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name= name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type= type;
	}
	
	public int getHealthTotal() {
		return healthTotal;
	}
	
	public void setHealthTotal(int healthTotal) {
		this.healthTotal= healthTotal;
	}
	
	public int getHealthLeft() {
		return healthLeft;
	}
	
	public void setHealthLeft(int healthLeft) {
		this.healthLeft= healthLeft;
	}
	
	public int getEnergy () {
		return energy;
	}
	
	public void setEnergy (int energy) {
		this.energy= energy;
	}
	
	public int getEnergyBoost () {
		return energyBoost;
	}
	
	public void setEnergyBoost (int energyBoost) {
		this.energyBoost= energyBoost;
	}
	public int getHeal () {
		return heal;
	}
	
	public void setHeal (int heal) {
		this.heal= heal;
	}
	
	public int getCharges () {
		return charge;
	}
	
	public void setCharges (int charge) {
		this.charge= charge;
	}
	
	public void setDodge (boolean dodge) {
		this.dodge= dodge;
	}
	
	public boolean getDodge () {
		return dodge;
	}
	
	public void setDamageTaken (int damageTaken) {
		this.damageTaken= damageTaken;
	}
	

	
	
}
