import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import server.Main_info;



public class TransferFrm extends JFrame implements ActionListener,ListSelectionListener{	//������ü â

	private Main_info bank;
	private JList first_account;		//ù��° ����Ʈ
	private JList sec_account;			//�ι�° ����Ʈ
	private int first_index=0;
	private int sec_index=0;
	private JLabel firstLabel;
	private JLabel secLabel;
	private JLabel moneyLabel;

	private JButton cancel;
	private JButton enter;

	private JTextField money;
	private JTextArea information;


	public TransferFrm(Main_info info)	//������
	{
		bank = info;
		first_account = new JList(bank.check());
		sec_account = new JList(bank.check());

		firstLabel = new JLabel("ù��° ����");
		secLabel = new JLabel("�ι�° ����");
		moneyLabel = new JLabel("��ü�� �ݾ�");
		enter= new JButton("Ȯ  ��");
		cancel = new JButton("��  ��");
		money = new JTextField();
		information = new JTextArea("\n\n\n\n\n\n��ü�� ���¿� \n��ü��ų ���¸� \n������ �ּ���");

		enter.addActionListener(this);
		cancel.addActionListener(this);
		first_account.addListSelectionListener(this);
		sec_account.addListSelectionListener(this);

		this.add(first_account);
		this.add(sec_account);
		this.add(firstLabel);
		this.add(secLabel);
		this.add(moneyLabel);
		this.add(enter);
		this.add(cancel);
		this.add(money);
		this.add(information);

		set();
	}


	private void set() 					//ũ�� ����
	{
		firstLabel.setBounds(190, 15, 150, 15);
		first_account.setBounds(190,35,150,120);
		secLabel.setBounds(190, 165, 150, 15);
		sec_account.setBounds(190, 185, 150, 120);
		moneyLabel.setBounds(190, 315, 150, 15);
		money.setBounds(190, 335, 150, 40);
		enter.setBounds(190, 385, 72, 40);
		cancel.setBounds(267,385,72,40);

		information.setBounds(10,10,170,415);

		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(370,470);

	}

	@Override
	public void actionPerformed(ActionEvent e) {										//�ൿ ���
		if(e.getSource() == cancel)
			this.setVisible(false);
		else if(e.getSource() == enter)				
		{
			int amount = Integer.parseInt(money.getText());
			boolean check_withdraw = bank.check_widr(first_index,amount);
			try
			{
				if(check_withdraw)														//��ü�� �ݾ��� ����Ѱ�
				{
					information.setText(bank.transfer(first_index, sec_index, amount));	//��ü�ϰ� �� ������ ���
				}
				else						
					JOptionPane.showMessageDialog(null, "�ܾ��� �����մϴ�");					//�ƴ϶�� ����
			}
			catch(Exception error)
			{JOptionPane.showMessageDialog(null,"���¸� ������ �ּ���");}						//Ŭ���� �ϼ���
		}
		else
			this.setVisible(false);

	}


	@Override
	public void valueChanged(ListSelectionEvent e) {									//Ŭ���� ���� ����
		first_index = first_account.getSelectedIndex();
		sec_index = sec_account.getSelectedIndex();
	}
}
