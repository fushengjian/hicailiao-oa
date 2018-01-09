package com.tomowork.oa.core.tools.database;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class DbConnection {

	@Inject
	private DataSource dataSource;

	private static final ThreadLocal<Connection> thread = new ThreadLocal<>();

	public Connection getConnection() {
		Connection conn = (Connection) thread.get();
		if (conn == null) {
			try {
				conn = this.dataSource.getConnection();
				thread.set(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public void closeAll() {
		try {
			Connection conn = (Connection) thread.get();
			if (conn == null)
				return;
			conn.close();
			thread.set(null);
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
