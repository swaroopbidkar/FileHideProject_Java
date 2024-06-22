package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import Model.User;
import dao.UserDAO;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

public class Welcome {
	public void welcomeScreen() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the App");
		System.out.println("Press 1 to Login");
		System.out.println("Press 2 to signUp");
		System.out.println("Press 0 to Exit");
		int choice =0;
		try {
			choice = Integer.parseInt(br.readLine());
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		switch (choice) {
		case 1 -> login();
		case 2 -> SignUp();
		case 3 -> System.exit(0);
		
		}
	}

	private void login() throws IOException {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter Email Id");
	String email = sc.nextLine();
	try {
		if(UserDAO.isExist(email)) {
			String genOTP = GenerateOTP.getOTP();
			SendOTPService.sendOTP(email, genOTP);
			System.out.println("Enter The Otp");
			String otp = sc.nextLine();
			if(otp.equals(genOTP)) {
				new UserView(email).home();
				
			}}
			else {
				System.out.println("User Not Found");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void SignUp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENter Name");
		String name = sc.nextLine();
		System.out.println("ENter Email");
		String email = sc.nextLine();
		String genOTP = GenerateOTP.getOTP();
		SendOTPService.sendOTP(email, genOTP);
		System.out.println("Enter The Otp");
		String otp = sc.nextLine();
		if(otp.equals(genOTP)) {
			User user = new User(name,email);
			int response = UserService.saveUser(user);
			switch (response) {
			case 0 -> System.out.println("User Registered");
			case 1 -> System.out.println("User already Registered");
			
			}
		}else {
			System.out.println("Wrong OTP");	
		}
	}
}
