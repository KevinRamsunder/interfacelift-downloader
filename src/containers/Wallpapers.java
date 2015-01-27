package containers;

import java.util.ArrayList;

public class Wallpapers {
   ArrayList<String> list;

   public Wallpapers(int amountOfWallpapers) {
      list = new ArrayList<String>(amountOfWallpapers * 10);
   }

   public void add(String url) {
      list.add(url);
   }
}
