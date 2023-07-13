package src;

import java.util.Arrays;
import java.util.List;

public class TransactionUtil {
private static List<String>TransationCode= Arrays.asList("00","01","02","03","04","05","06","07","10");
	
	public static boolean isCorrectTransationCode(String code) {
		return TransationCode.contains(code);
	}
	
}
