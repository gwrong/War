/**
 * WarPlayer contains the main method for running the card simulation
 * It keeps track of the stats for all games being simulated and 
 * records the maximums, minimums, counts, etc.
 * Data is written to a file called results.txt
 *
 * @date 7/3/12
 * @author Graham Wright
 */

import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;

public class WarPlayer {

	/**
	 * The main class, runs the game of War
	 */
    public static void main(String[] args) {

        double times = 1000;
    	
        double battles = 0.0;
        double faceOffs = 0.0;
        double cardsPlayed = 0.0;
        int p1wins = 0;
        int p2wins = 0;
        int p3wins = 0;
        int p4wins = 0;
        int doubleWars = 0;
        double tripleWars = 0.0;
        double quadrupleWars = 0.0;
        double quintupleWars = 0.0;
        double sextupleWars = 0.0;
        double septupleWars = 0.0;
        double octupleWars = 0.0;
        ArrayList<Integer> stats = new ArrayList<Integer>();
        int maxBattle = 0;
        int maxCards = 0;
        int maxWars = 0;
        int maxRounds = 0;
        int minRounds = Integer.MAX_VALUE;
        int minBattle = Integer.MAX_VALUE;
        int minCards = Integer.MAX_VALUE;
        int minWars = Integer.MAX_VALUE;
        int winnerHandStrength = 0;
        int winnerAceCount = 0;
        double winner0ace = 0;
        double winner1ace = 0;
        double winner2ace = 0;
        double winner3ace = 0;
        double winner4ace = 0;
        int winnerWarWins = 0;
        int under208 = 0;
        int over208 = 0;
        int finiteGame = 0;
        int infiniteGame = 0;
        int roundCount = 0;
        
        for(int i = 0; i < times; i++) {
            Player p1 = new Player(1);
            Player p2 = new Player(2);
            Player p3 = null;
            Player p4 = null;

            War game = new War(p1, p2, p3, p4);
            game.play();
            stats = game.computeStatistics();
            cardsPlayed = cardsPlayed + stats.get(0);
            battles = battles + stats.get(1);
            faceOffs = faceOffs + stats.get(2);
            doubleWars = doubleWars + stats.get(4);
            tripleWars = tripleWars + stats.get(5);
            quadrupleWars = quadrupleWars  + stats.get(6);
            quintupleWars = quintupleWars + stats.get(7);
            sextupleWars = sextupleWars + stats.get(8);
            septupleWars = septupleWars + stats.get(9);
            octupleWars = octupleWars + stats.get(10);
            if(stats.get(1) > maxBattle)  maxBattle = (int) stats.get(1);
            if(stats.get(2) > maxWars)  maxWars = (int) stats.get(2);
            if(stats.get(0) > maxCards)  maxCards = (int) stats.get(0);
            
            if(stats.get(1) < minBattle)  minBattle = (int) stats.get(1);
            if(stats.get(2) < minWars)  minWars = (int) stats.get(2);
            if(stats.get(0) < minCards)  minCards = (int) stats.get(0);
            
            winnerHandStrength = winnerHandStrength + stats.get(11);
            if(stats.get(12) == 0) winner0ace++;
            if(stats.get(12) == 1) winner1ace++;
            if(stats.get(12) == 2) winner2ace++;
            if(stats.get(12) == 3) winner3ace++;
            if(stats.get(12) == 4) winner4ace++;
            winnerAceCount = winnerAceCount + stats.get(12);
            winnerWarWins = winnerWarWins + stats.get(13);
            under208 = under208 + stats.get(14);
            over208 = over208 + stats.get(15);
            
            //Infinite Game
            finiteGame = finiteGame + stats.get(16);
            infiniteGame = infiniteGame + stats.get(17);
            roundCount = roundCount + stats.get(18);
            if(stats.get(18) > maxRounds) maxRounds = stats.get(18);
            if(stats.get(18) < minRounds) minRounds = stats.get(18);
            
            int player = stats.get(3);
            if(player == 1) p1wins++;
            else if(player == 2) p2wins++;
            else if(player == 3) p3wins++;
            else if(player == 4) p4wins++;
        }
        
        winner0ace = winner0ace / times;
        winner1ace = winner1ace / times;
        winner2ace = winner2ace / times;
        winner3ace = winner3ace / times;
        winner4ace = winner4ace / times;

        String finalStats = "";
        finalStats = finalStats + "Average Cards Played Per Game: " + cardsPlayed / times + "\nAverage Rounds Per Game: " + roundCount / times
        + "\nAverage Battles Per Game: " + battles / times + "\nAverage Wars Per Game "
        + faceOffs / times + "\nSingle Wars: " + faceOffs + "\nDouble Wars: " + doubleWars + "\nTriple Wars: " + tripleWars + "\nQuadruple Wars: " + quadrupleWars +
        "\nQuintuple Wars: " + quintupleWars
        + "\nSextuple Wars: "+ sextupleWars + "\nSeptuple Wars: " + septupleWars + "\nOctuple Wars: " + octupleWars +
        "\nMax Cards Played: " + maxCards + "\nMin Cards Played: " + minCards + "\nMax Rounds: " + maxRounds + "\nMin Rounds: " + minRounds +
        "\nMax Battles: " + maxBattle + "\nMin Battles: " + minBattle + "\nMax Wars: " + maxWars + "\nMin Wars: " + minWars + "\nWinner Hand Strength: "
        + winnerHandStrength / times + "\nWinning with under 208: " + under208 / times + "\nWinning with over 208: " + over208 / times +
        "\nWinning with 208: " + (times - (over208 + under208)) / times +
        "\nAvg Winner Ace Count: " + winnerAceCount / times + "\nWinning with 0 aces: " + winner0ace +
        "\nWinning with 1 ace: " + winner1ace + "\nWinning with 2 aces: " + winner2ace + "\nWinning with 3 aces: " + winner3ace +
         "\nWinning with 4 aces: " + winner4ace + "\nP(0): " + (winner0ace / (winner4ace + winner0ace)) + "\nP(1): " + (winner1ace / (winner3ace + winner1ace)) +
        "\nP(2): " + (winner2ace / (winner2ace + winner2ace)) + "\nP(3): " + (winner3ace / (winner1ace + winner3ace)) + "\nP(4): "
        + (winner4ace / (winner0ace + winner4ace)) + "\nAvg Winner War Wins: " + (winnerWarWins / times) + "\nFinite Games: " + finiteGame / times +
        "\nInfinite Games: " + infiniteGame / times +     
                
        "\nPlayer 1 Wins: " + p1wins + "\nPlayer 2 Wins: " + p2wins
        + "\nPlayer 3 Wins: " + p3wins + "\nPlayer 4 Wins: " + p4wins;
        
        //Write the data to a file called "results.txt"
        PrintWriter writer = null;
        String[] results = finalStats.split("\n");
        try {     	
        	writer = new PrintWriter("results.txt");
        } catch (IOException e) {
        	System.out.println("FDSDFSDFSD");
        }
        System.out.println(finalStats);
        for (int i = 0; i < results.length; i++) {
        	writer.println(results[i]);
        }
    	writer.close();
    }
}
