package fileSystem;
import java.io.File;
import java.lang.*;
import java.lang.Object;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

public class DirectoryTrees extends JFrame {
	
	// Starts file explorer at users desktop
	String rootPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
	public File rootDirectory = new File(rootPath);
	DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootDirectory);
	
	//Returns the folder contents for the very left JTree in the GUI
	public DefaultMutableTreeNode LeftTree() {
		 root = addNode(rootDirectory);
		return root;
	}
	
	// Empty tree node
	public DefaultMutableTreeNode empty()
	{
		String s = "";
		return new DefaultMutableTreeNode(s);
	}
	
	// Adds a node for input file and also
	// adds nodes for all of the file's children (subfolders)
	public DefaultMutableTreeNode addNode(File c) {
		File childrenFiles[]=c.listFiles();
		DefaultMutableTreeNode d = new DefaultMutableTreeNode(c);
        for(File child:childrenFiles)
         {
             d.add(new DefaultMutableTreeNode(child.getName()));
         }
		return d;
	}
	
	// Changes root directory to input
	public void setRootDirectory(File f)
	{
		rootDirectory = f;
	}
	
	// Implementation of the search bar 
	public DefaultMutableTreeNode search(String text)
	{
		// Creates a string builder using input arg and returns all nodes that contain string
		DefaultMutableTreeNode searchRootDirectory = new DefaultMutableTreeNode(rootDirectory);
		StringBuilder buff = new StringBuilder(text);
		File childrenFiles[]=rootDirectory.listFiles();
        for(File child:childrenFiles)
         {
        	if(child.getName().contains(buff))
        		// Changes view to display search results only
        		searchRootDirectory.add(new DefaultMutableTreeNode(child.getName()));
         }
        return searchRootDirectory;
	}
}
