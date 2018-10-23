package main.Beans;

/**
 * Category JavaBean
 * @author chelogetic
 */
/**
 * Any possible category that may be assigned to a company's coupon 
 * FOOD,ELECTRICITY,RESTAURANT,VACATION,EBAY,BEVERAGES,CLEANINGSUPPLIES,FROZENGOODS, and HOUSEHOLDGOODS are the only possible categories for the coupons unless
 * new categories will be added in the future.
 */
/**
 * 
 * The purpose of this class is to cast the category to different type of data according to the action being applied on the value.
 * If we would interact with the DB and add a coupon, we would want to cast it's category data member to an integer. In reverse, we would cast it back to a category.
 */
public enum Category {

	FOOD,
	ELECTRICITY,
	RESTAURANT,
	VACATION,
	EBAY,
	BEVERAGES,
	CLEANINGSUPPLIES,
	FROZENGOODS,
	HOUSEHOLDGOODS;

	public static int categoryToInt(Category category) {
		
		int categoryID = 99;
		
		if(category.equals(FOOD)) {
			categoryID = 1;
			return categoryID;
		}
		else if(category.equals(ELECTRICITY)) {
			categoryID = 2;
			return categoryID;
		}
		else if(category.equals(RESTAURANT)) {
			categoryID = 3;
			return categoryID;
		}
		else if(category.equals(VACATION)) {
			categoryID = 4;
			return categoryID;
		}
		else if(category.equals(EBAY)) {
			categoryID = 5;
			return categoryID;
		}
		else if(category.equals(BEVERAGES)) {
			categoryID = 6;
			return categoryID;
		}
		else if(category.equals(CLEANINGSUPPLIES)) {
			categoryID = 7;
			return categoryID;
		}
		else if(category.equals(FROZENGOODS)) {
			categoryID = 8;
			return categoryID;
		}
		else if(category.equals(HOUSEHOLDGOODS)) {
			categoryID = 9;
			return categoryID;
		}
		return categoryID;	
	}
	
	public static Category intToCategory(int categoryID) {

		Category category = null;

		switch(categoryID) {

		case 1: category = FOOD;
		break;
		case 2: category = ELECTRICITY;
		break;
		case 3: category = RESTAURANT;
		break;
		case 4: category = VACATION;
		break;
		case 5: category = EBAY;
		break;
		case 6: category = BEVERAGES;
		break;
		case 7: category = CLEANINGSUPPLIES;
		break;
		case 8: category = FROZENGOODS;
		break;
		case 9: category = HOUSEHOLDGOODS;

		}
		return category;
	}
	
	public static Category stringToCategory(String category) {

		Category category1 = null;
		
		switch(category) {

		case "FOOD": category1 = FOOD;
		break;
		case "ELECTRICITY": category1 = ELECTRICITY;
		break;
		case "RESTAURANT": category1 = RESTAURANT;
		break;
		case "VACATION": category1 = VACATION;
		break;
		case "EBAY": category1 = EBAY;
		break;
		case "BEVERAGES": category1 = BEVERAGES;
		break;
		case "CLEANINGSUPPLIES": category1 = CLEANINGSUPPLIES;
		break;
		case "FROZENGOODS": category1 = FROZENGOODS;
		break;
		case "HOUSEHOLDGOODS": category1 = HOUSEHOLDGOODS;
		
		}
		return category1;
	}
	
	public static String categoryToString(Category category) {

		String category1 = null;
		
		switch(category) {

		case FOOD: category1 = "FOOD";
		break;
		case ELECTRICITY: category1 = "ELECTRICITY";
		break;
		case RESTAURANT: category1 = "RESTAURANT";
		break;
		case VACATION: category1 = "VACATION";
		break;
		case EBAY: category1 = "EBAY";
		break;
		case BEVERAGES: category1 = "BEVERAGES";
		break;
		case CLEANINGSUPPLIES: category1 = "CLEANINGSUPPLIES";
		break;
		case FROZENGOODS: category1 = "FROZENGOODS";
		break;
		case HOUSEHOLDGOODS: category1 = "HOUSEHOLDGOODS";
		
		}
		return category1;
	}
}
