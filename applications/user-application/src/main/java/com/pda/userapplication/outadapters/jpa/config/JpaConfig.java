package com.pda.userapplication.outadapters.jpa.config;

//@Configuration
//@EnableJpaRepositories(
//    basePackages = "com.pda.userapplication",
//    entityManagerFactoryRef = "primaryEntityManager",
//    transactionManagerRef = "primaryTransactionManager"
//)
public class JpaConfig {
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(primaryDataSource());
//        em.setPackagesToScan(new String[] {"com.pda.userapplication"});
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setShowSql(true);
//        vendorAdapter.setGenerateDdl(true);
//        em.setJpaVendorAdapter(vendorAdapter);
//
////        HashMap<String, Object> prop = new HashMap<>();
////        prop.put("hibernate.dialect", "org.hibernate.dialect");
////        prop.put("hibernate.hbm2ddl.auto", "update");
////        prop.put("hibernate.format_sql", true);
////        em.setJpaPropertyMap(prop);
//
//        return em;
//    }
//
//    @Bean
//    @Primary
//    public PlatformTransactionManager primaryTransactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(primaryEntityManager().getObject());
//        return transactionManager;
//    }
}
