package mylovelypaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JList;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {

	public static final int EXTRA_SMALL = 10;
	public static final int SMALL = 25;
	public static final int MEDIUM = 50;
	public static final int LARGE = 75;
	public static final int EXTRA_LARGE = 100;
	public static final String NO_SHAPE = "none";
	public static final String CIRCLE = "circle";
	public static final String SQUARE = "square";
	// this line adds the string so you know what shape it is
	public static final String RECTANGLE = "rectangle";
	public static final String[] shapeNames = { NO_SHAPE, CIRCLE, SQUARE, RECTANGLE };
	//changes the sizes of the values *****************************************************
	public static final String EX_SMALL = "ex small";
	public static final String SMAL = "small";
	public static final String MED = "medium";
	public static final String LARG = "large";
	public static final String EX_LARGE = "ex large";
	
	public static final String TEXT = "text";
	public static final String[] SizeNames = { EX_SMALL, SMAL, MED, LARG, EX_LARGE };

	private int xStart = 0, yStart = 0;
	private int size;
	private int halfSize;
	private Color color;
	private String shape;

	public DrawingPanel() {
		//********************************************************
		setBackground(Color.BLACK);
		color = Color.GREEN;// default
		size = MEDIUM;
		shape = NO_SHAPE;
		// half size is half the size of the shape
		halfSize = size / 2;
		addMouseListener(this);// make the JPanel listen for mouse events
		addMouseMotionListener(this);// make the JPanel listen for MORE mouse
										// events
	}
	public int getLatestX() {
		return xStart;
	}
	public int getLatestY() {
		return yStart;
	}
	protected int getDrawingSize() {
		return size;
	}

	private void drawShape(int x, int y) {
		// Graphics g = getGraphics();
		Graphics2D g = (Graphics2D) getGraphics();
		g.setColor(color);
		switch (shape) {
		case CIRCLE:
			g.drawOval(x - size / 2, y - size / 2, size, size);
			// g.drawOval(x, y, size, size);//incorrect drawing
			break;
		case SQUARE:
			g.fillRect(x - size / 2, y - size / 2, size, size);
			break;
		// this code creates the Rectangle*************************************
		case RECTANGLE:
			g.fillRect(x - (halfSize * 2), y - halfSize, size * 2, size);
			break;
		default:
			shape = NO_SHAPE;
			g.setStroke(new BasicStroke(size / 8));
			g.drawLine(x, y, x, y);// hahahaha just a dot
		}
		g.dispose();
	}

	protected void record(int x, int y) {
		xStart = x;
		yStart = y;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		drawShape(x, y);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		record(e.getX(), e.getY()); // store the x,y
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// int xEnd = e.getX();
		// int yEnd = e.getY();
		// Graphics g = getGraphics();
		// g.setColor(color);
		// g.drawLine(xStart, yStart, xEnd, yEnd);
		// g.dispose();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//This code changes the style of the mouse when entered in the GUI*************************
		final int x = e.getX();
		final int y = e.getY();
		requestFocus();
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		record(x,y);
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int xEnd = e.getX();
		int yEnd = e.getY();

		Graphics2D g = (Graphics2D) getGraphics();
		g.setStroke(new BasicStroke(size / 10));
		g.setColor(color);
		g.drawLine(xStart, yStart, xEnd, yEnd);
		g.dispose();
		record(xEnd, yEnd); // store the x,y
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		final JList list = new JList(new String[] { "a", "b", "c" });
		list.addMouseMotionListener(this);
		final int x = e.getX();
		final int y = e.getY();
		// only display a hand if the cursor is over the items
		final Rectangle cellBounds = list.getCellBounds(0, list.getModel().getSize() - 1);
		if (cellBounds != null && cellBounds.contains(x, y)) {
			list.setCursor(new Cursor(Cursor.HAND_CURSOR));
			// list.setCursor(Cursor.HAND_CURSOR);
		} else {
			list.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

	public Color getColor() {
		return color;
	}

	protected void setColor(Color c) {
		color = c;
	}

	protected void setShape(String theShape) {
		if (theShape.equalsIgnoreCase(CIRCLE)) {
			shape = CIRCLE;
		} else if (theShape.equalsIgnoreCase(SQUARE)) {
			shape = SQUARE;
		}
		// sets shape to rectangle when the action is clicked***************************
		else if (theShape.equalsIgnoreCase(RECTANGLE)) {
			shape = RECTANGLE;
		} else {
			shape = NO_SHAPE;
			System.out.println("invalid shape was entered " + theShape);
		}

	}	
	protected void setSize(String theSize) {

		if (theSize.equalsIgnoreCase(EX_SMALL)) {
			size = EXTRA_SMALL;
		} else if (theSize.equalsIgnoreCase(SMAL)) {
			size = SMALL;
		} else if (theSize.equalsIgnoreCase(MED)) {
			size = MEDIUM;
		} else if (theSize.equalsIgnoreCase(LARG)) {
			size = LARGE;
		} else if (theSize.equalsIgnoreCase(EX_LARGE)) {
			size = EXTRA_LARGE;
		} else {
			size = SMALL;
			System.out.println("invalid size was entered " + theSize);
		}
	}

	}


