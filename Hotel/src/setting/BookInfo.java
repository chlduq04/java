package setting;
import java.awt.Button;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookInfo extends Setting{
	private int room_limit;
	public JPanel room_info_panel;
	private JLabel info_title;
	private JLabel day;
	private JButton room_info[];
//�����ڿ��� �ʱ�ȭ
	public BookInfo(){
		room_limit = room_count;
		room_info_panel = new JPanel();
		info_title = new JLabel("���� ���� ��Ȳ");
		room_info = new JButton[room_limit];
		this.room();
		this.recheck();
	}
//���� �̸��� ���δ�
	public void room(){
		for(int i=0;i<room_limit;i++){
			int j =0;
			if(i<10){j = i+101;}
			else{j = i+191;}
			room_info[i] = new JButton("< "+j+" >");		
		}
	}
//�� ������ ����
	public void recheck(){
		room_info_panel.setLayout(new GridLayout(room_limit/5,5));
		for(int i=0;i<room_limit;i++){
			room_info_panel.add(room_info[i]);
		}
	}
//���� ���ó�¥�� ������ٸ� �� ������ Ȯ���� �����Ѵ�
	public void room_info(int i){
		room_info[i].setText("FULL");
		this.room_info[i].repaint();
		this.room_info_panel.repaint();
	}
//���ó�¥�� ������ ��ҵǾ��ٸ� �ٽ� �����·� �ٲ۴�,	
	public void room_info_re(int i){
		int room =0;
		if(i<10){room=i+101;}
		else{room=i+191;}		
		room_info[i].setText("< "+room+" >");
		this.room_info[i].repaint();
		this.room_info_panel.repaint();
	}
}
