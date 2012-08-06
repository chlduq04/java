import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import javax.swing.JTextField;

import server.Main_info;


public class ManagerFrm_Make extends JFrame implements ActionListener {		//계좌 생성 창			
	private Main_info manager;
	private JButton enter;
	private JButton cancel;
	private JLabel numberLabel;
	private JTextField accountNumber;
	private JTextArea information;

	public ManagerFrm_Make(Main_info index) {								//생성자
		manager = index;
		enter = new JButton("생  성");
		cancel = new JButton("취  소");
		numberLabel = new JLabel("새 계좌 번호");
		accountNumber = new JTextField();
		information = new JTextArea("\n\n\n\n\n새로 만들 계좌의 번호를\n입력해주세요.");
		enter.addActionListener(this);
		cancel.addActionListener(this);

		this.add(enter);
		this.add(numberLabel);
		this.add(accountNumber);
		this.add(cancel);
		this.add(information);

		Set();
	}

	public void Set() {														//위치와 크기 지정

		numberLabel.setBounds(170, 15, 150, 15);
		accountNumber.setBounds(170, 35, 150, 40);
		enter.setBounds(170, 85, 150, 40);
		cancel.setBounds(170, 135, 150, 40);
		information.setBounds(10, 10, 155, 150);
		
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(345, 250);

	}

	@Override
	public void actionPerformed(ActionEvent e) {							//버튼 클릭시 행동
		if (e.getSource() == enter) {
			information.setText(manager.Make_new_account(accountNumber.getText()));
		}else if (e.getSource() == cancel)
			this.setVisible(false);
		// TODO Auto-generated method stub

	}
}