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

public class WithdrawFrm extends JFrame implements ActionListener,ListSelectionListener{//���
	private Main_info bank;
	private JList account_list;	
	private JButton enter;
	private JButton cancel;
	private JTextArea information;
	private JTextArea withdraw;	
	private int mouseclickindex = 0;		

	public WithdrawFrm(Main_info info)						//������
	{	
		bank = info;
		enter = new JButton("Ȯ��");
		cancel = new JButton("���");
		account_list = new JList(bank.check());
		information = new JTextArea("����� ���¸�\n������ �ּ���");
		withdraw = new JTextArea("����� ���ڸ� �Է�");

		enter.addActionListener(this);
		cancel.addActionListener(this);
		account_list.addListSelectionListener(this);

		this.add(enter);
		this.add(cancel);
		this.add(account_list);
		this.add(information);
		this.add(withdraw);

		Set();
	}
	private void Set()										//ũ��� ��ġ
	{

		account_list.setBounds(170,10,150,120);
		withdraw.setBounds(170,140,150,40);
		enter.setBounds(170,190,70,40);
		cancel.setBounds(245,190,70,40);
		information.setBounds(10,10,150,220);

		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(340,280);
	}
	@Override
	public void actionPerformed(ActionEvent e) 				//��ư �ൿ
	{		
		if(e.getSource() == cancel)
			this.setVisible(false);
		else if(e.getSource() == enter)
		{
			try
			{			
				int amount = Integer.parseInt(withdraw.getText());
				information.setText(bank.withdraw(mouseclickindex, amount));
			}
			catch(Exception error)
			{JOptionPane.showMessageDialog(null,"���¸� ������ �ּ���");}
		}
		else
			this.setVisible(false);
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {		//������ ���� ����
		mouseclickindex = account_list.getSelectedIndex();
	}
}
