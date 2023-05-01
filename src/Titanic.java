import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Titanic extends JFrame {

    private Image icon;

    public Titanic() {
        this.setTitle("Titanic Passengers Data");
        try {
            this.icon = ImageIO.read(new File("src/data/titanicIcon.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIconImage(this.icon);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.add(new ManageScreen(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setVisible(true);
        this.setResizable(false);

    }



}