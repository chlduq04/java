
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
//main���� 
//�ϴ� ���α׷��� �⺻ ���������� ���� bookinfo�� �����.
//�� �� connect_sql�� �Ķ���ͷ� ������ �Ѱ� bookinfo�� ������ ���� ���� �ְ�
//�׷��� ������� connect_sql�� ���� �������� frame���� class�� �Ķ���ͷ� �־� �ϳ��� oracle ���Ӱ� query���� ������ �����
		BookInfo book = new BookInfo();
		connect_sql sql = new connect_sql(book);
		Set_All frm = new Set_All(sql,book);
		
	}
}

