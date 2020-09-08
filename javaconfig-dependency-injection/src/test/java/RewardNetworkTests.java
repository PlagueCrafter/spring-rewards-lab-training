import org.junit.Before;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import rewards.TestInfrastructureConfig;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.StubAccountRepository;
import rewards.internal.StubRestaurantRepository;
import rewards.internal.StubRewardRepository;
import rewards.internal.account.AccountRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.RewardRepository;



public class RewardNetworkTests {
    private RewardNetworkImpl rewardNetwork;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = SpringApplication.run(TestInfrastructureConfig.class);

        AccountRepository accountRepo = new StubAccountRepository();
        RestaurantRepository restaurantRepo = new StubRestaurantRepository();
        RewardRepository rewardRepo = new StubRewardRepository();

        // setup the object being tested by handing what it needs to work
        rewardNetwork = new RewardNetworkImpl(accountRepo, restaurantRepo, rewardRepo);


    }
}