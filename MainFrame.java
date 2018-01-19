

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public MainFrame(String title){
		super(title);
		MainPanel aMain = new MainPanel();
		add(aMain);
		//setUndecorated(true);
		setResizable(false);
		setVisible(true);
		pack();
	}
	
	public static void main(String[] agrs){
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		new MainFrame("TCSS598 Sorting Visualization");
	}

}
