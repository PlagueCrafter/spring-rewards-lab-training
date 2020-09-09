package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rewards.RewardNetwork;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;
import javax.sql.DataSource;


@Configuration
public class RewardsConfig {

    // should work if we test, check
    // https://stackoverflow.com/questions/26889970/intellij-incorrectly-saying-no-beans-of-type-found-for-autowired-repository
    @Autowired
    private DataSource dataSource;

    @Bean
    public RewardNetwork rewardNetwork(){
        return new RewardNetworkImpl(
                accountRepository(),
                restaurantRepository(),
                rewardRepository());
    }

    @Bean
    public JdbcAccountRepository accountRepository(){
        JdbcAccountRepository repository = new JdbcAccountRepository();
        repository.setDataSource(dataSource);
        return repository;
    }

    @Bean
    public JdbcRestaurantRepository restaurantRepository(){
        JdbcRestaurantRepository repository = new JdbcRestaurantRepository();
        repository.setDataSource(dataSource);
        return repository;
    }

    @Bean
    public JdbcRewardRepository rewardRepository(){
        JdbcRewardRepository repository = new JdbcRewardRepository();
        repository.setDataSource(dataSource);
        return repository;
    }





}
