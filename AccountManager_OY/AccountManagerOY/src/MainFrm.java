import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MainFrm extends Main_info implements ActionListener{

	private JFrame MainFrame;
	private JButton find;
	private JButton deposit;
	private JButton withdraw;
	private JButton transfer;
	private JButton management;
	private JTextField information;
	
	private FindAccount findaccountfrm;
	private DepositFrm depositfrm;
	private WithdrawFrm withdrawfrm;
	private ManagerFrm managerfrm;
	private TransferFrm transferfrm;
	
	public MainFrm()
	{
		MainFrame = new JFrame();
		find = new JButton("조회");
		deposit = new JButton("입금");
		withdraw = new JButton("출급");
		transfer = new JButton("이체");
		management = new JButton("계좌관리");
		information = new JTextField("안녕하세요 한양은행입니다 원하시는 기능을 선택하세요");
		
		find.addActionListener(this);
		deposit.addActionListener(this);
		transfer.addActionListener(this);
		management.addActionListener(this);
		information.addActionListener(this);
		
		set();
	
	}
	public void set()
	{
		MainFrame.add(information);
		MainFrame.add(find);
		MainFrame.add(deposit);
		MainFrame.add(transfer);
		MainFrame.add(withdraw);
		MainFrame.add(management);
		
		information.setBounds(10,10,200,290);
		find.setBounds(210,10,200,50);
		deposit.setBounds(210,70,200,50);
		transfer.setBounds(210, 130,200,50);
		withdraw.setBounds(210,190,200,50);
		management.setBounds(210,250,200,50);
		

		MainFrame.setLayout(null);
		MainFrame.setSize(450, 350);
		MainFrame.setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == find)
			findaccountfrm = new FindAccount(); 
		else if(arg0.getSource() == deposit)
			depositfrm = new DepositFrm(index);
		else if(arg0.getSource() == transfer)
			transferfrm = new TransferFrm();
		else if(arg0.getSource() == withdraw)
			withdrawfrm = new WithdrawFrm();
		else if(arg0.getSource() == management)
			managerfrm = new ManagerFrm();
	}
	
}
