/* HotelManager
 * the class that includes the main function
 */


public class HotelManager {
	private HotelManagerFrame frame;
	
	public static void main(String [] args){
		HotelManager manager = new HotelManager();
		manager.init();
	}
	// 호텔관리 프로그램 초기화 
	public void init(){
		frame = new HotelManagerFrame("호텔 관리 프로그램", 700, 400);
	}
}
