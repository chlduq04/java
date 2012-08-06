import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class HotelManagerEmployeeInfoPanel extends JPanel implements ActionListener{
	private JTextField employee_name;
	private JTextArea employee_info;
	public HotelManagerEmployeeInfoPanel() {
		setComponents();
	}
	
	private void setComponents(){
		this.setLayout(new GridLayout(1,2));
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(2,2));
		panel.add(new JLabel("�����̸�"));
		employee_name = new JTextField();
		panel.add(employee_name);
		JButton button = new JButton("�������");
		button.addActionListener(this);
		panel.add(button);
		button = new JButton("��ȸ");
		button.addActionListener(this);
		panel.add(button);
		this.add(panel);
		
		employee_info = new JTextArea();
		this.add(employee_info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "�������"){
			new HotelManagerEmployeeInfoAddFrame();
		}
		else if(e.getActionCommand() == "��ȸ"){
			System.out.println(employee_name.getText());
			employee_info.setText(employee_name.getText());
			// �Է��� ������쿡�� ó���� ������Ѵ�. 
			if(employee_name.getText().isEmpty()){
				System.out.println('h');
				JOptionPane.showMessageDialog(this, "��ȸ�� ������ �̸��� �Է����ּ���");
				employee_name.requestFocus();
			}
			else{
				JOptionPane.showMessageDialog(this, "�ش� �̸��� ������ �����ϴ�");
			}
		}
		System.out.print(e.getActionCommand());
	} 
}
