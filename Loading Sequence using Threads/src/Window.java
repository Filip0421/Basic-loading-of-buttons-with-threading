import java.awt.Dimension;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
	
	private String sentence;
	private JPanel panel;
	
	private final int width = 300;
	private final int height = 300;
	
	public Window(String sentence) {
		
		this.sentence = sentence;

		JFrame frame = new JFrame();
		panel = new JPanel();
		
		frame.add(panel);
		//this.loadButtons();
		loadButtonsNoThread();
		loadButtons();
		
		frame.pack();
		frame.setMinimumSize(new Dimension(width,height));
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		frame.setVisible(true);
	}
	
	
	private void loadButtons(){
		
		long start = System.currentTimeMillis();
		
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		//Array of words that will be converted into buttons
		String[] words = this.sentence.split(" ");
		for(String word: words) {
			service.execute(new Runnable() {
				final String wordButton = word;
				@Override
				public void run() {
				//	System.out.println("Creating a button that contains the word: "+word);
					// TODO Auto-generated method stub
					panel.add(new Button(word));
				}
				
			});
		}
		panel.repaint();
		service.shutdown();
		
		
		long end = System.currentTimeMillis();
		System.out.println("Time take to load the text with threads: "+(end-start)+" ms");
	}
	
	
	private void loadButtonsNoThread() {
		String[] words = this.sentence.split(" ");
		
		long start = System.currentTimeMillis();
		
		for(String word:words) {
			panel.add(new Button(word));
		}
		
		panel.repaint();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time take to load the text no threads: "+(end-start)+" ms");
	}
}
