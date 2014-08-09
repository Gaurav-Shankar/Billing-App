package ccd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;



import ccd.bean.CCD;


public class CCDDAO extends BaseDAO {
	public static boolean update_price(CCD c1){
		Connection con = getCon1();
		boolean res = false;
		try {
			con = getCon1();
			String sql = "update CCD_Items set Item_price = ? where Item_name = ?; ";
			PreparedStatement stmt = con.prepareStatement(sql);
			int i =1;
			stmt.setFloat(i++, c1.getItem_price());
			stmt.setString(i++, c1.getItem_name());
			int n = stmt.executeUpdate();
			res = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return res;


	}
	public static boolean add_item(CCD c1){
		boolean res = false;
		Connection con1 = null;
		String sql  = "insert into CCD_Items (Item_name, Item_price) values (?, ?); ";

		int i =1;
		try {
			con1 = getCon1();
			PreparedStatement stmt = con1.prepareStatement(sql);
			stmt.setString(i++, c1.getItem_name());
			stmt.setFloat(i++, c1.getItem_price());
			int n = stmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closeCon1(con1);
		}
		return res;


	}

	public static void viewMen(){
		Connection con1 = null;
		String sql = "select * from CCD_Items;";
		try{
			con1 = getCon1();
			PreparedStatement stmt = con1.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				System.out.println("Item name " +rs.getString("Item_name"));
				System.out.println("Item price = " +rs.getFloat("Item_price"));
				System.out.println("============================================");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static float genBill(String name) {
		Connection con1 = null;
		String sql = "select * from CCD_Items;";
		try {
			con1 = getCon1();
			PreparedStatement stmt = con1.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String item_name = rs.getString("Item_name");
				Float item_price = rs.getFloat("Item_price");

				if(name.equalsIgnoreCase(item_name)) {
					System.out.println("Its price is : " +item_price);
					return item_price;
				}


			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			closeCon1(con1);
		}
		return 0;


	}

	public static boolean Customer_record(CCD c1) {
		Connection con1  = null;
		boolean res = false;
		String sql = "insert into Customer_record (Customer_name, [Items sold], [Total amount], Customer_phone) values(?, ?, ?, ?); ";
		try {
			con1 = getCon1();
			PreparedStatement stmt = con1.prepareStatement(sql);
			int i=1;
			stmt.setString(i++, c1.getCustomer_name());
			stmt.setString(i++, c1.getItems_sold());
			stmt.setFloat(i++, c1.getTotal_amt());
			stmt.setInt(i++, c1.getCustomer());
			int m = stmt.executeUpdate();
			System.out.println(m);
			res = true;


		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			closeCon1(con1);
		}
		return res;

	}


	public static CCD find_item(String name){
		CCD res = null;
		Connection con1 = null;
		try {		
			con1 = getCon1();
			String sql = "select * from CCD_Items where Item_name = ? ; ";
			PreparedStatement stmt = con1.prepareStatement(sql);
			int i=1;
			stmt.setString(i++, name);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				CCD c1 = new CCD();
				c1.setItem_name(rs.getString("Item_name"));
				c1.setItem_price(rs.getFloat("Item_price"));
				res = c1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closeCon1(con1);
		}
		return res;
	}


	public static LinkedList<CCD> searchShop(String name){
		LinkedList<CCD> res = new LinkedList<CCD>();
		Connection con = null;
		try {		
			con = getCon1();
			String sql = "select * from CCD_Items where Item_name like ? ; ";
			PreparedStatement stmt = con.prepareStatement(sql);
			int i=1;
			stmt.setString(i++, "%"+name+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				CCD c1 = new CCD();
				c1.setItem_name(rs.getString("Item_name"));
				c1.setItem_price(rs.getInt("Item_price"));
				res.add(c1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closeCon1(con);
		}
		return res;
	}
	public static CCD find_item(int cont){
		CCD res = null;
		Connection con1 = null;
		try {		
			con1 = getCon1();
			String sql = "select * from Customer_record where Customer_name = ? ; ";
			PreparedStatement stmt = con1.prepareStatement(sql);
			int i=1;
			stmt.setInt(i++, cont);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				CCD c1 = new CCD();
				c1.setCustomer(rs.getString("C"))
				res = c1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closeCon1(con1);
		}
		return res;
	}

}




