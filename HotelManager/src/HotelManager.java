/* HotelManager
 * the class that includes the main function
 */


public class HotelManager {
	private HotelManagerFrame frame;
	
	public static void main(String [] args){
		HotelManager manager = new HotelManager();
		manager.init();
	}
	// ȣ�ڰ��� ���α׷� �ʱ�ȭ 
	public void init(){
		frame = new HotelManagerFrame("ȣ�� ���� ���α׷�", 700, 400);
	}
}
