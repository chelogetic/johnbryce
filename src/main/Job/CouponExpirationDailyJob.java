package main.Job;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import main.Beans.Coupon;
import main.DAO.CouponsDAO;
import main.DAO.CouponsDBDAO;

/**
 * Coupon Expiration Daily Job
 * @author chelogetic
 */
public class CouponExpirationDailyJob implements Runnable{
	/**
	 * CouponsDAO object - used for working on the DB
	 */
	private CouponsDAO couponsDAO;
	/**
	 * Job's quit boolean parameter, when we want to finish the job, we make it false so that the while loops ends
	 */
	private boolean quit;

	public CouponExpirationDailyJob() {
		
		this.couponsDAO = new CouponsDBDAO();
		this.quit = false;
	}
/**
 * Method for starting the job - purpose is finding coupons which are expired by looking in the DB using the couponsDAO object and delete them
 * We want to delete expired coupons every day at 04:00 because the server is not overwhelmed at this time
 */
	public void run() {
	
		while(quit == false) {
	
		Calendar rightNow = Calendar.getInstance();
		int hour = rightNow.get(Calendar.HOUR_OF_DAY);
		int minute = rightNow.get(Calendar.MINUTE);

			if(hour == 4 && minute == 0) {
				
				try {

					ArrayList<Coupon> allDBCoupons = couponsDAO.getAllCoupons();

					for(Coupon listedCoupon : allDBCoupons) {

						if(listedCoupon.getEndDate().isBefore(LocalDate.now())){
							couponsDAO.deleteCoupon(listedCoupon.getId());
						}
					}

					Thread.sleep(1000*60*60*24);
					
				} catch (Exception ex) {
					System.out.println("Error: " + ex);
				}	
			}
		}
	}
	/**
	 * Method for stopping the job
	 */
	public void stop() {
		quit = true;
	}
}
