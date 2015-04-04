package GUI;

public class InterfaceLift {

   public static void main(String[] args) {
      Model model = new Model(); // web agents
      Display display = new Display(); // GUI elements
      
      // handles operations pertaining to web agent and GUI
      Controller controller = new Controller(model, display);
      
      // start connection to website
      controller.startConnection();
      
      // start gathering wallpapers
      controller.startScraping();
      
      
      // TODO: Downloader coming soon
   }
}
