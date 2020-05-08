package corp.gruposfa.novo.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@PropertySource({ "classpath:application.yml" })
@EnableJpaRepositories(basePackages = "corp.gruposfa.novo.consinco.repository", entityManagerFactoryRef = "consincoEntityManager", transactionManagerRef = "consincoTransactionManager")
public class ConsincoDataSourceConfig {
	
	@Autowired
    private Environment env;
	
	@Bean(name="datasource-consinco")
	@ConfigurationProperties(prefix = "spring.datasource-consinco")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name="consincoEntityManager")
	public LocalContainerEntityManagerFactoryBean userEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(userDataSource());
		em.setPackagesToScan(new String[] { "corp.gruposfa.novo.model" });
		em.setPersistenceUnitName("consinco");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean(name="consincoTransactionManager")
	public PlatformTransactionManager userTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(userEntityManager().getObject());
		return transactionManager;
	}
}