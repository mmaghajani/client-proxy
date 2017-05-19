package client.customView;


import client.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The class is a password field that has auto set text for field name
 * Created by mma on 6/8/2016.
 */
public class MyPasswordField extends JPasswordField {

    private String text = "";
    private boolean flag = true;
    private Image image = null;

    private final int fontSize = 12;

    public MyPasswordField() {
        super();
    }

    /**
     * Set auto text for this field and set background image with given path
     *
     * @param name auto text for the field
     * @param path path for background image
     */
    public MyPasswordField(String name, String path) {
        super();
        text = name;
        this.image = new ImageIcon(getClass().getResource(path)).getImage();
        this.setFont(new Font(Constants.arilFont, Font.CENTER_BASELINE, fontSize));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (flag == true) {
                    char c = 0;
                    MyPasswordField.this.setEchoChar(c);
                    MyPasswordField.this.setForeground(Color.LIGHT_GRAY);
                    MyPasswordField.this.setText(text);
                }

            }
        });
        thread.start();

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (flag == true) {
                    MyPasswordField.this.setCaretPosition(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (flag == true) {
                    if (e.getKeyChar() != '\b') {
                        MyPasswordField.this.setForeground(Color.BLACK);
                        MyPasswordField.this.setEchoChar('•');
                        MyPasswordField.this.setText("");
                        flag = false;
                    }
                } else {
                    if (MyPasswordField.this.getText().equals("")) {
                        MyPasswordField.this.setForeground(Color.LIGHT_GRAY);
                        char c = 0;
                        MyPasswordField.this.setEchoChar(c);
                        MyPasswordField.this.setText(text);
                        MyPasswordField.this.setCaretPosition(0);
                        flag = true;
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    @Override
    public char[] getPassword() {
        String s = new String(super.getPassword());
        if (s.equals("Password") && getCaretPosition() == 0)
            return null;
        else
            return super.getPassword();
    }

    @Override
    public void paint(Graphics g) {
        if (image != null)
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        super.paint(g);
    }
}
