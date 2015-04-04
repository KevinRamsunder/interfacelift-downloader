import agents.Agent;
import containers.Wallpapers;

public class Main {

   public static void main(String[] args) {
      System.out.println("Starting connection to InterfaceLift.com...");
      
      Agent crawler = new Agent();
      Wallpapers urls = crawler.getWallpaperList();

      System.out.println(urls.size());
      urls.printURLs();
   }
}
