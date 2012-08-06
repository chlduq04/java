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
		this.add(new JLabel("���/��ȸ"), BorderLayout.NORTH);
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("��", new HotelManagerUserInfoPanel());
		pane.addTab("����", new HotelManagerRoomInfoPanel());
		pane.addTab("����", new HotelManagerEmployeeInfoPanel());
		this.add(pane);
	}
}
