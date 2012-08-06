import java.awt.AWTEvent;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class HotelManagerUserInfoAddFrame extends JFrame implements ActionListener{
	private JTextField user_name;
	private JComboBox user_sex;
	private JTextField user_address;
	private JTextField user_phone;
	
	public HotelManagerUserInfoAddFrame(){
		HotelManagerInfo info = HotelManagerInfo.getInfo();
		user_name = new JTextField();
		user_sex = new JComboBox(info.getSex());
		user_address = new JTextField(); 
		user_phone = new JTextField();
		
		this.setLayout(new GridLayout(5,2));
		this.add(new JLabel("����"));
		this.add(user_name);
		this.add(new JLabel("����"));
		this.add(user_sex);
		this.add(new JLabel("�ּ�"));
		this.add(user_address);
		this.add(new JLabel("����ó"));
		this.add(user_phone);
		
		JButton button = new JButton("���Խ�û");
		button.addActionListener(this);
		this.add(button);
		button = new JButton("���");
		button.addActionListener(this);
		this.add(button);
	
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setSize(200, 300);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print(e.getActionCommand());
		if(e.getActionCommand() == "���Խ�û"){
			// ���� ��û ���� �� ���� ó�� �� â �ݱ�
			System.out.println(user_name.getText());
			System.out.println(user_sex.getSelectedItem());
			System.out.println(user_address.getText());
			System.out.println(user_phone.getText());
			
			JOptionPane.showMessageDialog(this, "ȸ�����ԵǾ����ϴ�");
			// �����͸� ��� �����Ѵ�. ������ ��쿡�� �����ߴٴ� �޽����� ����ش�.
			
			this.dispose();
		}
		else if(e.getActionCommand() == "���"){
			this.dispose();
		}
		
	}
	
}
