package GUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display {

   private JFrame frame;
   private JButton submitButton;
   private JLabel text;
   
   public Display() {
      initFrame(); 
      initButton();
      initText();
   }
 
   private void initFrame() {
      frame = new JFrame();
      frame.setResizable(false);
      frame.setTitle("InterfaceLift Downloader");
      frame.setBounds(100, 100, 275, 145);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      frame.setVisible(true);
   }
   
   private void initButton() {
      submitButton = new JButton();
      submitButton.setText("Download");
      submitButton.setBounds(15, 79, 242, 23);
      frame.getContentPane().add(submitButton);
      frame.getRootPane().setDefaultButton(submitButton);
   }
   
   private void initText() {
      text = new JLabel("Starting connection to InterfaceLift.com...");
      text.setFont(new Font("Arial", Font.BOLD, 11));
      text.setBounds(20,-50,275,145);
      frame.getContentPane().add(text);
   }
   
   public void setText(String str) {
      text.setText(str);
   }
   
   public void setButtonStatus(boolean state) {
      submitButton.setEnabled(state);
   }
}
