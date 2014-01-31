import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @date 7/3/12
 * @author Graham Wright
 * War.java
 * Defines the execution of the card game War
 * 
 * Rules/Exceptions/Terminology
 * 
 * Battle: Players place 1 card each into the battle pot
 * War: 2 or more players tie in a Battle and go into a faceoff
 * Faceoff: Each player burns 2 cards then plays a card face up. If another
 *          tie happens, the process repeats. If a player has less then 3 cards
 *          into a war/faceoff, they will lose after burning their cards.
 * 
 * 
 */
public class War {

    private Deck deck;
    private Player p1;
    private Player p2;
    private ArrayList<Player> players;
    private int numPlayers = 2;
    private Card[] battle;
    private boolean isOver;
    private ArrayList<Card> tieDiscard;
    private ArrayList<Card> tieDecision;
    private ArrayList<Integer> winners;
    private int battleCount;
    private int faceOffCount;
    private int doubleWars;
    private int tripleWars;
    private int quadrupleWars;
    private int quintupleWars;
    private int sextupleWars;
    private int septupleWars;
    private int octupleWars;
    private int p1warWins;
    private int p2warWins;
    private int under208;
    private int over208;
    private boolean finiteGame;
    private boolean infiniteGame;
    private int roundCount;
    
    private int startingHandFlag;
    private int returningCardFlag;
    
    /**
     * Constructs a game of War
     * 
     * @param player1 The first player
     * @param player2 The second player
     */
    public War(Player player1, Player player2) {
        winners = new ArrayList<Integer>();
        players = new ArrayList<Player>();
        p1 = player1;
        players.add(p1);
        p2 = player2;
        players.add(p2);
      
        deck = new Deck();
        battle = new Card[numPlayers];
        tieDiscard = new ArrayList<Card>(2 * numPlayers);
        tieDecision = new ArrayList<Card>(numPlayers);
        isOver = false;
        infiniteGame = false;
        finiteGame = true;
 
    }
    
    /**
     * Sets up the game of War, deals cards
     * Various if statements check for what
     * sorting, if any, to do at the start of the game
     * to player 1
     * Flags
     * 0 - Don't sort
     * 1 - Sort high to low
     * 2 - Sort high to low
     * 3 - Sort alternating high to low bottom
     * 4 - Sort alternating low to high bottom
     * 5 - Sort alternating high to low top
     * 6 - Sort alternating low to high top
     */
    public void setUpWar() {

        deck.dealCards(players);
        if (startingHandFlag == 1) {
        	players.get(0).sortCardsLtoH();
        } else if (startingHandFlag == 2) {
        	players.get(0).sortCardsHtoL();
        } else if (startingHandFlag == 3) {
        	players.get(0).sortCardsAltHLBottom();
        } else if (startingHandFlag == 4) {
        	players.get(0).sortCardsAltLHBottom();
        } else if (startingHandFlag == 5) {
        	players.get(0).sortCardsAltHLTop();
        } else if (startingHandFlag == 6) {
        	players.get(0).sortCardsAltLHTop();
        } 
        for (int i = 0; i < players.size(); i++) {
            players.get(i).updateHandStrength();
        }
    }
    
    /**
     * Takes the players' cards on each turn
     */
    public void collectCards(){
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).length() == 0) {
                players.remove(i);
                i--;
                if (players.size() == 1) {
                    isOver = true;
                    return;
                }
            }           
        }
        
        for (int i = 0; i < players.size(); i++) {
            battle[players.get(i).getPlayerNumber() - 1] = players.get(i).playCard();
        }
    }
    
    /**
     * Returns the index in players of the winning player of a battle in war
     * If the battle is tied the players that tied face off
     * 
     * @return int the Index of the player
     */
    public int declareBattleWinner() {
        if (isOver) return -1;
        winners.clear();
        
        Card highest = battle[0];
        if (highest == null) {
            for (int i = 0; i < battle.length; i++) {
                if (battle[i] != null) {
                    highest = battle[i];
                    i = battle.length;
                }
            }
        }
            
        for (int i = 1; i < battle.length; i++) {
            if (battle[i] != null) {
                Card current = battle[i];
                if (current.getValue() > highest.getValue()) highest = current;   
            }
                    
        }
        for (int i = 0; i < battle.length; i++) {
            if (battle[i] != null) {
                Card current = battle[i];
                if (highest.getValue() == current.getValue()) {
                    for (int j = 0; j < players.size(); j++) {
                        if (i + 1 == players.get(j).getPlayerNumber()) {
                            winners.add(players.get(j).getPlayerNumber());
                    	}
                    }
                }
            }
            
        }
        if (winners.isEmpty()) {
            for (int j = 0; j < battle.length; j++) {
                if (battle[j].getValue() == highest.getValue()) return players.get(j).getPlayerNumber();
            }
        }
        
        if (winners.size() > 1) faceOffCount++;
        else battleCount++;
        roundCount++;
        int count = 0;
        while (winners.size() > 1) {
            count++;
            if (count == 2) doubleWars++;
            else if (count == 3) tripleWars++;
            else if (count == 4) quadrupleWars++;
            else if (count == 5) quintupleWars++;
            else if (count == 6) sextupleWars++;
            else if (count == 7) septupleWars++;
            else if (count == 8) octupleWars++;
           
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).length() == 0) {
                    int temp = players.get(i).getPlayerNumber();
                    for (int j = 0; j < winners.size(); j++) {
                        if (temp == winners.get(j)) {
                            winners.remove(j);
                            j--;
                            if (winners.size() == 1) {
                                return winners.get(0);
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < winners.size(); i++) {
                for (int j = 0; j < players.size(); j++) {
                    if (winners.get(i) == players.get(j).getPlayerNumber()) {
                        if (players.get(j).length() == 0)  {
                            winners.remove(i);
                            players.remove(j);
                            j--;     //I took this out
                            if (winners.size() == 1) {
                                return winners.get(0);
                            }
                            if (players.size() == 1) {
                                isOver = true;
                                return players.get(0).getPlayerNumber();
                            }
                        }
                        
                        else {
                            players.get(j).burnCards(tieDiscard);
                            if (players.get(j).length() == 0) {
                                players.remove(j);
                                winners.remove(i);

                                
                                if (players.size() == 1) {
                                    isOver = true;
                                    return players.get(0).getPlayerNumber();
                                }
                                if (winners.size() == 1) {
                                    return winners.get(0);
                                }
                                j--;
                                if (i >= winners.size() && j + 1 < players.size()) {
                                    i--;
                                    j = 0;
                                }
                                if (j + 1 == players.size()) i--;
                            }
                            else {
                                players.get(j).playCard(tieDecision);
                            }
                        }
                        
                    }
                }                
            }
               
            highest = tieDecision.get(tieDecision.size() - winners.size());
            for (int j = tieDecision.size() - winners.size(); j < tieDecision.size(); j++) {

                Card current = tieDecision.get(j);
                if (current.getValue() < highest.getValue()) {

                    
                    if (j == tieDecision.size() - winners.size()) {
                        winners.remove(0);
                        j--;
                        j = tieDecision.size();
                    }
                    else if (j == tieDecision.size() - winners.size() + 1) {
                        winners.remove(1);
                        j--;
                        j = tieDecision.size();
                    }
                    else if (j == tieDecision.size() - winners.size() + 2) {
                        winners.remove(2);
                        j--;
                        j = tieDecision.size();
                    }
                    else if (j == tieDecision.size() - winners.size() + 3) {
                        winners.remove(3);
                        j--;
                        j = tieDecision.size();
                    }
                }
                
                else if (current.getValue() > highest.getValue()) {
                    highest = current;

                    if (winners.size() == 2) {
                        if (j % 2 != 0) {
                            winners.remove(0);
                            j = tieDecision.size();
                        }                       
                    }

                }      
                
                if (winners.size() == 1) {
                    return winners.get(0);
                }
                
            }
        }

        for (int i = 0; i < players.size(); i++) {
            if (winners.get(0) == players.get(i).getPlayerNumber()){            
                return winners.get(0);
            }
        }
        return -100;
        
    }
    
    /**
     * Distributes the discarded cards during the turn to the winner
     * Flag Values
     * 0 - Random order
     * 1 - Return low high, high low
     * 2 - Return high low, low high
     * 3 - Return high low, high low
     * 4 - Return low high, low high
     * 5 - Return same order
     * 
     * @param winner The player who won the round
     */
    public void distributeWinnings(Player winner) {
        int pnumber = winner.getPlayerNumber();
        if (tieDiscard.size() > 0) {
            if (pnumber == 1) p1warWins++;
            else if (pnumber == 2) p2warWins++;
        }
        
        if (isOver) return;
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < battle.length; i++) {
            if (battle[i] != null) {
                cards.add(battle[i]);
                battle[i] = null;
            }
        }
        
        while (cards.size() > 0) {
            if (returningCardFlag == 1) {
            	//returning low high, high low
				if (winner.getPlayerNumber() == 1) {
					if (winner.getSwitch() == 0) {
						if (cards.get(1).getValue() > cards.get(0).getValue()) {
				          winner.addCard(cards.get(1));
				          cards.remove(1);
				          winner.addCard(cards.get(0));
				          cards.remove(0);
						}
						else {
							 winner.addCard(cards.get(0));
							 cards.remove(0);
							 winner.addCard(cards.get(0));
							 cards.remove(0);
						}
				        winner.changeSwitch();
					}
				  
					else if (winner.getSwitch() == 1) {
						  if (cards.get(1).getValue() < cards.get(0).getValue()) {
						      winner.addCard(cards.get(1));
						      cards.remove(1);
						      winner.addCard(cards.get(0));
						      cards.remove(0);
						  } else {
						      winner.addCard(cards.get(0));
						      cards.remove(0);
						      winner.addCard(cards.get(0));
						      cards.remove(0);
						  }
				      winner.changeSwitch();
				  	}
				}
            } else if (returningCardFlag == 2) {
            	//returning high low, low high
            	if (winner.getPlayerNumber() == 1) {
            	    if (winner.getSwitch() == 0) {
            	        if (cards.get(1).getValue() > cards.get(0).getValue()) {
            	            winner.addCard(cards.get(1));
            	            cards.remove(1);
            	            winner.addCard(cards.get(0));
            	            cards.remove(0);

            	        }
            	        else {
            	            winner.addCard(cards.get(0));
            	            cards.remove(0);
            	            winner.addCard(cards.get(0));
            	            cards.remove(0);
            	        }
            	        winner.changeSwitch();
            	    }
            	    
            	    else if (winner.getSwitch() == 1) {
            	        if (cards.get(1).getValue() < cards.get(0).getValue()) {
            	            winner.addCard(cards.get(1));
            	            cards.remove(1);
            	            winner.addCard(cards.get(0));
            	            cards.remove(0);
            	        }
            	        
            	        else {
            	            winner.addCard(cards.get(0));
            	            cards.remove(0);
            	            winner.addCard(cards.get(0));
            	            cards.remove(0);
            	        }
            	        winner.changeSwitch();
            	    }
            	}
            } else if (returningCardFlag == 3) {
            	//Returning high low
            	if (winner.getPlayerNumber() == 1) {
            		if (cards.get(1).getValue() > cards.get(0).getValue()) {
            	      winner.addCard(cards.get(1));
            	      cards.remove(1);
            	      winner.addCard(cards.get(0));
            	      cards.remove(0);

            	    }
            	    else {
            	        winner.addCard(cards.get(0));
            	        cards.remove(0);
            	        winner.addCard(cards.get(0));
            	        cards.remove(0);
            	    }
            	    
            	}
            } else if (returningCardFlag == 4) {
            	//Returning low high
            	if (winner.getPlayerNumber() == 1) {
            	    if (cards.get(1).getValue() < cards.get(0).getValue()) {
            	        winner.addCard(cards.get(1));
            	        cards.remove(1);
            	        winner.addCard(cards.get(0));
            	        cards.remove(0);
            	    }
            	    else {
            	        winner.addCard(cards.get(0));
            	        cards.remove(0);
            	        winner.addCard(cards.get(0));
            	        cards.remove(0);
            	    }
            	}
            } else if (returningCardFlag == 5) {
            	//Returning in the same way they were played
            	winner.addCard(cards.get(0));
                cards.remove(0);
            } else {
            	//Returning cards in a random order
            	int temp = (int) (Math.random() * cards.size());
                winner.addCard(cards.get(temp));
                cards.remove(temp);
            }                     
        }
        
        while (!tieDiscard.isEmpty()) {
    		if (returningCardFlag == 5) {
    			//Return card in same order played
    			winner.addCard(tieDiscard.get(0));
                tieDiscard.remove(0);
    		} else {
    			//Return cards in random order
    			int temp = (int) (Math.random() * tieDiscard.size());
                winner.addCard(tieDiscard.get(temp));
                tieDiscard.remove(temp);
    		}
        }
        
        while (!tieDecision.isEmpty()) {
        	if (returningCardFlag == 5) {
        		//Return cards in the order they were played
        		winner.addCard(tieDecision.get(0));
            	tieDecision.remove(0);
        	} else {
        		//Returns card in random order
        		int temp = (int) (Math.random() * tieDecision.size());
                winner.addCard(tieDecision.get(temp));
                tieDecision.remove(temp);
        	} 
        }
    }

    /**
     * Tells if the game is over
     * 
     * @return boolean Whether or not the game is over
     */
    public boolean isOver() {
        return isOver;
    }
 
    /**
     * The play method tells the game to start
     */
    public void play(int startingHandFlag, int returningCardFlag) {
        this.startingHandFlag = startingHandFlag;
        this.returningCardFlag = returningCardFlag;
        setUpWar();
        while (!isOver) {
            
            //Testing for infinite games
            if (players.get(0).getCardsPlayed() + players.get(1).getCardsPlayed() > 50000) {
                finiteGame = false;
                infiniteGame = true;
                return;
            }
            collectCards();
            if (!isOver) {
                int temp = declareBattleWinner();
                for (int i = 0; i < players.size(); i++) {
                    if (temp == players.get(i).getPlayerNumber()) distributeWinnings(players.get(i));
                }
            }
        }
    }
    
    /**
     * Computes the number of cards played in the game
     * 
     * @return int the number of cards played
     */
    public int tallyCardsPlayed() {
        int count = 0;
        count = p1.getCardsPlayed() + p2.getCardsPlayed();
        return count;
    }

    /**
     * Computes statistics for this game
     * 
     * @return ArrayList<Integer> The stats
     */
    public ArrayList<Integer> computeStatistics() {
        ArrayList<Integer> results = new ArrayList<Integer>();
        results.add(tallyCardsPlayed());
        results.add(battleCount);
        results.add(faceOffCount);
        results.add(players.get(0).getPlayerNumber());
        results.add(doubleWars);
        results.add(tripleWars);
        results.add(quadrupleWars);
        results.add(quintupleWars);
        results.add(sextupleWars);
        results.add(septupleWars);
        results.add(octupleWars);
        results.add(players.get(0).getHandStrength());
        results.add(players.get(0).getAceCount());
        if (players.get(0).getPlayerNumber() == 1) {
            results.add(p1warWins);        
        }
        else results.add(p2warWins);
        if (players.get(0).getHandStrength() < 208) under208++;
        if (players.get(0).getHandStrength() > 208) over208++;
        results.add(under208);
        results.add(over208);
        if (finiteGame) results.add(1);
        else results.add(0);
        if (infiniteGame) results.add(1);
        else results.add(0);
        results.add(roundCount);
            
        return results;
    }


    public String toString() {
        String result = "";
        result = result + deck.toString() + "\n";
        for (int i = 0; i < players.size(); i++) {
            result = result + "Player " + players.get(i).getPlayerNumber() + ": " + players.get(i).toString() + "\n";
        }

        result = result + "\nBattle Pot: [";
        if (battle.length == 0) {
            result = result + "]";
        }
        
        for (int i = 0; i < battle.length; i++) {
            if (battle[i] != null) {
                result = result + battle[i].toString();
                if (i + 1 >= battle.length) result = result + "]";
                else result = result + ", ";
            }
            
        }
        
        result = result + "\nTieDiscard Pile: [";
        if (tieDiscard.isEmpty()) {
            result = result + "]";
        }
        
        for (int i = 0; i < tieDiscard.size(); i++) {
            result = result + tieDiscard.get(i).toString();
            if (i + 1 >= tieDiscard.size()) result = result + "]";
            else result = result + ", ";
        }
        
        result = result + "\nTieDecision Pile: [";
        if (tieDecision.isEmpty()) {
            result = result + "]";
        }
        
        for (int i = 0; i < tieDecision.size(); i++) {
            result = result + tieDecision.get(i).toString();
            if (i + 1 >= tieDecision.size()) result = result + "]";
            else result = result + ", ";
        }
        
        result = result + "\nwinners: ";
        
        for (int i = 0; i < winners.size(); i++) {
            if (winners.isEmpty()) {
                break;
            }
                result = result + winners.get(i);
        }

        result = result + "\nStats:\nCards Played: " + tallyCardsPlayed() + "\nRounds Played: " + roundCount + "\nBattles Played: "
        + battleCount + "\nFace off count: " + faceOffCount + "\nWinner: ";

        
        if (isOver) {
            result = result + "Player " + players.get(0).getPlayerNumber();
        }
        
        return result;
    }

}
