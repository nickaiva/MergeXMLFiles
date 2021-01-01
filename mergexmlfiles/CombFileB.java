package mergexmlfiles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

/**
 * A <i>BINARY STREAM</i> version used to merge or combine, several small files
 * to a greater destination or target file.
 *
 * @author nick augustakis, nickaiva
 * @version 2 on 28/1/2008
 */
public class CombFileB {

    /*
	public static void main(String args[ ]) {
		
		String target ="combined.xml";
		if (args.length != 1){
		 	System.out.println("You need to type an argument for the combined file:");
		 	target = null;
			System.exit(1);
			
		}
	else {
		target = args[0];
	}
		
		//CombFileB cf = new CombFileB();
		
		String names[] = { "35BA51CDF0DF4213EE5DF4E82E711C7A.xml", "2.xml" };			// It needs dynamic input! 
		for (int i = 0; i <= names.length - 1; i++) {
			cf.combineFile(names[i],  target );
		}
	
	}
     */
    /**
     * @param name for source small xml filename
     * @param target a txt filename for final combined file to store all .xml
     * source files
     * @param dir current working directory
     * @throws
     *
     */

    public void combineFile(String name, String target, File dir) {

        OutputStreamWriter out = null;
        InputStreamReader in = null;
        BufferedReader br = null;

        try {
            //System.out.println(" target is "+ target  );	
            // open target file combined.txt to append all small files

            FileOutputStream fos = new FileOutputStream(dir.getAbsolutePath() + "\\" + target, true);
            // final combined file to store all .xml source files
            FileDescriptor fd = fos.getFD();		 //Empty all buffers

            BufferedOutputStream bos = new BufferedOutputStream(fos);
            out = new OutputStreamWriter(bos, "UTF8");
            /*		
			out = new OutputStreamWriter(new BufferedOutputStream(
					new FileOutputStream( dir.getAbsolutePath() + "\\"+ target  , true)), "utf8"); 
			 // final combined file to store all .xml source files
             */

            //System.out.println(" directory is "+dir );
            FileInputStream fis = new FileInputStream(dir.getAbsolutePath() + "\\" + name); //  source xml file
            BufferedInputStream bis = new BufferedInputStream(fis);
            in = new InputStreamReader(bis, "UTF8");
            /*			 
			in = new InputStreamReader(new BufferedInputStream(
					new FileInputStream(dir.getAbsolutePath() + "\\"+ name)), "UTF8");   //  source xml files
             */
            br = new BufferedReader(in);
            String line = br.readLine();
            while (line != null) {
                //System.out.println(line);
                out.write(line.trim());		// append(line.trim());
                line = br.readLine();
            }
            out.flush();
            fd.sync();	  //Empty all buffers to disc 
            System.out.println("End of processing: " + name);
        } catch (UnsupportedEncodingException ue) {
            System.out.println("Not supported Encoding! ");
            ue.printStackTrace();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Unable to find file: " + name,
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage() + "Unable to find file: "
                    + name + " in the specified directory.");
            //ex.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Handle the exception
            System.out.println("An Error happened!");
            e.printStackTrace();

        } finally {
            try {
                br.close();
                in.close();
                out.flush();
                out.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
            br = null;
            in = null;
            out = null;
        }

    }
}
