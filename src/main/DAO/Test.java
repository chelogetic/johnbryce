package main.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import main.Beans.Category;
import main.Beans.Company;
import main.Beans.Coupon;
import main.Beans.Customer;
import main.FacadeClasses.AdminFacade;
import main.FacadeClasses.CompanyFacade;
import main.FacadeClasses.CustomerFacade;
import main.Job.CouponExpirationDailyJob;
import main.Login.ClientType;
import main.Login.LoginManager;


@SuppressWarnings("unused")
public class Test {

	public static void initialiseAllDBData() {

		try {
			LocalDate ld1 = LocalDate.parse("2017-07-01");
			LocalDate ld2 = LocalDate.parse("2018-10-17");
			LocalDate ld3 = LocalDate.parse("2018-11-18");
			LocalDate ld4 = LocalDate.parse("2018-11-19");
			LocalDate ld5 = LocalDate.parse("2018-11-20");
			LocalDate ld6 = LocalDate.parse("2018-11-21");
			LocalDate ld7 = LocalDate.parse("2018-11-22");

			Coupon coupon1 = new Coupon(1,1,Category.BEVERAGES,"Drinks","All alcohol and non-alcoholic drinks discount cards", ld1, ld2, 10, 19.99, "Drink's Image");
			Coupon coupon2 = new Coupon(2,1,Category.CLEANINGSUPPLIES,"Cleaning Supplies","Discount on Home's most wanted cleaning tools", ld1, ld3, 60, 21.99, "Tool's Image");
			Coupon coupon3 = new Coupon(3,2,Category.EBAY,"Gift Cards","Get specific items on ebay with 80% reduced price", ld1, ld4, 10, 39.99, "Gift Card's Image");
			Coupon coupon4 = new Coupon(4,2,Category.RESTAURANT,"Bonus Meals","Get special meals for free after buying two meals", ld1, ld5, 10, 19.99, "Meal's Image");
			Coupon coupon5 = new Coupon(5,3,Category.FOOD,"Value off Coupons","Get discount on many products in almost any shop", ld1, ld6, 5, 21.99, "Food's Image");
			Coupon coupon6 = new Coupon(6,3,Category.HOUSEHOLDGOODS,"Supplies for your Home","Get discount on the best quality households", ld1, ld7, 10, 39.99, "Energy Drink's Image");

			ArrayList<Coupon> coupons1 = new ArrayList<>();

			coupons1.add(coupon1);
			coupons1.add(coupon2);

			ArrayList<Coupon> coupons2 = new ArrayList<>();

			coupons2.add(coupon3);
			coupons2.add(coupon4);

			ArrayList<Coupon> coupons3 = new ArrayList<>();

			coupons3.add(coupon5);
			coupons3.add(coupon6);

			Company company1 = new Company(1,"HEB","HEB@gmail.com","heb123", coupons1);
			Company company2 = new Company(2,"SAP","SAP@gmail.com","sap123", coupons2);
			Company company3 = new Company(3,"Mall of America","MallOfAmerica@gmail.com","moa123", coupons3);

			Customer customer1 = new Customer(1, "Colleen", "Milano", "miles.low9@gmail.com", "1234", null);
			Customer customer2 = new Customer(2, "Diedre", "Miller", "itzel1972@yahoo.com", "5678", null);
			Customer customer3 = new Customer(3, "Charles", "Jenkins", "rafaela.will@hotmail.com", "9101", null);
			Customer customer4 = new Customer(4, "Roberto", "Cooper", "isaiah2008@gmail.com", "1121", null);
			
			CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
			CustomersDBDAO customersDBDAO = new CustomersDBDAO();
			CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
			
			companiesDBDAO.addCompany(company1);
			companiesDBDAO.addCompany(company2);
			companiesDBDAO.addCompany(company3);

			customersDBDAO.addCustomer(customer1);
			customersDBDAO.addCustomer(customer2);
			customersDBDAO.addCustomer(customer3);
			customersDBDAO.addCustomer(customer4);

			couponsDBDAO.addCoupon(coupon1);
			couponsDBDAO.addCoupon(coupon2);
			couponsDBDAO.addCoupon(coupon3);
			couponsDBDAO.addCoupon(coupon4);
			couponsDBDAO.addCoupon(coupon5);
			couponsDBDAO.addCoupon(coupon6);
			
			couponsDBDAO.addCouponPurchase(1, 1);
			couponsDBDAO.addCouponPurchase(1, 2);
			couponsDBDAO.addCouponPurchase(2, 3);
			couponsDBDAO.addCouponPurchase(2, 4);
			couponsDBDAO.addCouponPurchase(3, 5);
			couponsDBDAO.addCouponPurchase(3, 6);
			
			couponsDBDAO.addCouponPurchase(4, 2);
			couponsDBDAO.addCouponPurchase(4, 4);
			couponsDBDAO.addCouponPurchase(4, 6);
			
			System.out.println("Finished adding information to the DB.");

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	public static void addCategoriesToCategoriesTable() {

		try {

			CategoriesDBDAO categoriesDBDAO = new CategoriesDBDAO();
			
				categoriesDBDAO.addCategory(Category.FOOD);
				categoriesDBDAO.addCategory(Category.ELECTRICITY);
				categoriesDBDAO.addCategory(Category.RESTAURANT);
				categoriesDBDAO.addCategory(Category.VACATION);
				categoriesDBDAO.addCategory(Category.EBAY);
				categoriesDBDAO.addCategory(Category.BEVERAGES);
				categoriesDBDAO.addCategory(Category.CLEANINGSUPPLIES);
				categoriesDBDAO.addCategory(Category.FROZENGOODS);
				categoriesDBDAO.addCategory(Category.HOUSEHOLDGOODS);

				System.out.println("\nCategories Added Successfully");


		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	public static void createTables() throws InterruptedException {

		try {

			CreateDB.createCompaniesTableDB();
			CreateDB.createCustomerTableDB();
			CreateDB.createCategoriesTable();
			CreateDB.createCouponsTable();
			CreateDB.createCustomersVsCoupons();

			addCategoriesToCategoriesTable(); 

			System.out.println("--------------------------");
			System.out.println("Tables Are Ready For You\n");

		} catch (SQLException ex) {
			System.out.println("Error: " + ex);
		}
	}

	public static void testAll() {

		try {



			createTables(); // Uncheck me once executed first time

			initialiseAllDBData(); // Uncheck me once executed first time

			CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();

			Thread t = new Thread(couponExpirationDailyJob);
	
			t.start();
			// All commented line codes are made to check the logic capabilities of different clients
				
//			LoginManager loginManager = LoginManager.getInstance();
//
//			System.out.println("---------------------------");
//			System.out.println("*AdminFacade Logic Show Case Started*\n");
//
//			AdminFacade admin = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
//			Company company4 = new Company(4,"Apple","apple@me.com","apple1", null);
//			admin.addCompany(company4);
//			company4.setEmail("updatedApple@me.com");
//			admin.updateCompany(company4);
//			admin.deleteCompany(company4.getId());
//			System.out.println(admin.getAllCompanies());
//			System.out.println(admin.getOneCompany(2));
//			Customer customer5 = new Customer(5,"Mark","Neroda","chelogetic@gmail.com","chelogetic1234", null);
//			admin.addCustomer(customer5);
//			customer5.setEmail("updatedChelogetic@gmail.com");
//			admin.updateCustomer(customer5);
//			admin.deleteCustomer(customer5.getId());
//			System.out.println(admin.getAllCustomers());
//			System.out.println(admin.getOneCustomer(1));
//
//			System.out.println("\n*AdminFacade Logic Show Case Ended*");
//			System.out.println("---------------------------");
//			System.out.println("*CompanyFacade Logic Show Case Started*\n");
//
//			CompanyFacade company = (CompanyFacade) loginManager.login("HEB@gmail.com", "heb123", ClientType.COMPANY);
//			Coupon coupon7 = new Coupon(7,1,Category.FROZENGOODS,"Frozen Meat and Other","For every kg you buy you get additional half kg of that meat",LocalDate.parse("2018-08-08"),LocalDate.parse("2019-08-08"), 30, 38.99, "Frozen Good's Image");
//			company.addCoupon(coupon7);
//			coupon7.setAmount(100);
//			company.updateCoupon(coupon7);
//			company.deleteCoupon(coupon7.getId());
//			System.out.println(company.getCompanyCoupons());
//			System.out.println(company.getCompanyCoupons(Category.CLEANINGSUPPLIES));
//			System.out.println(company.getCompanyCoupons(20));
//			System.out.println(company.getCompanyDetailes());
//
//			System.out.println("\n*CompanyFacade Logic Show Case Ended*");
//			System.out.println("---------------------------");
//			System.out.println("*CustomerFacade Logic Show Case Started*\n");
//
//			CustomerFacade customer = (CustomerFacade) loginManager.login("miles.low9@gmail.com", "1234", ClientType.CUSTOMER);
//
//			Coupon sameCoupon7 = new Coupon(7,1,Category.FROZENGOODS,"Frozen Meat and Other","For every kg you buy you get additional half kg of that meat",LocalDate.parse("2018-08-08"),LocalDate.parse("2019-08-08"), 30, 38.99, "Frozen Good's Image");
//			company.addCoupon(sameCoupon7);
//
//			customer.purchaseCoupon(sameCoupon7);
//			System.out.println(customer.getCustomerCoupons());
//			System.out.println(customer.getCustomerCoupons(Category.HOUSEHOLDGOODS));
//			System.out.println(customer.getCustomerCoupons(39));
//			System.out.println(customer.getCustomerDetailes());
//			System.out.println("\n*CustomerFacade Logic Show Case Ended*");
//			System.out.println("---------------------------");
//
			couponExpirationDailyJob.stop();

			System.out.println("\nFinished Program");

		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
}