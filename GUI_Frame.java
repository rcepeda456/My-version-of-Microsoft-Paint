package mylovelypaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GUI_Frame extends JFrame{

	private DrawingPanel drawingPanel1;//, drawingPanel2;
	
	public GUI_Frame(){
		setSize(500,500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(Color.BLACK);
		
		JPanel mainJP = new JPanel();
//		mainJP.setLayout(new GridLayout(1,2));
		mainJP.setLayout(new BorderLayout());
		drawingPanel1 = new DrawingAndTypingPanel();//new DrawingPanel();
//		drawingPanel1.setBackground(Color.YELLOW);
//		drawingPanel2 = new DrawingPanel();
		
		JPanel westJP = new JPanel();
		westJP.setLayout(new GridLayout(1,2));
		ShapeSelectionPanel shapeSelectionPanel = new ShapeSelectionPanel();
		westJP.add(shapeSelectionPanel);
		SizeSelectionPanel sizeSelectionPanel = new SizeSelectionPanel();
		westJP.add(sizeSelectionPanel);
		
		//westJP.add(sizeSelectionPanel);
		
		ColorChooserPanel colorChooserJP = new ColorChooserPanel();
		
		mainJP.add(colorChooserJP, BorderLayout.NORTH);
		mainJP.add(westJP, BorderLayout.SOUTH);
		mainJP.add(drawingPanel1, BorderLayout.CENTER);
//		mainJP.add(drawingPanel2);
		add(mainJP);
	}
	
	
	
	private class ShapeSelectionPanel extends JPanel implements ActionListener{
		private JRadioButton [] radBtnArr;
		private ButtonGroup radBtnGroup;
		private final int NUM_SHAPES = DrawingPanel.shapeNames.length;
		
		public ShapeSelectionPanel(){
			radBtnGroup = new ButtonGroup();
			radBtnArr = new JRadioButton[NUM_SHAPES];
			setLayout(new GridLayout(NUM_SHAPES,1));
			for(int i=0; i<radBtnArr.length; i++){
				radBtnArr[i] = new JRadioButton(DrawingPanel.shapeNames[i]);//initialized and put a String of text
				radBtnArr[i].setActionCommand(DrawingPanel.shapeNames[i]);//set the ActionCommand
				radBtnArr[i].addActionListener(this);//make it listen for events to trigger the actionPerformed
				radBtnGroup.add(radBtnArr[i]);//add to the group so only 1 is selected at a time
				add(radBtnArr[i]);//add the radio button to the ShapeSelection JPanel
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String actCmd = e.getActionCommand();
			drawingPanel1.setShape(actCmd);
		}
	}
	
	private class SizeSelectionPanel extends JPanel implements ActionListener{
		private JRadioButton [] radBtnArr;
		private ButtonGroup radBtnGroup;
		private final int NUM_SIZES = DrawingPanel.SizeNames.length;
		
		public SizeSelectionPanel(){
			radBtnGroup = new ButtonGroup();
			radBtnArr = new JRadioButton[NUM_SIZES];
			setLayout(new GridLayout(NUM_SIZES,1));
			for(int i=0; i<radBtnArr.length; i++){
				radBtnArr[i] = new JRadioButton(DrawingPanel.SizeNames[i]);//initialized and put a String of text
				radBtnArr[i].setActionCommand(DrawingPanel.SizeNames[i]);//set the ActionCommand
				radBtnArr[i].addActionListener(this);//make it listen for events to trigger the actionPerformed
				radBtnGroup.add(radBtnArr[i]);//add to the group so only 1 is selected at a time
				add(radBtnArr[i]);//add the radio button to the ShapeSelection JPanel
			}
		}
//		private final int NUM_SIZES

		@Override
		public void actionPerformed(ActionEvent e) {
			String actCmd = e.getActionCommand();
			drawingPanel1.setSize(actCmd);
			// TODO Auto-generated method stub
			
		}
		
		//public SizeSelectionPanel(){
		//	setBackground(Color.RED);
		
		}
	//}
	private class ColorChooserPanel extends JPanel implements ActionListener{
		private JButton jb;
		private JColorChooser colChooser;
		public ColorChooserPanel(){
			colChooser = new JColorChooser();
			jb = new JButton("choose a color");
			jb.addActionListener(this);
			add(jb);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Color chosenColor = 
					colChooser.showDialog(null, 
							"Choose Color", 
							drawingPanel1.getColor());
			drawingPanel1.setColor(chosenColor);
			
			
			
//			Component[] comps = this.getParent().getComponents();
//			for(int i=0; i<comps.length; i++){
//				if(!comps[i].equals(drawingPanel1)){
//					comps[i].setBackground(chosenColor);
//				}
//			}
		}
	}}
	
	
	
	
	
	
	
	
	
	
