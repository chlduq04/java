public class RoomInfo{
		private String room_number;
		private String room_type;
		private String room_status;
		private int room_capacity;
		
		public RoomInfo(String room_number, String room_type, int room_capcity, String room_status){
			this.room_number = room_number;
			this.room_type = room_type;
			this.room_status = room_status;
			this.room_capacity = room_capcity;
		}
		
		public String getRoomNumber(){
			return room_number;
		}
		public String getRoomType(){
			return room_type;
		}
		public String getRoomStatus(){
			return room_status;
		}
		public int getRoomCapacity(){
			return room_capacity;
		}
	}