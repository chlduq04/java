

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import setting.connect_sql;
public class P_tap extends JPanel implements ActionListener{
	private JLabel name;
	private JButton join;
	private JButton result;
	private JTextArea person_info;
	private JTextField person_name;
	private connect_sql db;
//�ٸ� Ŭ������� ���� �� �ʱ�ȭ �� ���� �Ҵ�
	public P_tap(connect_sql only_db){
		db = only_db;
		name = new JLabel(" ���� ");
		join = new JButton(" ȸ������ ");
		join.addActionListener(this);
		result = new JButton(" ��ȸ ");
		result.addActionListener(this);
		person_info = new JTextArea();
		person_info.setLineWrap(true);
		person_name = new JTextField();
		this.setLayout(null);
		this.recheck();
	}
//��ġ��Ų��
	public void recheck(){
		this.add(name);
		name.setBounds(10,30,80,30);
		this.add(join);
		join.setBounds(10,80,100,30);
		this.add(result);
		result.setBounds(120,80,100,30);
		this.add(person_info);
		person_info.setBounds(250,10,500,150);
		this.add(person_name);
		person_name.setBounds(80,30,140,30);
	}
//��ư �׼� ������ ó��
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		if(bt==P_tap.this.join){
			Join_people input = new Join_people(db);
		}
		else{	
			person_info.setText(db.select_customer_info(person_name.getText()));
			person_info.updateUI();
			person_info.repaint();
		}
	}
}

