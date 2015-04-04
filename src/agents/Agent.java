package agents;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import strings.HTML;
import containers.Wallpapers;

public class Agent extends WebAgent {

   private int numberOfPages; // number of pages to search
   private int numberOfWallpapers; // number of wallpapers found
   private Wallpapers wallpapers; // container for wallpapers

   /**
    * Get the number of pages that contain wallpapers, then instantiate
    * Wallpapers object with this amount. Finally, crawl each page for the
    * wallpaper urls.
    */
   public Agent() {
      numberOfWallpapers = 0;
      numberOfPages = getNumberOfPages();
      wallpapers = new Wallpapers(numberOfPages);
   }

   /**
    * Get the list of selector elements from the webpage. The second to last
    * element from the list holds the number of pages.
    * 
    * @return number of pages
    */
   private int getNumberOfPages() {
      // html tag that has list of pages
      Elements listOfPages = webpage.select("a.selector");

      // the second to last number in list contains the total number of pages
      int size = listOfPages.size();
      size = Integer.parseInt(listOfPages.get(size - 2).text());
      return size;
   }

   /** Crawl each page for the wallpaper urls. */
   public void crawlPagesAndStoreResults() {
      int seed = 1; // needed for modifying URL

      int pages = numberOfPages;

      // go through each page that contains a wallpaper
      while (pages > 0) {
         // Get the picture links on the page, then add them to Wallpapers
         Elements dlLinks = webpage.select("div[id^=download] > a");

         // add each wallpaper on the page to the container
         addURLsFromPage(dlLinks);

         // get the next page ready
         super.initConnection(getNextURL(seed));

         seed++; // needed for modifying next url
         pages--; // page is finished, decrement
      }
   }

   /**
    * Get the image urls from the page, add them to Wallpaper
    * 
    * @param dlLinks Contains wallpaper download urls
    */
   private void addURLsFromPage(Elements dlLinks) {
      // add each url to container
      for (Element e : dlLinks) {
         wallpapers.add(e.absUrl("href"));
         numberOfWallpapers++;
      }
   }

   /**
    * append the original link with the modifier for the next page
    * 
    * @param seed the page number
    * @return the modified url
    */
   private String getNextURL(int seed) {
      return HTML.url + "index" + seed + ".html";
   }

   public int getPageCount() {
      return numberOfPages;
   }
   
   public Wallpapers getWallpaperList() {
      return wallpapers;
   }

   public int getNumberOfWallpapers() {
      return numberOfWallpapers;
   }
}