import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class HotelManagerUserInfoPanel extends JPanel implements ActionListener {
	private JTextArea customer_info;
	private JTextField customer_name;
	
	public HotelManagerUserInfoPanel(){
		setComponents();
	}
	
	private void setComponents(){
		this.setLayout(new GridLayout(1,2));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		customer_name = new JTextField();
		panel.add(new JLabel("����"));
		panel.add(customer_name);
		JButton button = new JButton("ȸ������");
		button.addActionListener(this);
		panel.add(button);
		button = new JButton("��ȸ");
		button.addActionListener(this);
		panel.add(button);
		this.add(panel);
		customer_info = new JTextArea();
		this.add(customer_info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "ȸ������"){
			new HotelManagerUserInfoAddFrame();
		}
		else if(e.getActionCommand() == "��ȸ"){
			// �ش� �̸����� ���� ��ȸ�Ѵ�.
			// ������� ������ ó���ϸ� �ǰ�, ������ �ȳ��޼��� ���
			System.out.println(customer_name.getText());
			
			customer_info.setText(customer_name.getText());
			// else 
			JOptionPane.showMessageDialog(this, "�ش� �̸��� ���� ȸ���� �����ϴ�");
			// end of else 
		}
	}
}
