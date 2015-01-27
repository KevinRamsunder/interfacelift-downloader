package agents;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import strings.HTML;
import containers.Wallpapers;

public class Agent extends WebAgent {

   private int numberOfPages;
   private Wallpapers wallpapers;

   public Agent() {
      numberOfPages = getNumberOfPages();
      wallpapers = new Wallpapers(numberOfPages);
      crawlPagesAndStoreResults();
   }

   private int getNumberOfPages() {
      // get the list of selector elements from the webpage
      // the second to last element from the list holds the number of pages
      Elements listOfPages = webpage.select("a.selector");
      int size = listOfPages.size();
      size = Integer.parseInt(listOfPages.get(size - 2).text());
      return size;
   }

   private void crawlPagesAndStoreResults() {
      int seed = 1;
      int pages = numberOfPages;

      while (pages > 0) {
         // Get the picture links on the page, then add them to wallpaper list
         Elements dlLinks = webpage.select("div[id^=download] > a");
         addLinksFromPage(dlLinks);

         // get the next page ready
         super.initConnection(getNextURL(seed));
         seed++;
         pages--;
      }
   }

   private void addLinksFromPage(Elements dlLinks) {
      for (Element e : dlLinks) {
         wallpapers.add(e.absUrl("href"));
      }
   }

   private String getNextURL(int seed) {
      // append the original link with the modifier for the next page
      StringBuilder linkAppend = new StringBuilder();
      linkAppend.append(HTML.url);
      linkAppend.append("index");
      linkAppend.append(seed);
      linkAppend.append(".html");
      return linkAppend.toString();
   }
}