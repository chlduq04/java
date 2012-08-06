import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import setting.connect_sql;

class Join_waiter extends JFrame implements ActionListener{
	private connect_sql db;
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

	Join_waiter(connect_sql only_db){
		db = only_db;
		this.setSize(240,300);
		this.setTitle("호텔 관리 시스템");
		this.setVisible(true);
		
		name = new JLabel("직원명");
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
	
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		if(bt==this.join){
			db.insert_staff_info(name_field.getText(),sex_field.getText(),address_field.getText(),call_field.getText());
			this.setVisible(false);
		}
		else{
			this.setVisible(false);
		}
		
	}
}