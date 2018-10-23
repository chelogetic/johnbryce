package main.Testing;

import main.Beans.Category;

public class IDsAndCategories {

	private int id;
	private Category category;

	public IDsAndCategories(int id, Category category) {
		setId(id);
		setCategory(category);
	}

	public String toString() {
		return "Category's id = " + id + 
				" Category's Name = " + category + "\n";	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
