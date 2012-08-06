import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* HotelManagerInfo 
 * HotelManager의 모든 데이터는 이곳에서 받아서 쓰게 만든다.
 */
public class HotelManagerInfo {
	private static HotelManagerInfo info;
		
	private String[] sex;
	private RoomInfo[] rooms;
	private String[] days;

	private HotelManagerInfo(){
		sex = new String[2];
		sex[0] = "남자";
		sex[1] = "여자";
		
		days = new String[5];
		days[0] = "1";
		days[1] = "2";
		days[2] = "3";
		days[3] = "4";
		days[4] = "5";
	}
	
	public static HotelManagerInfo getInfo(){
		if(info == null){
			info = new HotelManagerInfo();
		}
		info.refreshInfo();
		return info;
	}
	
	public String[] getDays(){
		return days;
	}
	public String[] getSex(){
		return sex;
	}
	public RoomInfo[] getRoomInfos(){
		return rooms;
	}
	public String[] getRoomNumbers(){
		String[] numbers = new String[rooms.length];
		for(int i = 0; i < rooms.length; i++){
			numbers[i] = rooms[i].getRoomNumber();
		}
		return numbers;
	}
	private void refreshInfo(){
		Connection conn = HotelManagerDB.getConnection();
		try{
			String query = "SELECT count(id) FROM rooms";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			int num_of_rooms = Integer.parseInt(rs.getString("count(id)"));
			if(num_of_rooms != 0){
				rooms = new RoomInfo[num_of_rooms];
				query = "SELECT * FROM rooms";
				rs = stmt.executeQuery(query);
				for(int i = 0; i < num_of_rooms; i++){
					rs.next();
					rooms[i] = new RoomInfo(rs.getString("room_number"), 
											rs.getString("type"), 
											Integer.parseInt(rs.getString("capacity")), 
											rs.getString("status"));
				}
			}
			else{ // 방정보가 업쪄
				//더미값으로 20개를 넣어놓는다
				
			}
		}
		catch(SQLException exception){
			rooms = new RoomInfo[20];
			for(int i = 0; i < 20; i++){
				rooms[i] = new RoomInfo("", "", 0, "미초기");
			}
			System.out.println(exception.getMessage());
		}
	}
}
