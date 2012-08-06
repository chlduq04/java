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
		panel.add(new JLabel("고객명"));
		panel.add(customer_name);
		JButton button = new JButton("회원가입");
		button.addActionListener(this);
		panel.add(button);
		button = new JButton("조회");
		button.addActionListener(this);
		panel.add(button);
		this.add(panel);
		customer_info = new JTextArea();
		this.add(customer_info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "회원가입"){
			new HotelManagerUserInfoAddFrame();
		}
		else if(e.getActionCommand() == "조회"){
			// 해당 이름으로 고객을 조회한다.
			// 있을경우 데이터 처리하면 되고, 없으면 안내메세지 출력
			System.out.println(customer_name.getText());
			
			customer_info.setText(customer_name.getText());
			// else 
			JOptionPane.showMessageDialog(this, "해당 이름을 가진 회원이 없습니다");
			// end of else 
		}
	}
}
