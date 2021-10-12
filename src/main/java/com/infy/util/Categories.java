package com.infy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Categories {

	private static final List<String> categories= new ArrayList<>(Arrays.asList(
		    "Food",
		    "Stationary",
		    "Fashion",
		    "Electronics",
		    "Household",
		    "Others"
		));

	public static List<String> getCategories() {
		return categories;
	}

}
