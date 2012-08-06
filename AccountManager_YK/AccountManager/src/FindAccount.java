import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import server.Main_info;


public class FindAccount extends JFrame implements ActionListener,ListSelectionListener{//��ȸ â
	private JList account_list;									//���� ����Ʈ
	private JButton enter;										//Ȯ��
	private JButton cancel;										//���
	private JTextArea information;								//�ȳ��� ���¸� ǥ���� â
	private int mouseclickindex = 0;							//���� ���� ��ġ
	private Main_info bank;										//�������� 

	public FindAccount(Main_info info) 							//������
	{
		bank = info;
		enter = new JButton("Ȯ  ��");
		cancel = new JButton("��  ��");
		information = new JTextArea("��ȸ�� ���¸�\n������ �ּ���");
		account_list = new JList(bank.check());
		enter.addActionListener(this);
		cancel.addActionListener(this);
		account_list.addListSelectionListener(this);

		this.add(enter);
		this.add(account_list);
		this.add(cancel);
		this.add(information);

		Set();
	}

	private void Set() 											//â�� ũ��� ��ġ 
	{

		account_list.setBounds(170,15,150,140);
		enter.setBounds(170, 165, 72, 40);
		cancel.setBounds(247,165,72,40);
		information.setBounds(10,10,150,200);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(345,250);
	}

	@Override
	public void actionPerformed(ActionEvent ea) 				//��ư Ŭ���� �ൿ
	{
		if(ea.getSource() == cancel)
			this.setVisible(false);
		else if(ea.getSource() == enter)
		{
			try
			{information.setText(
					"���� ����"
					+bank.money(mouseclickindex)+"�� �Դϴ�\n"
					+"�׸��� ���ݱ��� �ŷ� ������ "
					+bank.information(mouseclickindex)+"�� �Դϴ�")
					;}
			catch(Exception error)
			{JOptionPane.showMessageDialog(null,"���¸� ������ �ּ���");}
		}
		else
			this.setVisible(false);
	}

	@Override
	
	public void valueChanged(ListSelectionEvent e) {			//������ ���� Ȯ��
		mouseclickindex = account_list.getSelectedIndex();
	}
}
