package webAgent;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import strings.ErrorMessages;
import strings.HTML;

public class WebAgent {

   Document webpage;

   public WebAgent() {
      initConnection();
   }

   private void initConnection() {
      try {
         webpage = Jsoup.connect(HTML.url).userAgent("Mozilla")
               .timeout(60 * 1000).get();
      } catch (IOException e) {
         System.out.println(ErrorMessages.connectionFailed);
         System.exit(0);
      }
   }
   
   
}
