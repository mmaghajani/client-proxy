package client.customView;

import javax.swing.*;

/**
 * Created by mma on 6/24/2016.
 */
public class MyErrorPane extends JScrollPane {

    private JTextPane errorPane ;
    public MyErrorPane(JTextPane errorPane){
        super(errorPane , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ) ;
        this.errorPane = errorPane ;
    }

    /**
     * Append a log to end of content
     * @param log text that want to add
     */
    public void append( String log ){
        String s = errorPane.getText() ;
        s += '\n' ;
        s += log ;
        errorPane.setText(s);
    }

}
