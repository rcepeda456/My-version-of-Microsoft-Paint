package mylovelypaint;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JEditorPane;


public class DrawingAndTypingPanel extends DrawingPanel implements KeyListener {

	private Font font;
	private FontMetrics fm;
	private int fontSize;
	private static final String FONT_NAME = "MONOSPACED";
	public static final int FONT_STYLE = Font.ITALIC;
	 private static JEditorPane pane(final String name) {
		    JEditorPane p = new JEditorPane();
		    final Font currFont = p.getFont();
		    p.setFont(new Font(name, currFont.getStyle(), currFont.getSize()));
		    p.setText(name + " - 8\u1d00.\u1d0d.");
		    return p;
		  }
		

	//textpane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

	public DrawingAndTypingPanel() {

		super();
		fontSize = DrawingAndTypingPanel.SMALL;
		font = new Font(FONT_NAME, FONT_STYLE, fontSize);
		fm = getFontMetrics(font);
		addKeyListener(this);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		String s = String.valueOf(e.getKeyChar());
		Graphics2D g = (Graphics2D)getGraphics();
		font = new Font(FONT_NAME, FONT_STYLE, super.getDrawingSize());
		fm = getFontMetrics(font);
		g.setFont(font);
		g.setColor(getColor());
		g.drawString(s, getLatestX()+fm.stringWidth(s), getLatestY());
		record(getLatestX()+fm.stringWidth(s), getLatestY());
//		String str = s;
//		String[] splitStr = str.split("\\s+");

		}


	

	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}