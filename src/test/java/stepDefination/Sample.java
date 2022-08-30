package stepDefination;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.dockerjava.api.model.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Sample {
	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void isChecked(List<WebElement> buttons) throws Throwable{
		boolean checked = false;
		List<WebElement> button = buttons;
		for (WebElement radioButton : button) {
			checked = radioButton.isSelected();
			if (!checked) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true)", radioButton);
				radioButton.click();
				Thread.sleep(500);
			}
		}
	}

	public static void main(String[] args) throws Throwable {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 10);
		driver.get("https://www.cleartrip.com/");
		Thread.sleep(3000);

		try {
			if (driver.findElement(By.xpath("//div[contains(@class,'px-1 plNew')]")).isEnabled()) {
				driver.findElement(By.xpath("//div[contains(@class,'px-1 plNew')]")).click();
				System.out.println("Window is prasent");
			}
		} catch (Exception e) {
			System.out.println("There is no window prasent");
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Hotels']")).click();
		Thread.sleep(5000);
		WebElement wTo = driver.findElement(By.xpath("//label[text()='Where to?']/following::input"));
		wTo.sendKeys("Goa");
		wTo.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class,'bg-white br-4')]//li[1]")).click();
		Thread.sleep(1000);

		/*
		 * //method one not working
		 * 
		 * WebElement ele = driver.findElement(By.xpath(
		 * "(//div[contains(@class,'fs-inherit c-inherit')])[3]")); WebElement
		 * ele2 = driver.findElement(By.xpath(
		 * "(//div[contains(@class,'fs-inherit c-inherit')])[4]")); String date1
		 * = "Fri,Aug 26"; String date2 = "sat,Aug 27";
		 * 
		 * JavascriptExecutor js = (JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].setAttribute('value',"+date1+");",
		 * ele);
		 * js.executeScript("arguments[0].setAttribute('value',"+date2+");",
		 * ele2);
		 */

		// method 2
		driver.findElement(By.xpath("(//div[contains(@class,'fs-inherit c-inherit')])[3]")).click();
		Thread.sleep(1000);

		while (true) {
			if (driver.findElement(By.xpath("//div[contains(@class,'DayPicker-Day DayPicker-Day--start')]"))
					.isDisplayed()) {
				break;
			} else {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='flex-1 ta-right']")).click();

			}
		}
		WebElement checkIN = driver
				.findElement(By.xpath("//div[contains(@class,'DayPicker-Day DayPicker-Day--start')]"));
		Thread.sleep(1000);
		WebElement checkOut = driver
				.findElement(By.xpath("(//div[@class='DayPicker-Day DayPicker-Day--leftEdge'])[1]"));
		Thread.sleep(1000);
		WebElement traveller = driver.findElement(By.xpath("//button[@name='travellers']"));
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", checkIN);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click()", checkOut);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click()", traveller);
		Thread.sleep(1000);

		WebElement addTraveller = driver.findElement(By.xpath("//p[contains(@class,'p-2 fs-3')]"));
		Thread.sleep(1000);
		js.executeScript("arguments[0].click()", addTraveller);
		Thread.sleep(1000);

		WebElement adult = driver.findElement(By.xpath("(//li[contains(@class,'fs-4 mx-5')])[1]"));
		Thread.sleep(1000);
		WebElement childrens = driver.findElement(By.xpath("(//li[contains(@class,'fs-4 mx-5')])[2]"));
		Thread.sleep(1000);
		WebElement addRoom = driver.findElement(By.xpath("//span[contains(@class,'c-pointer flex')]"));
		Thread.sleep(1000);
		js.executeScript("arguments[0].click()", adult);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click()", childrens);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click()", addRoom);

		Thread.sleep(1000);
		WebElement sOptions = driver.findElement(By.xpath("//p[contains(@class,'fs-2 c-pointer')]"));
		js.executeScript("arguments[0].click()", sOptions);

		Thread.sleep(1000);
		WebElement Options = driver.findElement(By.xpath("(//li[contains(@class,'ls-reset br-4')])[1]"));
		js.executeScript("arguments[0].click()", Options);

		Thread.sleep(1000);
		WebElement sHotels = driver.findElement(By.xpath("//button[contains(@class,'px-7 bg-primary-500')]"));
		js.executeScript("arguments[0].click()", sHotels);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		List<WebElement> filters = driver.findElements(By.xpath("(//div[@class='pt-8']//p)"));
		List<WebElement> fHotel = driver.findElements(By.xpath("//div[text()='Filter by hotel']"));
		fHotel.addAll(filters);

		int no_filters = fHotel.size();
		System.out.println("Total number of filters : " + no_filters);

		for (WebElement Element : fHotel) {
			String names = Element.getText();
			System.out.println(names);
		}

		isChecked(fHotel);
		isChecked(fHotel);

		System.out.println();
		List<WebElement> t_price = driver.findElements(By.xpath("(//input[contains(@class,'bn box-border')])"));
		driver.findElement(By.xpath("(//input[contains(@class,'bn box-border')])[1]")).clear();
		driver.findElement(By.xpath("(//input[contains(@class,'bn box-border')])[1]")).sendKeys("500");
		driver.findElement(By.xpath("(//input[contains(@class,'bn box-border')])[2]")).clear();
		driver.findElement(By.xpath("(//input[contains(@class,'bn box-border')])[2]")).sendKeys("50000");
		System.out.println("Total price options : " + t_price.size());
		for (WebElement Tprice : t_price) {
			String price = Tprice.getAttribute("value");
			System.out.println(price);
		}

		isChecked(t_price);

		Thread.sleep(3000);
		System.out.println();
		driver.findElement(By.xpath("(//div[contains(@class,'flex flex-wrap col-22')]//div//p)[6]")).click();
		List<WebElement> priyority = driver.findElements(By.xpath("(//div[@class='mb-2'])"));
		System.out.println("Pripority options : " + priyority.size());
		for (WebElement pri : priyority) {
			// Thread.sleep(2000);
			System.out.println(pri.getText());
		}

		Thread.sleep(2000);
		System.out.println();
		System.out.println();
		List<WebElement> rating = driver.findElements(By.xpath("(//label[contains(@class,'radio w-100p')])"));
		System.out.println("Rating options : " + rating.size());
		for (WebElement Trating : rating) {
			System.out.println(Trating.getText());
		}
		Thread.sleep(5000);

		WebElement view = driver.findElement(By.xpath("(//button[text()='View details'])[1]"));
		js.executeScript("arguments[0].click()", view);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Select room'])[2]")));
		Thread.sleep(2000);

		WebElement selectRoom = driver
				.findElement(By.xpath("(//button[contains(@class,'bg-primary-500 hover:bg-primary-600')])[1]"));
		js.executeScript("arguments[0].click()", selectRoom);

		Thread.sleep(2000);

		WebElement book = driver.findElement(By.xpath("(//button[contains(@class,'ml-6 room_rate_btn')])[1]"));
		js.executeScript("arguments[0].click()", book);

		Thread.sleep(5000);
		String mWindow = driver.getWindowHandle();
		Set<String> cWindows = driver.getWindowHandles();
		driver.switchTo().window(mWindow);
		driver.quit();

		// ======================================================================//
		/*
		 * WebDriver driver; WebDriverWait wait;
		 * 
		 * @Given("^I open browser with url as \"([^\"]*)\"$") public void
		 * i_open_browser_with_url_as(String arg1) throws Throwable {
		 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
		 * driver.manage().window().maximize(); wait = new WebDriverWait(driver,
		 * 10); driver.get("https://www.cleartrip.com/"); Thread.sleep(3000);
		 * 
		 * try { if (driver.findElement(By.xpath(
		 * "//div[contains(@class,'px-1 plNew')]")).isEnabled()) {
		 * driver.findElement(By.xpath("//div[contains(@class,'px-1 plNew')]"
		 * )).click(); System.out.println("Window is prasent"); } } catch
		 * (Exception e) { System.out.println("There is no window prasent"); }
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//div[text()='Hotels']")).click();
		 * Thread.sleep(5000); WebElement wTo = driver.findElement(By.xpath(
		 * "//label[text()='Where to?']/following::input"));
		 * wTo.sendKeys("Goa"); wTo.click(); Thread.sleep(3000);
		 * driver.findElement(By.xpath(
		 * "//div[contains(@class,'bg-white br-4')]//li[1]")).click();
		 * Thread.sleep(1000); WebElement search = driver.findElement(By.xpath(
		 * "//button[text()='Search Hotels']")); JavascriptExecutor jc =
		 * (JavascriptExecutor) driver; jc.executeScript("arguments[0].click()",
		 * search);
		 * 
		 * 
		 * }
		 * 
		 * @Then("^I validate page of url$") public void
		 * i_validate_page_of_url() throws Throwable { System.out.println();
		 * Thread.sleep(3000); if (driver.findElement(By.xpath(
		 * "//button[text()='Modify search']")).isDisplayed()) {
		 * System.out.println("Url page open"); } else {
		 * 
		 * System.out.println("Enter Valid Url"); } }
		 * 
		 * @Then("^I validate modify search$") public void
		 * i_validate_modify_search() throws Throwable { System.out.println();
		 * wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//div[@class='row nmx-1']/div[4]"))); List<WebElement> modify =
		 * driver.findElements(By.xpath("//div[@class='row nmx-1']/div"));
		 * System.out.println("Number of modify options"); for (WebElement
		 * MSearch : modify) { System.out.println(MSearch.getText()); }
		 * 
		 * }
		 * 
		 * @Then("^I validate number of filters$") public void
		 * i_validate_number_of_filters() throws Throwable {
		 * System.out.println(); wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "(//div[@class='pt-8']//p)"))); List<WebElement> filters =
		 * driver.findElements(By.xpath("(//div[@class='pt-8']//p)"));
		 * List<WebElement> fHotel = driver.findElements(By.xpath(
		 * "//div[text()='Filter by hotel']")); fHotel.addAll(filters);
		 * 
		 * int no_filters = fHotel.size(); System.out.println(
		 * "Total number of filters : " + no_filters);
		 * 
		 * for (WebElement Element : fHotel) { String names = Element.getText();
		 * System.out.println(names); } }
		 * 
		 * @Then("^I validate number of options for priority$") public void
		 * i_validate_number_of_options_for_priority() throws Throwable {
		 * System.out.println(); wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions .visibilityOfElementLocated(By.xpath(
		 * "(//div[contains(@class,'flex flex-wrap col-22')]//div//p)[6]")));
		 * driver.findElement(By.xpath(
		 * "(//div[contains(@class,'flex flex-wrap col-22')]//div//p)[6]"
		 * )).click(); List<WebElement> priyority =
		 * driver.findElements(By.xpath("(//div[@class='mb-2'])"));
		 * System.out.println("Pripority options : " + priyority.size()); for
		 * (WebElement pri : priyority) { // Thread.sleep(2000);
		 * System.out.println(pri.getText()); } }
		 * 
		 * @Then("^I verify filter by hotels$") public void
		 * i_verify_filter_by_hotels() throws Throwable { System.out.println();
		 * wait = new WebDriverWait(driver, 10); WebElement Fhotels =
		 * driver.findElement(By.xpath(
		 * "(//input[contains(@class,'field bw-1')])[2]")); wait.until(
		 * ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "(//input[contains(@class,'field bw-1')])[2]"))); if
		 * (Fhotels.isDisplayed()) { System.out.println(
		 * "Filter hotel option is prasent"); } else {
		 * 
		 * System.out.println("Filter hotel option is not prasent"); } }
		 * 
		 * @Then("^I verify total price$") public void i_verify_total_price()
		 * throws Throwable { System.out.println(); wait = new
		 * WebDriverWait(driver, 10); wait.until(
		 * ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "(//input[contains(@class,'bn box-border')])"))); List<WebElement>
		 * t_price = driver.findElements(By.xpath(
		 * "(//input[contains(@class,'bn box-border')])"));
		 * driver.findElement(By.xpath(
		 * "(//input[contains(@class,'bn box-border')])[1]")).clear();
		 * driver.findElement(By.xpath(
		 * "(//input[contains(@class,'bn box-border')])[1]")).sendKeys("500");
		 * driver.findElement(By.xpath(
		 * "(//input[contains(@class,'bn box-border')])[2]")).clear();
		 * driver.findElement(By.xpath(
		 * "(//input[contains(@class,'bn box-border')])[2]")).sendKeys("50000");
		 * System.out.println("Total price options : " + t_price.size()); for
		 * (WebElement Tprice : t_price) { String price =
		 * Tprice.getAttribute("value"); System.out.println(price); } }
		 * 
		 * @Then("^I verify Hotel class$") public void i_verify_Hotel_class()
		 * throws Throwable { System.out.println(); wait = new
		 * WebDriverWait(driver, 10); wait.until(
		 * ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "(//label[contains(@class,'radio w-100p')])")));
		 * System.out.println(); List<WebElement> rating =
		 * driver.findElements(By.xpath(
		 * "(//label[contains(@class,'radio w-100p')])")); System.out.println(
		 * "Rating options : " + rating.size()); for (WebElement Trating :
		 * rating) { System.out.println(Trating.getText()); }
		 * Thread.sleep(5000); }
		 * 
		 * @Then("^I verify Special offers$") public void
		 * i_verify_Special_offers() throws Throwable { System.out.println();
		 * wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath(
		 * "(((//div[contains(@class,'flex flex-between')])[3]/following-sibling::div/label))"
		 * ))); List<WebElement> Soffers = driver.findElements( By.xpath(
		 * "(((//div[contains(@class,'flex flex-between')])[3]/following-sibling::div/label))"
		 * )); System.out.println("Special offers options : " + Soffers.size());
		 * for (WebElement special : Soffers) {
		 * System.out.println(special.getText()); } }
		 * 
		 * @Then("^I verify TripAdviser rating$") public void
		 * i_verify_TripAdviser_rating() throws Throwable {
		 * System.out.println(); wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath(
		 * "(((//div[contains(@class,'flex flex-between')])[4]/following-sibling::div/label))"
		 * ))); List<WebElement> TArating = driver.findElements( By.xpath(
		 * "(((//div[contains(@class,'flex flex-between')])[4]/following-sibling::div/label))"
		 * )); System.out.println("TA rating options : " + TArating.size()); for
		 * (WebElement TA : TArating) { System.out.println(TA.getText()); } }
		 * 
		 * @Then("^I verify Amenties$") public void i_verify_Amenties() throws
		 * Throwable { System.out.println(); wait = new WebDriverWait(driver,
		 * 10); wait.until(ExpectedConditions.visibilityOfElementLocated(
		 * By.xpath(
		 * "(((//div[contains(@class,'flex flex-between')])[5]/following-sibling::div/label))"
		 * ))); List<WebElement> vAmenties = driver.findElements( By.xpath(
		 * "(((//div[contains(@class,'flex flex-between')])[5]/following-sibling::div/label))"
		 * )); List<WebElement> vAmentie = driver.findElements(By.xpath(
		 * "(((//div[contains(@class,'flex flex-between')])[5]/following-sibling::div/label))[3]/following-sibling::div/div/labe"
		 * )); vAmenties.addAll(vAmentie); System.out.println(
		 * "Amenties options : " + vAmenties.size()); for (WebElement amenties :
		 * vAmenties) { System.out.println(amenties.getText()); } }
		 * 
		 * @Then("^I verify Property type$") public void
		 * i_verify_Property_type() throws Throwable { System.out.println();
		 * wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath(
		 * "(((//div[contains(@class,'flex flex-between')])[6]/following-sibling::div/label))"
		 * ))); List<WebElement> pType = driver.findElements( By.xpath(
		 * "(((//div[contains(@class,'flex flex-between')])[6]/following-sibling::div/label))"
		 * )); System.out.println("Property type options : " + pType.size());
		 * for (WebElement property : pType) {
		 * System.out.println(property.getText()); } }
		 * 
		 * @Then("^I verify D-block$") public void i_verify_D_block() throws
		 * Throwable { System.out.println(); wait = new WebDriverWait(driver,
		 * 10);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//p[text()='Featured']"))); List<WebElement> dBlock =
		 * driver.findElements(By.xpath("(//div[@class='d-block']//div)[1]/p"));
		 * System.out.println("D-block option : " + dBlock.size()); for
		 * (WebElement Dblock : dBlock) { System.out.println(Dblock.getText());
		 * } System.out.println(); String numberOfHotels =
		 * driver.findElement(By.xpath(
		 * "//p[@class='fs-3 c-neutral-700']//span[1]")).getText();
		 * System.out.println("Number of hotels");
		 * 
		 * }
		 * 
		 * @Then("^I verify clearAll button$") public void
		 * i_verify_clearAll_button() throws Throwable { System.out.println();
		 * wait = new WebDriverWait(driver, 10); driver.findElement(By.xpath(
		 * "(//div[text()='Clear all'])[1]")).click(); Thread.sleep(5000); wait
		 * = new WebDriverWait(driver, 10); String actual = "570"; String
		 * expected = driver.findElement(By.xpath(
		 * "(//input[contains(@class,'bn box-border')])[1]"))
		 * .getAttribute("value"); System.out.println(expected);
		 * 
		 * if (expected.equals(actual)) { System.out.println(
		 * "ClearAll button working "); } else { System.out.println(
		 * "ClearAll button not working");
		 * 
		 * } }
		 * 
		 * @Then("^I validate populer hotels$") public void
		 * i_validate_populer_hotels() throws Throwable { System.out.println();
		 * wait = new WebDriverWait(driver, 10); List<WebElement> pHotels =
		 * driver.findElements(By.xpath(
		 * "(//ul[contains(@class,'fs-2 ls-reset')])[1]/li"));
		 * System.out.println("Number of populer Hotels : " + pHotels.size());
		 * for (WebElement PHotels : pHotels) {
		 * System.out.println(PHotels.getText()); } }
		 * 
		 * @Then("^I validate populer hotel chains$") public void
		 * i_validate_populer_hotel_chains() throws Throwable {
		 * System.out.println(); wait = new WebDriverWait(driver, 10);
		 * List<WebElement> phChain = driver.findElements(By.xpath(
		 * "(//ul[contains(@class,'fs-2 ls-reset')])[2]/li"));
		 * System.out.println("Number of Populer hotel chains : " +
		 * phChain.size()); for (WebElement PHChain : phChain) {
		 * System.out.println(PHChain.getText()); } }
		 * 
		 * @Then("^I validate other tools$") public void
		 * i_validate_other_tools() throws Throwable { System.out.println();
		 * wait = new WebDriverWait(driver, 10); List<WebElement> oTools =
		 * driver.findElements(By.xpath(
		 * "(//ul[contains(@class,'fs-2 ls-reset')])[3]/li"));
		 * System.out.println("Number of Other Tools : " + oTools.size()); for
		 * (WebElement OTools : oTools) { System.out.println(OTools.getText());
		 * } }
		 * 
		 * @When("^I click view detials$") public void i_click_view_detials()
		 * throws Throwable { System.out.println(); wait = new
		 * WebDriverWait(driver, 10); WebElement view =
		 * driver.findElement(By.xpath("(//button[text()='View details'])[1]"));
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].click()", view);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "(//button[text()='Select room'])[2]"))); Thread.sleep(2000);
		 * 
		 * }
		 * 
		 * @Then("^I verify select room option$") public void
		 * i_verify_select_room_option() throws Throwable { if
		 * (driver.findElement(By.xpath("(//button[text()='Select room'])[2]"
		 * )).isDisplayed()) { System.out.println("Search room page open"); }
		 * else {
		 * 
		 * System.out.println("Search room page is not open"); } }
		 * 
		 * @Then("^I close Browser$") public void i_close_Browser() throws
		 * Throwable { driver.close(); }
		 * 
		 */
		/*
		 * 
		  Modify search 
		 ............. 
	       place xpath =(//input[@name='search'])[1] 
	       date list xpath =(//div[contains(@class,'col-6px-1')]//div[contains(@class,'fs-inherit c-inherit')]) 
	       passenger xpath = //button[@name='travellers']//div[1]
	        modify search btn =//button[text()='Modify search']
		  
		 * filters 
		 ............... 
		 options xpath = driver.findElements(By.xpath("(//div[@class='pt-8']//p)"))
		 options xpath = driver.findElements(By.xpath("//div[text()='Filter by hotel']"));

		 * hotel class 
		 ........... 
		 options xpath = (//div[contains(@class,'flex flex-between')])[2]/following-sibling::div/label/div/div
		  checkboxxpath = (//div[contains(@class,'flex flex-between')])[2]/following-sibling::div/label/div/span[contains(@class,'checkbox__mark')]
		 * 
		 * special offers 
		 ............... 
		 options xpath =(//div[contains(@class,'flex flex-between')])[3]/following-sibling::div/label/div/div 
		 checkbox xpath = (//div[contains(@class,'flex flex-between')])[3]/following-sibling::div/label/div/span[contains(@class,'checkbox__mark')]
		 * 
		 * TripAdviser rating 
		 ................... 
		 options xpath =(//div[contains(@class,'flex flex-between')])[4]/following-sibling::div/label/div/div 
		 checkbox xpath = (//div[contains(@class,'flex flex-between')])[4]/following-sibling::div/label/div/span[contains(@class,'radio__circle')]
		 * 
		 * Amenties 
		 ......... 
		 options1 xpath = (//div[contains(@class,'flex flex-between')])[5]/following-sibling::div/label/div/div 
		 options2 xpath = (//div[contains(@class,'flex flex-between')])[5]/following-sibling::div/label/following-sibling::div/div/label/div[contains(@class,'flex flex-middle flex-between flex-1')]/div 
		 *checkbox1 xpath = checkbox2 xpath =
		 * 
		 * Property types 
		  .............. 
		  options1 xpath =(//div[contains(@class,'flex flex-between')])[6]/following-sibling::div/label/div/div 
		  options2 xpath = (//div[contains(@class,'flex flex-between')])[6]/following-sibling::div/label/following-sibling::div/div/label/div[contains(@class,'flex flex-middle flex-between flex-1')]/div 
		  checkbox1 xpath = 
		  checkbox2 xpath =
		 *
		 * 
		 * 
		 * 
		 */

	}

}
