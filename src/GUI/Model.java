package GUI;

import agents.Agent;
import containers.Wallpapers;

public class Model {

   private Agent agent;
   private Wallpapers wallpapers;

   public Model() {

   }

   public void startScraping() {
      agent = new Agent();
      this.wallpapers = agent.getWallpaperList();
   }

   public Wallpapers getWallpapers() {
      return wallpapers;
   }

   public int getWallpaperCount() {
      return agent.getNumberOfWallpapers();
   }
}