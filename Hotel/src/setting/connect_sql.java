package setting;	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

public class connect_sql{
	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection myCon = null;
	private static int id = 0;
	private int day;
	private GregorianCalendar time;
	private BookInfo book;
//������ �� ���̵�� �н����带 �ٷ� �����. �׸��� ����
	public connect_sql(BookInfo books){
		book = books;
		time = new GregorianCalendar();
		day = time.get(GregorianCalendar.YEAR)*10000+(time.get(GregorianCalendar.MONTH)+1)*100+time.get(GregorianCalendar.DATE);

		try{
			String oracle_id=JOptionPane.showInputDialog("Oaacle ���̵� �����ּ���");
			String oracle_password=JOptionPane.showInputDialog("Oracle ����� �����ּ���");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			myCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost",oracle_id,oracle_password);					
			stmt = myCon.createStatement();
			//���̺� ���� string ����			
			String create_db_table = "create table DB 				(id				integer		not null, day			integer		not null, room_number		integer		not null, room_status	char(2)		not null, customer	char(20)	not null, staff		char(20)	not null)";
			String create_customer_table = "create table customer 	(customer		char(20)	not null, sex				char(5)		not null, address		char(20)	not null, context	integer		not null, primary key(context))";
			String create_room_table = "create table room			(room_number	integer		not null, room_capacity		integer		not null, room_type		char(20)	not null, primary key(room_number))";
			String create_staff_table ="create table staff 			(staff			char(20)	not null, sex				char(5)		not null, address		char(20)	not null, context	integer		not null, primary key(context))";
			//no return query(������ ���, �������� ��� ����� ����â �޼���)
			no_return_Query(create_db_table, "���� �̸��� ���̺��� �����մϴ�, db");
			no_return_Query(create_customer_table, "���� �̸��� ���̺��� �����մϴ�, customer");
			no_return_Query(create_room_table, "���� �̸��� ���̺��� �����մϴ�, room");
			no_return_Query(create_staff_table, "���� �̸��� ���̺��� �����մϴ�, staff");
			//id�� Ȯ���� �� id�� �ִٸ� ���ο� ���̵�� �߰��ϰ� ���ٸ� 0���� ���� �����Ѵ�
			try{String return_id = "select max(id) from db";
			rs = stmt.executeQuery(return_id);
			while(rs.next()){return_id = rs.getString(1);}
			id = Integer.parseInt(return_id);
			}catch(Exception e){
				id = 0;
			}
			check();
			
		}catch(Exception e){	
			JOptionPane.showMessageDialog(null, e);
		}
	}
//�� ������ ���� �̸����� �˻��� return
	public String select_room_info(String room_number){
		String capacity = "select room_capacity from room where room_number = " +room_number;
		String type = "select room_type from room where room_number = "+room_number;
		String status = "select room_type from room where room_number = "+room_number;
		String room_status = "select room_status from DB where room_number = " +room_number;
		String most_customer = "select stats_mode(customer) from DB where room_number = "+room_number+" group by room_number";
		String most_customer_count = "select max(count(customer)) from db where room_number = "+room_number+" group by customer";
		try{
			capacity=Query(capacity);
			type=Query(type);
			status=Query(status);
			room_status=Query(room_status);
			most_customer=Query(most_customer);
			most_customer_count=Query(most_customer_count);
			return "�� ��ȣ : "+room_number.trim()+"	�����ο� : "+capacity.trim()+"	Ÿ�� : "+type.trim()+"	���� ���� : "+room_status.trim()+"	�ִ� ���� �� : "+most_customer.trim()+"	("+most_customer_count.trim()+")";
		}
		catch(Exception e){
			return "�� ��ȣ : "+room_number.trim()+"	�����ο� : "+capacity.trim()+"	Ÿ�� : "+type.trim()+"	���� ���� : �������	�ִ� ���� �� : ����	( 0 )";
		}
	}
//�� ���� �߰�
	public void insert_room_info(int number, int capacity, String type){
		String insert_db = "insert into room values ("+number+","+capacity+",'"+type+"')";
		no_return_Query(insert_db, "�� �����Է� ����");
	}
//�������� �� �̸����� �˻� �� return
	public String select_customer_info(String customer){
		String sex = "select sex from customer where customer ='"+customer+"'";
		String address = "select address from customer where customer ='"+customer+"'";
		String context = "select context from customer where customer ='"+customer+"'";
		String total_stay = "select count(customer) from DB where customer = '"+customer+"'" ;
		String recently_stay = "select max(day) from DB where customer = '"+customer+"'";
		String maximum_staff = "select stats_mode(staff) from DB where customer= '"+customer+"' group by customer";
		String maximum_staff_count = "select max(count(staff)) from db where customer = '"+customer+"' group by staff";
		try{
			sex=Query(sex);
			address=Query(address);
			context=Query(context);
			total_stay=Query(total_stay);
			recently_stay=Query(recently_stay);
			maximum_staff=Query(maximum_staff);
			maximum_staff_count=Query(maximum_staff_count);

			return "�̸� : "+customer.trim()+"	���� : " + sex.trim() +"	�ּ� : "+address.trim()+"	����ó : "+context.trim()+"	�� �����Ⱓ "+total_stay.trim()+"	�ֱ� ������ : "+recently_stay.trim()+"	�ִ��������� () : "+maximum_staff.trim()+"	("+maximum_staff_count.trim()+")";
		}
		catch(Exception e){
			return "�̸� : "+customer.trim()+"	���� : " + sex.trim() +"	�ּ� : "+address.trim()+"	����ó : "+context.trim()+"	�� �����Ⱓ : 0	�ֱ� ������ : ����		�ִ��������� () : ����(0)";
		}
	}
//������ �߰�
	public void insert_customer_info(String c_name,String c_sex,String c_address,String phone){
		if(!(c_sex.equals("��"))&&!(c_sex.equals("��"))){
			JOptionPane.showMessageDialog(null, "������ ���̳� ���� ǥ���� �ּ���");
		}
		else if(!(isDigit(phone))){
			JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� ���ڷθ� �Է��� �ּ���");
		}
		else{
		String insert_db = "insert into customer values ('"+c_name+"','"+c_sex+"','"+c_address+"',"+phone+")";
		no_return_Query(insert_db, "�� ���� �Է� ����");
		}
	}
	//���������� ������ �̸����� �˻� �� return
	public String select_staff_info(String staff){
		String sex = "select sex from staff where staff ='"+staff+"'";
		String address = "select address from staff where staff ='"+staff+"'";
		String context = "select context from staff where staff ='"+staff+"'";
		//���� �ִ�
		String maximum_room_number = "select stats_mode(room_number) from db where staff = '"+staff+"' group by staff";
		String maximum_room_number_count = "select max(count(room_number)) from db where staff = '"+staff+"' group by room_number";;
		//��� �մ� �ִ�
		String maximum_customer = "select stats_mode(customer) from db where staff = '"+staff+"' group by staff";
		String maximum_customer_count = "select max(count(customer)) from db where staff = '"+staff+"' group by customer";
		try{
			//�ִ��� Ȯ�� ������ err�� ���

			sex=Query(sex);
			address=Query(address);
			context=Query(context);
			maximum_room_number=Query(maximum_room_number);
			maximum_room_number_count=Query(maximum_room_number_count);
			maximum_customer=Query(maximum_customer);
			maximum_customer_count=Query(maximum_customer_count);
			return 	"�̸� : "+staff.trim()+"	���� : " +sex.trim()+"	�ּ� : "+address.trim()+"	����ó : "+context.trim()+"	�ִ���� : "+maximum_customer.trim()+"	("+maximum_customer_count.trim()+")	"+"	�ִ��簴�� : "+maximum_room_number.trim()+"	("+maximum_room_number_count.trim()+")";

		}catch(Exception e){
			return 	"�̸� : "+staff.trim()+"	���� : " +sex.trim()+"	�ּ� : "+address.trim()+"	����ó : "+context.trim()+"	�ִ���� (0): ����(0)		�ִ��簴��() : ����(0)";
		}
	}
	//�������� �߰�
	public void insert_staff_info(String s_name,String s_sex,String s_address,String phone){
		if(!(s_sex.equals("��"))&&!(s_sex.equals("��"))){
			JOptionPane.showMessageDialog(null, "������ ���̳� ���� ǥ���� �ּ���");
		}
		else if(!(isDigit(phone))){
			JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� ���ڷҸ� ǥ���� �ּ���");
		}
		else{
			String insert_db = "insert into staff values ('"+s_name+"','"+s_sex+"','"+s_address+"',"+phone+")";
			no_return_Query(insert_db, "���� ���� �Է� ����");
		}
	}
	//���� ���� �߰�
	public void ToBook(String customer,String date,String day,String room_num){
		String staff_name = "select * from (select staff from staff order by DBMS_RANDOM.random)where rownum = 1";
		checkin(staff_name,customer,date,day,room_num);
	}
	//���� ���� �߰� �Լ��� tobook���� �ҷ��� �Լ��� ���� ������ �Ѵ�
	public void checkin(String staff_name,String customer,String date,String day,String room_num){
		if((date.trim().length()==8)&&(isDigit(date)))
		{
			try{
				String check = "select room_status from db where room_number ="+room_num;
				String T = "T";
				stmt = myCon.createStatement();
				rs = stmt.executeQuery(check);
				while(rs.next()){check=rs.getString(1);}
				if(check.trim().equals(T)){
					JOptionPane.showMessageDialog(null, "�̹� ��ϵ� ���� �ֽ��ϴ�. �׷��Ƿ� �����մϴ�");
					String check_id = "select id from db where day ="+date+" and room_number = "+room_num+" and customer = '"+customer+"'";
					check_id=Query(check_id);
					
					Delete_checkin(date, room_num, customer);
					
					String r_number = "select room_number from room where room_number = "+room_num;
					String customer_name = "select customer from customer where customer = '"+customer+"'";
					
					staff_name =Query(staff_name, "�������� �����ϴ�.");	
					r_number=Query(r_number, "���� �������� �ʽ��ϴ�.");
					customer_name=Query(customer_name, "�������� �����ϴ�.");

					for(int i=0;i<Integer.parseInt(day);i++){
						int set_day = Integer.parseInt(date)+i;
						String tobook = "insert into db values ("+check_id+","+set_day+","+ r_number.trim()+",'T','"+customer.trim()+"','"+ staff_name.trim()+"')";
						no_return_Query(tobook, "���� ���� ����");
					}
				}
				else{
					String r_number = "select room_number from room where room_number = "+room_num;
					String customer_name = "select customer from customer where customer = '"+customer+"'";

					staff_name =Query(staff_name, "�������� �����ϴ�.");	
					r_number=Query(r_number, "���� �������� �ʽ��ϴ�.");
					customer_name=Query(customer_name, "�������� �����ϴ�.");

					for(int i=0;i<Integer.parseInt(day);i++){
						int set_day = Integer.parseInt(date)+i;
						String tobook = "insert into db values ("+id+","+set_day+","+ r_number.trim()+",'T','"+customer.trim()+"','"+ staff_name.trim()+"')";
						no_return_Query(tobook, "���� ���� ����");
					}
					id++;
				}
				check();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"���ؿ� �°� �־��ּ���");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "checkin ����  YYYYMMDD�������� �����ּ���");
		}

	}
	//���� ����
	public void Delete_checkin(String date,String room,String name){
		String check_id = "select id from db where day ="+date+" and room_number = "+room+" and customer = '"+name+"'";
		check_id=Query(check_id);
		String check = "delete from db where id ="+check_id;
		no_return_Query(check, "������ �����ϴ�. �ٽ��ѹ� Ȯ�����ּ���");
		check();
	}
	//���� ��¥�� �� ������ ����ȭ ��Ű�� ���� �Լ��� ���� ��¥�� db���� �˻� �� �� �ֽ�ȭ
	public void check(){
		for(int i=0;i<20;i++){
			try{
				int k =0;
				if(i<10){k=101+i;}
				else{k=191+i;}
				String ch = "select room_number from db where room_number = "+k+" and day ="+day;
				stmt = myCon.createStatement();
				rs = stmt.executeQuery(ch);
				while(rs.next()){ch = rs.getString(1);}
				if(Integer.parseInt(ch)<200){
				book.room_info(Integer.parseInt(ch)-101);
				}
				else{
					book.room_info(Integer.parseInt(ch)-191);	
				}
			}
			catch(Exception e){
				book.room_info_re(i);
			}	
		}
	}
	//string ���� �������� �Ǻ�
	public boolean isDigit(String string)
	{
		for ( int i = 0 ; i < string.trim().length() ; ++i ){
			if ( !( 48   <=  ((int)string.charAt(i))   && 57>= ( (int)string.charAt(i) )  )  ){
				return false;
			}
		}   
		return true;
	}
	//return ����� �ʿ��� query�� �׷��� ������ �ʿ���� ����ó��
	public String Query(String query)
	{
		try{
			String report = "null";
			stmt = myCon.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){report = rs.getString(1);}
			return report;
		}catch(Exception e)
		{return null;}
	}
	//return ����� �ʿ��ϰ� ������ ���ٸ� Ư���� �޼����� ����� �־�� �� ����ó��
	public String Query(String query,String err)
	{
		try{
			String report = "null";
			stmt = myCon.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){report = rs.getString(1);}
			return report;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, err);
			return null;}
	}
	//return ����� �ʿ���� ������ ���ٸ� Ư���� �޼����� ����� �־�� �� ����ó��
	public void no_return_Query(String query,String err)
	{
		try{
			stmt = myCon.createStatement();
			rs = stmt.executeQuery(query);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, err);
		}
	}
}