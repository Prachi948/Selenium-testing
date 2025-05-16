package Demo;

import java.util.List;
import org.testng.Assert;

import java.time.Duration;

import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class OrangeHRM_Prachi {

	public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver;

	@BeforeTest
	public void setup() {
		System.out.println("Before Test executed");
		// TODO Auto-generated method stub
		driver = new ChromeDriver();
		// maximize windows
		driver.manage().window().maximize();

		// open url
		driver.get(baseUrl);
		// timer i kept as 60
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

	}

// Login with invalid credentials
	@Test(priority = 1)
	public void doLogintestwithinvalidcredentials() throws InterruptedException {
		// find username and enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		// find password and enter invalid password
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123");

		// login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		String message_expected = "Invalid Credentials";

		String message_actual = driver
				.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
		Assert.assertTrue(message_actual.contains(message_expected));

		Thread.sleep(5000);

	}

	// Login with Valid Credentials
	@Test(priority = 2)
	public void loginTestWithAValidCredentials() throws InterruptedException {
		// find username AND enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		// find password AND enter password"admin123"
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		// login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		// verify if the login was successful by checking the page title or a specific
		// element
		String pageTitle = driver.getTitle();
		/*
		 * if (pageTitle.equals("OrangeHRM")) {
		 * 
		 * System.out.println("Login successful"); } else {
		 * System.out.println(" Login failed!"); }
		 */
		logOut();
		Thread.sleep(3000);
		Assert.assertEquals("OrangeHRM", pageTitle);
	}

	// Add Employee
	@Test(priority = 4)

	public void addEmployee() throws InterruptedException {
		// find username AND enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		// find password AND enter password"admin123"
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		// login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		// find PIM and click on PIM menu
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		// find Add employee on PIM menu and click on it
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

		// IN Add employee enter your first name
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Danzel");

		// enter your last name
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Watson");

		// Click on save button to save the details
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

		Thread.sleep(3000);

		logOut();

	}

	// Search EMployee
	@Test(priority = 5)
	public void SearchEmployee() throws InterruptedException {
		// find username AND enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		// find password AND enter password"admin123"
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		// login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		// Click on Employee List
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();

		// enter the employee name
		driver.findElements(By.tagName("input")).get(1).sendKeys("Danzel");

		// Search the employee
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		Thread.sleep(5000);
		java.util.List<WebElement> element = driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span']"));
		String Expected_message = "Records Found";
		String message_actual = element.get(0).getText();
		System.out.println(message_actual);
		logOut();
		Assert.assertTrue(message_actual.contains("Records Found"));
		/*
		 * for (int i=0;i<element.size();i++) { System.out.println( " At index "+
		 * i+"text is:"+element.get(i).getText()); }
		 */

	}

	// Search Employee with ID
	@Test(priority = 6)

	public void SearchEmployeeWithID() {
		String empId = "0372";
		String message_actual = "";

		// find username AND enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		// find password AND enter password"admin123"
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		// login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		
		// navigate to PIM
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		// Click on Employee List
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();

		// enter the employee id
		driver.findElements(By.tagName("input")).get(2).sendKeys(empId);

		// click on search button

		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		List<WebElement> rows = driver.findElements(By.xpath("//div[@role='row']"));
		if (rows.size() > 1) {
			message_actual = driver.findElement(By.xpath("((//div[@role='row'])[2]/div[@role='cell'])[2]")).getText();
		}

		logOut();
		Assert.assertEquals(empId, message_actual);
	}

	// Delete the Employee
	@Test(priority = 7)
	public void DeleteEmployee() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		// find password AND enter password"admin123"
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		// login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		
		// navigate to PIM
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		// Click on Employee List
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();

		// enter the employee name
		driver.findElements(By.tagName("input")).get(1).sendKeys("bala");

		// Click the search button.
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		Thread.sleep(3000);

		// click on delete icon of the record
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();

		// click on yes, delete messaage button
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")).click();

		// check for message "No Record Found"
		String msg = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]")).getText();

		Assert.assertEquals(msg, "No Records Found");

		Thread.sleep(3000);
		logOut();
	}
	
	
	
	//Apply for a leave
	@Test(priority = 8)
	public void applyLeave() throws InterruptedException {
		// find username AND enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		// find password AND enter password"admin123"
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		// login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Click on Leave menu
	    driver.findElement(By.linkText("Leave")).click();

	    // Click on Apply under Leave
	    driver.findElement(By.xpath("//a[normalize-space()='Apply']")).click();
	    
	    Thread.sleep(2000);

	    
	}


	void logOut() {
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		// driver.findElement(By.xpath("//a[contains(normalize-space(),'Logout')]")).click();
		java.util.List<WebElement> elementlist = driver.findElements(By.xpath("//a[@class='oxd-userdropdown-link']"));

		// for (int i = 0; i < elementlist.size(); i++) {
		// System.out.println("Element " + i + ": " + elementlist.get(i).getText());
		// }

		elementlist.get(3).click();// click on logout
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000); // Wait before quitting
		driver.close(); // Close the current browser window
		driver.quit(); // Quit WebDriver session and all opened windows
	}
}
