import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Button extends JButton{
	
	private Button button = this;
	private final String text;
	
	public Button(String text) {
		super(text);
		
		this.setBorder(null);
		
		Color color = Color.WHITE;	
		
		this.text = text;
		
		int width = textToWidth(text);
		int height = this.getFontMetrics(this.getFont()).getHeight();
		this.setSize(width, height);
		this.setBackground(color);
		
		this.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                	//Button enters
                } else {
                	//Button exits
                }
                if (model.isPressed()) {
                	button.setForeground(new Color(0, 51, 204));
                }
            }
        });
		
		this.repaint();
	}
	
	private int textToWidth(String sentence) {
		int width = 0;
		//for(String s:sentence.split("")) {
		String[] array = sentence.split("");
		for(int i =0;i<array.length;i++) {
			//s is a char
			width += button.getFontMetrics(this.getFont()).charWidth(sentence.charAt(i));
			
		}
		return width;
	}
	
	

}
