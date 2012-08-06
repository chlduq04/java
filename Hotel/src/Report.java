

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import setting.connect_sql;


public class Report extends JTabbedPane{
	private P_tap person;
	private R_tap room;
	private W_tap waiter;
//탭들을 connect_sql과 연동시킨다.
//이렇게 함으로써 각 탭에서 실행시켜 여러번의 로그인을 피할 수 잇다
	public Report(connect_sql only_db){
		person = new P_tap(only_db);
		room = new R_tap(only_db);
		waiter = new W_tap(only_db);
	}
//그리고 실제 할당	
	public void set(){
		this.addTab(" 고객 ", person);
		this.addTab(" 객실 ", room);
		this.addTab(" 직원 ", waiter);		
	}
}
