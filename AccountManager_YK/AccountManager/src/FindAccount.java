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


public class FindAccount extends JFrame implements ActionListener,ListSelectionListener{//조회 창
	private JList account_list;									//계좌 리스트
	private JButton enter;										//확인
	private JButton cancel;										//취소
	private JTextArea information;								//안내와 상태를 표시할 창
	private int mouseclickindex = 0;							//선택 계좌 위치
	private Main_info bank;										//계좌정보 

	public FindAccount(Main_info info) 							//생성자
	{
		bank = info;
		enter = new JButton("확  인");
		cancel = new JButton("취  소");
		information = new JTextArea("조회할 계좌를\n선택해 주세요");
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

	private void Set() 											//창의 크기와 위치 
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
	public void actionPerformed(ActionEvent ea) 				//버튼 클릭시 행동
	{
		if(ea.getSource() == cancel)
			this.setVisible(false);
		else if(ea.getSource() == enter)
		{
			try
			{information.setText(
					"남은 돈은"
					+bank.money(mouseclickindex)+"원 입니다\n"
					+"그리고 지금까지 거래 내역은 "
					+bank.information(mouseclickindex)+"원 입니다")
					;}
			catch(Exception error)
			{JOptionPane.showMessageDialog(null,"계좌를 선택해 주세요");}
		}
		else
			this.setVisible(false);
	}

	@Override
	
	public void valueChanged(ListSelectionEvent e) {			//선택한 계좌 확인
		mouseclickindex = account_list.getSelectedIndex();
	}
}
