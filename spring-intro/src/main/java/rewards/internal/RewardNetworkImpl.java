package rewards.internal;

import common.money.MonetaryAmount;
import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.internal.account.Account;
import rewards.internal.account.AccountRepository;
import rewards.internal.restaurant.Restaurant;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.RewardRepository;

/**
 * Rewards an Account for Dining at a Restaurant.
 * 
 * The sole Reward Network implementation. This object is an application-layer service responsible for coordinating with
 * the domain-layer to carry out the process of rewarding benefits to accounts for dining.
 * 
 * Said in other words, this class implements the "reward account for dining" use case.
 */
public class RewardNetworkImpl implements RewardNetwork {

	private AccountRepository accountRepository;

	private RestaurantRepository restaurantRepository;

	private RewardRepository rewardRepository;

	/**
	 * Creates a new reward network.
	 * @param accountRepository the repository for loading accounts to reward
	 * @param restaurantRepository the repository for loading restaurants that determine how much to reward
	 * @param rewardRepository the repository for recording a record of successful reward transactions
	 */
	public RewardNetworkImpl(AccountRepository accountRepository, RestaurantRepository restaurantRepository,
			RewardRepository rewardRepository) {
		this.accountRepository = accountRepository;
		this.restaurantRepository = restaurantRepository;
		this.rewardRepository = rewardRepository;
	}

	public RewardConfirmation rewardAccountFor(Dining dining) {
		// returns account object from cc number
		Account account = accountRepository.findByCreditCard(dining.getCreditCardNumber());

		// returns restaurant object from merchant number
		Restaurant restaurant = restaurantRepository.findByMerchantNumber(dining.getMerchantNumber());

		// calculates money benefit ie money saved
		MonetaryAmount money = restaurant.calculateBenefitFor(account,dining);

		// adds the money contribution to the account
		AccountContribution accountContribution = account.makeContribution(money);

		// this is a void method, thus we're not returning, this only updates the current
		// beneficiaries money
		accountRepository.updateBeneficiaries(account);

		// the method will return a RewardConfirmation object
		return  rewardRepository.confirmReward(accountContribution,dining);

	}
}