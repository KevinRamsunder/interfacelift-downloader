package GUI;

public class Controller {

   private Model model;
   private Display display;

   public Controller(Model model, Display display) {
      this.model = model;
      this.display = display;
   }

   public void startConnection() {
      display.setText("Starting connection to InterfaceLift.com...");
      display.setButtonStatus(false);
      
      model.startScraping();
      
      display.setText("");
      display.setButtonStatus(true);
   }
}