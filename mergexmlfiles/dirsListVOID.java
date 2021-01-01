package mergexmlfiles;
import java.io.*;

public class dirsListVOID {

	
	public static void main(String[] args) {

		File directory;

		String directoryName;
		if (args.length != 1){
			 	System.out.println("You need to type an argument!");
				directoryName = null;
				System.exit(1);
				
			}
		else {
			directoryName = args[0];
		}
		 directory = new File(directoryName);
		 
		if (directory.isDirectory() == false) {
			if (directory.exists() == false) 
				System.out.println("There is no such directory!");
			 else 
				System.out.println("That file is not a directory.");
		} else 
			{
			listContents(directory, "");
			}
	}

	public static void listContents(File dir, String indent) {

		String[] files;
		System.out.println(indent + "Directory \"" + dir.getName() + "\":");
		files = dir.list();
		indent += " - ";
		for (int i = 0; i < files.length; i++) {
			File f = new File(dir, files[i]);
			if (f.isDirectory() == false) {
				System.out.println(indent + "File \"" + files[i] + "\":");
			} else {
				listContents(f, indent);
			}

		}

	}
}