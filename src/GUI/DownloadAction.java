package GUI;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import containers.Wallpapers;

public class DownloadAction implements ActionListener {

   private Controller controller; // needed to change view
   private Wallpapers wallpapers; // list of all wallpapers

   public DownloadAction(Controller c, Wallpapers w) {
      this.controller = c;
      this.wallpapers = w;
   }

   /** Method started on download button click */
   @Override
   public void actionPerformed(ActionEvent arg0) {
      // change the view of the GUI
      controller.startDownloadView();
      
      // Get the list of wallpapers and start downloading them.
      // do this on a separate thread to avoid 'blocking' the main thread
      Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
            // download list of wallpapers to current directory
            downloadWallpapers(); 
         }
      });

      thread.start();
   }

   /** Download all wallpapers */
   private void downloadWallpapers() {
      // get list of all wallpapers ready for processing
      ArrayList<String> list = wallpapers.getListOfWallpapers();

      // name of folder which will hold the downloaded images
      String topFolderPath = "Wallpapers\\";

      // Download each URL and save it to the local directory
      for (int i = 0; i < list.size(); i++) {
         String stringURL = list.get(i);

         // cast string to URL object
         URL source = parseURL(stringURL);

         // create valid file path from given image
         String fileName = topFolderPath + parseFileNameAndType(stringURL);
         File filePath = new File(fileName);

         // download file and save it to the given directory
         downloadAndSaveToDirectory(source, filePath);
      }
   }

   /** Process wallpaper string to get a downloadable URL */
   private URL parseURL(String stringURL) {
      // remove first 's', found in 'https' to get a downloadable URL
      String url = stringURL.replaceFirst("s", "");

      // create URL object from string URL
      URL returnValue = null;

      try {
         returnValue = new URL(url);
      } catch (MalformedURLException e) {
         // quit and tell user
         JOptionPane.showMessageDialog(null, "Error processing URL list.");
         System.exit(0);
      }

      return returnValue;
   }

   /** Process URL to extract a readable name */
   private String parseFileNameAndType(String stringURL) {
      // readable filename is contained in underscores, get indicies
      int firstUnderscore = stringURL.indexOf('_') + 1;
      int secondUnderscore = stringURL.indexOf('_', firstUnderscore);

      // extract readable name and append the file type
      String filename = stringURL.substring(firstUnderscore, secondUnderscore);
      filename += getFileExtension(stringURL);

      return filename;
   }

   /** Download file from URL, save file to given path */
   private void downloadAndSaveToDirectory(URL source, File path) {
      try {
         // download file
         FileUtils.copyURLToFile(source, path);
      } catch (IOException e) {
         // download failed, so skip download and move on
         return;
      }
   }

   /** Process a url and determine the image type (.jpg, .png) */
   private String getFileExtension(String stringURL) {
      // get location of the last 'dot', signifying file type
      int dotIndex = stringURL.lastIndexOf(".");
      // get string starting from the last dot
      return stringURL.substring(dotIndex);
   }
}