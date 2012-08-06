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


public class DepositFrm extends JFrame implements ActionListener,ListSelectionListener//입금창
{
	private Main_info bank;						//하나의 main info를 연동시키기 위한 변수
	private JList account_list;					//계좌 리스트
	private JButton enter;						//확인
	private JButton cancel;						//취소
	private JTextArea information;				//안내문을 띄우고 상태를 보여줄 창
	private JTextArea deposit;					//입금할 금액을 입력할 창
	private int mouseclickindex = 0;			//클릭된 계좌

	public DepositFrm(Main_info info)			//생성자
	{
		bank = info;
		enter = new JButton("확인");
		cancel = new JButton("취소");
		account_list = new JList(bank.check());
		information = new JTextArea("입금할 계좌를\n선택해 주세요");
		deposit = new JTextArea("지우고 숫자만 입력");

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
	private void Set()							//창의 크기와 각 구성요소의 위치 설정
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
	public void actionPerformed(ActionEvent e)	//클릭할 경우 일어날 액션
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
			{JOptionPane.showMessageDialog(null,"계좌를 선택해 주세요");}
			catch(Exception a)
			{
				System.err.print(a);
			}
		}
		else
			this.setVisible(false);
	}

	@Override
	//선택한 계좌 확인
	public void valueChanged(ListSelectionEvent e) 
	{
		mouseclickindex = account_list.getSelectedIndex();
	}
}
