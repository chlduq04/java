import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class fileopen {
	public static void main(String args[]){
		
		JFileChooser chooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("java files", "java");
		chooser.addChoosableFileFilter(filter);
		
		int value = chooser.showOpenDialog(null);
		if (value == JFileChooser.APPROVE_OPTION){
		
			System.out.println("������ ���� : "+chooser.getSelectedFile().getName());
			File file = chooser.getSelectedFile();
			System.out.println("������ �ִ� ���丮 : "+file);
		}


	}
}
