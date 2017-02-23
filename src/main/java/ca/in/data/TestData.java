package ca.in.data;

import org.testng.annotations.DataProvider;

public class TestData {
	
	// Args: URL, TITLE of main page
	@DataProvider(name="mainPageData")
	public Object [][] mainPageData() {
		return new Object [][] {
			{"http://ia.ca/","iA | Industrial Alliance | Insurance"}
		};
	}
	
	// Args: Loans text, on main page
		@DataProvider(name = "loans")
		public static Object[][] loans() {
			return new Object[][] { { "LOANS"} };
		}
}


//protected String field_MortgagesPageTitle = "Mortgage - Mortage rates | iA Financial Group";
