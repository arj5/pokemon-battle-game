/*Player.java 
 * April 14, 2022
 * ICS4U1 Arjun Sharma
 */
package Pokemon;
public class Player {
	
	//insatance variables of the 3 pokemon
	private Pokemon p1;
	private Pokemon p2;
	private Pokemon p3;
	private int wins;
	private int loss;
	private int tie;
	

	//constructor for the 3 pokemon
	public Player (Pokemon p1, Pokemon p2, Pokemon p3) {
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}
	

		
	//prints players pokemon (that are active)
	public String toString() {
		String display = "";
		if(p1.getHealthLeft()==p1.getHealthTotal()) {
			display += "-----\nName: "+ p1.getName() + "\t"+ p1.getHealthTotal() + " HP" + "\nType: " + p1.getType()+ "\n-----";
		}
		if(p2.getHealthLeft()==p2.getHealthTotal()) {
			display += "-----\nName: "+ p2.getName() + "\t"+ p2.getHealthTotal() + " HP" + "\nType: " + p2.getType()+ "\n-----";
		}
		if(p3.getHealthLeft()==p3.getHealthTotal()) {
			display += "-----\nName: "+ p3.getName() + "\t"+ p3.getHealthTotal() + " HP" + "\nType: " + p3.getType()+ "\n-----";
		}
		return display;
	}

	
	//prints stars art for number of stage wins
	public void stageWins() {
		
		if(wins==1) {
			System.out.println("| || |\r\n"
				+ "| || |\r\n"
				+ "|_/\\_|\r\n"
				+ "<_  _>\r\n"
				+ "  \\/\n");
		}
		if(wins==2) {
			System.out.println("| || |  | || |\r\n"
					+ "| || |  | || |\r\n"
					+ "|_/\\_|  |_/\\_|\r\n"
					+ "<_  _>  <_  _>\r\n"
					+ "  \\/      \\/\n");
		}
		if(wins==3) {
			System.out.println("| || |  | || |  | || |\r\n"
					+ "| || |  | || |  | || |\r\n"
					+ "|_/\\_|  |_/\\_|  |_/\\_|\r\n"
					+ "<_  _>  <_  _>  <_  _>\r\n"
					+ "  \\/      \\/      \\/");
		}
		if(wins ==0) {
			System.out.println("No star medals.");
		}
	}
	
	
	//adds wins 
	public void wins() {
		
		
		wins++;
	}
	//adds losses
	public void loss() {
		loss++;
	}
	
	//adds ties
		public void tie() {
			tie++;
		}
	

	//Accesor and Mutators
	public Pokemon getP1() {
		return p1;
	}
	
	public void SetP1(Pokemon p1) {
		this.p1= p1;
	}
	
	public Pokemon getP2() {
		return p2;
	}
	
	public void SetP2(Pokemon p2) {
		this.p2= p2;
	}
	public Pokemon getP3() {
		return p3;
	}
	
	public void SetP3(Pokemon p3) {
		this.p3= p3;
	}

	public void setLoss(int loss) {
		this.loss=loss;
	}
	public void setWins(int wins) {
		this.wins=wins;
	}
	
	public void setTies(int tie) {
		this.tie=tie;
	}
	
	public int getWins() {
		return wins;
	}
	public int getLoss() {
		return loss;
	}
	
	public int getTies() {
		return tie;
	}
	


	//Made by me
	//| || |
	//| || |
	//|_/\_|
	//<_  _>
	  //\/
	  
	  //| || |  | || |
	  //| || |  | || |
	  //|_/\_|  |_/\_|
	  //<_  _>  <_  _>
	    //\/      \/
	    
	    //| || |  | || |  | || |
	    //| || |  | || |  | || |
	    //|_/\_|  |_/\_|  |_/\_|
	    //<_  _>  <_  _>  <_  _>
	     // \/      \/      \/
		



	




}
