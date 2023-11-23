/*PokemonGame.java 
 * April 14, 2022
 * ICS4U1 Arjun Sharma
 */
package Pokemon;
import java.util.Scanner;  
public class PokemonGame {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);// for user input
	
		//Adding 3 poemon and there stats
		Pokemon p1 = new Pokemon("Pikachu", "Electric", 200, 200);
		Pokemon p2 = new Pokemon("Charmander", "Fire", 250, 250);
		Pokemon p3 = new Pokemon("Diglett", "Ground", 220, 220);
	
		//player object made to hold the 3 pokemon
		Player player1 = new Player(p1, p2, p3);
		
		//repeated for player 2
		Pokemon p4 = new Pokemon("Bulbasaur", "Grass", 200, 200);
		Pokemon p5 = new Pokemon("Koffing", "Poison", 250, 250);
		Pokemon p6 = new Pokemon("Squirtle", "Water", 220, 220);
		
		Player player2 = new Player(p4, p5, p6);
		//setting pokemon to current for each stage
		Pokemon currentPKM = new Pokemon();
		Pokemon currentEnemy = new Pokemon();
		
		//variables used in game
		int round =1;
		int stage = 1;
		int botRecovery=1;
	
		String nextMove = "";
		String options ="\n - Fast Attack (A) - Charged Attack (B) -";
		String select = " - Pikachu (A) - Charmander (B) - Diglett (C)";
		int once = 0;
		int once2 =0;
		boolean battle = false;
		int a=0;
		int b =0;
		int c= 0;
		boolean cd1=false;
		boolean cd2=false;
		
		//Rules
		pokemonTitle();
		System.out.println("\n- GAME RULES AND GAMEPLAY -");
		System.out.println("\nIn this pokemon Tournament, your 3 pokemon will face off against the computers pokemon 1 by 1. Giving 3 stages, with each pokemon.");
		System.out.println("Pokemon have Health Points (HP) and Energy (%). HP will vary between pokemon, but all will have 100% At the start of each stage.");
		System.out.println("The stage ends if any pokemon reaches 0 HP or 0% Energy.");
		System.out.println("In each stage, multiple rounds will continue, where you can help guide your pokemon with the options provided.");
		System.out.println("Only one can be chosen per round - The options consist of...");
		System.out.println("\nFast Attack: Damage between 1-50 - Uses 10% energy - Will always be an option to use.");
		System.out.println("Charged Attack: Damage between 50-100 - Uses 20% Energy - Can only be used twice per pokemon - ");
		System.out.println("Will not be an option once both charges are used or once energy is below 20% (Will not come back even if energy boost is used).");
		System.out.println("Heal: Heals pokemon with 50 HP - One time use per pokemon.");
		System.out.println("Energy Boost: Provide additional 20% energy - One time use per pokemon.");
		System.out.println("Dodge: A dodge can happen 1/6 chances automatically - Pokemon can only dodge Fast Attacks");
		System.out.println("The computer pokemon can also recover in battle, with heal, energy boost and will do a charge attack.");
		System.out.println("Each round you will be able to choose between your 3 pokemon - be wary of which TYPE of pokemon you battle against and its HP.");
		System.out.println("Which every players Pokemon win the most stages, wins the Tournament. GOOD LUCK AND HAVE FUN. (TRY TO GET STAR MEDALS as a small reward)");
		
		
		//loops full game until finished(3 stages only)
		while(stage!=4) {
			//prints before every new stage
			System.out.println("\n - STAR MEDALS -\n");
			player1.stageWins();
			//allows user to see enemy pokemons and their own
			System.out.println("\n - Opponents Pokemon in order for each stage - ");
			System.out.println("\n" + player2 + "\n");
			System.out.println(" - Your active Pokemon - (Choose wisely)");
			System.out.println("\n" + player1 + "\n");
			System.out.println(" - Choose your pokemon - \n" );
			System.out.println(select);
			String pokemon = in.next().toUpperCase();
			//making sure correct inputs are given
			while(!(pokemon.equals("A") && a==0 || pokemon.equals("B") && b==0 ||pokemon.equals("C")&& c==0)){
				
				System.out.println("Please enter one of the options above.");
				pokemon = in.next().toUpperCase();
			}
			
		
			//choosing pokemon
			//sets user pokemon
			if(pokemon.equals("A")) {
				//takes out option
				select = select.replaceAll(" - Pikachu \\(A\\)", "");
				a=1;
				currentPKM = p1;
			}//same as above
			if(pokemon.equals("B")) {
				select = select.replaceAll(" - Charmander \\(B\\)", "");
				currentPKM = p2;
				b=1;
			}
			//same as above
			if(pokemon.equals("C")) {
				select = select.replaceAll(" - Diglett \\(C\\)", "");
				currentPKM = p3;
				c=1;
			}
			//determines opponent pokemon with stage num
			if(stage==1) {
				currentEnemy = p4;
			}
			if(stage==2) {
				currentEnemy = p5;
			}
			if(stage==3) {
				currentEnemy = p6;
			}
			
			
			//prints everytime before stage
			System.out.println("\nWelcome to Pokemon GO Battle!");
			System.out.println("Stage " + stage);
			System.out.println("BEGIN BATTLE");		
			System.out.println(currentPKM.getName() + " VS " + currentEnemy.getName());
			//runs while loop when battle is true (turns false when stage ends)
			battle = true;
			while(battle) {
			
				//prints round number and stats of pokemon after each round + along with available options for next moves
				System.out.println("\nRound " + round);
				System.out.println(currentPKM);
				System.out.println(currentEnemy);
				System.out.println(options);

				
				nextMove = in.next().toUpperCase();
				
				//making sure correct inputs are being given
				while(!(nextMove.equals("A")||nextMove.equals("B")||
						currentPKM.getHeal()==1 && currentPKM.getHealthLeft()<=currentPKM.getHealthTotal()-50&&nextMove.equals("H")
						|| currentPKM.getEnergyBoost()==1 && currentPKM.getEnergy()<=80&&nextMove.equals("E"))
						||nextMove.equals("B") && currentPKM.getCharges()<1){
					System.out.println("Please enter one of the options above.");
					nextMove = in.next().toUpperCase();					
				}
			
				//gives the 1/6 chance that pokemon dodges
				currentPKM.dodge();
				currentEnemy.dodge();
				

				
				//option A will do fast attack
				if(nextMove.equals("A")) {
					//if the dodge occurs - no damage taken
					if(currentEnemy.getDodge()==true) {
						System.out.println(currentEnemy.getName() + " dodged the attack.");
						cd2=true;
					}
					//fast attack is done on opposing pokemon - also showing the amount done
					else {
						
					//type based damage	(increase decrease by 20)
					//Electric vs bot
					if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Water")) {	
					currentEnemy.damageTaken(currentPKM.fastAttack()+20);
					}
					else if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Grass")) {
						currentEnemy.damageTaken(currentPKM.fastAttack()-20);
					}
					else if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Poison")) {
						currentEnemy.damageTaken(currentPKM.fastAttack());
					}
					
					//Fire vs bot
					
					if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Water")) {	
						currentEnemy.damageTaken(currentPKM.fastAttack()-20);
						}
					else if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Grass")) {
						currentEnemy.damageTaken(currentPKM.fastAttack()+20);
					}
					else if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Poison")) {
						currentEnemy.damageTaken(currentPKM.fastAttack());
					}
					
					//Ground vs bot
					if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Water")) {	
						currentEnemy.damageTaken(currentPKM.fastAttack()-20);
						}
					else if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Grass")) {
						currentEnemy.damageTaken(currentPKM.fastAttack()-20);
					}
					else if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Poison")) {
						currentEnemy.damageTaken(currentPKM.fastAttack()+20);
					}
					
					
					//display damage
					System.out.println(currentPKM.getName()+ " did " + currentEnemy.getDamageTaken() + " damage.");
				
					}
				
				}
				//condition to use charge attacks
				else if(currentPKM.getCharges()==2 || currentPKM.getCharges()==1 && currentPKM.getEnergy()>=20) {
					//using charge attacks as option B						
					if(nextMove.equals("B")) {
						//does actual damage and displays amount
						//type based damage	
						//Electric vs bot
						if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Water")) {	
						currentEnemy.damageTaken(currentPKM.chargeAttack()+20);
						}
						else if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Grass")) {
							currentEnemy.damageTaken(currentPKM.chargeAttack()-20);
						}
						else if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Poison")) {
							currentEnemy.damageTaken(currentPKM.chargeAttack());
						}
						
						//Fire vs bot
						
						if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Water")) {	
							currentEnemy.damageTaken(currentPKM.chargeAttack()-20);
							}
						else if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Grass")) {
							currentEnemy.damageTaken(currentPKM.chargeAttack()+20);
						}
						else if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Poison")) {
							currentEnemy.damageTaken(currentPKM.chargeAttack());
						}
						
						//Ground vs bot
						if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Water")) {	
							currentEnemy.damageTaken(currentPKM.chargeAttack()-20);
							}
						else if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Grass")) {
							currentEnemy.damageTaken(currentPKM.chargeAttack()-20);
						}
						else if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Poison")) {
							currentEnemy.damageTaken(currentPKM.chargeAttack()+20);
						}
						//prints damage taken
						System.out.println(currentPKM.getName()+ " did " + currentEnemy.getDamageTaken() + " damage.");
						//resets next move
					

						//will remove charge attack option if conditions met
						if(currentPKM.getCharges()==0||currentPKM.getEnergy()<20) {
							options = options.replaceAll(" Charged Attack \\(B\\) -", "");
						}
							

						}
				
					
				}
				//recovery for the computer pokemon, allowing it to heal, energy bost and charge attack when  hp is 50
				if(botRecovery==1 &&currentEnemy.getHealthLeft()<=50) {
					currentEnemy.heal();
					currentEnemy.energyBoost();
					
					//type based damage for bot
					if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Water")) {	
						currentPKM.damageTaken(currentEnemy.chargeAttack()-20);
						}
						else if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Grass")) {
							currentPKM.damageTaken(currentEnemy.chargeAttack()+20);
						}
						else if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Poison")) {
							currentPKM.damageTaken(currentEnemy.chargeAttack());
						}
						
					
						
						if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Water")) {	
							currentPKM.damageTaken(currentEnemy.chargeAttack()+20);
							}
						else if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Grass")) {
							currentPKM.damageTaken(currentEnemy.chargeAttack()-20);
						}
						else if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Poison")) {
							currentPKM.damageTaken(currentEnemy.chargeAttack());
						}
						
					
						if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Water")) {	
							currentPKM.damageTaken(currentEnemy.chargeAttack()+20);
							}
						else if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Grass")) {
							currentPKM.damageTaken(currentEnemy.chargeAttack()+20);
						}
						else if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Poison")) {
							currentPKM.damageTaken(currentEnemy.chargeAttack()-20);
						}
					
	
					System.out.println(currentEnemy.getName()+ " did " + currentPKM.getDamageTaken() + " damage.");
					System.out.println("Your opponents Pokemon healed and also gained an energy boost.");

					//can only happen once
					botRecovery=0;
					
				}
				//allows possibility for user to dodge
				else {
					if(currentPKM.getDodge() == true) {
						System.out.println(currentPKM.getName() + " dodged the attack.");
						cd1=true;
						//easter egg
						if(cd1==true&&cd2==true) {
							System.out.println("Easter Egg: This is a very rare case that both pokemon dodged (1/36 chance!!) - hi mrs hwang");
						}
					}
					else {
						//damage done to user pokemon
						
						//type based damage for bot
						if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Water")) {	
							currentPKM.damageTaken(currentEnemy.fastAttack()-20);
							}
							else if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Grass")) {
								currentPKM.damageTaken(currentEnemy.fastAttack()+20);
							}
							else if(currentPKM.getType().equals("Electric")&&currentEnemy.getType().equals("Poison")) {
								currentPKM.damageTaken(currentEnemy.fastAttack());
							}
							
						
							
							if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Water")) {	
								currentPKM.damageTaken(currentEnemy.fastAttack()+20);
								}
							else if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Grass")) {
								currentPKM.damageTaken(currentEnemy.fastAttack()-20);
							}
							else if(currentPKM.getType().equals("Fire")&&currentEnemy.getType().equals("Poison")) {
								currentPKM.damageTaken(currentEnemy.fastAttack());
							}
							
						
							if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Water")) {	
								currentPKM.damageTaken(currentEnemy.fastAttack()+20);
								}
							else if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Grass")) {
								currentPKM.damageTaken(currentEnemy.fastAttack()+20);
							}
							else if(currentPKM.getType().equals("Ground")&&currentEnemy.getType().equals("Poison")) {
								currentPKM.damageTaken(currentEnemy.fastAttack()-20);
							}
						
						
						
					System.out.println(currentEnemy.getName()+ " did " + currentPKM.getDamageTaken() + " damage.");
					}
				}
				
			
				//add healing, energy boosts
					//pokemon must be total-50 or less and have an available heal
					if(currentPKM.getHeal()==1 && currentPKM.getHealthLeft()<=currentPKM.getHealthTotal()-50) {
						if(once==0) {
							//adds the option
						options += " Heal Pokemon (H) - ";
						once =1;
						}		
						//commits the heal
						if(nextMove.equals("H")) {
							//removes option since its a one time use
							options = options.replaceAll(" Heal Pokemon \\(H\\) - ", "");
							currentPKM.heal();
							System.out.println("Pokemon gained 50HP, zero heals remaining.");
						

						}
					}
					//same concept as heals - must have 80% or less energy to use
                    if(currentPKM.getEnergyBoost()==1 && currentPKM.getEnergy()<=80) {
                        if(once2==0) {
                            options += " Give Pokemon Energy Boost (E) - ";
                            once2 =1;
                        }
                        if(nextMove.equals("E")) {
                            options = options.replaceAll(" Give Pokemon Energy Boost \\(E\\) - ", "");
                            currentPKM.energyBoost();
                            System.out.println("Pokemon gained 20% Energy, zero energy boost remaining.");
                            
                        }
                    }
                    //round is complete, adds number of rounds
                    //resetting checker for double dodge
                    cd2=false;
                    cd1=false;
                    round++;
                    nextMove="";
                    //continues until either energy is 0 or HP is 0 for either user or computer pokemon to determine a winner/loser/tie
                    if((currentPKM.getEnergy()==0||currentPKM.getHealthLeft()==0)&&(currentEnemy.getEnergy()==0||currentEnemy.getHealthLeft()==0)) {
                    	System.out.println("Both pokemon fought well in battle, but both have fallen in the end. This is a tie.\n");
                    	//adds to ties
                    	player1.tie();
                    	player2.tie();
                    
						//adds to the next stage - which will break out of this loop and start second loop for stage 2 and so on
						stage++;
						//resets rounds
						round=1;
						//breaks loop
						battle = false;
						//resets options 
						options = "\n - Fast Attack (A) - Charged Attack (B) -";
						//reset bots and ability use game mechanics
						botRecovery =1;
						 once = 0;
						 once2 =0;
						 //resets dodge
						 currentPKM.setDodge(false);
						 currentEnemy.setDodge(false);
                    }
                    //loss
                    else if(currentPKM.getEnergy()==0||currentPKM.getHealthLeft()==0) {
						System.out.println("\nYou Lost this battle.");
						//ascii art
						System.out.println("\n");
						if(stage==1) {
							bulbasaur();
						}
						if(stage==2) {
							koffing();
						}
						if(stage==3) {
							squirtle();
						}
						//adds to losses and wins
						player1.loss();
						player2.wins();
						//adds to the next stage - which will break out of this loop and start second loop for stage 2 and so on
						stage++;
						//resets rounds
						round=1;
						//breaks loop
						battle = false;
						//resets options 
						options = "\n - Fast Attack (A) - Charged Attack (B) -";
						//reset bots and ability use game mechanics
						botRecovery =1;
						 once = 0;
						 once2 =0;
						 //resets dodge
						 currentPKM.setDodge(false);
						 currentEnemy.setDodge(false);
					
						
					}
					//if opposing pokemon energy is 0 or hp is 0 - same concept as above
                    //win
                    else if(currentEnemy.getEnergy()==0||currentEnemy.getHealthLeft()==0) {
						System.out.println("\nYou Won this battle!");
						System.out.println("\n");
						//ascii art
						if(pokemon.equals("A")) {
							pikachu();
						}
						if(pokemon.equals("B")) {
							charmander();
						}
						if(pokemon.equals("C")) {
							diglett();
						}
						
						//adds to losses and wins
						player2.loss();
						player1.wins();
						//adds to the next stage - which will break out of this loop and start second loop for stage 2 and so on
						stage++;
						//resets rounds
						round=1;
						//breaks loop
						battle = false;
						//resets options 
						options = "\n - Fast Attack (A) - Charged Attack (B) -";
						//reset bots and ability use game mechanics
						botRecovery =1;
						 once = 0;
						 once2 =0;
						 //resets dodge
						 currentPKM.setDodge(false);
						 currentEnemy.setDodge(false);
				
					}
					
					
					
				}
	
					


					
				
				
				
			}
		
		//final statement to give results of the tournament
		//if user losses
		System.out.println("The tournament is now over, each pokemon and trainer put up a good battle.");
		if(player1.getTies()==player2.getTies()&&player1.getTies()!=0) {
			System.out.println("Pokemon tournaments are always unpredicatable, though this doesn't happen often, you and you opponent are equally matched.");
			System.out.println("Well Played. Come back again, to take the trophy next time.");
			System.out.println("                       \\ | /\r\n"
					+ "                      -- * --\r\n"
					+ "                       / | \\\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "        /')    ./')             ('\\.    ('\\\r\n"
					+ "      /' /.--''./'')           (''\\.''--.\\ '\\\r\n"
					+ " :--''  ;    ''./'')           (''\\.''    ;  ''--:\r\n"
					+ " :     '     ''./')             ('\\.''     '     :\r\n"
					+ " :           ''./'               '\\.''           :\r\n"
					+ " :--''-..--''''                     ''''--..-''--:");
			
		
		}
		
		//if user wins
		else if(player1.getWins()>player2.getWins()) {
			System.out.println("CONGRATUALTIONS! You and your pokemon won the tournament!");
			
			System.out.println("             ___________\r\n"
					+ "            '._==_==_=_.'\r\n"
					+ "            .-\\:      /-.\r\n"
					+ "           | (|:.     |) |\r\n"
					+ "            '-|:.     |-'\r\n"
					+ "              \\::.    /\r\n"
					+ "               '::. .'\r\n"
					+ "                 ) (\r\n"
					+ "               _.' '._\r\n"
					+ "              `\"\"\"\"\"\"\"`\n");
			System.out.println("\n - STAR MEDALS - ");
			//medals
			player1.stageWins();
			//outputs stats
			System.out.println("Wins: " + player1.getWins());
			System.out.println("Losses: " + player1.getLoss());
			System.out.println("\nComputer Wins: " + player2.getWins());
			System.out.println("Computer Losses: " + player2.getLoss());
			System.out.println("\nTies: " + player1.getTies());
			System.out.println("Your journey to become the best pokemon trainer awaits. Come back again if you want to join another tournament. But for now, Good bye Pokemon Trainer!");

			
		}
		//if user looses 
		else {
			System.out.println("Unfortunetly the opposing Pokemon trainer won the tournament.");
			System.out.println("Well Played. Come back again, to take the trophy next time.\n");
			System.out.println("                                               _\r\n"
					+ "                 ___                          (_)\r\n"
					+ "               _/XXX\\\r\n"
					+ "_             /XXXXXX\\_                                    __\r\n"
					+ "X\\__    __   /X XXXX XX\\                          _       /XX\\__      ___\r\n"
					+ "    \\__/  \\_/__       \\ \\                       _/X\\__   /XX XXX\\____/XXX\\\r\n"
					+ "  \\  ___   \\/  \\_      \\ \\               __   _/      \\_/  _/  -   __  -  \\__/\r\n"
					+ " ___/   \\__/   \\ \\__     \\\\__           /  \\_//  _ _ \\  \\     __  /  \\____//\r\n"
					+ "/  __    \\  /     \\ \\_   _//_\\___     _/    //           \\___/  \\/     __/\r\n"
					+ "__/_______\\________\\__\\_/________\\_ _/_____/_____________/_______\\____/_______\r\n"
					+ "                                  /|\\\r\n"
					+ "                                 / | \\\r\n"
					+ "                                /  |  \\\r\n"
					+ "                               /   |   \\\r\n"
					+ "                              /    |    \\\r\n"
					+ "                             /     |     \\\r\n"
					+ "                            /      |      \\\r\n"
					+ "                           /       |       \\\r\n"
					+ "                          /        |        \\\r\n"
					+ "                         /         |         \\");
			
			
			System.out.println("\n - STAR MEDALS - ");
			//medals
			player1.stageWins();
			//stats
			System.out.println("\nWins: " + player1.getWins());
			System.out.println("Losses: " + player1.getLoss());
			System.out.println("\nComputer Wins: " + player2.getWins());
			System.out.println("Computer Losses: " + player2.getLoss());
			System.out.println("\nTies: " + player1.getTies());

			System.out.println("You have gained lots of experience throughout these battles. Come again another time to get stronger, in your journey to become the best Pokemon Trainer!");
		}
		
		
		
			}
			
			
		//ascii art in methods below	
	public static void pokemonTitle() {
		System.out.println( "                                  ,'\\\r\n"
				+ "    _.----.        ____         ,'  _\\   ___    ___     ____\r\n"
				+ "_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\r\n"
				+ "\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\r\n"
				+ " \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |\r\n"
				+ "   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\r\n"
				+ "    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |\r\n"
				+ "     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\r\n"
				+ "      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\r\n"
				+ "       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\r\n"
				+ "        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\r\n"
				+ "                                `'                            '-._|");
	}
	
	public static void pikachu() {
		System.out.println( "⠸⣷⣦⠤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣤⠀⠀⠀\r\n"
				+ "⠀⠙⣿⡄⠈⠑⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠔⠊⠉⣿⡿⠁⠀⠀⠀\r\n"
				+ "⠀⠀⠈⠣⡀⠀⠀⠑⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⠁⠀⠀⣰⠟⠀⠀⠀⣀⣀\r\n"
				+ "⠀⠀⠀⠀⠈⠢⣄⠀⡈⠒⠊⠉⠁⠀⠈⠉⠑⠚⠀⠀⣀⠔⢊⣠⠤⠒⠊⠉⠀⡜\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⡽⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠩⡔⠊⠁⠀⠀⠀⠀⠀⠀⠇\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⡇⢠⡤⢄⠀⠀⠀⠀⠀⡠⢤⣄⠀⡇⠀⠀⠀⠀⠀⠀⠀⢰⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⢀⠇⠹⠿⠟⠀⠀⠤⠀⠀⠻⠿⠟⠀⣇⠀⠀⡀⠠⠄⠒⠊⠁⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⢸⣿⣿⡆⠀⠰⠤⠖⠦⠴⠀⢀⣶⣿⣿⠀⠙⢄⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⢻⣿⠃⠀⠀⠀⠀⠀⠀⠀⠈⠿⡿⠛⢄⠀⠀⠱⣄⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⢸⠈⠓⠦⠀⣀⣀⣀⠀⡠⠴⠊⠹⡞⣁⠤⠒⠉⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⣠⠃⠀⠀⠀⠀⡌⠉⠉⡤⠀⠀⠀⠀⢻⠿⠆⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠰⠁⡀⠀⠀⠀⠀⢸⠀⢰⠃⠀⠀⠀⢠⠀⢣⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⢶⣗⠧⡀⢳⠀⠀⠀⠀⢸⣀⣸⠀⠀⠀⢀⡜⠀⣸⢤⣶⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠈⠻⣿⣦⣈⣧⡀⠀⠀⢸⣿⣿⠀⠀⢀⣼⡀⣨⣿⡿⠁⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠈⠻⠿⠿⠓⠄⠤⠘⠉⠙⠤⢀⠾⠿⣿⠟⠋");
	}
	
	public static void charmander() {
		System.out.println( "              _.--\"\"`-..\r\n"
				+ "            ,'          `.\r\n"
				+ "          ,'          __  `.\r\n"
				+ "         /|          \" __   \\\r\n"
				+ "        , |           / |.   .\r\n"
				+ "        |,'          !_.'|   |\r\n"
				+ "      ,'             '   |   |\r\n"
				+ "     /              |`--'|   |\r\n"
				+ "    |                `---'   |\r\n"
				+ "     .   ,                   |                       ,\".\r\n"
				+ "      ._     '           _'  |                    , ' \\ `\r\n"
				+ "  `.. `.`-...___,...---\"\"    |       __,.        ,`\"   L,|\r\n"
				+ "  |, `- .`._        _,-,.'   .  __.-'-. /        .   ,    \\\r\n"
				+ "-:..     `. `-..--_.,.<       `\"      / `.        `-/ |   .\r\n"
				+ "  `,         \"\"\"\"'     `.              ,'         |   |  ',,\r\n"
				+ "    `.      '            '            /          '    |'. |/\r\n"
				+ "      `.   |              \\       _,-'           |       ''\r\n"
				+ "        `._'               \\   '\"\\                .      |\r\n"
				+ "           |                '     \\                `._  ,'\r\n"
				+ "           |                 '     \\                 .'|\r\n"
				+ "           |                 .      \\                | |\r\n"
				+ "           |                 |       L              ,' |\r\n"
				+ "           `                 |       |             /   '\r\n"
				+ "            \\                |       |           ,'   /\r\n"
				+ "          ,' \\               |  _.._ ,-..___,..-'    ,'\r\n"
				+ "         /     .             .      `!             ,j'\r\n"
				+ "        /       `.          /        .           .'/\r\n"
				+ "       .          `.       /         |        _.'.'\r\n"
				+ "        `.          7`'---'          |------\"'_.'\r\n"
				+ "       _,.`,_     _'                ,''-----\"'\r\n"
				+ "   _,-_    '       `.     .'      ,\\\r\n"
				+ "   -\" /`.         _,'     | _  _  _.|\r\n"
				+ "    \"\"--'---\"\"\"\"\"'        `' '! |! /\r\n"
				+ "                            `\" \" -' ");
	}
	
	public static void squirtle() {
		System.out.println( "               _,........__\r\n"
				+ "            ,-'            \"`-.\r\n"
				+ "          ,'                   `-.\r\n"
				+ "        ,'                        \\\r\n"
				+ "      ,'                           .\r\n"
				+ "      .'\\               ,\"\".       `\r\n"
				+ "     ._.'|             / |  `       \\\r\n"
				+ "     |   |            `-.'  ||       `.\r\n"
				+ "     |   |            '-._,'||       | \\\r\n"
				+ "     .`.,'             `..,'.'       , |`-.\r\n"
				+ "     l                       .'`.  _/  |   `.\r\n"
				+ "     `-.._'-   ,          _ _'   -\" \\  .     `\r\n"
				+ "`.\"\"\"\"\"'-.`-...,---------','         `. `....__.\r\n"
				+ ".'        `\"-..___      __,'\\          \\  \\     \\\r\n"
				+ "\\_ .          |   `\"\"\"\"'    `.           . \\     \\\r\n"
				+ "  `.          |              `.          |  .     L\r\n"
				+ "    `.        |`--...________.'.        j   |     |\r\n"
				+ "      `._    .'      |          `.     .|   ,     |\r\n"
				+ "         `--,\\       .            `7\"\"' |  ,      |\r\n"
				+ "            ` `      `            /     |  |      |    _,-'\"\"\"`-.\r\n"
				+ "             \\ `.     .          /      |  '      |  ,'          `.\r\n"
				+ "              \\  v.__  .        '       .   \\    /| /              \\\r\n"
				+ "               \\/    `\"\"\\\"\"\"\"\"\"\"`.       \\   \\  /.''                |\r\n"
				+ "                `        .        `._ ___,j.  `/ .-       ,---.     |\r\n"
				+ "                ,`-.      \\         .\"     `.  |/        j     `    |\r\n"
				+ "               /    `.     \\       /         \\ /         |     /    j\r\n"
				+ "              |       `-.   7-.._ .          |\"          '         /\r\n"
				+ "              |          `./_    `|          |            .     _,'\r\n"
				+ "              `.           / `----|          |-............`---'\r\n"
				+ "                \\          \\      |          |\r\n"
				+ "               ,'           )     `.         |\r\n"
				+ "                7____,,..--'      /          |\r\n"
				+ "                                  `---.__,--.");
	}
	
	
	public static void bulbasaur () {
		System.out.println( "             `;,;.;,;.;.'\r\n"
				+ "              ..:;:;::;: \r\n"
				+ "        ..--''' '' ' ' '''--.  \r\n"
				+ "      /' .   .'        '.   .`\\\r\n"
				+ "     | /    /            \\   '.|\r\n"
				+ "     | |   :             :    :|\r\n"
				+ "   .'| |   :             :    :|\r\n"
				+ " ,: /\\ \\.._\\ __..===..__/_../ /`.\r\n"
				+ "|'' |  :.|  `'          `'  |.'  ::.\r\n"
				+ "|   |  ''|    :'';          | ,  `''\\\r\n"
				+ "|.:  \\/  | /'-.`'   ':'.-'\\ |  \\,   |\r\n"
				+ "| '  /  /  | / |...   | \\ |  |  |';'|\r\n"
				+ " \\ _ |:.|  |_\\_|`.'   |_/_|  |.:| _ |\r\n"
				+ "/,.,.|' \\__       . .      __/ '|.,.,\\\r\n"
				+ "     | ':`.`----._____.---'.'   |\r\n"
				+ "      \\   `:\"\"\"-------'\"\"' |   |\r\n"
				+ "       ',-,-',             .'-=,=,");
	}
	
	public static void koffing() {
		System.out.println( "                               ,----.\r\n"
				+ "                              '      |\r\n"
				+ "                             /       '\r\n"
				+ "                       __,..'         \"-._        _\r\n"
				+ "                  _.-\"\"                   `-.   ,\" `\".\r\n"
				+ "         ,-._  _.'                           `\"'      '\r\n"
				+ "       ,'    `\"                                       |\r\n"
				+ "      .                                               .\r\n"
				+ "       `.          _.--..               ____          '\r\n"
				+ "       /         ,'    . `           ,\"' .  `.         `.\r\n"
				+ "      /         .         |         /         \\          \\\r\n"
				+ "     /          `------...'        ._____      .          \\\r\n"
				+ "    .                                    `'\"\"\"'            \\\r\n"
				+ "    '                    ________                           .\r\n"
				+ "   j           `.\"\"/'\"\"\"`        '\"\"\"'\"'--....,-            |\r\n"
				+ "   |             `/.                      ,\\ /              `.\r\n"
				+ "   |                `-._               _.'  '                 `-.\r\n"
				+ "   |                    `\"-----------\"'                         |\r\n"
				+ " .\"                         ____                                |\r\n"
				+ "|                      ,-\"\"'    `\".                            ,'\r\n"
				+ "|                     .   .----.   `.                        .\"\r\n"
				+ "`.._                  |  '.____,'   |                        '\r\n"
				+ "    |             ,\". `.           ,' _                     /\r\n"
				+ "    '            '   `._`.'._\".__,' .' .                   /\r\n"
				+ "     .            `'-._ `._     _.-'  _.'                 /\r\n"
				+ "      `.               `.  `--'\" _,.-'                    `\r\n"
				+ "        .               ,'     .\"                          '\r\n"
				+ "         '        .-..-' _,.--._`\"-..,-.                 ,'\r\n"
				+ "        /         \\    ,'       `-.    |           .-'\"-\"\r\n"
				+ "        \\          `-.'            `..'         _,'\r\n"
				+ "         `.,.-\"`._                           ,-'\r\n"
				+ "                  `\"-.                       |\r\n"
				+ "                      \\       ,..----.     _.'\r\n"
				+ "                       `\"\"---\"        `..-\" ");
	}
	
	
	public static void diglett ()
	{
		System.out.println("    ______");
	    System.out.println("   /      \\");
	    System.out.println("  /        \\");
	    System.out.println("  |  0   0  |");
	    System.out.println("  |   ( )   |");
	    System.out.println("  |         |");
	    System.out.println("  |         |");
	    System.out.println(" (  )O   o( ())");
	    System.out.println(" (   )o(  ) o( )");
	    System.out.println("Credits: Mrs. Hwang");
	}
	
			
	
	
	}


