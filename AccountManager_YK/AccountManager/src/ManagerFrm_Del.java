import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import server.Main_info;

public class ManagerFrm_Del extends JFrame implements ActionListener,ListSelectionListener {//계좌 삭제 창
	private Main_info manager;
	private JButton enter;
	private JButton cancel;
	private JList account_list;
	private JTextArea information;
	private int clicklist=0;
	public ManagerFrm_Del(Main_info index) 													//생성자
	{
		manager = index;
		enter = new JButton("확  인");
		cancel = new JButton("취  소");
		account_list = new JList(manager.check());											//계좌 리스트를 받아온다
		information = new JTextArea("\n\n  삭제할 계좌 번호를\n  클릭한후 \n  확인 버튼을 눌러 주세요.");

		enter.addActionListener(this);
		cancel.addActionListener(this);
		account_list.addListSelectionListener(this);

		this.add(cancel);
		this.add(enter);
		this.add(information);
		this.add(account_list);

		set();
	}

	public void set() 																		//위치와 크기 지정
	{
		account_list.setBounds(180, 10, 160, 120);
		enter.setBounds(180, 140, 72, 40);
		cancel.setBounds(257, 140, 72, 40);
		information.setBounds(10, 10, 160, 200);

		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(345, 250);
	}

	public void actionPerformed(ActionEvent arg0)											//버틀 클릭시 행동 
	{
		if (arg0.getSource() == enter)
		{
			manager.delete_account(clicklist);
			account_list.updateUI();
		}
		else if (arg0.getSource() == cancel)
			this.setVisible(false);
	}

	public void valueChanged(ListSelectionEvent e) 											//선택한 계좌 정보
	{
		clicklist = account_list.getSelectedIndex();
	}
}