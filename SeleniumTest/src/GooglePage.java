import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
	WebDriver driver;
	Boolean flag = false;
	String strPageNumber = "23";

	String strTestData = "";
	By impSearchBox = By.xpath("//input[@class='gLFyf gsfi']");
	By btnGoogleSearch = By.xpath("//input[@aria-label='Google Search']");
	By btnNext = By.xpath("//td//a[@id='pnnext']//span[contains(text(),'Next')]");
	By lnkPageNo = By.xpath("//table[@id='nav']//tbody//td//a[@class='fl']");
	By lnkSearchResult = By.xpath("//ul[@class='erkvQe']//li//div//span");
	By lnkLinksUrl = By
			.xpath("//div[@id='ires']//div[@class='srg']//div[@class='r']//a//h3[@class='LC20lb']//parent::a");

	public void openGoogle() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ivavsys\\workspace\\SeleniumTest\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		System.out.println("Open Google");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	public void searchText() {
		this.setText(impSearchBox, "India");
		List<WebElement> list = driver.findElements(lnkSearchResult);
		System.out.println("Size of search links==> " + list.size());
		for (WebElement ele : list) {
			if (ele.getText().equalsIgnoreCase("India")) {
				System.out.println("Link text==>" + ele.getText());
				ele.click();
				break;
			}
		}
		System.out.println(" Search India");

	}

	public void openParticularPage() {
		By lnkPageNumber = By
				.xpath("//table[@id='nav']//tbody//td//a[@class='fl'][contains(text(),'" + strPageNumber + "')]");
		while (true) {
			if (strPageNumber.equals("1")) {
				break; // For default page
			}
			if (this.checkElementDisplayed(lnkPageNumber)) {
				this.clickOnElement(lnkPageNumber);
				System.out.println("Click On " + strPageNumber + " Page No");
				break;
			} else {
				List<WebElement> list = driver.findElements(lnkPageNo);
				this.clickOnElement(list.get(list.size() - 1));
			}

		}
	}

	public void clickOnSecondLastLink() {

		List<WebElement> list = driver.findElements(lnkLinksUrl);
		int secondLastElemnt = list.size() - 2;
		clickOnElement(list.get(secondLastElemnt));
		System.out.println("open Second Last Page");

	}

	public void getTitlePage() {

		String actualTitle = driver.getTitle();
		System.out.println("Title of page is===> " + actualTitle);
	}

	public Boolean checkElementDisplayed(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			flag = element.isDisplayed();
		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}

	public void clickOnElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void clickOnElement(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void setText(By locator, String strSetText) {
		try {
			WebElement element = driver.findElement(locator);
			element.sendKeys(strSetText);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
