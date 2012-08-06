

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import setting.connect_sql;

public class W_tap extends JPanel implements ActionListener{

	private JButton input;
	private JButton result;
	
	private JLabel waiter_name;
	private JTextField name_field;
	private JTextArea info;

	private connect_sql db;
//이미 실행된 sql을 연동시켜 한번의 로그인으로 쿼리문을 연동시킨다
	public W_tap(connect_sql only_db){
		db = only_db;
		input = new JButton("직원 등록");
		result = new JButton(" 조회 ");
		
		input.addActionListener(this);
		result.addActionListener(this);
		
		waiter_name = new JLabel(" 직원명 ");
		name_field = new JTextField();
		info = new JTextArea();
		info.setLineWrap(true);
		
		this.setLayout(null);
		this.recheck();
	}
//값들을 할당한다
	public void recheck(){
		
		this.add(waiter_name);
		waiter_name.setBounds(10,30,80,30);
		this.add(input);
		input.setBounds(10,80,100,30);
		this.add(result);
		result.setBounds(120,80,100,30);
		this.add(name_field);
		name_field.setBounds(80,30,140,30);
		this.add(info);
		info.setBounds(250,10,500,150);
	}
//버튼 클릭을 하는 것에대한 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		if(bt == W_tap.this.input){
			Join_waiter input = new Join_waiter(db);
		}
		else{
			info.setText(db.select_staff_info(name_field.getText()));
			info.updateUI();
			info.repaint();
		}
	}
}

