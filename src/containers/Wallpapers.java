package containers;

import java.util.ArrayList;

public class Wallpapers {

   private ArrayList<String> list;

   /**
    * Initialize object with the amount of pages you have. Each page holds 10
    * wallpapers, so multiply by 10.
    */
   public Wallpapers(int numberOfPages) {
      list = new ArrayList<String>(numberOfPages * 10);
   }

   public void add(String url) {
      list.add(url);
   }
}
