
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import setting.connect_sql;


public class R_tap extends JPanel implements ActionListener{
	private JLabel room;
	private JComboBox room_num;
	private JTextArea info;
	private int room_limit;
	private String String_room[];
	private connect_sql db;
	private JButton check;
	//�ٸ� Ŭ������� ���� �� �ʱ�ȭ �� ���� �Ҵ�
	public R_tap(connect_sql only_db){
		db = only_db;
		room_limit = 20;
		String_room = new String[room_limit];
		for(int i=0;i<room_limit;i++){
			int j=0;
			if(i<10){j= i+101;}
			else{j= i+191;}
			 String_room[i] = ""+j;
			}

		room = new JLabel(" ���� ");
		room_num = new JComboBox(String_room);
		info = new JTextArea();
		check = new JButton(" Ȯ�� ");
		check.addActionListener(this);
		info.setLineWrap(true);
		this.setLayout(null);
		this.recheck();
	}
//��ġ��Ų��
	public void recheck(){
		this.add(room);
		room.setBounds(10,30,80,20);
		this.add(room_num);
		room_num.setBounds(80,30,140,30);
		this.add(info);
		info.setBounds(250,10,500,150);
		this.add(check);
		check.setBounds(10,80,100,30);

	}
//��ư �׼� ������ ó��
	@Override
 	public void actionPerformed(ActionEvent e){
		//String click = ;
		int check = 100+Integer.parseInt((String)(room_num.getSelectedItem()));
		info.setText(db.select_room_info(""+check));
		info.updateUI();
		info.repaint();
	}
}
