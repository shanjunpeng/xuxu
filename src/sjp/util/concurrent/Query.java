package sjp.util.concurrent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Query<E> {
	public abstract Connection getConnection();

	public abstract String getQuerySql();

	public static int THREAD_NUM = 10;

	public List<E> executeQuery() {
		String countSql = "select count(*) from (" + getQuerySql() + ")";
		List<E> result = new ArrayList<E>();
		int count = 0;
		try {
			PreparedStatement ps = getConnection().prepareStatement(countSql);
			ResultSet rs = ps.executeQuery();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count == 0) {
			return result;
		}
		int per = count / THREAD_NUM;

		ExecutorService executorService = Executors.newCachedThreadPool();
		return result;
	}

}
