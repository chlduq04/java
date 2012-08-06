import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import setting.BookInfo;
import setting.Setting;
import setting.connect_sql;

public class ToBook extends Setting implements ActionListener{
	public JPanel ToBook;
	private String String_room[];
	private String String_day[];
	private JLabel Title;  
	private JLabel name;
	private JLabel checkin;
	private JLabel day;
	private JLabel room_number;
	private JTextField name_field;
	private JTextField check_in_field;
	private JButton input;
	private JButton delete;
	private JComboBox day_scroll;
	private JComboBox room_scroll;
	private connect_sql db;
	private BookInfo book;
//connect_sql�� ����, �׸��� bookinfo�� ���� , �⺻���� â ����
	public ToBook(connect_sql only_db,BookInfo books)
	{
		db = only_db;
		book = books;

		String_room = new String[room_count];
		String_day = new String[day_count];


		for(int i=0;i<room_count;i++){
			int j=0;
			if(i<10){j= i+101;}
			else{j= i+191;} String_room[i] = ""+j;
			}
		for(int i=0;i<day_count;i++){int j = i+1; String_day[i] = ""+j;}

		ToBook = new JPanel();
		Title = new JLabel("���� ����");

		name = new JLabel("����");
		checkin = new JLabel("üũ��(YYYYMMDD)");
		day = new JLabel("��");
		room_number = new JLabel("����");

		input = new JButton("���� ���/����");
		input.addActionListener(this);
		delete = new JButton("���� ���");
		delete.addActionListener(this);

		name_field = new JTextField();
		check_in_field = new JTextField();

		day_scroll = new JComboBox(String_day);
		room_scroll = new JComboBox(String_room);

		this.recheck();
	}
//�Ҵ��Ѵ�	
	public void recheck(){
		ToBook.setLayout(null);

		ToBook.add(Title);
		Title.setBounds(10, 10, 100, 20);	

		ToBook.add(name);
		name.setBounds(10,40,40,60);
		ToBook.add(checkin);
		checkin.setBounds(10,60,180,80);
		ToBook.add(day);
		day.setBounds(10,80,40,100);
		ToBook.add(room_number);
		room_number.setBounds(10, 100, 40, 120);

		ToBook.add(name_field);
		name_field.setBounds(180,60,180,20);
		ToBook.add(check_in_field);
		check_in_field.setBounds(180, 90, 180, 20);
		ToBook.add(day_scroll);
		day_scroll.setBounds(180,120,180,20);
		ToBook.add(room_scroll);
		room_scroll.setBounds(180,150,180,20);

		ToBook.add(input);
		input.setBounds(10, 200, 170, 30);
		ToBook.add(delete);
		delete.setBounds(190, 200, 170, 30);

	}
//��ư Ŭ�� ������
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		if(bt==input){
			db.ToBook(name_field.getText(),check_in_field.getText(),(day_scroll.getSelectedItem().toString()),(room_scroll.getSelectedItem().toString()));
		}
		else if(bt==delete){	
			db.Delete_checkin(check_in_field.getText(), (room_scroll.getSelectedItem().toString()), name_field.getText());
		}

	}
}
