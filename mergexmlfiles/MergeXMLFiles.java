package mergexmlfiles;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MergeXMLFiles {

    public MergeXMLFiles() {
        Frame frame = new MergeXMLFilesFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.gc();
                System.exit(0);

            }
        });
        frame.setVisible(true);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //com.sun.java.swing.plaf.motif.MotifLookAndFeel javax.swing.plaf.metal.MetalLookAndFeel
            new MergeXMLFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        new MergeXMLFiles();
    }
}
