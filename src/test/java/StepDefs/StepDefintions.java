package StepDefs;


import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;




public class StepDefintions {
	
	WebDriver driver;
	String value="deprecated";
	String res_desc = "Repository automation";
	String issue_id = "";
	String repo_name = "";
	String issue_name = "";
	String repo_details="";
	
	
	@Given("^User is present on github page$")
	public void user_is_present_on_github_page() {
		
		System.setProperty("webdriver.chrome.driver" , "C:\\Users\\sides\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://github.com/");
	    
	    
	}

	@When("^User clicks on Sign-in button$")
	public void user_clicks_on_Sign_in_button() {
		driver.findElement(By.xpath("//div[@class='HeaderMenu HeaderMenu--logged-out position-fixed top-0 right-0 bottom-0 height-fit position-lg-relative d-lg-flex flex-justify-between flex-items-center flex-auto']/div[2]//a[@href='/login']")).click();
	    
	}

	@When("^User enters login details$")
	public void user_enters_login_details() {
	  driver.findElement(By.id("login_field")).sendKeys("etishademo");
	  driver.findElement(By.id("password")).sendKeys("Jun@2020");
	  driver.findElement(By.xpath("//input[@value='Sign in']")).click();
	}

	@Then("^User navigates to home page$")
	public void user_navigates_to_home_page() {
	    String title = driver.getTitle();
	    System.out.println(title);
	}

	@Then("^User clicks on create repository$")
	public void user_clicks_on_create_repository() {
	    driver.findElement(By.xpath("(//a[text()= 'Create repository' ])[1]")).click();
	}

	@Then("^User enters repo details$")
	public void user_enters_repo_details() throws InterruptedException {
		driver.findElement(By.id("repository_name")).sendKeys("TestScenarios");
		driver.findElement(By.id("repository_description")).sendKeys(res_desc);
	    driver.findElement(By.id("repository_auto_init")).click();
	    Thread.sleep(3000);
//	    JavascriptExecutor js = (JavascriptExecutor) driver;
//	    js.executeScript("arguments[0].scrollTo(0, document.body.scrollHeight)");
	    driver.findElement(By.xpath("//button[contains(text(),'Create repository')]")).click();
	    Thread.sleep(3000);
	    
	}

	@Then("^Verify repository created$")
	public void repository_created() {

		String user_name = driver.findElement(By.xpath("//a[text()='etishademo']")).getText();
		Boolean val= user_name.equalsIgnoreCase("etishademo");
		Assert.assertTrue(val, "Repository created successfully");
		
	    
	}

	@Given("^User navigates to repository$")
		public void user_navigates_to_repository() throws InterruptedException {
		
			driver.findElement(By.xpath("//a[@href = '/etishademo/TestScenarios']")).click();
			
		}

	@Given("^User navigates to Issues column$")
	public void user_navigates_to_Issues_column() {
		
		driver.findElement(By.xpath("//a[@href='/etishademo/TestScenarios/issues']")).click();
    
	}

	@When("^User click on New issue button$")
	public void user_click_on_New_issue_button() {
		
		driver.findElement(By.xpath("//span[text() = 'New issue']")).click();
	   
	}
	@And("^User enters issue details$")
	public void user_enters_issue_details() throws InterruptedException
	{
		driver.findElement(By.id("issue_title")).sendKeys(value);
		driver.findElement(By.id("labels-select-menu")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text() = 'bug']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("labels-select-menu")).click();
		
	}
	@Then("^User clicks on Submit new issue button$")
	public void user_clicks_on_Submit_new_issue_button() {
		
		driver.findElement(By.xpath(" //div[@class= 'flex-items-center flex-justify-end mx-2 mb-2 px-0 d-none d-md-flex']/button")).click();

	}	
	
	@Then("^Verify issue created$")
	public void issue_created()
	{
		
			Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'"  + value + "')]")).isDisplayed(),"Issue created successfully");
		    issue_id = driver.findElement(By.xpath("//span[contains(text(),'"  + value +"')]/following-sibling::span")).getText();
	}
   @Then("^User creates another issue mentioning previous issue$")
   public void user_creates_another_issue() {
	   driver.findElement(By.xpath("(//a[contains(text(),'New issue')])[2]")).click();
	   driver.findElement(By.id("issue_title")).sendKeys(issue_id);
	   driver.findElement(By.xpath(" //div[@class= 'flex-items-center flex-justify-end mx-2 mb-2 px-0 d-none d-md-flex']/button")).click();   
	
}
   
   @When("^User clicks on existing issue$")
   public void user_clicks_existing_issue() throws InterruptedException {
	   Thread.sleep(3000);
	   List<WebElement> exist_issue = driver.findElements(By.xpath("//div[contains(@id ,'issue_')]/div/div[2]/a"));
	   exist_issue.get(0).click();
	   Thread.sleep(3000);
   }
   
   @When("^User add a comment to an issue$") 
   public void user_add_comment() {
	WebElement comment_field = driver.findElement(By.xpath("//textarea[@id='new_comment_field']"));
	comment_field.click();
	comment_field.sendKeys("commenting...");
	driver.findElement(By.xpath("//button[contains(text(),'Comment')]")).click();
   }

   @When("^User clicks on Edit button$")
   public void user_clicks_edit() {
	   repo_name= driver.findElement(By.xpath("//div[@class='f4']/span")).getText();
	   
	   driver.findElement(By.xpath("//div[@class='f4']/span")).click();
   }
   
   @When("^User enters emoji in description$")
   public void user_add_emogi() {
	   repo_name+=":tada:";
	   driver.findElement(By.xpath("//input[@id='repo_description']")).clear();
	   driver.findElement(By.xpath("//input[@id='repo_description']")).sendKeys(repo_name);
	   driver.findElement(By.xpath("//button[text()='Save']")).click();
	   
	   
   }
   
   @Then("^emoji is added to repository$")
   public void user_check_emoji() {
	   String new_desc = driver.findElement(By.xpath("//div[@class='f4']/span")).getText(); 
	   System.out.println(new_desc);
	   
   }

   @And("^User add a comment of the previous issue name$")
   public void add_comment_previous_issue_name() throws InterruptedException {
	   issue_name = driver.findElement(By.xpath("//h1[@class = 'gh-header-title f1 mr-0 flex-auto break-word']/span[2]")).getText();
	   System.out.println(issue_name);
	   Thread.sleep(3000);
	   driver.navigate().back();
	   Thread.sleep(2000);
	   List<WebElement> exist_issue1 = driver.findElements(By.xpath("//div[contains(@id ,'issue_')]/div/div[2]/a"));
	   exist_issue1.get(1).click();
	   Thread.sleep(3000);
		WebElement comment_field1 = driver.findElement(By.xpath("//textarea[@id='new_comment_field']"));
		comment_field1.click();
		comment_field1.sendKeys(issue_name,Keys.ENTER);
		driver.findElement(By.xpath("//button[contains(text(),'Comment')]")).click();
		Thread.sleep(2000);
   }
   
   @Then("^User clicks on the link mentioned in comment$")
   public void link_mentioned() {
	   driver.findElement(By.xpath("//td[@class = 'd-block comment-body markdown-body  js-comment-body']/p/a")).click();
   }
   
   @When("^User clicks on settings button$") 
   public void user_clicks_settings() throws InterruptedException {
	   
	   Thread.sleep(2000);
		String author=driver.findElement(By.xpath("//span[@itemprop='author']/a")).getText();
		String name= driver.findElement(By.xpath("//strong[@itemprop='name']/a")).getText();
		 repo_details=author +"/"+ name;
		driver.findElement(By.xpath("//a[@href = '/etishademo/TestScenarios/settings']")).click();
	    Thread.sleep(2000);
   }
   @Then("^User clicks on delete repository under Damage Area$")
   public void user_click_delete_button() throws InterruptedException {
	   
	   JavascriptExecutor js = ((JavascriptExecutor) driver);
	   js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	   js.executeScript("arguments[0]. click();",driver.findElement(By.xpath("//div[@class='Box Box--danger']/ul/li[4]/details/summary")));
	   
   }
	   
	   @Then("^User clicks on delete repository button$")
	   public void delete() {
	   WebElement delete_data = driver.findElement(By.xpath("//form[@action = '/etishademo/TestScenarios/settings/delete']/p/input"));
	   delete_data.click();
	   delete_data.sendKeys(repo_details);
	   driver.findElement(By.xpath("//button[contains(text(),'delete')]")).click();
   }
		
	}

