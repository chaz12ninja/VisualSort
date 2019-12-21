import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;


public class Tower {
	
	private int X; 
	private int Y; 
	private int value;
	private int towerWidth = 10;
	private int towerHeight = 0;
	private int steps = 5;
	private boolean grown = false;
	private boolean marked = false;
	private boolean active = false;
	private Color color;
	//private int counter =0;
	
	public Tower(int X, int Y, int value) {
		this.value = value;
		setX(X);
		setY(Y);
	}
	
	public void draw(int X, int Y,Graphics g) {
		if(!grown) growup();
		g.setColor(Color.cyan);
		g.fillRect(X,Y,towerWidth,getHeight());
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString (""+getHeight(),X-5,getHeight()+65);
	}
	
	public void updateValues(int newtowerHeight,Color color, Graphics g) {
		//setTowerHeight(newtowerHeight);
		g.setColor(getColor());
		g.fillRect(X,Y,towerWidth,towerHeight);
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString (""+getHeight(),X-5,getHeight()+65);
	}
	
	private void growup() {
		towerHeight+=steps;
		if(towerHeight>=value) {
			towerHeight = value;
			grown = true;
		}
	}
	
	private Color getColor() {
		if(active) return Color.yellow;
		else if(marked) return Color.green;
		else return Color.cyan;
	}
	
	public void setX(int X) { this.X = X; }
	public void setY(int Y) { this.Y = Y; }
	public void setTowerHeight(int towerHeight) { this.towerHeight = towerHeight; }
	public void setActive(boolean status) { active = status;}
	public void setMarked(boolean status) { marked = status;}
	
	public int getX() { return X; }
	public int getY() { return Y; }
	public int getHeight() { return towerHeight; }
}
