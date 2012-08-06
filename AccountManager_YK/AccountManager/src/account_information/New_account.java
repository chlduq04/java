package account_information;


public class New_account{			//실제 계좌

	private int ti_limit=10;
	private int t_index=0;
	private String account_name = "";
	private int account;
	private String t_info[];
	private float interest=0;

	public New_account(String number,float int_value,int inf_value)	//새 계좌
	{
		ti_limit = inf_value;
		interest = int_value;
		t_info = new String[ti_limit];
		for(int i=0;i<ti_limit;i++)
			t_info[i] = "";
		account_name = number;
		account = 0;

	}
	public New_account(String number,int money,float int_value,int inf_value)//새 계좌 여기선 쓰지 않았습니다
	{
		ti_limit = inf_value;
		interest = int_value;
		t_info = new String[ti_limit];
		for(int i=0;i<ti_limit;i++)
			t_info[i] = "";
		account_name = number;
		account = money;
	}

	public int getinterest()	//이자
	{
		int value = (int)(account*interest);
		if(account!=0)
			account += value;
		return value;
	}

	public void record(String information)		//기록
	{
		if(t_index >= ti_limit)
		{
			t_index = ti_limit;
			for(int i=0;i<ti_limit-1;i++)
			{
				t_info[i] = t_info[i+1];
			}
			t_info[ti_limit-1] = information;
		}
		else
			t_info[t_index++] = information;

	}

	public String print_info()					//기록 출력
	{
		String print = "";
		for(int i=0;i<ti_limit;i++)
		{
			print+="\n"+t_info[i];
		}
		return print;
	}

	public int now_account()					//현재금액
	{return account;}

	public void withdraw(int money)				//출금
	{account -= money;}

	public void deposit(int money)				//입금
	{account += money;}

	public String toString()					//이름
	{return account_name;}
}
