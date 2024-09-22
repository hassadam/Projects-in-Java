import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Date;

public class passwordGenerator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		FileWriter fw = null;
		int len = 0;
		String password;
		int strength;
		String purposeMessage;
		Date date;
		
		try {
			fw = new FileWriter("passwordGeneratorStorage.txt",true);
		}catch (Exception e) {
			System.out.println("Failed");
		}
		
		
		System.out.println("Please enter the purpose of the password");
		purposeMessage = scan.nextLine();
		
		System.out.println("Please enter the length of the password you wish to receive. Must be atleast 8");
		while(true) {
		len = scan.nextInt();
		System.out.println("Please enter a valid input.");
		
		if(len<8) {
			System.out.println("Please enter a valid input.");
		}else {
			break;
		}
		}
		
		System.out.println("Please select a complexity level.");
		System.out.println("1: Includes only lower case letters.");
		System.out.println("2: Includes upper and lower case letters.");
		System.out.println("3: Includes numbers, upper and lower case letters.");
		System.out.println("4: Includes special characters, numbers, upper and lower case letters.");
		
		while(true) {
			strength = scan.nextInt();
			
			if(strength!=1 && strength!=2 && strength!=3 && strength!=4) {
				System.out.println("Please enter a valid input.");
				continue;
			}
			break;
		}
		
		if(strength == 1) {
			password = option1(len);
		}else if(strength == 2) {
			password = option2(len);
		}else if(strength == 3) {
			password = option3(len);
		}else {
			password = option4(len);
		}
		
		date = new Date();
		
		System.out.println("The password is: "+password);
		try {
		fw.write("Purpose: "+purposeMessage+"\nPassword: "+password+"\nCreated: "+ date.toString()+"\n");
		}catch(Exception e) {
			System.out.println("Failed to write into notepad.");
		}
		scan.close();
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public static String option1(int x) {
		int val;
		Random rand = new Random();
		String str="";
		for(int i=0;i<x;i++) {
			val = rand.nextInt(96,122)+1;
			str+= String.valueOf((char)val);
		}
		return str;
	}
	
	public static String option2(int x) {
		int val1,val2;
		Random rand = new Random();
		String str="";
		
		for(int i=0;i<x;i++) {
			val2 = rand.nextInt(2)+1;
			if(val2 == 1) {
				val1 = rand.nextInt(96,122)+1;
				str+= String.valueOf((char)val1);
			}else {
				val1 = rand.nextInt(64,90)+1;
				str+= String.valueOf((char)val1);
			}
		}
		return str;
	}
	
	public static String option3(int x) {
		int val1,val2;
		Random rand = new Random();
		String str="";
		
		for(int i=0;i<x;i++) {
			val2 = rand.nextInt(3)+1;
			if(val2 == 1) {
				val1 = rand.nextInt(96,122)+1;
				str+= String.valueOf((char)val1);
			}else if(val2 == 2){
				val1 = rand.nextInt(64,90)+1;
				str+= String.valueOf((char)val1);
			}else {
				val1 = rand.nextInt(10);
				str+= String.valueOf(val1);
			}
		}
		return str;
	}
	
	public static String option4(int x) {
		int val1,val2;
		Random rand = new Random();
		String str="";
		
		for(int i=0;i<x;i++) {
			val2 = rand.nextInt(4)+1;
			if(val2 == 1) {
				val1 = rand.nextInt(96,122)+1;
				str+= String.valueOf((char)val1);
			}else if(val2 == 2){
				val1 = rand.nextInt(64,90)+1;
				str+= String.valueOf((char)val1);
			}else if(val2 == 3){
				val1 = rand.nextInt(10);
				str+= String.valueOf(val1);
			}else {
				val2 = rand.nextInt(4)+1;
				if(val2 == 1) {
					val1 = rand.nextInt(32,47)+1;
					str+= String.valueOf((char)val1);
				}else if(val2 == 2) {
					val1 = rand.nextInt(57,64)+1;
					str+= String.valueOf((char)val1);
				}else if(val2 == 3) {
					val1 = rand.nextInt(90,96)+1;
					str+= String.valueOf((char)val1);
				}else {
					val1 = rand.nextInt(122,126)+1;
					str+= String.valueOf((char)val1);
				}
			}
		}
		return str;
	}

}
