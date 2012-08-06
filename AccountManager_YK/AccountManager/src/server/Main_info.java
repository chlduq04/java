package server;
import java.util.Calendar;

import account_information.New_account;

public class Main_info{								//계좌를 조정할 수 있는 유일한 프레임
	private static float interest_check = 0.01f;	//이자를 변하게 할 수 있는 설정값
	private static int information_check = 10;		//조회 양을 변하게 할 수 있는 설정 값
	private static int account_check = 5;
	
	private int account_index = 0;					//현재 계좌의 index값
	private int check=0;							

	private Calendar time;
	private New_account account[];
	private Calendar interest;
	public Main_info(int q1,int q2,float q3) 		//생성자에서 입력받은 수로 프로그램의 한계 조정
	{
	    account_check = q1;
		information_check = q2;
		interest_check = q3;
		interest = Calendar.getInstance();
		check = interest.get(Calendar.MINUTE);
		account = new New_account[5];
		account_index = 0;
	}

	public New_account[] check()					//계좌 리스트 반환
	{return account;}

	public int money(int index)						//계좌의 현재 금액 반환
	{return account[index].now_account();}

	public String transfer(int s_index,int t_index,int trans_m)	//이체
	{
		if(check_widr(s_index, trans_m))						//이체는 울금과 임력으로 구현
		{

			account[s_index].withdraw(trans_m);
			account[t_index].deposit(trans_m);
			recorder(s_index, now_time(), "이체", trans_m);
			recorder(t_index, now_time(), "이체 받음", trans_m);
			return ""
			+account[s_index].toString()+"에 남은 금액은\n"
			+account[s_index].now_account()+"입니다\n"
			+account[t_index].toString()+"에 남은 금액은\n"
			+account[t_index].now_account()+"입니다";
		}
		else
		{
			return "잔액이 부족합니다";
		}

	}

	public String deposit(int index ,int deposit_m)				//입금
	{
		account[index].deposit(deposit_m);
		recorder(index, now_time(), "입금", deposit_m);
		return "현재 금액은"+account[index].now_account()+"입니다";
	}

	public String withdraw(int index,int withdraw_m)			//출금
	{
		if(check_widr(index, withdraw_m))
		{
			account[index].withdraw(withdraw_m);
			recorder(index, now_time(), "출금", withdraw_m);
			return "남은 돈은"+account[index].now_account()+"입니다";
		}
		else
		{
			return "잔액이 부족합니다";
		}
	}

	public boolean check_widr(int index, int withdraw_m)		//출금하기 충분한가
	{
		if(account[index].now_account()>=withdraw_m)
			return true;
		else
			return false;
	}

	public String Make_new_account(String number) 				//새로운 계좌 생성
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
			String s = "계좌가 생성되었습니다. \n계좌 : " + number;
			return s;
		} 
		else 
		{
			String s = "중복되는 계좌가 있거나\n 계좌의 갯수가 너무 많습니다";
			return s;
		}
	}

	public void delete_account(int num)							//계좌 삭제
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

	public String now_time()									//현재 시간 확인
	{
		time = Calendar.getInstance();
		return time.get(Calendar.DATE)+"월"+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE);
	}

	public void recorder(int index,String date,String info,int amount)	//기록
	{account[index].record(date+" "+info+" "+amount);}

	public String information(int index)						//계좌 조회 정보 반환
	{return account[index].print_info();}

	public void run() {											//이자를 주기위한 함수
		for(;;){
			Calendar time = Calendar.getInstance();
			int now = time.get(Calendar.MINUTE);
			if(now-check>=1)
			{
				int i=0;
				for (i = 0; i < account_index; i++) 
				{
					account[i].record(time.get(Calendar.DATE)+"월"+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE)+" 이자 "+account[i].getinterest());
				}			
				check = now;
			}
		}
	}
}
