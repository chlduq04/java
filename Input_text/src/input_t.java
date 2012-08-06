import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class input_t {
	public static void main(String args[]) throws IOException{
		/*
		String source = "파일 쓰기.";
		char input[] = new char[source.length()];
		source.getChars(0, source.length(), input, 0);
		FileWriter fw = new FileWriter("a.txt");
		fw.write(input);
		fw.close();
		
		System.out.println("파일이 생성됨. \n");*/
		
		FileReader File = new FileReader("a.txt");
		int i;
		while((i=File.read()) != -1){
			System.out.print((char)i);
		}
		File.close();
		
	}
}
