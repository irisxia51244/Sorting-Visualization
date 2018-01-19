import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;


public class VisualPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int size = 100;
	private int[] random = new int[size];
	private int[] reverse = new int[size];
	private int[] select;
	
	private Random aRandom = new Random();
	private int max = 500;
	private int min = 0;
	
	private final int height = 300;
	private int width;
	
	//data 
	private double yCoordinate;
	private int dataWidth = 3;
	private int gap;
	private boolean notRun = true;
	public static volatile boolean keepRun;
	
	private String algorithm;
	private String dataSet;
	
	public VisualPanel(){
		width = (dataWidth + gap) * size+3;
		setPreferredSize(new Dimension(width, height));
		createRandomSet();
		createReverseSet();
		generateScale();
	}
	
	public void initial(){
		setSelectedData("Random");
		repaint();
	}
	public void reInitial(){
		createRandomSet();
		createReverseSet();
		generateScale();
		setSelectedData(dataSet);
		repaint();
	}
	
	//begin
	public void beginAnimation(String algorithm, String dataSet){
		this.algorithm = algorithm;
		this.dataSet = dataSet;
		setSelectedData(dataSet);
		startSortThread(algorithm);
		startPaintThread();
	}
	
	//stop
	public void stopAnimation(){
		keepRun = false;
	}
	
	private void startSortThread(String algorithm){
		if(algorithm.equals("Bubble")){
			BubbleSort.sort(select);
			System.out.println("Bubble");
		}
		else if(algorithm.equals("Insertion")){
			InsertionSort.sort(select);
		}
		else if(algorithm.equals("Merge")){
			MergeSort.sort(select);
		}
		else if(algorithm.equals("Quick")){
			QuickSort.sort(select);
		}
		else if(algorithm.equals("Selection")){
			SelectionSort.sort(select);
		}
	}
	
	private void startPaintThread(){
		new Thread(){
			@Override
			public void run(){
				while(keepRun){
					try{
						repaint();
						Thread.sleep(30);
					}catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	@Override
	public void paint(Graphics g){
		if(notRun){
			super.paint(g);
			setCoordinate(g);
			drawAllRect(g);
		}
		else{
			super.paint(g);
			setCoordinate(g);
			drawAllRect(g);
			drawChangingRect(g);
		}
	}
	
	public void setSelectedData(String dataSet){
		if(dataSet.equals("Random")){
			select = random;
		}
		else{
			select = reverse;
		}
	}
	
	public void getReady(){
		notRun = false;
		keepRun = true;
	}
	
	private void createRandomSet(){
	
		for(int i = 0; i < 100; i++){
			random[i] = min + (int)(aRandom.nextDouble()*(max -min));
		}
	}
	
	private void createReverseSet(){
		int[] temp = new int[100];
		for(int i =0; i< 100; i++){
			temp[i] = random[i];
		}
		Arrays.sort(temp);
		for(int i = 0; i < 100; i++){
			reverse[i] = temp[100-i-1];
		}
	}
	
	private void generateScale(){
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for (int i = 0; i< 100; i++)
		{
			if (random[i] > max)
				max = random[i];
			if (random[i] < min)
				min = random[i];
		}
		max = Math.abs(max);
		min = Math.abs(min);
		double theMAX = max > min ? max : min;
		yCoordinate = height / 1.2 / theMAX;
	}
	
	private void setCoordinate(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(0,297);
		g2.scale(1.0, -1.0);
		g2.draw(new Line2D.Double(0.0,0.0,width,0.0));
	}
	
	private void drawAllRect(Graphics g){
		g.setColor(Color.GREEN);
		g.fill3DRect(8, 0, dataWidth, (int)(select[0] * yCoordinate), true);
		for(int i = 1; i < size; i++){
			g.fill3DRect(i * (dataWidth + gap),0,dataWidth, (int)(select[i] * yCoordinate),true);
		}	
	}
	
	private void drawChangingRect(Graphics g){
		int changing[] = getChangingAndPosition();
		int i = changing[0];
		g.setColor(Color.WHITE);
		g.fill3DRect(i*(dataWidth+gap), 0, dataWidth, (int)(select[i]*yCoordinate), true);
		
		i = changing[1];
		g.setColor(Color.RED);
		g.fill3DRect(i*(dataWidth + gap), 0, dataWidth, (int)(select[i]*yCoordinate),true);
	}
	
	private int[] getChangingAndPosition(){
		int[] changing = new int[2];
		if(algorithm.equals("Bubble")){
			changing[0] = BubbleSort.changing;
			changing[1] = BubbleSort.beChanged;
		}
		else if(algorithm.equals("Insertion")){
			changing[0] = InsertionSort.changing;
			changing[1] = InsertionSort.beChanged;
		}
		else if(algorithm.equals("Merge")){
			changing[0] = MergeSort.changing;
			changing[1] = MergeSort.beChanged;
		}
		else if(algorithm.equals("Quick")){
			changing[0] = QuickSort.changing;
			changing[1] = QuickSort.beChanged;
		}
		else if(algorithm.equals("Selection")){
			changing[0] = SelectionSort.changing;
			changing[1] = SelectionSort.beChanged;
		}
		return changing;
	}
}
