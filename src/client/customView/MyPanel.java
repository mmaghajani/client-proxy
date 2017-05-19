package client.customView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mma on 6/8/2016.
 */
public class MyPanel extends Container{
    private Image image = null ;

    /**
     * Set background image with given path
     * @param path  path for background
     */
    public MyPanel( String path ){
        this.image = new ImageIcon(getClass().getResource(path)).getImage();
       // this.setVisible(true);
    }

    /**
     * Paint panel with its image if image not null
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        if (image != null)
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        super.paint(g);
    }
}
