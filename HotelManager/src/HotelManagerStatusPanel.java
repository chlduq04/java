import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/* Status Panel 
 * showing the status of hotel rooms
 */

public class HotelManagerStatusPanel extends JPanel{
	public HotelManagerStatusPanel(){
		setComponents();
		//Connection conn = HotelManagerDB.getConnection();
//		try{
//			Statement stmt = conn.createStatement();
//			// 
//		}
//		catch(SQLException e){
//			System.out.println(e.getMessage());
//		}
	}
	
	private void setComponents(){
		this.setLayout(new BorderLayout());
		this.add(new JLabel("객실 예약 현황"), BorderLayout.NORTH);
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new GridLayout(4,5));
		for(int i = 0; i < 20; i++){
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(Color.black, 1, true));
			panel.setBackground(Color.red);
			mainpanel.add(panel);
		}
		this.add(mainpanel, BorderLayout.CENTER);
	}
}
