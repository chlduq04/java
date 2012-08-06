package server;
import java.util.Calendar;

import account_information.New_account;

public class Main_info{								//���¸� ������ �� �ִ� ������ ������
	private static float interest_check = 0.01f;	//���ڸ� ���ϰ� �� �� �ִ� ������
	private static int information_check = 10;		//��ȸ ���� ���ϰ� �� �� �ִ� ���� ��
	private static int account_check = 5;
	
	private int account_index = 0;					//���� ������ index��
	private int check=0;							

	private Calendar time;
	private New_account account[];
	private Calendar interest;
	public Main_info(int q1,int q2,float q3) 		//�����ڿ��� �Է¹��� ���� ���α׷��� �Ѱ� ����
	{
	    account_check = q1;
		information_check = q2;
		interest_check = q3;
		interest = Calendar.getInstance();
		check = interest.get(Calendar.MINUTE);
		account = new New_account[5];
		account_index = 0;
	}

	public New_account[] check()					//���� ����Ʈ ��ȯ
	{return account;}

	public int money(int index)						//������ ���� �ݾ� ��ȯ
	{return account[index].now_account();}

	public String transfer(int s_index,int t_index,int trans_m)	//��ü
	{
		if(check_widr(s_index, trans_m))						//��ü�� ��ݰ� �ӷ����� ����
		{

			account[s_index].withdraw(trans_m);
			account[t_index].deposit(trans_m);
			recorder(s_index, now_time(), "��ü", trans_m);
			recorder(t_index, now_time(), "��ü ����", trans_m);
			return ""
			+account[s_index].toString()+"�� ���� �ݾ���\n"
			+account[s_index].now_account()+"�Դϴ�\n"
			+account[t_index].toString()+"�� ���� �ݾ���\n"
			+account[t_index].now_account()+"�Դϴ�";
		}
		else
		{
			return "�ܾ��� �����մϴ�";
		}

	}

	public String deposit(int index ,int deposit_m)				//�Ա�
	{
		account[index].deposit(deposit_m);
		recorder(index, now_time(), "�Ա�", deposit_m);
		return "���� �ݾ���"+account[index].now_account()+"�Դϴ�";
	}

	public String withdraw(int index,int withdraw_m)			//���
	{
		if(check_widr(index, withdraw_m))
		{
			account[index].withdraw(withdraw_m);
			recorder(index, now_time(), "���", withdraw_m);
			return "���� ����"+account[index].now_account()+"�Դϴ�";
		}
		else
		{
			return "�ܾ��� �����մϴ�";
		}
	}

	public boolean check_widr(int index, int withdraw_m)		//����ϱ� ����Ѱ�
	{
		if(account[index].now_account()>=withdraw_m)
			return true;
		else
			return false;
	}

	public String Make_new_account(String number) 				//���ο� ���� ����
	{
		boolean check = false;
		if (account_index >= account_check)
			check = true;
		else if(account_index == 0)
			check = false;
		else 
		{
			for (int i = 0; i < account_index; i++) 
			{
				if (account[i].toString().equals(number))
					check = true;
			}
		}

		if (check == false) 
		{
			New_account acc_new = new New_account(number,interest_check,information_check);
			account[account_index++] = acc_new;
			String s = "���°� �����Ǿ����ϴ�. \n���� : " + number;
			return s;
		} 
		else 
		{
			String s = "�ߺ��Ǵ� ���°� �ְų�\n ������ ������ �ʹ� �����ϴ�";
			return s;
		}
	}

	public void delete_account(int num)							//���� ����
	{
		account[num] = null;
		for (int i = 0; i < account_index-1; i++) 
		{
			if(i>=num)
				account[i] = account[i+1];
		}
		account[account_index-1]=null;
		account_index--;
	}

	public String now_time()									//���� �ð� Ȯ��
	{
		time = Calendar.getInstance();
		return time.get(Calendar.DATE)+"��"+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE);
	}

	public void recorder(int index,String date,String info,int amount)	//���
	{account[index].record(date+" "+info+" "+amount);}

	public String information(int index)						//���� ��ȸ ���� ��ȯ
	{return account[index].print_info();}

	public void run() {											//���ڸ� �ֱ����� �Լ�
		for(;;){
			Calendar time = Calendar.getInstance();
			int now = time.get(Calendar.MINUTE);
			if(now-check>=1)
			{
				int i=0;
				for (i = 0; i < account_index; i++) 
				{
					account[i].record(time.get(Calendar.DATE)+"��"+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE)+" ���� "+account[i].getinterest());
				}			
				check = now;
			}
		}
	}
}
