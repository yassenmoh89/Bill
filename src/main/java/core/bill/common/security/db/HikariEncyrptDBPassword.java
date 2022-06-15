package core.bill.common.security.db;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import core.bill.common.security.SecureParams;

@Configuration
public class HikariEncyrptDBPassword extends HikariConfig {

	// com.zaxxer.hikari.HikariDataSource
	// com.zaxxer.hikari.HikariConfig

	public HikariEncyrptDBPassword() {
		super();
	}


	@Override
	public String getPassword() {


			String password1 = super.getDataSourceProperties().getProperty("password") ;
			System.out.println("encyrpted password :" + password1);
			password1 = SecureParams.decrypt(password1);

			System.out.println("decyrpt password :" + password1);

		return "root";
	}
	
	@Bean
	public DataSource dataSource() {
	    try {
	        final HikariConfig hikariConfig = new HikariConfig();
	        hikariConfig.setDriverClassName("");
	        hikariConfig.setJdbcUrl("");
	        hikariConfig.setUsername("");
	        hikariConfig.setPassword("");

	        hikariConfig.setMaximumPoolSize(5);
	        hikariConfig.setConnectionTestQuery("SELECT 1");
	        hikariConfig.setPoolName("springHikariCP");

	        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
	        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
	        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
	        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

	        return new HikariDataSource(hikariConfig);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
