
public class New_account {

	private String account_name = "";
	private int account;
	
	public New_account(String number)
	{
		account_name = number;
		account = 0;
	}
	public New_account(String number,int account)
	{
		account_name = number;
		this.account = account;
	}
	
	public String now_account()
	{
		return ""+account;
	}
	
/*	public String account_name()
	{
		return account_name;
	}
	*/
	public String withdraw(int money)
	{
		if(money<=account){
			account -= money;
			return "���� ����"+account+"�Դϴ�";
		}
		else
		{
			return "�ܾ��� �����մϴ�";
		}
	}
	
	public void deposit(int money)
	{
		account += money; 
	}
	
	public String toString()
	{
		return account_name;
	}
}
