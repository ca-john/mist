package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static int v(int m) {
		return ((int) (Math.random() * 100000)) % m;
	}

	public static void showUserList() {
		BackEndService bes = BackEndService.getInstance();

		for (User u : bes.getUsers().values()) {
			System.out.println(u.userInfo());
			System.out.print("\t own the games:");
			for (Game g : u.getGames()) {
				System.out.print(g.gameInfo());
			}
			System.out.println();
		}
	}

	public static void showGames() {
		BackEndService bes = BackEndService.getInstance();
		for(Game g:bes.getGames()) {
			System.out.println(g.gameInfo());
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


		// Create a new FrontEnd
		BackEndService bes = BackEndService.getInstance();
		DailyTransactionLog log = new DailyTransactionLog();
		bes.loadUsers("userInfo.txt");
		bes.loadGames("gameInfo.txt");
		bes.loadUserGames("userGameInfo.txt");
		FrontEndLogin frontEndLogin = new FrontEndLogin(bes, log);
	}

}
