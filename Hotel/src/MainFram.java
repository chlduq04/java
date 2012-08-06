
import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;

import setting.BookInfo;
import setting.connect_sql;


public class MainFram{
	public static void main(String args[]){
//main으로 
//일단 프로그램의 기본 세팅정보를 담은 bookinfo를 만든다.
//그 후 connect_sql에 파라미터로 정보를 넘겨 bookinfo의 정보를 토대로 값을 넣고
//그렇게 만들어진 connect_sql을 가장 마지막의 frame연동 class에 파라미터로 넣어 하나의 oracle 접속과 query문을 보내게 만든다
		BookInfo book = new BookInfo();
		connect_sql sql = new connect_sql(book);
		Set_All frm = new Set_All(sql,book);
		
	}
}

