import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class HotelManagerInfoPanel extends JPanel{
	public HotelManagerInfoPanel(){
		setComponents();
	}
	
	private void setComponents(){
		this.setLayout(new BorderLayout());
		this.add(new JLabel("µî·Ï/Á¶È¸"), BorderLayout.NORTH);
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("°í°´", new HotelManagerUserInfoPanel());
		pane.addTab("°´½Ç", new HotelManagerRoomInfoPanel());
		pane.addTab("Á÷¿ø", new HotelManagerEmployeeInfoPanel());
		this.add(pane);
	}
}
