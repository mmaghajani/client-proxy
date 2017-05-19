package client; /**
 * Created by mma on 5/19/17.
 */
import javax.swing.*;

/**
 * Created by mma on 6/8/2016.
 */
public class GraphicHandler extends JFrame {
    private static GraphicHandler instance ;
    private final int heightScreen = (int) getToolkit().getScreenSize().getHeight();
    private final int widthScreen = (int) getToolkit().getScreenSize().getWidth();

    private GraphicHandler(){

    }

    /**
     * Returns one instance of this class ( single tone )
     * @return instance of Graphic handler
     */
    public static GraphicHandler getInstance(){
        if( instance == null ){
            instance = new GraphicHandler() ;
        }

        return instance ;
    }

    /**
     * Return height of monitor screen
     * @return
     */
    public int getHeightScreen(){
        return heightScreen ;
    }

    /**
     * Return width of monitor screen
     * @return
     */
    public int getWidthScreen(){
        return widthScreen ;
    }

    /**
     * Place frame f in center of screen
     * @param f
     */
    public void placeInCenter( JFrame f ){
        f.setLocation(widthScreen / 2 - f.getWidth()/2 , heightScreen / 2 - f.getHeight()/2);
    }
}
