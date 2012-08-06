
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import setting.BookInfo;
import setting.connect_sql;


public class Set_All extends JFrame implements ActionListener{
	
	private ToBook first;
	private JPanel first_panel;
	private BookInfo second;
	private Report third;
	private JPanel third_panel;
	private JButton button;		
	private connect_sql db;
	private GregorianCalendar time;
	private String day = "";
	private int input_day;
	JTextArea date;
	
//connect_sql과 bookinfo를 연동한다.
//그리고 만들어진 탭과 panel들을 위치시킨다
	public Set_All(connect_sql only_db,BookInfo book)
	{
		db = only_db;
		second = book;
		
		time = new GregorianCalendar();
		day =""+time.get(GregorianCalendar.YEAR)+"//"+(time.get(GregorianCalendar.MONTH)+1)+"//"+time.get(GregorianCalendar.DATE);
		first = new ToBook(only_db,second);
		first_panel = new JPanel();
		third = new Report(only_db);
		third_panel = new JPanel();
		button = new JButton("OPEN");	
		date = new JTextArea();
		

		this.setLayout(null);
		button.addActionListener(this);
		this.add(button);
		button.setBounds(10,10,80,30);
		this.add(first.ToBook);
		first.ToBook.setBounds(10,50,400,250);
		this.add(second.room_info_panel);
		second.room_info_panel.setBounds(410, 100, 400, 200);
		
		date.setText(day);
		this.add(date);
		date.setBounds(750,10,80,20);
		
		third.set();
		this.add(third);
		third.setBounds(10,350,800,200);
		this.setSize(900,600);
		this.setTitle("호텔 관리 시스템");
		this.setVisible(true);
	}
//버튼 액션 리스너	
	@Override
	public void actionPerformed(ActionEvent e) {
		File_open fo = new File_open(db);
	}
	
}
