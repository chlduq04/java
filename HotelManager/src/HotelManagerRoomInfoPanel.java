import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class HotelManagerRoomInfoPanel extends JPanel implements ActionListener{
	private JComboBox selected_room;
	private JTextArea room_info;
	
	public HotelManagerRoomInfoPanel(){
		setComponents();
	}
	
	private void setComponents(){
		HotelManagerInfo info = HotelManagerInfo.getInfo();
		room_info = new JTextArea();
		selected_room = new JComboBox(info.getRoomNumbers());
		this.setLayout(new GridLayout(1,2));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,2));
		panel1.add(new JLabel("방번호"));
		panel1.add(selected_room);
		panel.add(panel1);
		JButton button = new JButton("조회");
		button.addActionListener(this);
		panel.add(button);
		this.add(panel);
		this.add(room_info);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "조회"){
			System.out.println(selected_room.getSelectedItem());
			room_info.setText((String)selected_room.getSelectedItem());
			JOptionPane.showMessageDialog(this, "해당 방번호가 없습니다"); 
		}
		
	}
}
