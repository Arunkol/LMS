import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;


public class TestClass {

	public static void main(String[] args) throws InterruptedException {
		

			
			String entry;
			String inputString =null;
			WebDriver driver;
			{
			String workingDirectory = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", workingDirectory + "\\Config\\Driver\\chromedriver.exe");
			//driver = new ChromeDriver();
			//Disable chrome options
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			driver = new ChromeDriver(options);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("http://10.31.101.105:7000");
			Thread.sleep(3000);

			driver.findElement(By.xpath("//ul[@class='sidebar-menu']//li[1]/a")).click();

			Thread.sleep(3000);

			driver.findElement(By.xpath("//div[@class='row']//a")).click();
			Thread.sleep(3000);

			//Business

			driver.findElement(By.xpath("//*[@id='Container']//h4[1]")).click();
			Thread.sleep(1000);

            //Generic method to call all the dropdowns
			ClickDropDown(driver);
			
			driver.quit();
	}

}

	private static void ClickDropDown(WebDriver driver) {
		Select DropDown = null;
		
		String entry;
		String tagName;
		//Finding number of rows which are having controls
		List<WebElement> rowCountsForElements = driver.findElements(By.xpath("//section[@class='tab']/ul[1]/li/div[@class='bg-color']/div"));
		
		System.out.println("Number of ROwCounts: "+rowCountsForElements.size());
		
		for(int i=1;i<rowCountsForElements.size();i++){
			tagName = driver.findElement(By.xpath("//*[@id='Container']//div[@class='bg-color']//div[@class='row']["+i+"]//div[2]//*")).getTagName();
			System.out.println("Tag Name: "+tagName +" row Name: "+i);
			
			if(tagName.equalsIgnoreCase("select")){
				DropDown = new Select(driver.findElement(By.xpath("//*[@id='Container']//div[@class='row']["+i+"]//select")));
				
				//Randomly picking any option from dropdown. Hence calling randomDropDownIndex(DropDown) method. It returns a random integer number
				DropDown.selectByIndex(randomDropDownIndex(DropDown));
				
				entry=DropDown.getFirstSelectedOption().getText();
				System.out.println(entry);
			}
		}
	}

	private static int randomDropDownIndex(Select dropDown) {
		Random rand = new Random();
		List<WebElement> items = dropDown.getOptions();
		int count= items.size();
		int Rvalue = rand.nextInt(count);
		System.out.println("Selected Random number: "+Rvalue);
		return Rvalue;
	}
}
