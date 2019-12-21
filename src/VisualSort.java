import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class VisualSort extends JPanel implements KeyListener,ActionListener{
	
	
	private Timer timer;
	private int delay = 8;
	private int borderThickness = 3;
	private int InfoHeight = 50;
	private String algrithmName;
	private boolean start = false;
	public static int[] array;
	private int arraySize = 0;
	private boolean sorting = false;
	private int pass;
	
	private boolean right = false;
	
	ArrayList<Tower> towers = new ArrayList<Tower>() ;
	ArrayList<Integer> finalHeights = new ArrayList<Integer>();
	
	public VisualSort() {
		//setValues();
	    addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.darkGray);
		g.fillRect(1, 1, Main.windowWidth-8, Main.windowHeight-8);
		
		//borders
		g.setColor(Color.black);
		g.fillRect(0,0,borderThickness,Main.windowHeight-8);
		g.fillRect(0,0,Main.windowWidth,borderThickness);
		g.fillRect(Main.windowWidth-8,0,borderThickness,Main.windowHeight-8);
		g.fillRect(0, InfoHeight, Main.windowWidth-8, borderThickness);
		
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString ("Start "+start,Main.windowWidth-200,32);
		g.drawString ("Sorting "+sorting,Main.windowWidth-350,32);
		g.drawString("Numbers "+arraySize,Main.windowWidth-600,32);
		g.drawString("Algorithm "+algrithmName,Main.windowWidth-1000,32);

		
		if(start&&!sorting) {
			if(!towers.isEmpty()) {
				for(Tower t:towers) {
					t.draw(t.getX(), t.getY(), g);
				}
			}
		}
		if(sorting) {
			for(Tower t:towers) {
				//Temporary just drawing
				Color color = Color.green;
				t.updateValues(t.getHeight(), color, g);
				//Need to animate the movement
			}
		}
		g.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		//if(right) {
			if(sorting && pass<SortAlgo.startI.size()) {
				Tower startTower = towers.get(SortAlgo.startI.get(pass));
				Tower endTower   = towers.get(SortAlgo.endI.get(pass));
	
				//Marking 2 towers active
				startTower.setActive(true);
				endTower.setActive(true);
					
				//Swapping the place of the towers
				swapTowers(startTower, endTower);
					
				//Marking 2 towers inactive
				startTower.setActive(false);
				endTower.setActive(false);
				pass++;
	
			}
			//right = false;
		//}
		if(sorting)markFinishedTowers();
		if(pass>=SortAlgo.startI.size()) sorting = false;
		
		repaint();
	}
	
	private void swapTowers(Tower startTower, Tower endTower) {

		int tempHeight = startTower.getHeight();

		System.out.println("Swapping values of tower #"
							+towers.indexOf(startTower)
							+" with the tower #"
							+towers.indexOf(endTower));
		
		//Exchanging heights for the pass
		startTower.setTowerHeight(endTower.getHeight());
		endTower.setTowerHeight(tempHeight);
	}
	
	private void popArray(int size) {
		Random random = new Random();
		this.arraySize = size;
		array = new int[arraySize];
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(750) +50;
		}
	}
	
	private void markFinishedTowers() {
		for (Tower t : towers) {
			if(t.getHeight() == finalHeights.get(towers.indexOf(t))) {
				t.setMarked(true);
			}else t.setMarked(false);
		}
	}
	
	private void setValues() {
		popArray(100);
		for (int i = 0; i < array.length; i++) {
			towers.add(new Tower((i+4)*15,53,array[i]));
			//System.out.println("#"+i+" tower added with Height "+towers.get(i).getHeight());
		}
		algrithmName = "nothing";
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			setValues();
			start = true;
			for (int i = 0; i < array.length; i++) {
				System.out.print(array[i]+" ");
			}
			System.out.println();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			Sort();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
	}
	
	private void Sort() {
		SortAlgo sortAlgo = new SortAlgo();
		array = sortAlgo.bubbleSort(array);
		for (int i = 0; i < array.length; i++) {
			finalHeights.add(array[i]);
		}
		pass = 0;
		sorting = true;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}

	
