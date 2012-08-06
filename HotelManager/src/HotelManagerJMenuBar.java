import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class HotelManagerJMenuBar extends JMenuBar implements ActionListener{
	JMenu menu;
	JMenuItem fileChoose;
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Open"){
			System.out.print("called");
			JFileChooser chooser = new JFileChooser();
		    int returnVal = chooser.showOpenDialog(this.getParent());
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println(chooser.getSelectedFile().getName());
		       System.out.println(chooser.getSelectedFile().getPath());
		       File file = new File(chooser.getSelectedFile().getPath());
		       try {
		    	   Connection conn = HotelManagerDB.getConnection();

		    	   // 파일을 읽어들인다
		    	   BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		    	   String line;
		    	   int state = 0;
		    	   while((line=reader.readLine()) != null){
		    		   // 자료의 줄수를 구한다
		    		   int rows = getNumOfRows(line);
	    			   while(rows > 0){
	    				   line = reader.readLine();
	    				   if( state == 0 ){ // customer information process
	    					   // table이 존재하지 않는다면 테이블을 생성한다
	    					   
	    					   try{
	    						   String query = "select * from  customers";
	    						   Statement stmt = conn.createStatement();
	    						   stmt.executeQuery(query);
	    					   }
	    					   catch(SQLException exception){
	    						   try{
	    							   String query = "CREATE TABLE customers(" +
	   	   	    					   		"id int PRIMARY KEY," +
	   	   	    							"name varchar(30)," +
	   	   	    							"sex varchar(20)," +
	   	   	    							"addr varchar(20)," +
	   	   	    							"phone varchar(45)" +
	   	   	    							");";
	    							   Statement stmt = conn.createStatement();
	    							   stmt.executeQuery(query);
	    							   query = "CREATE SEQUENCE customers_seq START WITH 1 INCREMENT BY 1";
	    							   stmt.executeQuery(query);
	    						   }
	    						   catch(SQLException exception1){
	    							   System.out.println(exception1.getMessage());
	    						   }
	    						   System.out.println(exception.getMessage());
	    					   }
	    					   
	    					   String [] datas = parseData(line);
	    					   String name = datas[0];
	    					   String sex = datas[1];
	    					   String addr = datas[2];
	    					   String phone = datas[3];
	    					   String query = String.format("INSERT INTO customers (id, name, sex, addr, phone) VALUES (customers_seq.nextval, '%s', '%s', '%s', '%s')", name, sex, addr, phone);
	    					   System.out.println(query);
	    					   try{
	    						   Statement stmt = conn.createStatement();
	    						   stmt.executeQuery(query);
	    					   }
	    					   catch(SQLException exception){
	    						   System.out.println(exception.getMessage());
	    					   }
	    					   
	    					   System.out.println(state + name + sex + addr + phone);
	    				   }
	    				   else if ( state == 1 ){ // staff information process
	    					   // create table 'staffs' if not exists
	    					   
	    					   try{
	    						   String query = "select * from  staffs";
	    						   Statement stmt = conn.createStatement();
	    						   stmt.executeQuery(query);
	    					   }
	    					   catch(SQLException exception){
	    						   try{
	    							   String query = "CREATE TABLE staffs (" +
	   	   	    					   		"id int PRIMARY KEY," +
	   	   	    					   		"name varchar(30)," +
	   	   	    					   		"sex varchar(20)," +
	   	   	    					   		"addr varchar(20)," +
	   	   	    					   		"phone varchar(45)" +
	   	   	    					   		");";
	    							   Statement stmt = conn.createStatement();
	    							   stmt.executeQuery(query);
	    							   query = "CREATE SEQUENCE staffs_seq START WITH 1 INCREMENT BY 1";
	    							   stmt.executeQuery(query);
	    						   }
	    						   catch(SQLException exception1){
	    							   System.out.println(exception1.getMessage());
	    						   }
	    					   }
	    					   String [] datas = parseData(line);
	    					   String name = datas[0];
	    					   String sex = datas[1];
	    					   String addr = datas[2];
	    					   String phone = datas[3];
	    					   
	    					   String query = String.format("INSERT INTO staffs (id, name, sex, addr, phone) VALUES (staffs_seq.nextval, '%s', '%s', '%s', '%s')", name, sex, addr, phone);
	    					   try{
	    						   Statement stmt = conn.createStatement();
	    						   stmt.executeQuery(query);
	    					   }
	    					   catch(SQLException exception){
	    						   System.out.println(exception.getMessage());
	    					   }
	    					   
	    					   System.out.println(state + name + sex + addr + phone);
	    				   }
	    				   else if ( state == 2){ // room information process
	    					   // create table rooms if not exists 
	    					   
	    					   try{
	    						   String query = "select * from  rooms";
	    						   Statement stmt = conn.createStatement();
	    						   stmt.executeQuery(query);
	    					   }
	    					   catch(SQLException exception){
	    						   try{
	    							   String query = "CREATE TABLE rooms(" +
	   	   	    					   		"id int PRIMARY KEY," +
	   	   	    					   		"room_number int," +
	   	   	    					   		"capacity int," +
	   	   	    					   		"type varchar(30)" +
	   	   	    					   		"status tinyint" +
	   	   	    					   		");";
	    							   Statement stmt = conn.createStatement();
	    							   stmt.executeQuery(query);
	    							   query = "CREATE SEQUENCE rooms_seq START WITH 1 INCREMENT BY 1";
	    							   stmt.executeQuery(query);
	    						   }
	    						   catch(SQLException exception1){
	    							   System.out.println(exception1.getMessage());
	    						   }
	    						   System.out.println(exception.getMessage());
	    					   }
	    					   
	    					   String [] datas = parseData(line);
	    					   String room_number = datas[0];
	    					   String capacity = datas[1];
	    					   String type = datas[2];
	    					   
	    					   String query = String.format("INSERT INTO rooms (id, room_number, capacity, type) VALUES (rooms_seq.nextval, %s, %s, '%s')", room_number, capacity, type);
	    					   try{
	    						   Statement stmt = conn.createStatement();
	    						   stmt.executeQuery(query);
	    					   }
	    					   catch(SQLException exception){
	    						   System.out.println(exception.getMessage());
	    					   }
	    					   
	    					   System.out.println(state + room_number + capacity + type);
	    				   }
	    				   rows--; // 줄 하나가 처리되었슴
	    			   }
	    			   state++; // 다음 정보들을 처리하기 위해 진행시킴
		    	   }
		       } catch (FileNotFoundException exception) {
				// TODO Auto-generated catch block
		    	   System.out.println(exception.getMessage());
		    	   exception.printStackTrace();
		       } 
		       catch (IOException exception){
		    	   System.out.println(exception.getMessage());
		       }
		    }
		}
	}
	public HotelManagerJMenuBar(){
		super();
		addMenus();
	}
	private void addMenus(){
		// 파일 메뉴 생성후 추가 
		menu = new JMenu("File");
		fileChoose = new JMenuItem("Open");
		fileChoose.addActionListener(this);
		menu.add(fileChoose);
		this.add(menu);
	}
	private int getNumOfRows(String line){
		int rows;
		if(line.matches("[0-9]*")){
			rows = Integer.parseInt(line);
		}
		else{
			rows = 0;
		}
		return rows;
	}
	private String[] parseData(String line){
		String[] datas = line.split(" |\t");
		return datas;
	}
}
