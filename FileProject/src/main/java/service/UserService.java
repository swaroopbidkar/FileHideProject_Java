package service;

import java.sql.SQLException;

import Model.User;
import dao.UserDAO;

public class UserService {
	public static Integer saveUser(User user) {
		try {
			if(UserDAO.isExist(user.getEmail())) {
				return 0;
			}else {
				return UserDAO.saveUser(user);
			}
		}
			catch(SQLException ex){
				ex.printStackTrace();
		}
			return null;
	}
}
