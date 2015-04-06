package GUI;

public class InterfaceLiftDownloader {

   /** Main GUI for program */
   
   public static void main(String[] args) {
      // web agents
      Model model = new Model(); 
      
      // GUI elements
      Display display = new Display(); 
      
      // handles operations pertaining to web agent and GUI
      Controller controller = new Controller(model, display);
      
      // start controller operations
      controller.start();
   }
}