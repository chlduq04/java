import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class HotelManagerEmployeeInfoAddFrame extends JFrame implements ActionListener{
	private JTextField employee_name;
	private JComboBox employee_sex;
	private JTextField employee_address;
	private JTextField employee_phone;
	
	public HotelManagerEmployeeInfoAddFrame(){
		setComponent();
	}
	
	private void setComponent(){
		this.setLayout(new GridLayout(5,2));
		this.setTitle("�������");
		this.setSize(200, 400);

		HotelManagerInfo info = HotelManagerInfo.getInfo();
		employee_name = new JTextField();
		employee_sex = new JComboBox(info.getSex());
		employee_address = new JTextField();
		employee_phone = new JTextField();
		
		this.add(new JLabel("������"));
		this.add(employee_name);
		this.add(new JLabel("����"));
		this.add(employee_sex);
		this.add(new JLabel("�ּ�"));
		this.add(employee_address);
		this.add(new JLabel("����ó"));
		this.add(employee_phone);
		
		JButton button = new JButton("�������");
		button.addActionListener(this);
		this.add(button);
		button = new JButton("���");
		button.addActionListener(this);
		this.add(button);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "�������"){
			// post the employees info to database
			System.out.println(employee_name.getText());
			System.out.println(employee_sex.getSelectedItem());
			System.out.println(employee_address.getText());
			System.out.println(employee_phone.getText());
			// �̸��� ����ó�� �������� Ȯ���Ѵ�.
			if( employee_name.getText().isEmpty() ){
				JOptionPane.showMessageDialog(this, "�̸��� �Է����ּ���");
				employee_name.requestFocus();
			}
			if( employee_phone.getText().isEmpty() ){
				JOptionPane.showMessageDialog(this, "����ó�� �Է����ּ���");
				employee_phone.requestFocus();
			}
			
			JOptionPane.showMessageDialog(this, "ȸ�����ԵǾ����ϴ�");
			this.dispose();
		}
		else if(e.getActionCommand() == "���"){
			this.dispose();
		}
	}
}
