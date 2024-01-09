/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package webcrawler;

import java.util.Scanner;
import webcrawler.Crawler;
import java.util.Locale;
/**
 *
 * @author alexa
 */
public class WebCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a URL: ");
        String url = input.nextLine(); 
        System.out.print("Enter word: ");
        String word = input.nextLine();
        System.out.println(word);
        Crawler.crawler(url, word);
    }
    
}
