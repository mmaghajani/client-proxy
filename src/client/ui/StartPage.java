package client.ui; /**
 * Created by mma on 5/19/17.
 */

import client.Constants;
import client.GraphicHandler;
import client.UserPI;
import client.customView.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class shows start page for signing in or up! For this class supposed a imaginary layout rather than
 * a layout manager for working easier with this.Magnitude of this imaginary layouts is records as final integer
 * Created by mma on 6/8/2016.
 */
public class StartPage extends JFrame {

    private int width = GraphicHandler.getInstance().getWidthScreen()*2 / 3;
    private int height = GraphicHandler.getInstance().getHeightScreen()*4 / 5;
    private MyLabel titleLbl;
    private MyTextField command;
    private MyTextArea board;
    private JButton execute;
    private JToolBar toolBar;

    /**
     * Magnitude of imaginary layouts
     */
    private final int heightOfTitleBar = height / 15;
    private final int widthOfTitleBar = width;
    private final int titleBarX = 0;
    private final int titleBarY = height / 75;
    private final int titleFontSize = 35;
    private final int mainPanelX = 0;
    private final int mainPanelY = heightOfTitleBar;
    private final int heightOfMainPanel = height * 8 / 10;
    private final int widthOfMainPanel = width;
    private final int toolBarX = 0;
    private final int toolBarY = heightOfTitleBar + heightOfMainPanel ;
    private final int heightOfToolBar = height - (heightOfMainPanel + heightOfTitleBar)*95/100;
    private final int widthOfToolBar = width;

    private static StartPage ourInstance = new StartPage();

    public static StartPage getInstance() {
        return ourInstance;
    }

    private StartPage() {
        super();

        initialize();

        setTitle();

        setMainPanel();

        setToolBar();

        AddComponentsToFrame();

        setVisible(true);
    }

    public synchronized void printToBoard(String s){
        board.setText(board.getText() + "\n" + s);
    }

    private void setMainPanel() {
        command = new MyTextField("Command", Constants.buttonJPGPath);
        command.setSize(width * 5/ 6, height / 10);
        command.setLocation(widthOfMainPanel / 2 - command.getWidth() / 2, mainPanelY + heightOfMainPanel / 14);

        board = new MyTextArea("" , Constants.buttonJPGPath);
        board.setEditable(false);
        board.setSize(width * 5 / 6, height * 7 / 10);
        board.setLocation(widthOfMainPanel / 2 - command.getWidth() / 2, mainPanelY + heightOfMainPanel / 8);

        execute = new MyButton("execute", Constants.buttonJPGPath);
        execute.setSize(board.getWidth() / 5, heightOfMainPanel / 12);
        execute.setEnabled(true);
        execute.setLocation(widthOfMainPanel / 2 - execute.getWidth() / 2, mainPanelY + heightOfMainPanel );
        execute.addActionListener(e -> {
            UserPI.getInstance().runCommand(command.getText() + "\n");
        });
    }

    private void initialize() {
        GraphicHandler graphicHandler = GraphicHandler.getInstance();

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graphicHandler.placeInCenter(this);
        this.setResizable(false);
        this.setLayout(null);
        this.setUndecorated(true);

        try {
            UIManager
                    .setLookAndFeel(Constants.nimbusLookAndFeel);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setTitle() {
        titleLbl = new MyLabel(Constants.appName, Constants.labelJPGPath, JLabel.CENTER);
        titleLbl.setLocation(titleBarX, titleBarY);
        titleLbl.setSize(widthOfTitleBar, heightOfTitleBar);
        titleLbl.setForeground(Color.WHITE);
        titleLbl.setFont(new Font(Constants.arilFont, Font.CENTER_BASELINE, titleFontSize));
    }

    private void setToolBar() {
        JButton exit = new MyButton(Constants.exitJPGPath);
        exit.setLocation(0, 0);
        exit.setSize(Constants.sizeOfExitButton, Constants.sizeOfExitButton);
        exit.setIcon(new ImageIcon(getClass().getResource(Constants.exitPNGPath)));
        exit.setBorder(BorderFactory.createEmptyBorder());
        //exit.setBackground(Color.decode("#01aaeb"));
        exit.setToolTipText("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                UserPI.getInstance().runCommand("QUIT");
                dispose();
            }
        });

        toolBar = new JToolBar();
        toolBar.setLocation(toolBarX, toolBarY);
        toolBar.setSize(widthOfToolBar, heightOfToolBar);
        toolBar.setBorder(BorderFactory.createEmptyBorder());
        toolBar.setFloatable(false);
        toolBar.add(exit);
    }

    private void AddComponentsToFrame() {
        getContentPane().add(execute);
        getContentPane().add(titleLbl);
        getContentPane().add(toolBar);
        getContentPane().add(command);
        getContentPane().add(board);
    }

    /**
     * This method draws a blue image background of this frame
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Image image = new ImageIcon(getClass().getResource(Constants.blueBackJPGPath)).getImage();
        g.drawImage(image, 0, 0, width, height, null);

        componentRepaint();
    }

    private void componentRepaint() {
        titleLbl.repaint();
        toolBar.getComponent(0).repaint();
        execute.repaint();
        command.repaint();
        board.repaint();
    }
}
