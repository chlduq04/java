import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class HotelManagerFrame extends JFrame {
	public HotelManagerFrame(String title, int width, int height){
		super(title);
		this.setSize(width, height);
		this.setComponents();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public HotelManagerFrame(String title){
		super(title);
	}
	private void setComponents(){
		HotelManagerJMenuBar menubar = new HotelManagerJMenuBar();
		this.setJMenuBar(menubar);
		this.setLayout(new GridLayout(2,1));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(new HotelManagerReservationPanel());
		panel.add(new HotelManagerStatusPanel());
		this.add(panel);
		this.add(new HotelManagerInfoPanel());
	}
}
