package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SearchfloorBooksLibrary {
  public static void main(String[] args) {
    ArrayList<String> authors = new ArrayList<>();
    ArrayList<String> series = new ArrayList<>();
    ArrayList<String> bookTitles = new ArrayList<>();
    ArrayList<String> bookNumbers1 = new ArrayList<>();
    ArrayList<String> downloadButtonXPaths = new ArrayList<>();



    try {
      String url = "https://searchfloor.org/?status=is_finished";
      Document document = Jsoup.connect(url).get();
      for (int i = 13526; i >= 13526; i--) {
        Element NumberElement3 = document.selectFirst("div[id=\"book" + i + "\"][style=\"margin-top: 1rem;\"]");


        if (NumberElement3 != null) {

          Elements authorsElements = document.select("div[id=\"book" + i + "\"][style=\"margin-top: 1rem;\"]");
          authorsElements.forEach(authorsElement -> {
            Element authorsElement1 = authorsElement.child(1);
            String author = authorsElement1.text();
            author = author.replace("Автор:", "");
            authors.add(author);


          });

          Elements titleElements = document.select("div[id=\"book" + i + "\"][style=\"margin-top: 1rem;\"]");
          titleElements.forEach(titleElement -> {
            Element titleElement1 = titleElement.child(0);
            Element titleElement2 = titleElement1.selectFirst("b");
            String title = titleElement2.text();
            bookTitles.add(title);


          });

          Elements seriesElements = document.select("div[id=\"book" + i + "\"][style=\"margin-top: 1rem;\"]");
          seriesElements.forEach(seriesElement -> {
            Element seriesElement1 = seriesElement.child(2);
            Element seriesElement2 = seriesElement1.selectFirst("a");
            String serie = seriesElement2.text();
            series.add(serie);


          });

        }
        // Тут і був затик, ти практично оказався правий . Так що призові тобі з ботом на пополам аби не та срана -1 то код нехотів працювати
        Elements bookNumbers1Elements = document.select("div[id=\"book" + i + "\"][style=\"margin-top: 1rem;\"]");
        String bookNumber2= bookNumbers1Elements.last().child(2).text();
        char bookNumber = bookNumber2.charAt(bookNumber2.length() - 1);
        bookNumbers1.add(String.valueOf(bookNumber));
















        String downloadButtonXPath = "//*[@id=\""+i+"\"]";
        downloadButtonXPaths.add(downloadButtonXPath);


        try (PrintWriter writer = new PrintWriter("library.txt")) {
          for (int j = 0; j < authors.size(); j++) {
            writer.println(authors.get(j));
            writer.println(series.get(j));
            writer.println(bookTitles.get(j));
            writer.println(bookNumbers1.get(j));
            writer.println(downloadButtonXPaths.get(j));
            writer.println();

          }
        } catch (IOException e) {
          e.printStackTrace();

        }

        }
      } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  }

