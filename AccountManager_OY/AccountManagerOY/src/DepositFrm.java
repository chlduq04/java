import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class DepositFrm extends JFrame implements ActionListener,ListSelectionListener{
	JList account_list;
	JButton enter;
	JButton cancel;
	JTextField information;
	JTextField money;
	
	public DepositFrm(New_account[] index)
	{
		enter = new JButton("확인");
		cancel = new JButton("취소");
		account_list = new JList(index);
		information = new JTextField("입금할 계좌를 선택해 주세요");
		money = new JTextField();
		
		enter.addActionListener(this);
		cancel.addActionListener(this);
		account_list.addListSelectionListener(this);
		set();
	}
	public void set()
	{
		this.add(enter);
		this.add(cancel);
		this.add(account_list);
		this.add(information);
		this.add(money);
		
		enter.setBounds(100,80,80,30);
		cancel.setBounds(100,120,80,30);
		account_list.setBounds(100,10,80,30);
		information.setBounds(10,10,80,140);
		money.setBounds(100,40,80,30);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(200,200);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == enter)
		{
			String account = money.getText();
			Integer.parseInt(account);
		//	account_list.sele
		}
		else if(e.getSource() == cancel)
		{
			this.setVisible(false);
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
