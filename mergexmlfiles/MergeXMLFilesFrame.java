package mergexmlfiles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

//
import javax.swing.Timer;


public class MergeXMLFilesFrame extends JFrame {
    @SuppressWarnings("compatibility:1441562345554527506")
    private static final long serialVersionUID = 1L;
    private ImageIcon imageHelp = new ImageIcon(MergeXMLFilesFrame.class.getResource("help.gif"));
    private ImageIcon imageClose = new ImageIcon(MergeXMLFilesFrame.class.getResource("closefile.gif"));
    private ImageIcon imageOpen = new ImageIcon(MergeXMLFilesFrame.class.getResource("openfile.gif"));
    private JLabel statusBar = new JLabel();
    private JMenuItem menuHelpAbout = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenu menuFile = new JMenu();
    private JMenuBar menuBar = new JMenuBar();
    private JPanel panelCenter = new JPanel();
    private BorderLayout layoutMain = new BorderLayout();
    private JLabel jLabelSourcePath = new JLabel();
    private JLabel jLabelTargetFile = new JLabel();
    private JTextField jTextFieldSourcePath = new JTextField();
    private JTextField jTextFieldFilename = new JTextField();
    private JButton jButtonMerge = new JButton();
    private JProgressBar progressBar = new JProgressBar(); // new JProgressBar(0,100);

    private transient CombFileB cf = new CombFileB(); // read each source xml file

    public MergeXMLFilesFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.setJMenuBar(menuBar);
        this.getContentPane().setLayout(layoutMain);
        panelCenter.setLayout(null);
        panelCenter.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jLabelSourcePath.setText("Path of source xml files");
        jLabelSourcePath.setBounds(new Rectangle(10, 25, 160, 40));
        jLabelSourcePath.setFont(new Font("Arial", 1, 12));
        jLabelTargetFile.setText("Target filename");
        jLabelTargetFile.setBounds(new Rectangle(10, 95, 160, 40));
        jLabelTargetFile.setFont(new Font("Arial", 1, 12));
        jTextFieldSourcePath.setText("c:\\xml");
        jTextFieldSourcePath.setBounds(new Rectangle(203, 40, 145, 20));
        jTextFieldSourcePath.setFont(new Font("Arial", 0, 12));
        jTextFieldSourcePath.setToolTipText("Enter the absolute path of the source xml files ie: G:\\xml");
        jTextFieldSourcePath.setPreferredSize(new Dimension(81, 19));
        jTextFieldFilename.setText("combined.txt");
        jTextFieldFilename.setBounds(new Rectangle(200, 105, 150, 20));
        jTextFieldFilename.setFont(new Font("Arial", 0, 12));
        jTextFieldFilename.setToolTipText("Enter value for destination file");
        jButtonMerge.setText("Merge!");
        jButtonMerge.setToolTipText("Click here to start!");
        jButtonMerge.setFont(new Font("Arial", 1, 12));
        jButtonMerge.setContentAreaFilled(false);
        jButtonMerge.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jButtonMerge.setBounds(new Rectangle(65, 165, 275, 25));
        jButtonMerge.setMnemonic('m');
        progressBar.setBounds(new Rectangle(65, 200, 275, 25));
        progressBar.setToolTipText("Execution progress");
        progressBar.setString("Progress");
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(true);
        progressBar.setIndeterminate(true);

        jButtonMerge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButtonMerge_actionPerformed(e);
            }
        });
        this.setSize(new Dimension(400, 324));
        this.setTitle("MergeXMLFiles");
        menuFile.setText("File");
        menuFileExit.setText("Exit");
        menuFileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                fileExit_ActionPerformed(ae);
            }
        });
        menuHelp.setText("Help");
        menuHelpAbout.setText("About");
        menuHelpAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                helpAbout_ActionPerformed(ae);
            }
        });
        statusBar.setText("");
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuHelp);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        panelCenter.add(progressBar, null);
        panelCenter.add(jTextFieldFilename, null);
        panelCenter.add(jTextFieldSourcePath, null);
        panelCenter.add(jLabelTargetFile, null);
        panelCenter.add(jLabelSourcePath, null);
        panelCenter.add(jButtonMerge, null);
        this.getContentPane().add(panelCenter, BorderLayout.CENTER);
    }

    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    void helpAbout_ActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, new MergeXMLFilesFrame_AboutBoxPanel1(), "About",
                                      JOptionPane.PLAIN_MESSAGE);
    }

    private void jButtonMerge_actionPerformed(ActionEvent e) {
        jButtonMerge.setEnabled(false);
        String target = (jTextFieldFilename.getText() != null) ? jTextFieldFilename.getText() : "combined.txt";
        String directoryName = jTextFieldSourcePath.getText();
        File directory;
        directory = new File(directoryName);

        /*timer*/
        new javax.swing.Timer(500, new ActionListener() {
            int value = 0;

            public void actionPerformed(ActionEvent e) {
                if (value <= progressBar.getMaximum())
                    progressBar.setValue(value++);
            }
        }).start();
        /*- */

        if (directory.isDirectory() == false) {
            if (directory.exists() == false) {
                //System.out.println("There is no such directory!");

                statusBar.setText("There is no " + directory + " directory!");
                jButtonMerge.setEnabled(true);
            } else {
                //System.out.println("That file is not a directory.");
                statusBar.setText("That file " + directory + " is not a directory");
                jButtonMerge.setEnabled(true);
            }
        } else {
            progressBar.setIndeterminate(false);
            statusBar.setText("");
            listContents(directory, target);
            //setProgress(100);             //end
            progressBar.setString("Done!");
        }
    }

    public String[] listContents(File dir, String target) {

        String[] files;
        System.out.println("Directory \"" + dir.getName() + "\":" + dir.getAbsolutePath());
        files = dir.list(new XmlFilter()); // determine number of input source xml files

        //Set  maxValue for progressbar
        initProgress(files.length);

        for (int i = 0; i < files.length; i++) {
            File f = new File(dir, files[i]);
            if (f.isDirectory() == false) {
                System.out.println("Processing file \"" + files[i] + "\":");
                cf.combineFile(files[i], target, dir); // append to final  "combined.txt" file
                setProgress(i + 1); //count processed files starting from 1!

            }
        }
        //progressBar.setString("Done!");
        return files;
    }
    //initialize the value property of JProgressBar
    public void initProgress(final int value) {
        Runnable initialize = new Runnable() {
            public void run() {
                progressBar.setValue(0);
                progressBar.setMaximum(value);

            }
        };
        SwingUtilities.invokeLater(initialize);

    }

    public void setProgress(final int value) {
        // TASK 4b: Write code to alter the value property of JProgressBar
        Runnable setValue = new Runnable() {
            public void run() {
                progressBar.setValue(value);

            }
        };
        SwingUtilities.invokeLater(setValue);
    }

}
