package twiliopack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonRegistration
{
	public static void main(String[] args) throws Exception
	{
		//disable notifications
		ChromeOptions co=new ChromeOptions();
		co.addArguments("--disable-notifications");
		//Launch browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		//maximize browser
		driver.manage().window().maximize();
		//open site
		driver.get("https://www.amazon.in/");
		//create wait object
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@data-nav-role='signin'])[1]")));
		WebElement e=driver.findElement(By.xpath("(//*[@data-nav-role='signin'])[1]"));
		Actions a=new Actions(driver);
		a.moveToElement(e).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='New customer? '])[1]/a"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerName"))).sendKeys("shahnaz");
		WebElement e1=driver.findElement(By.name("countryCode"));
		Select cc=new Select(e1);
		cc.selectByValue("US");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("2026014998");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("shahnaz@123");
		Thread.sleep(6000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();
		Thread.sleep(6000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("code")));
		Thread.sleep(6000);
		//connect to twilio cloud for SMS service
		String asid="AC8265893ca78750905f0aa5ba861b2e76";
		String auth="dc067a4dd12743049ce88ba2ed401625";
		Twilio.init(asid,auth);
		ResourceSet<Message> messages=Message.reader().read();
		Message m=messages.iterator().next();
		String body=m.getBody();
		String[] wimb=body.split(" ");
		String otp=wimb[0];
		/*Pattern p=Pattern.compile("[0-9]{6}");
		Matcher mat=p.matcher(body);
		if(mat.find())
		{
			String otp=mat.group();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("code"))).sendKeys(otp);
		}*/
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("code"))).sendKeys(otp);
	}
}
