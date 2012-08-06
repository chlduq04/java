import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import setting.connect_sql;


public class File_open extends JFrame{
	private connect_sql db;
	private JFileChooser filechoose;
	private int status;
	private BufferedReader input_file;
	private StringTokenizer token;
	private int order;
	//file을 읽어 db에 저장하기 위해 filechooser을 통해 파일을 읽게 만들고 
	//읽어들인 정보를 통해 db에 값을 집어넣는다.
	File_open(connect_sql only_db){
		db = only_db;
		filechoose = new JFileChooser(".");
		status = filechoose.showOpenDialog(null);
		order = 0;

		JFileChooser chooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("java files", "java");
		chooser.addChoosableFileFilter(filter);

		int value = chooser.showOpenDialog(null);
		if (value == JFileChooser.APPROVE_OPTION){
			System.out.println("선택한 파일 : "+chooser.getSelectedFile().getName());
			File file = chooser.getSelectedFile();
			System.out.println("파일이 있는 디렉토리 : "+file);

			try{
				input_file = new BufferedReader(new FileReader(file));
				String line;

				while((line = input_file.readLine())!=null){
					//System.out.println(line);

					token = new StringTokenizer(line);
					String check = token.nextToken();

					if(order<3){
						if((48<=(int)check.charAt(0))&&57>=(int)check.charAt(0)){
							order++;
							line = input_file.readLine();
						}
					}

					switch(order){
					case 1:
						token = new StringTokenizer(line);
						db.insert_customer_info(token.nextToken().trim(), token.nextToken().trim(), token.nextToken().trim(), token.nextToken().trim());
						break;
					case 2:
						token = new StringTokenizer(line);
						db.insert_staff_info(token.nextToken().trim(), token.nextToken().trim(), token.nextToken().trim(),token.nextToken().trim());
						break;
					case 3:
						token = new StringTokenizer(line);
						db.insert_room_info(Integer.parseInt(token.nextToken().trim()), Integer.parseInt(token.nextToken().trim()), token.nextToken().trim());
						break;
					}
				}
				input_file.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
	}
}
