package flynas.web.production.routes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPage;
import flynas.web.workflows.BookingPageFlow;

public class TC171_roundTripInternationalBusiness_RUH_ATZ extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataProdRoutes"),"Chrome_TestData");

	@Test(dataProvider = "testData",groups={"Business"})
	public  void TC_171_roundTripInternationalBusiness_RUH_ATZ( String bookingClass,
			String mobilenum,
			String paymentType,
			String newDate,
			String Departuredate,String rtnDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,String seatSelect,
			String Description) throws Throwable {
		try {			
	
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			String deptDate = pickDate(Departuredate);
			String retrnDate = pickDate(rtnDate);
										
			String[] Credentials = pickCredentials("UserCredentials");
				String username =Credentials[0];
				String password =Credentials[1];					
				
			BookingPage.clickLogin();			
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptDate , "", "", retrnDate,adult, child, infant,"","","");
			selectClass(bookingClass, "");
			clickContinueBtn();
			continueOnPassengerDetails();
			coninueOnBaggage();
			continueOnSeatSelection();
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			verifyPNRforSadad();
		
			Reporter.SuccessReport("TC171_roundTripInternationalBusiness_RUH_ATZ", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC171_roundTripInternationalBusiness_RUH_ATZ", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		
	    		xls.getCellValue("Booking Class", "Value2"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		xls.getCellValue("NewDate", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Origin", "Value47"),
	    		xls.getCellValue("Destination", "Value47"),
	    		xls.getCellValue("Trip Type", "Value2"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Select Seat", "Value3"),
	    		"Validate RoundTrip International Business_RUH_ATZ"}};
	}

}
