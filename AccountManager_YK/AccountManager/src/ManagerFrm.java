import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import server.Main_info;

public class ManagerFrm extends JFrame implements ActionListener {		//���°��� â
	private Main_info account;
	private JButton cancel;
	private JButton make;
	private JButton delete;
	private JTextArea information;
	private ManagerFrm_Make m_account;
	private ManagerFrm_Del d_account;
	
	public ManagerFrm(Main_info index) {								//������
		account = index;
		make = new JButton("��  ��");
		delete = new JButton("��  ��");
		cancel = new JButton("��  ��");
		information = new JTextArea("\n\n\n  ���Ͻô� ����� \n  �����ϼ���.");

		make.addActionListener(this);
		delete.addActionListener(this);
		cancel.addActionListener(this);

		this.add(make);
		this.add(delete);
		this.add(cancel);
		this.add(information);
	
		Set();
	}

	public void Set() 													//ũ��� ��ġ ����
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
	public void actionPerformed(ActionEvent e) {						//��ư Ŭ���� �ൿ
		if (e.getSource() == make) 
			m_account = new ManagerFrm_Make(account);					//����ų� ������ â�� ���� ���� �̰����� ��üȭ
		else if (e.getSource() == delete) 
			d_account = new ManagerFrm_Del(account);
		else if (e.getSource() == cancel)
			this.setVisible(false);
		else
			this.setVisible(false);
	}
}