import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	
	private VisualPanel bubbleVisual = new VisualPanel();
	private VisualPanel insertionVisual = new VisualPanel();
	private VisualPanel mergeVisual = new VisualPanel();
	private VisualPanel quickVisual = new VisualPanel();
	private VisualPanel selectionVisual = new VisualPanel();
	private JPanel cmdPanel = new JPanel();
	private JPanel visualPanels = new JPanel();
	private ButtonGroup dataSetGroup = new ButtonGroup();
	
	private JRadioButton[] dataSetButton = new JRadioButton[2];
	
	private JButton start = new JButton("Start");
	
	public MainPanel(){
		
		//add buttons to ButtonGroup
		dataSetButton[0] = new JRadioButton("Random");
		dataSetGroup.add(dataSetButton[0]);
		dataSetButton[1] = new JRadioButton("Reverse");
		dataSetGroup.add(dataSetButton[1]);
		
		//set default
		dataSetButton[0].setSelected(true);
		//create data buttons
		
		dataSetButton[0].addActionListener(new dataSetListener());
		dataSetButton[1].addActionListener(new dataSetListener());
		dataSetButton[0].setForeground(Color.WHITE);
		dataSetButton[1].setForeground(Color.WHITE);
		
		
		
		start.addActionListener(new startListener());
		
		//add to cmdPanel
		cmdPanel.setLayout(new FlowLayout());

		for(int i = 0; i < 2 ; i++){
			cmdPanel.add(dataSetButton[i]);
		}
		cmdPanel.add(start);
		cmdPanel.setBackground(Color.BLACK);
		
		
		bubbleVisual.initial();
		insertionVisual.initial();
		mergeVisual.initial();
		quickVisual.initial();
		selectionVisual.initial();
		
		visualPanels.setBackground(java.awt.Color.BLACK);
		visualPanels.setLayout(new GridLayout(0,3,0,0));
		
		Border sortOuter = BorderFactory.createLineBorder(Color.WHITE,3);
		//Border sortInner = BorderFactory.createEmptyBorder();
		//Border sortBorder = BorderFactory.createCompoundBorder(sortOuter,sortInner);
		JLabel bubble = new JLabel("Bubble Sort");
		bubble.setAlignmentX(Component.CENTER_ALIGNMENT);
		bubble.setForeground(Color.WHITE);
		bubbleVisual.add(bubble);
		bubbleVisual.setBackground(java.awt.Color.BLACK);
		bubbleVisual.setBorder(sortOuter);
		
		JLabel insertion = new JLabel("Insertion Sort");
		insertion.setAlignmentX(Component.CENTER_ALIGNMENT);
		insertion.setForeground(Color.WHITE);
		insertionVisual.add(insertion);
		insertionVisual.setBackground(java.awt.Color.BLACK);
		insertionVisual.setBorder(sortOuter);
		
		
		JLabel merge = new JLabel("Merge Sort");
		merge.setAlignmentX(Component.CENTER_ALIGNMENT);
		merge.setForeground(Color.WHITE);
		mergeVisual.add(merge);
		mergeVisual.setBackground(java.awt.Color.BLACK);
		mergeVisual.setBorder(sortOuter);
		
		JLabel quick = new JLabel("Quick Sort");
		quick.setAlignmentX(Component.CENTER_ALIGNMENT);
		quick.setForeground(Color.WHITE);
		quickVisual.add(quick);
		quickVisual.setBackground(java.awt.Color.BLACK);
		quickVisual.setBorder(sortOuter);
		
		JLabel selection = new JLabel("Selection Sort");
		selection.setAlignmentX(Component.CENTER_ALIGNMENT);
		selection.setForeground(Color.WHITE);
		selectionVisual.add(selection);
		selectionVisual.setBackground(java.awt.Color.BLACK);
		selectionVisual.setBorder(sortOuter);
		
		visualPanels.add(bubbleVisual);
		visualPanels.add(insertionVisual);
		visualPanels.add(mergeVisual);
		visualPanels.add(quickVisual);
		visualPanels.add(selectionVisual);
		
		
		JPanel titlePanel = new JPanel();
		Dimension titleSize = new Dimension();
		titleSize.width = visualPanels.getWidth();
		titleSize.height = 150;
		titlePanel.setPreferredSize(titleSize);
		//titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);;
		JLabel projectName = new JLabel("Sorting Visualization");
		projectName.setFont(new Font("Serif",Font.BOLD,50));
		Border inner = BorderFactory.createLineBorder(Color.WHITE,1);
		Border outer = BorderFactory.createEmptyBorder(30,0,0,0);
		Border border = BorderFactory.createCompoundBorder(outer,inner);
		projectName.setBorder(border);
		projectName.setAlignmentX(Component.CENTER_ALIGNMENT);
		//titlePanel.setBorder(border);
		projectName.setForeground(java.awt.Color.WHITE);
		
		
		JLabel authorDate = new JLabel("Author: SHIHUI XIA   Date:11/30/2016");
		authorDate.setFont(new Font("Serif",Font.BOLD,20));
		authorDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		Border outer2 = BorderFactory.createEmptyBorder(10,0,0,0);
		authorDate.setBorder(outer2);
		authorDate.setForeground(Color.WHITE);
		
		
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.add(projectName);
		titlePanel.add(authorDate);
		
		titlePanel.setBackground(java.awt.Color.BLACK);
		
		this.setLayout(new BorderLayout());
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(visualPanels, BorderLayout.CENTER);
		this.add(cmdPanel, BorderLayout.SOUTH);
	}
	
	private class dataSetListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			String dataSet = e.getActionCommand();
			bubbleVisual.setSelectedData(dataSet);
			System.out.println(dataSet);
			bubbleVisual.repaint();
			insertionVisual.setSelectedData(dataSet);
			insertionVisual.repaint();
			mergeVisual.setSelectedData(dataSet);
			mergeVisual.repaint();
			quickVisual.setSelectedData(dataSet);
			quickVisual.repaint();
			selectionVisual.setSelectedData(dataSet);
			selectionVisual.repaint();
		}
	}
	
	private class startListener implements ActionListener{
		String dataSet;
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("Start")){
				//check selected data set
				
				for(int i = 0; i < 2; i++){
					if(dataSetButton[i].isSelected()){
						dataSet = dataSetButton[i].getText();
						break;
					}
				}
				setEnable(false);
				start.setText("Reset");
				bubbleVisual.getReady();
				bubbleVisual.beginAnimation("Bubble", dataSet);
				insertionVisual.getReady();
				insertionVisual.beginAnimation("Insertion", dataSet);
				mergeVisual.getReady();
				mergeVisual.beginAnimation("Merge", dataSet);
				quickVisual.getReady();
				quickVisual.beginAnimation("Quick", dataSet);
				selectionVisual.getReady();
				selectionVisual.beginAnimation("Selection", dataSet);
			}
			else
			{
				bubbleVisual.stopAnimation();
				insertionVisual.stopAnimation();
				mergeVisual.stopAnimation();
				quickVisual.stopAnimation();
				selectionVisual.stopAnimation();
				
				bubbleVisual.reInitial();
				insertionVisual.reInitial();
				mergeVisual.reInitial();
				quickVisual.reInitial();
				selectionVisual.reInitial();
				
				setEnable(true);
				start.setText("Start");
				
			}
		}
		
		private void setEnable(boolean b){
			
			for(int i = 0; i < 2; i++){
				dataSetButton[i].setEnabled(b);
			}
		}
	}
	
}
