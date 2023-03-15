/********************************************************************
 ADT + RECORDS OUTLINE
 - getAllEpisodes()
 - getAllSeasons()
 - createTvShow()
 - addSeasonToShow()
 - addEpisodeToShow()
 - searchEpisode()
 - listEpisodesFromSeason()
 ********************************************************************/


import java.util.*;

// Record outline for a TV Show
class TvShow {
    final int TOTAL_NUM_OF_SEASONS = 50;
    int[] seasonNum = new int[TOTAL_NUM_OF_SEASONS];
    String[][] episodeNames = new String[seasonNum.length][1000];
}

public class TvShowInfos {
    // Add the season number to the array in the record
    public static TvShow setSeasonNum(TvShow t, int n) {
        int counter = 0;
        boolean changed = false;
        while (!changed) {
            if (t.seasonNum[counter] == 0) {
                t.seasonNum[counter] = n;
                changed = true;
            }
            else {
                counter++;
            }
        }
        return t;
    }

    // Add an episode name to the array in the record
    public static TvShow setEpisodeName(TvShow t, int sN, String eN) {
        int counter = 0;
        boolean changed = false;
        while (!changed) {
            if (t.episodeNames[sN-1][counter] == null) {
                t.episodeNames[sN-1][counter] = eN;
                changed = true;
            }
            else {
                counter++;
            }
        }
        return t;
    }

    // Get the episode name from the record
    public static String[][] getAllEpisodes(TvShow t) {
        return t.episodeNames;
    }

    // Get the season number from the record
    public static int[] getAllSeasons(TvShow t) {
        return t.seasonNum;
    }

    // Create a tv show {record}
    public static TvShow createTvShow() {
        TvShow temp = new TvShow();
        return temp;
    }

    // Add a season number to tv show {record}
    public static TvShow addSeasonToShow(TvShow t, int sN) {
        t = setSeasonNum(t, sN);
        return t;
    }

    // Add an episode to tv show {record}
    public static TvShow addEpisodeToShow(TvShow t, int sN, String eN) {
        t = setEpisodeName(t, sN, eN);
        return t;
    }


    // Search for episode
    public static void searchEpisode(TvShow t, String eN) {
        int seasonNum = 0;
        int[] allSeasons = getAllSeasons(t);
        String[][] allEpisodes = getAllEpisodes(t);

        for (int i = 0; i < allEpisodes.length; i++)
        {
            for (String eName : allEpisodes[i]) {
                if (Objects.equals(eName, eN)) {
                    seasonNum = allSeasons[i + 1];
                    break;
                }
            }
        }

        if (seasonNum == 0) {
            System.out.println("The episode was not found");
        }
        else {
            System.out.println("Episode '" + eN + "' was in Season " + seasonNum);
        }
    }

    // List the episodes in the season
    public static void listEpisodesFromSeason(TvShow t, int sN) {
        String[][] allEpisodes = getAllEpisodes(t);
        int counter = 0;

        for (String eName : allEpisodes[sN - 1]) {
            if (eName != null) {
                System.out.println(eName);
                counter++;
            }
        }

        System.out.println("Total episodes in season " + sN + " was " + counter);

    }

    // ask + validate choice
    public static int askChoice() {
        int ans = 0;
        boolean isValid = false;
        Scanner input = new Scanner(System.in);

        while (!isValid) {
            System.out.println("Enter option [1-4]: ");
            try {
                ans = Integer.parseInt(input.nextLine());
                if ((ans >= 1) && (ans <= 4)) {
                    isValid = true;
                }
                else {
                    System.out.println("ENTER WITHIN THE RANGE !!!");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("ONLY ENTER A NUMBER !!!");
            }
        }
        return ans;
    }

    // Check is integer
    public static int askSeasonNumber() {
        int ans2 = 0;
        boolean isValid2 = false;
        Scanner input2 = new Scanner(System.in);

        while (!isValid2) {
            System.out.println("Season number? ");
            try {
                ans2 = Integer.parseInt(input2.nextLine());
                isValid2 = true;
            }
            catch (NumberFormatException e) {
                System.out.println("ONLY ENTER A NUMBER !!!");
            }
        }
        return ans2;
    }

    public static void main(String[] args) {
        boolean quit = false;
        int choice;
        Scanner scanner = new Scanner(System.in);
        int seasonNumber;
        String episodeName;

        TvShow show = createTvShow();

        while (!quit) {
            System.out.println("(1) Add episode (2) Search for title (3) List season (4) Exit");
            choice = askChoice();

            if (choice == 1) {
                seasonNumber = askSeasonNumber();
                show = addSeasonToShow(show, seasonNumber);
                System.out.println("Title? ");
                episodeName = scanner.nextLine();
                show = addEpisodeToShow(show, seasonNumber, episodeName);
            }
            else if(choice == 2) {
                System.out.println("Title to search for? ");
                episodeName = scanner.nextLine();
                searchEpisode(show, episodeName);
            }
            else if (choice == 3) {
                seasonNumber = askSeasonNumber();
                listEpisodesFromSeason(show, seasonNumber);
            }
            else {
                quit = true;
            }
        }
    }
}
