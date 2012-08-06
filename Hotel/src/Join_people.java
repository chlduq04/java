import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import setting.connect_sql;


class Join_people extends JFrame implements ActionListener{
	
	private JLabel name;
	private JLabel sex;
	private JLabel address;
	private JLabel call;

	private JTextField name_field;
	private JTextField call_field;
	private JTextField address_field;
	private JTextField sex_field;
	
	private JButton join;
	private JButton exit;
	
	connect_sql db;
//connect_sql을 연동시키고 할당
	Join_people(connect_sql only_db){
		db = only_db;
		
		this.setSize(240,300);
		this.setTitle("호텔 관리 시스템");
		this.setVisible(true);
		
		name = new JLabel("고객명");
		sex = new JLabel("성별");
		address = new JLabel("주소");
		call = new JLabel("연락처");
		
		join = new JButton("가입 하기");
		exit = new JButton(" 취 소 ");
		
		
		name_field = new JTextField();
		call_field = new JTextField();
		address_field = new JTextField();
		sex_field = new JTextField();
		this.setLayout(null);
		this.recheck();
	}
//위치시킨다
	public void recheck(){
		this.add(name);
		name.setBounds(10,10,40,30);
		this.add(sex);
		sex.setBounds(10,50,40,30);
		this.add(address);
		address.setBounds(10,90,40,30);
		this.add(call);
		call.setBounds(10,130,40,30);
		
		this.add(join);
		join.addActionListener(this);
		join.setBounds(10,180,80,30);
		this.add(exit);
		exit.addActionListener(this);
		exit.setBounds(100,180,80,30);
		
		
		this.add(name_field);
		name_field.setBounds(70,10,100,30);
		this.add(sex_field);
		sex_field.setBounds(70,50,100,30);
		this.add(address_field);
		address_field.setBounds(70,90,100,30);
		this.add(call_field);
		call_field.setBounds(70,130,100,30);

	}
//버튼 액션 리스너 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		if(bt==this.join){
			db.insert_customer_info(name_field.getText(),sex_field.getText(),address_field.getText(),call_field.getText());
			this.setVisible(false);
		}
		else{
			this.setVisible(false);
		}
	}
}