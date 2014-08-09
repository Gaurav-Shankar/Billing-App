package ccd.bean;
public class CCD {
	// Variables declaring list of items and their Price
	private String Item_name;
	private float Item_price;
	//Variables to maintain customer 
	private String Customer_name;
	private String Items_sold;
	private float total_amt;
	private int customer ;
	
	
	public String getItem_name() {
		return Item_name;
	}
	public void setItem_name(String item_name) {
		Item_name = item_name;
	}
	public float getItem_price() {
		return Item_price;
	}
	public void setItem_price(float item_price) {
		Item_price = item_price;
	}
	public String getCustomer_name() {
		return Customer_name;
	}
	public String setCustomer_name(String customer_name) {
		return Customer_name = customer_name;
	}
	public String getItems_sold() {
		return Items_sold;
	}
	public String setItems_sold(String items_sold) {
		return Items_sold = items_sold;
	}
	public float getTotal_amt() {
		return total_amt;
	}
	public float setTotal_amt(float total_amt) {
		return this.total_amt = total_amt;
	}
	public int getCustomer() {
		return customer;
	}
	public int setCustomer(int customer) {
		return this.customer = customer;
		}
	
	

}
