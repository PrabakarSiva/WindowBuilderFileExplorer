package fileSystem;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Panel;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.tree.TreeNode;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.lang.Object;



public class FileSystemGUI{
	
	// Initialize main tree used for file system and GUI elements
	DirectoryTrees t = new DirectoryTrees();

	private JFrame frame;
	private JButton btnOpenFile;
	private JTree tree_1;
	private File Selection;
	private File nextFile;
	private String Jtreevar;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextField txtSearch;
	private JList list_1;
	private String[] pinnedFiles = new String[10];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileSystemGUI window = new FileSystemGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileSystemGUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Defines GUI frame
		frame = new JFrame();
		frame.setBounds(100, 100, 654, 462);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Back button implementation
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(t.rootDirectory.getParentFile().exists())
				{
					// Next file becomes current directory and then goes to parent file of current directory
					// FUTURE comments: Stack implementation of back would be better
					nextFile = t.rootDirectory;
					t.rootDirectory = t.rootDirectory.getParentFile();
					tree_1 = new JTree(t.LeftTree());
					scrollPane.setViewportView(tree_1);
					
					// Adds mouse input to get path of selected file (path needs to be reformatted)
					tree_1.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							Jtreevar = tree_1.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
						}
					});
				}
			}
		});
		btnNewButton_1.setBounds(6, 13, 63, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		// Next button implementation
		JButton btnForward = new JButton("Next");
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Sets current directory to next if it exists
				if(nextFile.exists())
				{
					t.rootDirectory = nextFile;
					tree_1 = new JTree(t.LeftTree());
					scrollPane.setViewportView(tree_1);
					tree_1.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							Jtreevar = tree_1.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
							System.out.println(Jtreevar);
						}
					});
				}
			}
		});
		btnForward.setBounds(67, 13, 63, 21);
		frame.getContentPane().add(btnForward);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Pinned Favorites");
		lblNewJgoodiesLabel.setBounds(6, 301, 122, 16);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		
		// Implementation of buttons that allow access to different drives
		JButton btnC = new JButton("C:");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Selection = new File ("C:\\");
				t.rootDirectory = Selection;
				tree_1 = new JTree (t.LeftTree());
				tree_1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						Jtreevar = tree_1.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
					}
				});
				scrollPane.setViewportView(tree_1);
			}
		});
		btnC.setBounds(6, 124, 124, 29);
		frame.getContentPane().add(btnC);
		
		JButton btnD = new JButton("D:");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Selection = new File ("D:\\");
				t.rootDirectory = Selection;
				tree_1 = new JTree (t.LeftTree());
				tree_1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						Jtreevar = tree_1.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
					}
				});
				scrollPane.setViewportView(tree_1);
			}
		});

		btnD.setBounds(6, 150, 124, 29);
		frame.getContentPane().add(btnD);
		
		JButton btnE = new JButton("E:");
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Selection = new File ("E:\\");
				t.rootDirectory = Selection;
				tree_1 = new JTree (t.LeftTree());
				tree_1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						Jtreevar = tree_1.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
					}
				});
				scrollPane.setViewportView(tree_1);
			}
		});
		btnE.setBounds(6, 175, 124, 29);
		frame.getContentPane().add(btnE);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Drives");
		lblNewJgoodiesLabel_1.setBounds(6, 106, 120, 16);
		frame.getContentPane().add(lblNewJgoodiesLabel_1);
		
		// Allows scrolling capabilities 
		scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 13, 390, 294);
		frame.getContentPane().add(scrollPane);
		
		tree_1 = new JTree(t.LeftTree());
		tree_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Jtreevar = tree_1.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
			}
		});
		scrollPane.setViewportView(tree_1);
		
		// Search bar GUI implementation
		txtSearch = new JTextField();
		txtSearch.setText("Search");
		scrollPane.setColumnHeaderView(txtSearch);
		txtSearch.setColumns(10);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				update();
			}
			
			// Updates view with search results if search input exists
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				try {
					boolean empty = txtSearch.getDocument().getText(0, txtSearch.getDocument().getLength()).isEmpty();
					if(!empty)
						update();
					else
					{
						tree_1 = new JTree(t.LeftTree());
						scrollPane.setViewportView(tree_1);
					}
				}
				catch(Exception e)
				{
					
				}
				
			}
			
			public void update()
			{
				try {
					tree_1 = new JTree(t.search(txtSearch.getDocument().getText(0, txtSearch.getDocument().getLength())));
					scrollPane.setViewportView(tree_1);
				}
				catch(Exception e)
				{
					
				}
			}
			
		});
			
		// Pinned favorites implementation	
		// Saves path of pins in an array of up to 10 pins
		JButton btnNewButton = new JButton("Pin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Selection = new File(Jtreevar);
					if (Selection.exists()){
						if(Desktop.isDesktopSupported()) {
							for (int x = 0; x < 10; x++)
							{
								if (pinnedFiles[x] == null)
								{
									pinnedFiles[x] = Jtreevar;
									x = 10;
									
								}
							}
							// Allows user to get path by selecting pin in UI
							list_1 = new JList(pinnedFiles);
							list_1.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent arg0) {
									Jtreevar = (String) list_1.getSelectedValue();
								}
							});
							scrollPane_1.setViewportView(list_1);
						
		
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Awt Desktop is not supported", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "File does not exist", "Error", JOptionPane.INFORMATION_MESSAGE);
						
					}
				
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
			}
		});
		btnNewButton.setBounds(363, 328, 82, 38);
		frame.getContentPane().add(btnNewButton);
		
		
		btnOpenFile = new JButton("Open File");
		btnOpenFile.setBounds(540, 153, 89, 23);
		frame.getContentPane().add(btnOpenFile);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 328, 337, 85);
		frame.getContentPane().add(scrollPane_1);
		
		// Desktop button to return user to their desktop by
		// setting root directory to desktop
		JButton btnDesktop = new JButton("Desktop");
		btnDesktop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						Selection = new File ("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop");
						t.rootDirectory = Selection;
						tree_1 = new JTree (t.LeftTree());
						tree_1.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Jtreevar = tree_1.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
							}
						});
						scrollPane.setViewportView(tree_1);
					}
			
			
		});
		btnDesktop.setBounds(6, 60, 124, 29);
		frame.getContentPane().add(btnDesktop);
		
		// Clears current pinned favorites
		JButton btnCLR = new JButton("Clear");
		btnCLR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int x = 0; x < 10; x++)
				{
					pinnedFiles[x] = null;
				}
				list_1 = new JList(pinnedFiles);
				scrollPane_1.setViewportView(list_1);
			}
		});
		btnCLR.setBounds(363, 375, 82, 38);
		frame.getContentPane().add(btnCLR);
		
		// Open button implementation that checks if path
		// is a file or folder and opens accordingly
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						Selection = new File(Jtreevar);
						if (Selection.exists()){
							if(Desktop.isDesktopSupported() && Selection.isFile()) {
								Desktop.getDesktop().open(Selection);
							}
							else if(Selection.isDirectory())
							{
								t.rootDirectory = Selection;
								tree_1 = new JTree(t.LeftTree());
								tree_1.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										Jtreevar = tree_1.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
									}
								});
								scrollPane.setViewportView(tree_1);
			
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Awt Desktop is not supported", "Error", JOptionPane.INFORMATION_MESSAGE);
							}
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "File does not exist", "Error", JOptionPane.INFORMATION_MESSAGE);
							
						}
					
					
					
				} catch (Exception ex) {
					ex.printStackTrace();
				} 
				
				} 
					
					
			
			}); 

			
	}
}
