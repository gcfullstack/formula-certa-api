package corp.formulacerta.integracao.config;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
@PropertySource({ "classpath:application.yml" })
@EnableJpaRepositories(basePackages = {"corp.formulacerta.integracao.repository"}, entityManagerFactoryRef = "formulaCertaEntityManager", transactionManagerRef = "formulaCertaTransactionManager")
public class FormulaCertaDataSourceConfig {
	
	@Autowired
    private Environment env;
	
	@Bean(name="datasource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource totvsDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name="formulaCertaEntityManager")
	public LocalContainerEntityManagerFactoryBean userEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(totvsDataSource());
		em.setPackagesToScan(new String[] {"corp.formulacerta.integracao.model"});
		em.setPersistenceUnitName("pu-fc");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		/*properties.put("hikari.max-lifetime", "1800000");
		properties.put("hikari.connection-timeout", "1800000");
		properties.put("hikari.validation-timeout", "1800000");*/
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean(name="formulaCertaTransactionManager")
	public PlatformTransactionManager userTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(userEntityManager().getObject());
		return transactionManager;
	}
}