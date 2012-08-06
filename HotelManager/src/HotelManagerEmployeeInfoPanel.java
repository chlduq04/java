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
		panel.add(new JLabel("직원이름"));
		employee_name = new JTextField();
		panel.add(employee_name);
		JButton button = new JButton("직원등록");
		button.addActionListener(this);
		panel.add(button);
		button = new JButton("조회");
		button.addActionListener(this);
		panel.add(button);
		this.add(panel);
		
		employee_info = new JTextArea();
		this.add(employee_info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "직원등록"){
			new HotelManagerEmployeeInfoAddFrame();
		}
		else if(e.getActionCommand() == "조회"){
			System.out.println(employee_name.getText());
			employee_info.setText(employee_name.getText());
			// 입력이 없을경우에는 처리를 해줘야한다. 
			if(employee_name.getText().isEmpty()){
				System.out.println('h');
				JOptionPane.showMessageDialog(this, "조회할 직원의 이름을 입력해주세요");
				employee_name.requestFocus();
			}
			else{
				JOptionPane.showMessageDialog(this, "해당 이름의 직원이 없습니다");
			}
		}
		System.out.print(e.getActionCommand());
	} 
}
