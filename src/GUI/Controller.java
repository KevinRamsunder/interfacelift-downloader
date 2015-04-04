package GUI;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Controller {

   private Model model; // web agent for scraping wallpapers
   private Display display; // GUI elements

   public Controller(Model model, Display display) {
      this.model = model;
      this.display = display;
   }

   /** Start connection to website */
   public void startConnection() {
      // disable download button
      display.setButtonStatus(false);
      display.setText("Starting connection to InterfaceLift.com...");

      // initialize web agent
      model.startAgent();
   }

   /** Start collecting all wallpapers */
   public void startScraping() {
      // use a separate thread to gather wallpapers in background
      startScrapingInBackground();

      // tell user
      String displayText = "Connection established\n" + "Searching "
            + model.getPageCount() + " pages...\n"
            + "(This may take some time)\n";
      display.showMessage(displayText);

      // update user with current count until scraping is finished
      repeatUpdateWallpaperCount();

      // gathering wallpapers is finished, re-enable button
      display.showMessage("Total wallpapers found: "
            + model.getWallpaperCount());
      display.setText("Complete. Start download below.");
      display.setButtonStatus(true);
   }

   /** Use a separate thread to collect and store wallpapers */
   private void startScrapingInBackground() {
      // new thread
      Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
            // start the web scraping
            model.startScraping();
         }
      });

      thread.start(); // start the above thread
   }

   /** Update GUI text to tell user current wallpaper count */
   private void repeatUpdateWallpaperCount() {
      // Latch needed to pause the current thread
      CountDownLatch latch = new CountDownLatch(1);

      // while agent is still gathering wallpapers, update the user on count
      while (model.isScraping()) {
         // display current count on GUI
         display.setText("Wallpapers found: " + model.getWallpaperCount());

         // wait 5 seconds before checking again, prevent excessive looping
         try {
            latch.await(5, TimeUnit.SECONDS);
         } catch (InterruptedException e) {
         }
      }
   }
}