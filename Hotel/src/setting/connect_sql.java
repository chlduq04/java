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
//시작한 뒤 아이디와 패스워드를 바로 물어본다. 그리고 접속
	public connect_sql(BookInfo books){
		book = books;
		time = new GregorianCalendar();
		day = time.get(GregorianCalendar.YEAR)*10000+(time.get(GregorianCalendar.MONTH)+1)*100+time.get(GregorianCalendar.DATE);

		try{
			String oracle_id=JOptionPane.showInputDialog("Oaacle 아이디를 적어주세요");
			String oracle_password=JOptionPane.showInputDialog("Oracle 비번을 적어주세요");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			myCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost",oracle_id,oracle_password);					
			stmt = myCon.createStatement();
			//테이블 구성 string 문장			
			String create_db_table = "create table DB 				(id				integer		not null, day			integer		not null, room_number		integer		not null, room_status	char(2)		not null, customer	char(20)	not null, staff		char(20)	not null)";
			String create_customer_table = "create table customer 	(customer		char(20)	not null, sex				char(5)		not null, address		char(20)	not null, context	integer		not null, primary key(context))";
			String create_room_table = "create table room			(room_number	integer		not null, room_capacity		integer		not null, room_type		char(20)	not null, primary key(room_number))";
			String create_staff_table ="create table staff 			(staff			char(20)	not null, sex				char(5)		not null, address		char(20)	not null, context	integer		not null, primary key(context))";
			//no return query(전송할 명령, 실패했을 경우 띄워줄 오류창 메세지)
			no_return_Query(create_db_table, "같은 이름의 테이블이 존재합니다, db");
			no_return_Query(create_customer_table, "같은 이름의 테이블이 존재합니다, customer");
			no_return_Query(create_room_table, "같은 이름의 테이블이 존재합니다, room");
			no_return_Query(create_staff_table, "같은 이름의 테이블이 존재합니다, staff");
			//id를 확인한 후 id가 있다면 새로운 아이디로 추가하고 없다면 0부터 새로 시작한다
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
//방 정보를 방의 이름으로 검색후 return
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
			return "방 번호 : "+room_number.trim()+"	수용인원 : "+capacity.trim()+"	타입 : "+type.trim()+"	방의 상태 : "+room_status.trim()+"	최대 투숙 고객 : "+most_customer.trim()+"	("+most_customer_count.trim()+")";
		}
		catch(Exception e){
			return "방 번호 : "+room_number.trim()+"	수용인원 : "+capacity.trim()+"	타입 : "+type.trim()+"	방의 상태 : 비어있음	최대 투숙 고객 : 없음	( 0 )";
		}
	}
//방 정보 추가
	public void insert_room_info(int number, int capacity, String type){
		String insert_db = "insert into room values ("+number+","+capacity+",'"+type+"')";
		no_return_Query(insert_db, "방 정보입력 오류");
	}
//고객정보를 고객 이름으로 검색 후 return
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

			return "이름 : "+customer.trim()+"	성별 : " + sex.trim() +"	주소 : "+address.trim()+"	연락처 : "+context.trim()+"	총 투숙기간 "+total_stay.trim()+"	최근 투숙일 : "+recently_stay.trim()+"	최다전담직원 () : "+maximum_staff.trim()+"	("+maximum_staff_count.trim()+")";
		}
		catch(Exception e){
			return "이름 : "+customer.trim()+"	성별 : " + sex.trim() +"	주소 : "+address.trim()+"	연락처 : "+context.trim()+"	총 투숙기간 : 0	최근 투숙일 : 없음		최다전담직원 () : 없음(0)";
		}
	}
//고객정보 추가
	public void insert_customer_info(String c_name,String c_sex,String c_address,String phone){
		if(!(c_sex.equals("남"))&&!(c_sex.equals("여"))){
			JOptionPane.showMessageDialog(null, "성별은 남이나 여로 표시해 주세요");
		}
		else if(!(isDigit(phone))){
			JOptionPane.showMessageDialog(null, "전화번호는 숫자로만 입력해 주세요");
		}
		else{
		String insert_db = "insert into customer values ('"+c_name+"','"+c_sex+"','"+c_address+"',"+phone+")";
		no_return_Query(insert_db, "고객 정보 입력 오류");
		}
	}
	//직원정보를 스테프 이름으로 검색 후 return
	public String select_staff_info(String staff){
		String sex = "select sex from staff where staff ='"+staff+"'";
		String address = "select address from staff where staff ='"+staff+"'";
		String context = "select context from staff where staff ='"+staff+"'";
		//객실 최다
		String maximum_room_number = "select stats_mode(room_number) from db where staff = '"+staff+"' group by staff";
		String maximum_room_number_count = "select max(count(room_number)) from db where staff = '"+staff+"' group by room_number";;
		//잠당 손님 최다
		String maximum_customer = "select stats_mode(customer) from db where staff = '"+staff+"' group by staff";
		String maximum_customer_count = "select max(count(customer)) from db where staff = '"+staff+"' group by customer";
		try{
			//있는지 확인 없으면 err이 뜬다

			sex=Query(sex);
			address=Query(address);
			context=Query(context);
			maximum_room_number=Query(maximum_room_number);
			maximum_room_number_count=Query(maximum_room_number_count);
			maximum_customer=Query(maximum_customer);
			maximum_customer_count=Query(maximum_customer_count);
			return 	"이름 : "+staff.trim()+"	성별 : " +sex.trim()+"	주소 : "+address.trim()+"	연락처 : "+context.trim()+"	최대담당고객 : "+maximum_customer.trim()+"	("+maximum_customer_count.trim()+")	"+"	최대담당객실 : "+maximum_room_number.trim()+"	("+maximum_room_number_count.trim()+")";

		}catch(Exception e){
			return 	"이름 : "+staff.trim()+"	성별 : " +sex.trim()+"	주소 : "+address.trim()+"	연락처 : "+context.trim()+"	최대담당고객 (0): 없음(0)		최대담당객실() : 없음(0)";
		}
	}
	//직원정보 추가
	public void insert_staff_info(String s_name,String s_sex,String s_address,String phone){
		if(!(s_sex.equals("남"))&&!(s_sex.equals("여"))){
			JOptionPane.showMessageDialog(null, "성별은 남이나 여로 표시해 주세요");
		}
		else if(!(isDigit(phone))){
			JOptionPane.showMessageDialog(null, "전화번호는 숫자롬만 표기해 주세요");
		}
		else{
			String insert_db = "insert into staff values ('"+s_name+"','"+s_sex+"','"+s_address+"',"+phone+")";
			no_return_Query(insert_db, "직원 정보 입력 오류");
		}
	}
	//예약 정보 추가
	public void ToBook(String customer,String date,String day,String room_num){
		String staff_name = "select * from (select staff from staff order by DBMS_RANDOM.random)where rownum = 1";
		checkin(staff_name,customer,date,day,room_num);
	}
	//예약 정보 추가 함수인 tobook에서 불러질 함수로 실제 예약을 한다
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
					JOptionPane.showMessageDialog(null, "이미 등록된 방이 있습니다. 그러므로 변경합니다");
					String check_id = "select id from db where day ="+date+" and room_number = "+room_num+" and customer = '"+customer+"'";
					check_id=Query(check_id);
					
					Delete_checkin(date, room_num, customer);
					
					String r_number = "select room_number from room where room_number = "+room_num;
					String customer_name = "select customer from customer where customer = '"+customer+"'";
					
					staff_name =Query(staff_name, "스테프가 없습니다.");	
					r_number=Query(r_number, "방이 존재하지 않습니다.");
					customer_name=Query(customer_name, "고객정보가 없습니다.");

					for(int i=0;i<Integer.parseInt(day);i++){
						int set_day = Integer.parseInt(date)+i;
						String tobook = "insert into db values ("+check_id+","+set_day+","+ r_number.trim()+",'T','"+customer.trim()+"','"+ staff_name.trim()+"')";
						no_return_Query(tobook, "객실 예약 오류");
					}
				}
				else{
					String r_number = "select room_number from room where room_number = "+room_num;
					String customer_name = "select customer from customer where customer = '"+customer+"'";

					staff_name =Query(staff_name, "스테프가 없습니다.");	
					r_number=Query(r_number, "방이 존재하지 않습니다.");
					customer_name=Query(customer_name, "고객정보가 없습니다.");

					for(int i=0;i<Integer.parseInt(day);i++){
						int set_day = Integer.parseInt(date)+i;
						String tobook = "insert into db values ("+id+","+set_day+","+ r_number.trim()+",'T','"+customer.trim()+"','"+ staff_name.trim()+"')";
						no_return_Query(tobook, "객실 예약 오류");
					}
					id++;
				}
				check();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"기준에 맞게 넣어주세요");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "checkin 에는  YYYYMMDD형식으로 적어주세요");
		}

	}
	//예약 제거
	public void Delete_checkin(String date,String room,String name){
		String check_id = "select id from db where day ="+date+" and room_number = "+room+" and customer = '"+name+"'";
		check_id=Query(check_id);
		String check = "delete from db where id ="+check_id;
		no_return_Query(check, "예약이 없습니다. 다시한번 확인해주세요");
		check();
	}
	//현재 날짜와 방 정보를 동기화 시키기 위한 함수로 현재 날짜로 db정보 검색 후 방 최신화
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
	//string 값이 숫자인지 판별
	public boolean isDigit(String string)
	{
		for ( int i = 0 ; i < string.trim().length() ; ++i ){
			if ( !( 48   <=  ((int)string.charAt(i))   && 57>= ( (int)string.charAt(i) )  )  ){
				return false;
			}
		}   
		return true;
	}
	//return 결과가 필요한 query문 그러나 오류는 필요없는 문장처리
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
	//return 결과가 필요하고 오류가 난다면 특별한 메세지를 출력해 주어야 할 문장처리
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
	//return 결과는 필요없고 오류가 난다면 특별한 메세지를 출력해 주어야 할 문장처리
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