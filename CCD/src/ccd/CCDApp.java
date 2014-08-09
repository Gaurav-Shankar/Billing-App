package ccd;

import java.io.*;
import java.util.LinkedList;

import ccd.bean.CCD;
import ccd.dao.CCDDAO;

public class CCDApp extends CCD {
	public static void main(String[] args) {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		final String[] item_name = new String[25];

		while(true) {

			System.out.println("====================Welcome to CCD======================");
			System.out.println("To add an item to the menu type'add");
			System.out.println("To update the price of an item type 'upd");
			System.out.println("To search an item from the items list type 'ser'");
			System.out.println("To view menu type 'men'");
			System.out.println("To Generate customer bill type 'gen'");
			System.out.println("To Exit type 'exit' ");
			System.out.println("Enter your choice");

			try {
				String opt = br.readLine();
				if("upd".equalsIgnoreCase(opt)){
					CCD c1 = new CCD();
					System.out.println("Enter the item for which you want to update the price: ");
					c1.setItem_name(br.readLine());
					if(CCDDAO.find_item(c1.getItem_name()) == null){
						System.out.println("Does not exists!!!");
						continue; 
					}
					System.out.println("Enter the new price: ");
					c1.setItem_price(Float.parseFloat(br.readLine()));

					boolean res = CCDDAO.update_price(c1);
					if(res){
						System.out.println("Record updted Successfully!!!!");
					}
				}
				else if("add".equalsIgnoreCase(opt)){
					CCD c1 = new CCD();
					System.out.println("Enter item name : ");
					c1.setItem_name(br.readLine());
					if(CCDDAO.find_item(c1.getItem_name()) != null){
						System.out.println("Already exists!!!!");
						continue;
					}
					System.out.println("Enter price of the item : ");
					c1.setItem_price(Float.parseFloat(br.readLine()));

					boolean res = CCDDAO.add_item(c1);
					if(res){
						System.out.println("Record updated successfully!!!");
					}
				}


				else if("men".equalsIgnoreCase(opt)){
					CCDDAO.viewMen();
				}

				else if("gen".equalsIgnoreCase(opt)) { 
					CCD c1 = new CCD();
					float total_amt = (float) 0.0;
					int i= 0;
					int n=0;
					float[] price  = new float[25];
					
					System.out.println("Enter an item : ");
					while(true) {
						item_name[i] = br.readLine();
						if("end".equalsIgnoreCase(item_name[i])){
							item_name[i] =null;
							break;
						}


						if(CCDDAO.find_item(c1.getItem_name()) != null){
							System.out.println("The entered item name does not exists!!");
							System.out.println("\n Enter a valid item name");
						}
						price[i] =  (float) CCDDAO.genBill(item_name[i]);
						total_amt = total_amt + price[i];
						n++;
						i++;
					}
					System.out.println(" To generate bill type 'gen'");
					String choice = br.readLine();
					if("gen".equalsIgnoreCase(choice)) {
						System.out.println("\n\n");
						System.out.println("===============Your bill====================================================");
						System.out.println("Items sold					Item Price");
						for(int j=0;j<n;j++){
							if(price[j] == 0.0){
								continue;
							}
							else{
								System.out.println(item_name[j]+"			    "+price[j]);
							}
						}
						System.out.println("=============================================================================");
						System.out.println("Total Amount : " +total_amt);
						System.out.println("==============================================================================");
						System.out.println("Customer name : ");
						String name = c1.setCustomer_name(br.readLine());
						System.out.println("Items purchased, separated by a space");
						for(int l=0;item_name[l]!=null;l++){
							
							System.out.println(item_name[l]);
						}
						System.out.println("Total amount");
						System.out.println(total_amt);
						System.out.println("Customer contatc number : ");
						int cont = c1.setCustomer(Integer.parseInt(br.readLine()));
						boolean res = CCDDAO.Customer_record(c1);
						if(res) {
							System.out.println("Thanks!! The entered customer data has been inserted to the  DATABASE");
						}
						System.out.println("\n\n");

					}
					else {
						System.out.println("invalid option!!");

					}



					System.out.println("The total amount is :  " +total_amt);
				}
				else if("ser".equalsIgnoreCase(opt)){
					System.out.println("Search an item!");
					System.out.println("Please enter item name Initials ");
					String item = br.readLine();
					LinkedList<CCD> res = CCDDAO.searchShop(item);

					if(res.size()==0){
						System.out.println("No record found!!!");
						continue;
					}else{
						for(CCD c1 : res) {
							System.out.println("========================");
							System.out.println("Item name is  = " +c1.getItem_name());
							System.out.println("Item price is = " +c1.getItem_price());
						}
					}
				}
				
				else if("exit".equalsIgnoreCase(opt)){
					break;
				}
				else{
					System.out.println("INVALID OPTION!!!");
				}

			} catch(Exception e) {
				e.printStackTrace();
			}


		}


	}
}
