import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
    private JTextField jTextField;
    private boolean isLetters;
    private JLabel dialogMassage;
    public KeyInputs(JTextField jTextField,boolean isLetters){
        this.jTextField=jTextField;
        this.isLetters = isLetters;
        this.dialogMassage = new JLabel();
        this.dialogMassage.setBounds(Constants.WINDOW_WIDTH/2-100,Constants.WINDOW_HEIGHT/2-100,200,200);

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (isLetters){
            textLettersValidation();
        }else {
            textDigitsValidation();
        }

    }

    //!this.jTextField.getText().matches("[a-zA-Z]{1,20}"
    public  void textLettersValidation() {
        if (!this.jTextField.getText().matches("[\\D]{1,20}")) {
            if (!this.jTextField.getText().equals("")){
                showMessage("only letters");
            }
        }
    }
    public  void textDigitsValidation(){
        if (!this.jTextField.getText().matches("[\\d]")){
            if (!this.jTextField.getText().equals("")){
                showMessage("Only one number!");
            }
        }
    }
    private void showMessage(String textMessage){
        this.jTextField.setText("");
        JOptionPane.showMessageDialog(this.dialogMassage,textMessage);
    }



}
