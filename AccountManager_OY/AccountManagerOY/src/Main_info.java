public class Main_info {
	private static int account_index = 0;
	protected New_account index[];

	public Main_info() {
		index = new New_account[5];
	}

	public String Make_new_account(String number) {
		boolean check = false;
		for (int i = 0; i < index.length; i++) {
			if (index[i].toString() == number)
				check = true;
		}
		if (account_index >= 5)
			check = true;

		if (check == false) {
			New_account a = new New_account(number);
			index[account_index++] = a;
			return "계좌가 생성되었습니다";
		} else {
			return "중복되는 계좌가 있거나 계좌의 개수가 너무 많습니다";
		}
	}
}
