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
		this.add(new JLabel("���� ����"), BorderLayout.NORTH);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,2));
		panel.add(new JLabel("����"));
		panel.add(customer_name);
		panel.add(new JLabel("üũ��(YYYY/MM/DD"));
		panel.add(checkin_date);
		panel.add(new JLabel("��"));
		panel.add(new JComboBox(info.getDays()));
		panel.add(new JLabel("����"));
		panel.add(new JComboBox(info.getRoomNumbers()));
		JButton button = new JButton("���� ���/����");
		button.addActionListener(this);
		panel.add(button);
		button = new JButton("���� ���");
		button.addActionListener(this);
		panel.add(button);
		// ������ ����� ��񿡼� ���´�.
		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print(e.getActionCommand());
		
	}
}
