package agents;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import strings.HTML;
import containers.Wallpapers;

public class Agent extends WebAgent {

   private int numberOfPages;
   private Wallpapers wallpapers;

   /**
    * Get the number of pages that contain wallpapers, then instantiate
    * Wallpapers object with this amount. Finally, crawl each page for the
    * wallpaper urls.
    */
   public Agent() {
      numberOfPages = getNumberOfPages();
      wallpapers = new Wallpapers(numberOfPages);
      crawlPagesAndStoreResults();
   }

   public Wallpapers getWallpaperList() {
      return wallpapers;
   }

   /**
    * get the list of selector elements from the webpage. The second to last
    * element from the list holds the number of pages
    * 
    * @return number of pages
    */
   private int getNumberOfPages() {
      Elements listOfPages = webpage.select("a.selector");
      int size = listOfPages.size();
      size = Integer.parseInt(listOfPages.get(size - 2).text());
      return size;
   }

   /**
    * crawl each page for the wallpaper urls.
    */
   private void crawlPagesAndStoreResults() {
      int seed = 1;
      int pages = numberOfPages;
      System.out.println("Crawling " + pages
            + " pages...\n(This may take some time)");

      while (pages > 0) {
         // Get the picture links on the page, then add them to Wallpapers
         Elements dlLinks = webpage.select("div[id^=download] > a");
         addURLsFromPage(dlLinks);

         // get the next page ready
         super.initConnection(getNextURL(seed));
         seed++;
         pages--;
      }
   }

   /**
    * Get the image urls from the page, add them to Wallpapers
    * 
    * @param dlLinks
    *           Contains wallpaper download urls
    */
   private void addURLsFromPage(Elements dlLinks) {
      for (Element e : dlLinks) {
         // add the url to Wallpapers
         wallpapers.add(e.absUrl("href"));
      }
   }

   /**
    * append the original link with the modifier for the next page
    * 
    * @param seed
    *           the page number
    * @return the modified url
    */
   private String getNextURL(int seed) {
      StringBuilder linkAppend = new StringBuilder();
      linkAppend.append(HTML.url);
      linkAppend.append("index");
      linkAppend.append(seed);
      linkAppend.append(".html");
      return linkAppend.toString();
   }
}