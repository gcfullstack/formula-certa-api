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
@EnableJpaRepositories(basePackages = {"corp.formulacerta.integracao.mirror.repository"}, entityManagerFactoryRef = "mirrorEntityManager", transactionManagerRef = "mirrorTransactionManager")
public class MirrorDataSourceConfig {
	
	
	@Bean(name="datasource-mirror")
	@ConfigurationProperties(prefix = "spring.datasource-mirror")
	public DataSource totvsDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name="mirrorEntityManager")
	public LocalContainerEntityManagerFactoryBean userEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(totvsDataSource());
		em.setPackagesToScan(new String[] {"corp.formulacerta.integracao.mirror.model"});
		em.setPersistenceUnitName("pu-mirror");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean(name="mirrorTransactionManager")
	public PlatformTransactionManager userTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(userEntityManager().getObject());
		return transactionManager;
	}
}