package twiliopack;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ZomatoResgistration 
{
	public static void main(String[] args) throws Exception
	{
		//Open browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		//Maximize
		driver.manage().window().maximize();
		//open site
		driver.get("https://www.zomato.com/hyderabad");
		//create wait object
		WebDriverWait wait=new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Signup']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='Login'])[2]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='sc-1rq4z74-7 caphYZ']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='sc-1rq4z74-5 fmgAfn']/descendant::p[text()='United States']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Phone']"))).sendKeys("2026014998");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Send One Time Password']"))).click();
		//Connect to twilio cloud for SMS Service
		String asid="AC8265893ca78750905f0aa5ba861b2e76";
		String auth="dc067a4dd12743049ce88ba2ed401625";
		Twilio.init(asid, auth);
		ResourceSet<Message> messages=Message.reader().read();
		Message m=messages.iterator().next();
		String body=m.getBody();
		String[] wimb=body.split(" ");
		String otp=wimb[0];
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='hp56s6-1 hnCSAc']")));
		List<WebElement> l=driver.findElements(By.xpath("//*[@class='hp56s6-1 hnCSAc']"));
		for(int i=0;i<l.size();i++)
		{
			Thread.sleep(2000);
			Character c=otp.charAt(i);
			l.get(i).sendKeys(c.toString());
		}
	}
}
