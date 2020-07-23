package PayPalPojo;

public class purchaseUnits {
	private amount amount;
	
	public purchaseUnits(String currency_code, String value)
	{
		
		this.amount= new amount(currency_code, value);
	}

	public amount getAmount() {
		return amount;
	}

	public void setAmount(amount amount) {
		this.amount = amount;
	}
	
	

}
