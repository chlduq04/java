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
		this.setTitle("직원등록");
		this.setSize(200, 400);

		HotelManagerInfo info = HotelManagerInfo.getInfo();
		employee_name = new JTextField();
		employee_sex = new JComboBox(info.getSex());
		employee_address = new JTextField();
		employee_phone = new JTextField();
		
		this.add(new JLabel("직원명"));
		this.add(employee_name);
		this.add(new JLabel("성별"));
		this.add(employee_sex);
		this.add(new JLabel("주소"));
		this.add(employee_address);
		this.add(new JLabel("연락처"));
		this.add(employee_phone);
		
		JButton button = new JButton("직원등록");
		button.addActionListener(this);
		this.add(button);
		button = new JButton("취소");
		button.addActionListener(this);
		this.add(button);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "직원등록"){
			// post the employees info to database
			System.out.println(employee_name.getText());
			System.out.println(employee_sex.getSelectedItem());
			System.out.println(employee_address.getText());
			System.out.println(employee_phone.getText());
			// 이름과 연락처가 공백인지 확인한다.
			if( employee_name.getText().isEmpty() ){
				JOptionPane.showMessageDialog(this, "이름을 입력해주세요");
				employee_name.requestFocus();
			}
			if( employee_phone.getText().isEmpty() ){
				JOptionPane.showMessageDialog(this, "연락처를 입력해주세요");
				employee_phone.requestFocus();
			}
			
			JOptionPane.showMessageDialog(this, "회원가입되었습니다");
			this.dispose();
		}
		else if(e.getActionCommand() == "취소"){
			this.dispose();
		}
	}
}
