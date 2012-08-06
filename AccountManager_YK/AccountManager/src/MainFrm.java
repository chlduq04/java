import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import server.Main_info;

public class MainFrm extends Thread implements ActionListener{//가장 기본이 되는 창
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

	public MainFrm(String s)															//생성자
	{
		try{																			//설정
			q1=Integer.parseInt(JOptionPane.showInputDialog("계좌의 한계는 얼마로 할까요?"));
			q2=Integer.parseInt(JOptionPane.showInputDialog("계좌조회는 몇개를 보여줄까요?"));
			q3=Float.parseFloat(JOptionPane.showInputDialog("이자는 몇 %로 할까요?"));
		}
		catch(Exception e)
		{JOptionPane.showMessageDialog(null, "모두 숫자로 입력하세요, 기본 룰로 시행합니다");};			//조건에 맞지 않으면 기본설정으로 실행
		
		info = new Main_info(q1,q2,q3*0.01f);
		MainFrame = new JFrame();
		find = new JButton("조  회");
		deposit = new JButton("입  금");
		withdraw = new JButton("출  금");
		transfer = new JButton("이  체");
		management = new JButton("계좌관리");
		cancel = new JButton("취  소");
		information = new JTextArea(s);
		
		this.start();																	//쓰레드 시작

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
	public void set() 																	//창의 위치와 크기
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

	public void actionPerformed(ActionEvent arg0) {										//액션이 일어난 경우


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
	public void run()																	//쓰레드가 행할 행동
	{
		info.run();
	}
}
