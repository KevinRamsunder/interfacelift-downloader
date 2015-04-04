package GUI;

import agents.Agent;
import containers.Wallpapers;

public class Model {

   private Agent agent; // web agent
   private Wallpapers wallpapers; // container for wallpapers

   private boolean isScraping; // variable to signal if agent is scraping

   public Model() {
      // by default agent is not scraping
      isScraping = false;
   }

   /** Connect agent to website */
   public void startAgent() {
      agent = new Agent(); // init
   }

   /** Start collecting wallpapers */
   public void startScraping() {
      isScraping = true; // scraping is starting, set true
      
      // store every wallpaper on every page
      agent.crawlPagesAndStoreResults();
      this.wallpapers = agent.getWallpaperList();
      
      isScraping = false; // scraping is finished, set false
   }

   public Wallpapers getWallpapers() {
      return wallpapers;
   }

   public boolean isScraping() {
      return isScraping;
   }

   public int getPageCount() {
      return agent.getPageCount();
   }

   public int getWallpaperCount() {
      return agent.getNumberOfWallpapers();
   }
}