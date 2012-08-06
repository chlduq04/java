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


public class DepositFrm extends JFrame implements ActionListener,ListSelectionListener//�Ա�â
{
	private Main_info bank;						//�ϳ��� main info�� ������Ű�� ���� ����
	private JList account_list;					//���� ����Ʈ
	private JButton enter;						//Ȯ��
	private JButton cancel;						//���
	private JTextArea information;				//�ȳ����� ���� ���¸� ������ â
	private JTextArea deposit;					//�Ա��� �ݾ��� �Է��� â
	private int mouseclickindex = 0;			//Ŭ���� ����

	public DepositFrm(Main_info info)			//������
	{
		bank = info;
		enter = new JButton("Ȯ��");
		cancel = new JButton("���");
		account_list = new JList(bank.check());
		information = new JTextArea("�Ա��� ���¸�\n������ �ּ���");
		deposit = new JTextArea("����� ���ڸ� �Է�");

		enter.addActionListener(this);
		cancel.addActionListener(this);
		account_list.addListSelectionListener(this);

		this.add(enter);
		this.add(cancel);
		this.add(account_list);
		this.add(information);
		this.add(deposit);

		Set();
	}
	private void Set()							//â�� ũ��� �� ��������� ��ġ ����
	{
		account_list.setBounds(170,10,150,120);
		deposit.setBounds(170,140,150,40);
		enter.setBounds(170,190,70,40);
		cancel.setBounds(245,190,70,40);
		information.setBounds(10,10,150,220);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(340,280);
	}
	@Override
	public void actionPerformed(ActionEvent e)	//Ŭ���� ��� �Ͼ �׼�
	{		
		if(e.getSource() == cancel)
			this.setVisible(false);
		else if(e.getSource() == enter)
		{
			try
			{
				int amount = Integer.parseInt(deposit.getText());
				information.setText(bank.deposit(mouseclickindex, amount));
			}
			catch(NullPointerException not_select_error)
			{JOptionPane.showMessageDialog(null,"���¸� ������ �ּ���");}
			catch(Exception a)
			{
				System.err.print(a);
			}
		}
		else
			this.setVisible(false);
	}

	@Override
	//������ ���� Ȯ��
	public void valueChanged(ListSelectionEvent e) 
	{
		mouseclickindex = account_list.getSelectedIndex();
	}
}
