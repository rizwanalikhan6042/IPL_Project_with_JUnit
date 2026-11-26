import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class projectFileTest {
 //for every question, first we are checking empty data and edge cases then
 // comparing the expected result with actual result

    //for 1st question
    @Test
    void testMatchesPerYearEdgeCases() throws IOException {

        HashMap<Integer, Integer> result = projectFile.matchesPerYear();

        assertFalse(result.isEmpty());
        for (Integer year : result.keySet()) {
            assertNotNull(result.get(year));
            assertTrue(result.get(year) > 0);
        }
    }

    @Test
    void testMatchesPerYear() throws IOException{
        var result=projectFile.matchesPerYear();
        assertEquals(58, result.get(2008));
        assertEquals(57, result.get(2009));
        assertEquals(60, result.get(2010));
        assertEquals(73, result.get(2011));
        assertEquals(74, result.get(2012));
        assertEquals(76, result.get(2013));
        assertEquals(60, result.get(2014));
        assertEquals(59, result.get(2015));
        assertEquals(60, result.get(2016));
        assertEquals(59, result.get(2017));
    }
    //for ques 2 ,, we verified the actual data by sql and then doing testing here
    @Test
    void testMatchesWonByTeamsEdgeCases() throws IOException {

        HashMap<String, Integer> result = projectFile.matchesWonByTeams();

        assertFalse(result.isEmpty());
        for (String team : result.keySet()) {
            assertNotNull(result.get(team));
            assertTrue(result.get(team) >= 0);
        }
    }

    @Test
    void testMatchesWonByTeams() throws Exception {

        HashMap<String, Integer> result1 = projectFile.matchesWonByTeams();

        assertEquals(92, result1.get("Mumbai Indians"));
        assertEquals(79, result1.get("Chennai Super Kings"));
        assertEquals(77, result1.get("Kolkata Knight Riders"));
        assertEquals(73, result1.get("Royal Challengers Bangalore"));
        assertEquals(70, result1.get("Kings XI Punjab"));
        assertEquals(63, result1.get("Rajasthan Royals"));
        assertEquals(62, result1.get("Delhi Daredevils"));
        assertEquals(42, result1.get("Sunrisers Hyderabad"));
        assertEquals(29, result1.get("Deccan Chargers"));
        assertEquals(13, result1.get("Gujarat Lions"));
        assertEquals(12, result1.get("Pune Warriors"));
        assertEquals(10, result1.get("Rising Pune Supergiant"));
        assertEquals(6, result1.get("Kochi Tuskers Kerala"));
        assertEquals(5, result1.get("Rising Pune Supergiants"));

        assertEquals(14, result1.size());
    }
    //for ques 3
    @Test
    void testExtraRuns2016EdgeCases() throws IOException {

        HashMap<String, Integer> result = projectFile.extraRuns2016();

        assertFalse(result.isEmpty());

        for (String team : result.keySet()) {
            assertNotNull(result.get(team));
            assertTrue(result.get(team) >= 0);
        }
    }
    @Test
    void testExtraRuns2016() throws IOException {

        HashMap<String, Integer> result = projectFile.extraRuns2016();

        assertEquals(98, result.get("Gujarat Lions"));
        assertEquals(102, result.get("Mumbai Indians"));
        assertEquals(107, result.get("Sunrisers Hyderabad"));
        assertEquals(100, result.get("Kings XI Punjab"));
        assertEquals(106, result.get("Delhi Daredevils"));
        assertEquals(108, result.get("Rising Pune Supergiants"));
        assertEquals(122, result.get("Kolkata Knight Riders"));
        assertEquals(156, result.get("Royal Challengers Bangalore"));

        assertEquals(8, result.size());
    }
//ques 4
@Test
void testEconomicalBowlersEdgeCases() throws IOException {

    HashMap<String, Double> result = projectFile.economicalBowlers2015();

    assertFalse(result.isEmpty());

    for (String bowler : result.keySet()) {
        assertNotNull(result.get(bowler));
        assertTrue(result.get(bowler) >= 0);
    }
}
@Test
void testPlayerOfMatchAwardsForInvalidPlayer() throws IOException {

    HashMap<String, Integer> result = projectFile.playerOfMatchAwards();

    assertNull(result.get("Invalid Player"));
    assertNull(result.get(""));
}
    @Test
    void testPlayerOfMatchAwardsMapNotEmpty() throws IOException {

        HashMap<String, Integer> result = projectFile.playerOfMatchAwards();

        assertFalse(result.isEmpty());
        assertTrue(result.size() > 0);
    }
@Test
void testEconomicalBowlers2015() throws IOException {

    HashMap<String, Double> result = projectFile.economicalBowlers2015();

    assertEquals(5.85, result.get("R Ashwin"), 0.01);
    assertEquals(6.32, result.get("MC Henriques"), 0.01);
    assertEquals(6.46, result.get("Z Khan"), 0.01);
    assertEquals(6.77, result.get("MA Starc"), 0.01);
    assertEquals(6.86, result.get("GB Hogg"), 0.01);
    assertEquals(7.00, result.get("Sandeep Sharma"), 0.01);
    assertEquals(7.19, result.get("IC Pandey"), 0.01);
    assertEquals(7.21, result.get("DJ Muthuswami"), 0.01);
    assertEquals(7.24, result.get("A Nehra"), 0.01);
    assertEquals(7.31, result.get("SP Narine"), 0.01);
}
//for ques 5 ,, own scenario player with no of man_of_the_match award
@Test
void testPlayerOfMatchAwards() throws IOException {

    HashMap<String, Integer> result = projectFile.playerOfMatchAwards();

    assertEquals(18, result.get("CH Gayle"));

    assertEquals(15, result.get("AB de Villiers"));
    assertEquals(15, result.get("DA Warner"));
    assertEquals(14, result.get("RG Sharma"));
    assertEquals(14, result.get("SK Raina"));
    assertEquals(13, result.get("MS Dhoni"));
    assertEquals(13, result.get("G Gambhir"));
    assertEquals(12, result.get("MEK Hussey"));
    assertEquals(12, result.get("AM Rahane"));
    assertEquals(11, result.get("V Kohli"));
}

}



