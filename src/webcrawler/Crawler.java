package webcrawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Crawler {
    public static void crawler(String startingURL, String word) {
        ArrayList<String> listOfPendingURLs = new ArrayList<>();
        ArrayList<String> listOfTraversedURLs = new ArrayList<>();

        listOfPendingURLs.add(startingURL);

        while (!listOfPendingURLs.isEmpty()) {
            String urlString = listOfPendingURLs.remove(0);

            if (findWordOnPage(urlString, word)) {
                System.out.println("The word " + word + " found on page " + urlString);
                return;
            }

            if (!listOfTraversedURLs.contains(urlString)) {
                listOfTraversedURLs.add(urlString);
                System.out.println("Crawling " + urlString);

                for (String s : getSubURLs(urlString)) {
                    if (!listOfTraversedURLs.contains(s))
                        listOfPendingURLs.add(s);
                }
            }
        }
    }

    public static boolean findWordOnPage(String url, String word) {
        try {
            URL urlObj = new URL(url);
            Scanner scanner = new Scanner(urlObj.openStream());

            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                if (str.indexOf(word) != -1)
                    return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<String> getSubURLs(String urlString) {
        ArrayList<String> list = new ArrayList<>();

        try {
            URL url = new URL(urlString);
            Scanner input = new Scanner(url.openStream());
            int current = 0;
            while (input.hasNext()) {
                String line = input.nextLine();
                current = line.indexOf("http:", current);

                while (current > 0) {
                    int endIndex = line.indexOf("\"", current);

                    if (endIndex > 0) {
                        list.add(line.substring(current, endIndex));
                        current = line.indexOf("http:", endIndex);
                    } else {
                        current = -1;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }
}