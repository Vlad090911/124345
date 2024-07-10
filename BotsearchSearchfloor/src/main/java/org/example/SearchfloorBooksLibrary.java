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
    ArrayList<String> idbook = new ArrayList<>();
    ArrayList<String> idbooknot = new ArrayList<>();



    try {
      String url = "https://searchfloor.org/?status=is_finished";
      Document document = Jsoup.connect(url).get();

      for (int i = 13547; i >= 13250; i--) {
        Element NumberElement3 = document.selectFirst("div[id=\"book" + i + "\"][style=\"margin-top: 1rem;\"]");
        String idbook1 = String.valueOf(i);
        idbook.add(idbook1);




        if (NumberElement3 != null) {

          Elements bookElements = document.select("div[id=\"book" + i + "\"][style=\"margin-top: 1rem;\"]");
          bookElements.forEach(bookElement -> {
            Element authorsElement1 = bookElement.child(1);
            Element titleElement1 = bookElement.child(0);
            Element seriesElement1 = bookElement.child(2);
            Element seriesElement2 = seriesElement1.selectFirst("a");



            String author = authorsElement1.text().replace("Автор:", "");
            String title = titleElement1.selectFirst("b").text();
            String serie = seriesElement2.text();
            String bookNumber2= bookElements.last().child(2).text();




            char bookNumber = bookNumber2.charAt(bookNumber2.length() - 1);



            authors.add(author);
            bookTitles.add(title);
            series.add(serie);
            bookNumbers1.add(String.valueOf(bookNumber));



          });

          String downloadButtonXPath = "//*[@id=\"" + i + "\"]";
          downloadButtonXPaths.add(downloadButtonXPath);


        }
        ;

        if (NumberElement3 == null) {

          String idbooknot1 = String.valueOf(i);
          idbooknot.add(idbooknot1);


        }

        try (PrintWriter writer1 = new PrintWriter("idbook.txt")) {
          for (int l = 0; l < idbook.size(); l++) {
            writer1.println(idbook.get(l));
          }

        }



        try (PrintWriter writer2 = new PrintWriter("idbooknot.txt")) {
          for (int k = 0; k < idbooknot.size(); k++) {
            writer2.println(idbooknot.get(k));
          }

        }



        try (PrintWriter writer = new PrintWriter("library.txt")) {
          for (int j = 0; j < authors.size(); j++) {
            writer.println(authors.get(j));
            writer.println(series.get(j));
            writer.println(bookTitles.get(j));
            writer.println(bookNumbers1.get(j));
            writer.println(downloadButtonXPaths.get(j));
            writer.println();

          }



        }
        catch(IOException e){
          e.printStackTrace();

        }


      }

    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  }

