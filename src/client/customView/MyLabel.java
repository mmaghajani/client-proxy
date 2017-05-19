package client.customView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mma on 6/5/2016.
 */
public class MyLabel extends JLabel {

    private Image image = null;

    /**
     * Set text for this label with alignment and set background .
     * @param text text of label
     * @param image background image
     * @param alignment text alignment
     */
    public MyLabel(String text, ImageIcon image, int alignment) {
        super(text, image, alignment);// TODO Auto-generated constructor stub
        this.image = image.getImage() ;
    }

    /**
     * Set text with alignment for this label
     * @param s
     * @param alignment
     */
    public MyLabel(String s, int alignment) {
        // TODO Auto-generated constructor stub
        super(s, alignment);
    }

    /**
     * Set text with alignment for this label and set a background image with given path
     * @param text text of label
     * @param path  path for background image
     * @param alignment alignment for text of label
     */
    public MyLabel(String text, String path, int alignment) {
        super(text, alignment);
        image = new ImageIcon(getClass().getResource(path)).getImage();
    }

    /**
     * Paint label with its image if image not null
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        if (image != null)
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        super.paint(g);
    }
}

