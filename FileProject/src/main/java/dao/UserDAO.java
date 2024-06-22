package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

import db.MyConnection;

public class UserDAO {
	public static boolean isExist(String email) throws SQLException {
		Connection connection = MyConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("select email from users");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String e = rs.getString(1);
			if(e.equals(email))
				return true;
		}
		return false;
	}
	public  static int saveUser(Model.User user)throws SQLException{
		Connection connection = MyConnection.getConnection();
		PreparedStatement ps =connection.prepareStatement("insert into users values(default, ?, ?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        return ps.executeUpdate();
		
		}
}
