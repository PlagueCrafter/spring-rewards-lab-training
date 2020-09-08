package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;

import javax.sql.DataSource;


@Configuration
public class RewardsConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcAccountRepository accountRepository(){
        return new JdbcAccountRepository();
    }

    @Bean
    public JdbcRestaurantRepository restaurantRepository (){
        return new JdbcRestaurantRepository();
    }

    @Bean
    public JdbcRewardRepository rewardRepository(){
        return new JdbcRewardRepository();
    }

    @Bean
    public RewardNetwork rewardNetwork(){
        return new RewardNetwork() {
            @Override
            public RewardConfirmation rewardAccountFor(Dining dining) {
                return null;
            }
        };
    }



}
