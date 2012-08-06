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
		this.add(new JLabel("고객명"));
		this.add(user_name);
		this.add(new JLabel("성별"));
		this.add(user_sex);
		this.add(new JLabel("주소"));
		this.add(user_address);
		this.add(new JLabel("연락처"));
		this.add(user_phone);
		
		JButton button = new JButton("가입신청");
		button.addActionListener(this);
		this.add(button);
		button = new JButton("취소");
		button.addActionListener(this);
		this.add(button);
	
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setSize(200, 300);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print(e.getActionCommand());
		if(e.getActionCommand() == "가입신청"){
			// 가입 신청 전달 후 각종 처리 후 창 닫기
			System.out.println(user_name.getText());
			System.out.println(user_sex.getSelectedItem());
			System.out.println(user_address.getText());
			System.out.println(user_phone.getText());
			
			JOptionPane.showMessageDialog(this, "회원가입되었습니다");
			// 데이터를 디비에 저장한다. 성공할 경우에는 성공했다는 메시지를 띄워준다.
			
			this.dispose();
		}
		else if(e.getActionCommand() == "취소"){
			this.dispose();
		}
		
	}
	
}
