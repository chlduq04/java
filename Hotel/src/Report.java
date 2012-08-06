

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import setting.connect_sql;


public class Report extends JTabbedPane{
	private P_tap person;
	private R_tap room;
	private W_tap waiter;
//�ǵ��� connect_sql�� ������Ų��.
//�̷��� �����ν� �� �ǿ��� ������� �������� �α����� ���� �� �մ�
	public Report(connect_sql only_db){
		person = new P_tap(only_db);
		room = new R_tap(only_db);
		waiter = new W_tap(only_db);
	}
//�׸��� ���� �Ҵ�	
	public void set(){
		this.addTab(" �� ", person);
		this.addTab(" ���� ", room);
		this.addTab(" ���� ", waiter);		
	}
}
