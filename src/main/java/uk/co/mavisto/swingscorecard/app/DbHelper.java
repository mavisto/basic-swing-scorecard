package uk.co.mavisto.swingscorecard.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.flyway.core.Flyway;

public class DbHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbHelper.class);

	private static final DbHelper INSTANCE = new DbHelper();

	public static DbHelper getInstance() {
		return DbHelper.INSTANCE;
	}

	private BasicDataSource ds;

	private DbHelper() {

	}

	public void init() {
		DbHelper.LOGGER.debug("Creating the data source");
		final Properties properties = new Properties();
		properties.put("db.path", "data/db");
		properties.put("db.username", "sa");
		properties.put("db.password", "");
		try {
			properties.load(getClass().getResourceAsStream("/app.properties"));
		} catch (IOException e) {
			DbHelper.LOGGER.error("Failed to load the properties");
		}
		
		
		ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:" + properties.getProperty("db.path"));
		ds.setUsername(properties.getProperty("db.username"));
		ds.setPassword(properties.getProperty("db.password"));

		DbHelper.LOGGER.debug("Executing Flyway (database migration)");
		final Flyway flyway = new Flyway();
		flyway.setDataSource(ds);
		flyway.migrate();
	}

	public DataSource getDataSource() {
		return ds;
	}

	public void close() {
		if (ds != null) {
			LOGGER.debug("Closing the data source");
			try {
				ds.close();
			} catch (SQLException e) {
				LOGGER.error("Failed to close the data source", e);
			}
		}
	}

	public static Connection getConnection() throws SQLException {
		return getInstance().getDataSource().getConnection();
	}

	public void registerShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				close();
			}
		}));
	}

}
