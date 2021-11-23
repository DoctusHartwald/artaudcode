package rewards.internal.restaurant;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import common.money.Percentage;

public class HibernateRestaurantRepositoryTests extends TestCase {

	private HibernateRestaurantRepository repository;

	private PlatformTransactionManager transactionManager;

	private TransactionStatus status;

	@Override
	protected void setUp() throws Exception {
		repository = new HibernateRestaurantRepository();
		SessionFactory sessionFactory = createTestSessionFactory();
		repository.setSessionFactory(sessionFactory);
		transactionManager = new HibernateTransactionManager(sessionFactory);
		status = transactionManager.getTransaction(new DefaultTransactionDefinition());
	}

	@Override
	protected void tearDown() throws Exception {
		transactionManager.rollback(status);
	}

	public void testFindRestaurantByMerchantNumber() {
		Restaurant restaurant = repository.findByMerchantNumber("1234567890");
		assertNotNull("the restaurant should never be null", restaurant);
		assertEquals("the merchant number is wrong", "1234567890", restaurant.getNumber());
		assertEquals("the name is wrong", "AppleBees", restaurant.getName());
		assertEquals("the benefitPercentage is wrong", Percentage.valueOf("8%"), restaurant.getBenefitPercentage());
		assertEquals("the benefit availability policy is wrong", AlwaysAvailable.INSTANCE, restaurant
				.getBenefitAvailabilityPolicy());
	}

	private SessionFactory createTestSessionFactory() throws Exception {
		// simulate the Spring bean initialization lifecycle
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(createTestDataSource());
		Resource[] mappingLocations = new ClassPathResource[] { new ClassPathResource("Restaurant.hbm.xml",
				Restaurant.class) };
		factoryBean.setMappingLocations(mappingLocations);
		factoryBean.afterPropertiesSet();
		return (SessionFactory) factoryBean.getObject();
	}

	private DataSource createTestDataSource() {
		return new EmbeddedDatabaseBuilder()
			.addScript("rewards/db-schema.sql")
			.addScript("rewards/db-test-data.sql")
			.build();
	}
}
