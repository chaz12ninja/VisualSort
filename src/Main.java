import javax.swing.JFrame;

public class Main {
	
	public static int windowWidth = 1600;
	public static int windowHeight = 900;
	
	public static void main(String[] args) {
		
		JFrame obj = new JFrame();
			
		VisualSort vs = new VisualSort();
		obj.setBounds( 10, 10, windowWidth, windowHeight);
		obj.setTitle("Space Impact");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(vs);
	}

}
