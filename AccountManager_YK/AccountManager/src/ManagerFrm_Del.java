import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import server.Main_info;

public class ManagerFrm_Del extends JFrame implements ActionListener,ListSelectionListener {//���� ���� â
	private Main_info manager;
	private JButton enter;
	private JButton cancel;
	private JList account_list;
	private JTextArea information;
	private int clicklist=0;
	public ManagerFrm_Del(Main_info index) 													//������
	{
		manager = index;
		enter = new JButton("Ȯ  ��");
		cancel = new JButton("��  ��");
		account_list = new JList(manager.check());											//���� ����Ʈ�� �޾ƿ´�
		information = new JTextArea("\n\n  ������ ���� ��ȣ��\n  Ŭ������ \n  Ȯ�� ��ư�� ���� �ּ���.");

		enter.addActionListener(this);
		cancel.addActionListener(this);
		account_list.addListSelectionListener(this);

		this.add(cancel);
		this.add(enter);
		this.add(information);
		this.add(account_list);

		set();
	}

	public void set() 																		//��ġ�� ũ�� ����
	{
		account_list.setBounds(180, 10, 160, 120);
		enter.setBounds(180, 140, 72, 40);
		cancel.setBounds(257, 140, 72, 40);
		information.setBounds(10, 10, 160, 200);

		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(345, 250);
	}

	public void actionPerformed(ActionEvent arg0)											//��Ʋ Ŭ���� �ൿ 
	{
		if (arg0.getSource() == enter)
		{
			manager.delete_account(clicklist);
			account_list.updateUI();
		}
		else if (arg0.getSource() == cancel)
			this.setVisible(false);
	}

	public void valueChanged(ListSelectionEvent e) 											//������ ���� ����
	{
		clicklist = account_list.getSelectedIndex();
	}
}