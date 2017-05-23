package client.customView;

import client.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by mma on 5/23/2017.
 */
public class MyTextArea extends JTextArea {
    private String text = "" ;
    private boolean flag = true ;
    private Image image = null ;
    private final int fontSize = 12 ;

    public MyTextArea(){
        super() ;
    }

    /**
     * Set auto text for this field and set background image with given path
     * @param name auto text for the field
     * @param path path for background image
     */
    public MyTextArea( String name , String path){
        super() ;
        text = name ;
        this.image = new ImageIcon(getClass().getResource(path)).getImage();
        this.setFont(new Font(Constants.arilFont, Font.CENTER_BASELINE , fontSize ) );

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if( flag == true ) {
                    MyTextArea.this.setForeground(Color.LIGHT_GRAY);
                    MyTextArea.this.setText(text);
                }

            }
        }) ;
        thread.start();


        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if( flag == true ){
                    MyTextArea.this.setCaretPosition(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if( flag == true ) {
                    if( e.getKeyChar() != '\b') {
                        MyTextArea.this.setForeground(Color.BLACK);
                        MyTextArea.this.setText("");
                        flag = false;
                    }
                }else{
                    if( MyTextArea.this.getText().equals("")) {
                        MyTextArea.this.setForeground(Color.LIGHT_GRAY);
                        MyTextArea.this.setText(text);
                        MyTextArea.this.setCaretPosition(0);
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
    public void paint(Graphics g) {
        if (image != null)
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        super.paint(g);
    }
}
