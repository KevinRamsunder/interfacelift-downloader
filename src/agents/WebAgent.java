package agents;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import strings.ErrorMessages;
import strings.HTML;

public abstract class WebAgent {

   protected Document webpage;

   public WebAgent() {
      initConnection();
   }

   /**
    * Initialize connection with InterfaceLift.com
    */
   private void initConnection() {
      try {
         webpage = Jsoup.connect(HTML.url).userAgent("Mozilla")
               .timeout(30 * 1000).get();
      } catch (IOException e) {
         System.out.println(ErrorMessages.connectionFailed);
         System.exit(0);
      }
   }

   /**
    * Use this method to get the next page of images. Provide the modified url
    * as the parameter.
    * 
    * @param url
    */
   public void initConnection(String url) {
      try {
         webpage = Jsoup.connect(url).userAgent("Mozilla").timeout(30 * 1000)
               .get();
      } catch (IOException e) {
         System.out.println(ErrorMessages.connectionFailed);
         System.exit(0);
      }
   }
}
