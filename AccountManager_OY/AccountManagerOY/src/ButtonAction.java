import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class ButtonAction implements ActionListener{

	private JFrame new_frame;
	public ButtonAction(JFrame frame)
	{
		new_frame = frame;
		this.actionPerformed(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		new_frame = new JFrame();
	}
}
