package src;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class facilitates all the backend services that deal with the txt files of the users and games.
 */
public class BackEndService {

	private static BackEndService bes = new BackEndService();

	/**
	 * This methods gets the current instance of the backend service
	 * @return The current instance of the service
	 */
	public static BackEndService getInstance() {
		return bes;
	}

	private Map<String, User> users = new HashMap<>();
	private List<Game> games = new ArrayList<Game>();
	private User loginUser = null;
	private String userInfoPath;
	private String gamePath;
	private String userGamePath;
	private boolean auctionSale = false;

	/**
	 * Getter to get the list of all games
	 * @return All games
	 */
	public List<Game> getGames() {
		return games;
	}

	/**
	 * Sets the current games to the ones provided.
	 * @param games The games that will replace the current ones.
	 */
	public void setGames(List<Game> games) {
		bes.games = games;
	}

	/**
	 * Returns the user object given the username of the user.
	 * @param username The username of the user.
	 * @return The respective user object.
	 */
	public User getUserByUsername(String username) {
		return bes.users.getOrDefault(username, null);
	}

	/**
	 * This method deletes a user from the record.
	 * @param u The user to be deleted
	 */
	public void deleteUser(User u) {
		bes.users.remove(u.getUsername());
	}

	/**
	 * This method writes out all the updated information of the Users and Games into the respective files and displays an
	 * error when the operation was not successful.
	 */
	public void save() {
		try {
			Files.write(Paths.get(bes.userInfoPath),
					users.values().stream()
							.map(u -> String.format("%s,%s,%s,%s", u.username, u.userType.toString(), formatDouble(u.balance), u.getPassword()))
							.collect(Collectors.toList()));
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Save userinfo to file Fail:" + e.toString());
		}
		try {
			Files.write(Paths.get(bes.gamePath), games.stream().map(g -> String.format("%s,%.2f", g.getName(), g.getPrice())).collect(Collectors.toList()));
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Save games to file Fail:" + e.toString());
		}
		try {
			Files.write(Paths.get(bes.userGamePath), users.values().stream().map(u -> {
				return u.username + ","
						+ String.join(",", u.games.stream().map(g -> String.format("%s,%f", g.toString(),g.getDiscount())).collect(Collectors.toList()));
//				return u.username + ","
//				+ String.join(",", u.games.stream().map(g -> String.format("%s,%f", g.toString(),0d)).collect(Collectors.toList()));
//				return u.username + ","
//				+ String.join(",", u.games.stream().map(g -> String.format("%s", g.toString())).collect(Collectors.toList()));
			}).collect(Collectors.toList()));
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Save user's game info to file Fail:" + e.toString());
		}
	}

	// A mapping of usernames to User Objects.
	public Map<String, User> getUsers() {
		return users;
	}

	/**
	 * This method sets this service's users to the provided ones.
	 * @param users The map of the users.
	 */
	public void setUsers(Map<String, User> users) {
		bes.users = users;
	}

	/**
	 * Constructor for a BackEndService.
	 */
	public BackEndService() {
	}

	/**
	 * This method checks whether a provided game is in the list of current games.
	 * @param g The game to look for.
	 * @return True if the game is found, false otherwise.
	 */
	public boolean haveGame(String g) {
		return games.contains(new Game(g));
	}


	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User user) {
		bes.loginUser = user;
	}

	/**
	 * This method adds a user to the current list of users, with a definitive password.
	 * @param username The username of the user to be added.
	 * @param ut The user type (Admin, buyer, etc.)
	 * @param credit The credit to open the account with.
	 * @param password The login password of the user
	 * @return The user that was just created
	 */
	public User addUser(String username, UserType ut, double credit, String password) {
		User u = bes.getUserByUsername(username);
		if (u == null) {
			u = new User(username, credit, ut, password);
			bes.users.put(u.getUsername(), u);
		}
		return u;
	}

	/**
	 * This method adds a user to the current list of users, with an empty password.
	 * @param username The username of the user to be added.
	 * @param ut The user type (Admin, buyer, etc.)
	 * @param credit The credit to open the account with.
	 * @return The user that was just created
	 */
	public User addUser(String username, UserType ut, double credit) {
		User u = new User(username, credit, ut);
		bes.users.put(u.getUsername(), u);
		return u;
	}

	public User addUserInit(String username, UserType ut, double credit, String password) {
		User u = bes.getUserByUsername(username);
		if (u == null) {
			u = new User(username, credit, ut, password);
			bes.users.put(u.getUsername(), u);
		}
		return u;
	}

	/**
	 * Loads the list of users from the txt file
	 * @param userInfoPath The path to the file with the list of users.
	 */
	public void loadUsers(String userInfoPath) {
		try {
			bes.userInfoPath = userInfoPath;
			bes.users.clear();
			Files.readAllLines(Paths.get(userInfoPath)).stream().forEach(line -> {
				String[] us = line.split(",");
				bes.addUserInit(us[0], UserType.valueOf(us[1]), Double.parseDouble(us[2]), us[3]);
			});
		} catch (IOException e) {
			System.out.println("Can't open user info file:" + userInfoPath);
		}

	}

	/**
	 * Loads the list of games from the txt file
	 * @param gamePath The path to the file
	 */
	public void loadGames(String gamePath) {
		try {
			bes.gamePath = gamePath;
			Files.readAllLines(Paths.get(gamePath)).stream().forEach(line -> {
				
				String[] gs = line.split(",");
				double d = Math.round(Double.parseDouble(gs[1])*100)/100;
				bes.games.add(new Game(gs[0], d));
			});
		} catch (IOException e) {
			System.out.println("Can't open game info file:" + gamePath);
		}
	}

	/**
	 * This method loads the games of the users
	 * @param userGamePath The path to the file
	 */
	public void loadUserGames(String userGamePath) {
		try {
			bes.userGamePath = userGamePath;
			Files.readAllLines(Paths.get(userGamePath)).stream().forEach(line -> {
				String[] us = line.split(",");
				User u = bes.getUserByUsername(us[0]);
				if (u != null){
				for (int i = 1; i < us.length; i+=2) {
					Game g = bes.getGame(us[i]);
					g.discount = Double.parseDouble(us[i + 1]);
					u.games.add(g);
				}


				}
			});
		} catch (IOException e) {
			System.out.println("Cann't open user's game info file:" + userGamePath);
		}

	}

	
	
	
	public  boolean dealTransactionFile(String fielOrDirPath) {
		File fd = new File(fielOrDirPath);

		if (fd.isFile()) {
			return bes.dealOne(fd);
		} else {
			for (File f : fd.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getPath().endsWith(".txt");
				}
			})) {
				bes.dealOne(f);
			}
		}

		return false;
	}

	private boolean dealOne(File file) {
//		Files.readAllLines(file.toPath()).stream()
		try {
			for (String line : Files.readAllLines(file.toPath())) {
				try {
					Transaction t = Transaction.getTransaction(line, file.getPath());
					System.out.println(t.doTransaction());
				} catch (TransactionErrorException | TransactionConstraintException e) {
					System.out.println(e.toString());
				}
			}
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Can't open the file: " + file.getPath());
		}
		return false;
	}

	/**
	 * This method starts an auction sale.
	 */

	public static void main(String[] args) {
		System.out.println(UserType.valueOf("BS").getValue());
	}

	/**
	 * This method finds the game object given the name of the game
	 * @param gameName Game's name
	 * @return The game object.
	 */
	public Game getGame(String gameName) {
		for (Game g : games) {
			if (g.getName().equalsIgnoreCase(gameName))
				return g;
		}
		return null;
	}
	
	public Game getUserGame(String username,String gameName) {
		User u = bes.getUserByUsername(username);
		if(u==null) return null;
		for (Game g : u.getGames()) {
			if (g.getName().equalsIgnoreCase(gameName))
				return g;
		}
		return null;
	}

	public String formatDouble(double d){

		DecimalFormat formatter = new java.text.DecimalFormat("000.00");
		String str = formatter.format(d);
		return str;

	}
	
}
