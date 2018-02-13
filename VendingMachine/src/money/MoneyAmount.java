package money;

/**
 * Model an amount of money in common increments:
 * pennies, nickels, dimes, quarters, dollar coins, dollar bills, five dollar bills
 * @author Bill Nicholson nicholdw@ucmail.uc.edu
 */
public class MoneyAmount {
	private int pennies;
	private int nickels;
	private int dimes;
	private int quarters;
	private int dollarCoins;
	private int dollarBills;
	private int fiveDollarBills;
	
	/**
	 * Constructor. Initializes all the increment counts to zero.
	 */
	public MoneyAmount() {
		resetToZero();
	}
	/**
	 * Copy Constructor
	 * @param moneyAmount The MoneyAmount object to copy from
	 */
	public MoneyAmount(MoneyAmount moneyAmount) {
		this.setNickels(moneyAmount.getNickels());
		this.setDimes(moneyAmount.getDimes());
		this.setQuarters(moneyAmount.getQuarters());
		this.setDollarCoins(moneyAmount.getDollarCoins());
		this.setDollarBills(moneyAmount.getDollarBills());
		this.setFiveDollarBills(moneyAmount.getFiveDollarBills());
	}
	/**
	 * Remove some money from the accumulator
	 * @param moneyAmount The money to remove
	 */
	public void remove(MoneyAmount moneyAmount) {
		this.pennies -= moneyAmount.getPennies();
		this.nickels -= moneyAmount.getNickels();
		this.dimes -= moneyAmount.getDimes();
		this.quarters -= moneyAmount.getQuarters();
		this.dollarCoins -= moneyAmount.getDollarCoins();
		this.dollarBills -= moneyAmount.getDollarBills();
		this.fiveDollarBills -= moneyAmount.getFiveDollarBills();
	}
	/**
	 * Subtract a dollar amount from a MoneyAmount object
	 * @param ma The MoneyAmount object
	 * @param cost The amount to be subtracted from ma
	 * @param change The cost, mapped into a MoneyAmount object. Can't be null. 
	 * @return The result of the subtraction or null if it can't be done.
	 */
	public static MoneyAmount subtract(MoneyAmount ma, double cost, MoneyAmount change) {
		MoneyAmount tmp = new MoneyAmount(ma);	// Make a copy of the money we are subtracting from
		double carry = 0;
		//cost *= 100;			// Convert to pennies
		int fiveDollarBills = 0, dollarBills = 0, dollarCoins = 0, quarters = 0, dimes = 0, nickels = 0, pennies = 0; 
		if (ma.getTotalAmount() < cost) return null;
		fiveDollarBills = ((int)cost) / 500;
		if (fiveDollarBills > 0 && tmp.getFiveDollarBills() > 0) {
			if (tmp.getFiveDollarBills() >= fiveDollarBills){
				tmp.setFiveDollarBills(tmp.getFiveDollarBills() - fiveDollarBills);
				cost -= fiveDollarBills * 500;
				change.setFiveDollarBills(fiveDollarBills);
			} else {
				cost -= tmp.getFiveDollarBills() * 500;
				change.setFiveDollarBills(tmp.getFiveDollarBills());
				tmp.setFiveDollarBills(0);
			}
		}
		dollarBills = (int)cost / 100;
		if (dollarBills > 0 && tmp.getDollarBills() > 0) {
			if (tmp.getDollarBills() >= dollarBills){
				tmp.setDollarBills(tmp.getDollarBills() - dollarBills);
				cost -= dollarBills * 100;
				change.setDollarBills(dollarBills);
			} else {
				cost -= tmp.getDollarBills() * 100;
				change.setDollarBills(tmp.getDollarBills());
				tmp.setDollarBills(0);
			}
		}
		dollarCoins = (int)cost / 100;
		if (dollarCoins > 0 && tmp.getDollarCoins() > 0) {
			if (tmp.getDollarCoins() >= dollarCoins){
				tmp.setDollarCoins(tmp.getDollarCoins() - dollarCoins);
				cost -= dollarCoins * 100;
				change.setDollarCoins(dollarCoins);
			} else {
				cost -= tmp.getDollarCoins() * 100;
				change.setDollarCoins(tmp.getDollarCoins());
				tmp.setDollarCoins(0);
			}
		}
		quarters = (int)(cost / .25);
		if (quarters > 0 && tmp.getQuarters() > 0) {
			if (tmp.getQuarters() >= quarters){
				tmp.setQuarters(tmp.getQuarters() - quarters);
				change.setQuarters(quarters);
				cost -= quarters * .25;
			} else {
				cost -= tmp.getQuarters() * .25;
				change.setQuarters(tmp.getQuarters());
				tmp.setQuarters(0);
			}
		}
		dimes = (int)(cost / .10);
		if (dimes > 0 && tmp.getDimes() > 0) {
			if (tmp.getDimes() >= dimes){
				tmp.setDimes(tmp.getDimes() - dimes);
				change.setDimes(dimes);
				cost -= dimes * .10;
			} else {
				cost -= tmp.getDimes() * .10;
				change.setDimes(tmp.getDimes());
				tmp.setDimes(0);
			}
		}
		nickels = (int)(cost % .05);
		if (nickels > 0 && tmp.getNickels() > 0) {
			if (tmp.getNickels() >= nickels){
				tmp.setNickels(tmp.getNickels() - nickels);
				change.setNickels(nickels);
				cost -= nickels * .05;
			} else {
				cost -= tmp.getNickels() * .05;
				change.setNickels(tmp.getNickels());
				tmp.setNickels(0);
			}
		}
		pennies = (int)cost;
		if (pennies > 0 && tmp.getPennies() > 0) {
			if (tmp.getPennies() >= pennies){
				tmp.setPennies(tmp.getPennies() - pennies);
				change.setPennies(pennies);
				cost -= pennies;
			} else {
				cost -= tmp.getPennies();
				change.setPennies(tmp.getPennies());
				tmp.setPennies(0);
			}
		}
		// Cost should be zero now. If it's not, we can't give the change
		if (cost == 0) {
			return tmp;
		} else {
			return null;
		}
	}
	/**
	 * Add a MoneyAmount
	 * @param moneyAmount The MoneyAmount object to add
	 */
	public void add(MoneyAmount moneyAmount) {
		this.addNickels(moneyAmount.getNickels());
		this.addDimes(moneyAmount.getDimes());
		this.addQuarters(moneyAmount.getQuarters());
		this.addDollarCoins(moneyAmount.getDollarCoins());
		this.addDollarBills(moneyAmount.getDollarBills());
		this.addFiveDollarBills(moneyAmount.getFiveDollarBills());
	}
	/**
	 * Set all increments in the MoneyAmount object to zero
	 */
	public void resetToZero() {
		setPennies(0);
		setNickels(0);
		setDimes(0);
		setQuarters(0);
		setDollarCoins(0);
		setDollarBills(0);
		setFiveDollarBills(0);
	}
	/**
	 * Add nickels to the MoneyAmount
	 * @param nickels number of nickels to add
	 */
	public void addNickels(int nickels) {
		this.nickels += nickels;
	}
	/**
	 * Add dimes to the MoneyAmount
	 * @param dimes number of dimes to add
	 */
	public void addDimes(int dimes) {
		this.dimes += dimes;
	}
	/**
	 * Add quarters to the MoneyAmount
	 * @param quarters number of quarters to add 
	 */
	public void addQuarters(int quarters) {
		this.quarters += quarters;
	}
	/**
	 * Add dollar coins to the MoneyAmount
	 * @param dollarCoins number of dollar coins to add
	 */
	public void addDollarCoins(int dollarCoins) {
		this.dollarCoins += dollarCoins;
	}
	/**
	 * Add dollar bills to the MoneyAmount
	 * @param dollarBills number of dollar bills to add
	 */
	public void addDollarBills(int dollarBills) {
		this.dollarBills += dollarBills;
	}
	/**
	 * Ass five dollar bills to the MoneyAmount
	 * @param fiveDollarBills number of five dollar bills to add
	 */
	public void addFiveDollarBills(int fiveDollarBills) {
		this.fiveDollarBills += fiveDollarBills;
	}
	/**
	 * Get the total amount of money in the MoneyAmount object
	 * @return the total amount, in dollars and fractions of a dollar
	 */
	public double getTotalAmount() {
		return pennies *.01 + nickels * .05 + dimes * .10 + quarters * .25 + dollarCoins * 100 + dollarBills * 100 + fiveDollarBills * 500;
	}
	/**
	 * Get the pennies in the MoneyAmount
	 * @return number of pennies currently stored in the MoneyAmount object
	 */
	public int getPennies() {
		return pennies;
	}
	/**
	 * Define the number of pennies in the object
	 * @param pennies Must be >= 0
	 */
	public void setPennies(int pennies) {
		this.pennies = pennies;
	}

	/**
	 * Get the nickels in the MoneyAmount
	 * @return number of nickels currently stored in the MoneyAmount object
	 */
	public int getNickels() {
		return nickels;
	}
	/**
	 * Define the number of nickels in the object
	 * @param nickels Must be >= 0
	 */
	public void setNickels(int nickels) {
		this.nickels = nickels;
	}
	/**
	 * Get the dimes in the MoneyAmount
	 * @return number of dimes currently stored in the MoneyAmount object
	 */
	public int getDimes() {
		return dimes;
	}
	/**
	 * Define the number of dimes in the object
	 * @param dimes Must be >= 0
	 */
	public void setDimes(int dimes) {
		this.dimes = dimes;
	}

	/**
	 * Get the quarters in the MoneyAmount
	 * @return number of quarters currently stored in the MoneyAmount object
	 */
	public int getQuarters() {
		return quarters;
	}
	/**
	 * Define the number of quarters in the object
	 * @param quarters Must be >= 0
	 */
	public void setQuarters(int quarters) {
		this.quarters = quarters;
	}
	/**
	 * Get the dollar coins in the MoneyAmount
	 * @return number of dollar coins currently stored in the MoneyAmount object
	 */
	public int getDollarCoins() {
		return dollarCoins;
	}
	/**
	 * Define the number of dollar coins in the object
	 * @param dollarCoins Must be >= 0
	 */
	public void setDollarCoins(int dollarCoins) {
		this.dollarCoins = dollarCoins;
	}

	/**
	 * Get the dollar bills in the MoneyAmount
	 * @return number of dollar bills currently stored in the MoneyAmount object
	 */
	public int getDollarBills() {
		return dollarBills;
	}
	/**
	 * Define the number of dollar bills in the object
	 * @param dollarBills Must be >= 0
	 */
	public void setDollarBills(int dollarBills) {
		this.dollarBills = dollarBills;
	}
	/**
	 * Get the five dollar bills in the MoneyAmount
	 * @return number of five dollar bills currently stored in the MoneyAmount object
	 */
	public int getFiveDollarBills() {
		return fiveDollarBills;
	}
	/**
	 * Define the number of five dollar bills in the object
	 * @param fiveDollarBills Must be >= 0
	 */
	public void setFiveDollarBills(int fiveDollarBills) {
		this.fiveDollarBills = fiveDollarBills;
	}
	/**
	 * The classic toString method
	 * @return String representation of the MoneyAmount object
	 */
	public String toString() {
		return "$" + (fiveDollarBills * 5 + dollarBills + dollarCoins + quarters * .25 + dimes * .10 + nickels * .05 + pennies * .01);
	}
}
