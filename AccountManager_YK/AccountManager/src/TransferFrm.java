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



public class TransferFrm extends JFrame implements ActionListener,ListSelectionListener{	//계좌이체 창

	private Main_info bank;
	private JList first_account;		//첫번째 리스트
	private JList sec_account;			//두번째 리스트
	private int first_index=0;
	private int sec_index=0;
	private JLabel firstLabel;
	private JLabel secLabel;
	private JLabel moneyLabel;

	private JButton cancel;
	private JButton enter;

	private JTextField money;
	private JTextArea information;


	public TransferFrm(Main_info info)	//생성자
	{
		bank = info;
		first_account = new JList(bank.check());
		sec_account = new JList(bank.check());

		firstLabel = new JLabel("첫번째 계좌");
		secLabel = new JLabel("두번째 계좌");
		moneyLabel = new JLabel("이체할 금액");
		enter= new JButton("확  인");
		cancel = new JButton("취  소");
		money = new JTextField();
		information = new JTextArea("\n\n\n\n\n\n이체할 계좌와 \n이체시킬 계좌를 \n선택해 주세요");

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


	private void set() 					//크기 조정
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
	public void actionPerformed(ActionEvent e) {										//행동 모샤
		if(e.getSource() == cancel)
			this.setVisible(false);
		else if(e.getSource() == enter)				
		{
			int amount = Integer.parseInt(money.getText());
			boolean check_withdraw = bank.check_widr(first_index,amount);
			try
			{
				if(check_withdraw)														//이체할 금액이 충분한가
				{
					information.setText(bank.transfer(first_index, sec_index, amount));	//이체하고 그 정보를 출력
				}
				else						
					JOptionPane.showMessageDialog(null, "잔액이 부족합니다");					//아니라면 오류
			}
			catch(Exception error)
			{JOptionPane.showMessageDialog(null,"계좌를 선택해 주세요");}						//클릭을 하세요
		}
		else
			this.setVisible(false);

	}


	@Override
	public void valueChanged(ListSelectionEvent e) {									//클릭된 계좌 정보
		first_index = first_account.getSelectedIndex();
		sec_index = sec_account.getSelectedIndex();
	}
}
