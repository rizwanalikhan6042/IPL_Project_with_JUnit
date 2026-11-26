import java.io.*;
import java.util.*;
import java.util.HashMap;
public class projectFile {
   //matches per year
    public static HashMap<Integer, Integer> matchesPerYear() throws IOException {

        HashMap<Integer, Integer> matchesPerYear = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader("Data/matches.csv"));
        br.readLine();

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");
            int season = Integer.parseInt(data[1]);

            if (matchesPerYear.containsKey(season)) {
                int old = matchesPerYear.get(season);
                matchesPerYear.put(season, old + 1);
            } else {
                matchesPerYear.put(season, 1);
            }
        }

        br.close();

        return matchesPerYear;
    }
    public static HashMap<String, Integer> matchesWonByTeams() throws IOException {

        HashMap<String, Integer> matchesWon = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader("Data/matches.csv"));
        br.readLine();

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");
            String winner = data[10];

            if (!winner.isEmpty()) {

                if (matchesWon.containsKey(winner)) {
                    int old = matchesWon.get(winner);
                    matchesWon.put(winner, old + 1);
                } else {
                    matchesWon.put(winner, 1);
                }
            }
        }

        br.close();

        return matchesWon;
    }
    public static HashMap<String, Integer> extraRuns2016() throws IOException {

        HashMap<String, Integer> extraRunsPerTeam = new HashMap<>();

        HashSet<Integer> matchIds2016 = new HashSet<>();

        BufferedReader br = new BufferedReader(new FileReader("Data/matches.csv"));
        br.readLine();

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");
            int season = Integer.parseInt(data[1]);
            int matchId = Integer.parseInt(data[0]);

            if (season == 2016) {
                matchIds2016.add(matchId);
            }
        }

        br.close();

        BufferedReader br2 = new BufferedReader(new FileReader("Data/deliveries.csv"));
        br2.readLine();

        while ((line = br2.readLine()) != null) {

            String[] data = line.split(",");

            int matchId = Integer.parseInt(data[0]);
            String bowlingTeam = data[3];
            int extraRuns = Integer.parseInt(data[16]);

            if (matchIds2016.contains(matchId)) {

                if (extraRunsPerTeam.containsKey(bowlingTeam)) {
                    int old = extraRunsPerTeam.get(bowlingTeam);
                    extraRunsPerTeam.put(bowlingTeam, old + extraRuns);
                } else {
                    extraRunsPerTeam.put(bowlingTeam, extraRuns);
                }
            }
        }

        br2.close();

        return extraRunsPerTeam;
    }
    //ques 4
    public static HashMap<String, Double> economicalBowlers2015() throws IOException {

        HashSet<Integer> matchIds2015 = new HashSet<>();
        HashMap<String, Integer> runsConceded = new HashMap<>();
        HashMap<String, Integer> legalBalls = new HashMap<>();
        HashMap<String, Double> economy = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader("Data/matches.csv"));
        br.readLine();

        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            int season = Integer.parseInt(data[1]);
            int matchId = Integer.parseInt(data[0]);

            if (season == 2015) {
                matchIds2015.add(matchId);
            }
        }

        br.close();

        BufferedReader br2 = new BufferedReader(new FileReader("Data/deliveries.csv"));
        br2.readLine();

        while ((line = br2.readLine()) != null) {

            String[] data = line.split(",");

            int matchId = Integer.parseInt(data[0]);
            String bowler = data[8];

            int totalRuns = Integer.parseInt(data[17]);
            int bye = Integer.parseInt(data[11]);
            int legBye = Integer.parseInt(data[12]);
            int penalty = Integer.parseInt(data[14]);

            int wide = Integer.parseInt(data[10]);
            int noBall = Integer.parseInt(data[13]);

            if (matchIds2015.contains(matchId)) {

                int bowlerRuns = totalRuns - bye - legBye - penalty;

                if (runsConceded.containsKey(bowler)) {
                    int old = runsConceded.get(bowler);
                    runsConceded.put(bowler, old + bowlerRuns);
                } else {
                    runsConceded.put(bowler, bowlerRuns);
                }

                if (wide == 0 && noBall == 0) {

                    if (legalBalls.containsKey(bowler)) {
                        int old = legalBalls.get(bowler);
                        legalBalls.put(bowler, old + 1);
                    } else {
                        legalBalls.put(bowler, 1);
                    }
                }
            }
        }

        br2.close();

        for (String bowler : runsConceded.keySet()) {

            int runs = runsConceded.get(bowler);
            int balls = legalBalls.get(bowler);
            if (balls >= 60) {
                double overs = balls / 6.0;
                double rate = runs / overs;

                economy.put(bowler, rate);
            }
        }


        return economy;
    }
    //5th one additional scenario
    public static HashMap<String, Integer> playerOfMatchAwards() throws IOException {

        HashMap<String, Integer> awards = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader("Data/matches.csv"));
        br.readLine();

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");
            String player = data[13];

            if (!player.isEmpty()) {

                if (awards.containsKey(player)) {
                    int old = awards.get(player);
                    awards.put(player, old + 1);
                } else {
                    awards.put(player, 1);
                }
            }
        }

        br.close();

        return awards;
    }
    public static void main(String[] args) throws IOException {

        HashMap<Integer, Integer> result = matchesPerYear();

//        for (int key : result.keySet()) {
//            System.out.println("Year : " + key + " No of matches : " + result.get(key));
//        }
        HashMap<String, Integer> result1 = matchesWonByTeams();

//        for (String key : result1.keySet()) {
//            System.out.println("Team : " +key+ " Wins : " + result1.get(key));
//        }
        HashMap<String,Integer>result2=extraRuns2016();
//        for(String key:extraRuns2016().keySet()){
//            System.out.println("Team : "+key+ " Exra runs : "+result2.get(key));
//        }

        HashMap<String, Double> result3 = economicalBowlers2015();

        result3.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(10)
                .forEach(entry ->
                        System.out.printf("%s:%.2f%n",
                                entry.getKey(), entry.getValue()));

        HashMap<String,Integer>result4=playerOfMatchAwards();
        for(String key:result4.keySet()){
            System.out.println(" Player name : "+key+" total awards : "+result4.get(key));
        }


    }



}