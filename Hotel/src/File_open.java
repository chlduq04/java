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
	//file�� �о� db�� �����ϱ� ���� filechooser�� ���� ������ �а� ����� 
	//�о���� ������ ���� db�� ���� ����ִ´�.
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
			System.out.println("������ ���� : "+chooser.getSelectedFile().getName());
			File file = chooser.getSelectedFile();
			System.out.println("������ �ִ� ���丮 : "+file);

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
