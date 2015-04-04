package GUI;

public class InterfaceLift {

   public static void main(String[] args) {
      Model model = new Model();
      Display display = new Display();
      
      Controller controller = new Controller(model, display);
      
      controller.startConnection();
   }
}
