import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class HotelManagerReservationPanel extends JPanel implements ActionListener{
	private JTextField customer_name;
	private JTextField checkin_date;
	private JComboBox days;
	private JComboBox room_number;
	
	public HotelManagerReservationPanel(){
		HotelManagerInfo info = HotelManagerInfo.getInfo();
		customer_name = new JTextField();
		checkin_date = new JTextField();
		days = new JComboBox(info.getDays());
		this.setLayout(new BorderLayout());
		this.add(new JLabel("투숙 예약"), BorderLayout.NORTH);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,2));
		panel.add(new JLabel("고객명"));
		panel.add(customer_name);
		panel.add(new JLabel("체크인(YYYY/MM/DD"));
		panel.add(checkin_date);
		panel.add(new JLabel("박"));
		panel.add(new JComboBox(info.getDays()));
		panel.add(new JLabel("객실"));
		panel.add(new JComboBox(info.getRoomNumbers()));
		JButton button = new JButton("예약 등록/변경");
		button.addActionListener(this);
		panel.add(button);
		button = new JButton("예약 취소");
		button.addActionListener(this);
		panel.add(button);
		// 객실의 목록은 디비에서 얻어온다.
		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print(e.getActionCommand());
		
	}
}
