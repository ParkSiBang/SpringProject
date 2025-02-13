package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class AdminDAO {
	// 관리자 로그인 유효성 체크
	public boolean AdminLogin(String login_id, String login_pw) {
		String sql = "select * from Admin where admin_id = ? and admin_pw = ?";
		boolean isValid = false;

		try (Connection conn = DBUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, login_id);
			st.setString(2, login_pw);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next())
					isValid = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// false = 관리자 유효하지 않음, true = 관리자 유효함
		return isValid;
	}
}
