import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import server.Main_info;

public class ManagerFrm extends JFrame implements ActionListener {		//계좌관리 창
	private Main_info account;
	private JButton cancel;
	private JButton make;
	private JButton delete;
	private JTextArea information;
	private ManagerFrm_Make m_account;
	private ManagerFrm_Del d_account;
	
	public ManagerFrm(Main_info index) {								//생성자
		account = index;
		make = new JButton("생  성");
		delete = new JButton("삭  제");
		cancel = new JButton("취  소");
		information = new JTextArea("\n\n\n  원하시는 기능을 \n  선택하세요.");

		make.addActionListener(this);
		delete.addActionListener(this);
		cancel.addActionListener(this);

		this.add(make);
		this.add(delete);
		this.add(cancel);
		this.add(information);
	
		Set();
	}

	public void Set() 													//크기와 위치 조정
	{
		make.setBounds(170, 10, 150, 40);
		delete.setBounds(170, 60, 150, 40);
		cancel.setBounds(170, 110, 150, 40);
		information.setBounds(10, 10, 155, 140);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(345, 197);

	}

	@Override
	public void actionPerformed(ActionEvent e) {						//버튼 클릭시 행동
		if (e.getSource() == make) 
			m_account = new ManagerFrm_Make(account);					//만들거나 삭제시 창을 띄우기 위해 이곳에서 객체화
		else if (e.getSource() == delete) 
			d_account = new ManagerFrm_Del(account);
		else if (e.getSource() == cancel)
			this.setVisible(false);
		else
			this.setVisible(false);
	}
}