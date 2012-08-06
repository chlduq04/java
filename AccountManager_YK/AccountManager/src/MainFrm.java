import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import server.Main_info;

public class MainFrm extends Thread implements ActionListener{//���� �⺻�� �Ǵ� â
	private Main_info info;
	private JFrame MainFrame;
	private JButton find;
	private JButton deposit;
	private JButton withdraw;
	private JButton transfer;
	private JButton management;
	private JButton cancel;
	private JTextArea information;

	private FindAccount findaccountfrm;
	private DepositFrm depositfrm;
	private WithdrawFrm withdrawfrm;
	private ManagerFrm managerfrm;
	private TransferFrm transferfrm;
	private int q1=5,q2=10;
	private float q3=10.0f;

	public MainFrm(String s)															//������
	{
		try{																			//����
			q1=Integer.parseInt(JOptionPane.showInputDialog("������ �Ѱ�� �󸶷� �ұ��?"));
			q2=Integer.parseInt(JOptionPane.showInputDialog("������ȸ�� ��� �����ٱ��?"));
			q3=Float.parseFloat(JOptionPane.showInputDialog("���ڴ� �� %�� �ұ��?"));
		}
		catch(Exception e)
		{JOptionPane.showMessageDialog(null, "��� ���ڷ� �Է��ϼ���, �⺻ ��� �����մϴ�");};			//���ǿ� ���� ������ �⺻�������� ����
		
		info = new Main_info(q1,q2,q3*0.01f);
		MainFrame = new JFrame();
		find = new JButton("��  ȸ");
		deposit = new JButton("��  ��");
		withdraw = new JButton("��  ��");
		transfer = new JButton("��  ü");
		management = new JButton("���°���");
		cancel = new JButton("��  ��");
		information = new JTextArea(s);
		
		this.start();																	//������ ����

		find.addActionListener(this);
		deposit.addActionListener(this);
		transfer.addActionListener(this);
		management.addActionListener(this);
		//		information.addActionListener(this);
		withdraw.addActionListener(this);
		cancel.addActionListener(this);

		MainFrame.add(information);
		MainFrame.add(find);
		MainFrame.add(deposit);
		MainFrame.add(transfer);
		MainFrame.add(withdraw);
		MainFrame.add(management);
		MainFrame.add(cancel);

		set();
	}
	public void set() 																	//â�� ��ġ�� ũ��
	{
		information.setBounds(10, 10, 210, 290);
		management.setBounds(225, 10, 200, 40);
		find.setBounds(225, 60, 200, 40);
		deposit.setBounds(225, 110, 200, 40);
		withdraw.setBounds(225, 160, 200, 40);
		transfer.setBounds(225, 210, 200, 40);
		cancel.setBounds(225, 260, 200, 40);
		
		MainFrame.setResizable(false);
		MainFrame.setLayout(null);
		MainFrame.setSize(450, 350);
		MainFrame.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {										//�׼��� �Ͼ ���


		if(arg0.getSource() == find)
			findaccountfrm = new FindAccount(info);
		else if(arg0.getSource() == deposit)
			depositfrm = new DepositFrm(info);
		else if(arg0.getSource() == transfer)
			transferfrm = new TransferFrm(info);
		else if(arg0.getSource() == withdraw)
			withdrawfrm = new WithdrawFrm(info);
		else if(arg0.getSource() == management)
			managerfrm = new ManagerFrm(info);
		else if(arg0.getSource() == cancel)
			MainFrame.setVisible(false);
		else 
			MainFrame.setVisible(false);

	}
	public void run()																	//�����尡 ���� �ൿ
	{
		info.run();
	}
}
