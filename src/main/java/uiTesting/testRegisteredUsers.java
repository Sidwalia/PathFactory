package uiTesting;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class testRegisteredUsers {	
	 static WebDriver driver ; 
	 public static WebElement Email;
     public static WebElement Password;
	 public static WebElement SignInButton;
	 public static WebElement ForgotPassword;
	 String registeredUser= "siddharthwalia08@gmail.com";
	 String nonRegisteredUser= "notaregistereduser@gmail.com";
	 String validPassword= "TestPassword";
	 String invalidPassword= "InvalidPassword";
	  
	 @BeforeMethod
	 public void launchBrowser() {
	 System.out.println("launching firefox browser"); 
	 System.setProperty("webdriver.gecko.driver", "C:\\Users\\sid\\workspace\\interview\\Driver\\geckodriver.exe");
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	 driver.manage().deleteAllCookies();
	 driver.get("http://automationpractice.com/index.php");
	 driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=my-account']")).click();
	 Email= driver.findElement(By.xpath("//input[@id='email']"));
	 Password= driver.findElement(By.xpath("//input[@id='passwd']"));
	 SignInButton= driver.findElement(By.xpath("//button[@id='SubmitLogin']"));
	 ForgotPassword= driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=password']"));
	 }
	 
	 @Test (priority=1)
	 public void validRegistereduserValidPassword() throws InterruptedException {
	 Email.sendKeys(registeredUser);
	 Password.sendKeys(validPassword); 
	 SignInButton.click();
	 String pageHeading = driver.findElement(By.className("page-heading")).getText();
	 System.out.println(pageHeading);
	 //Verify that the User is logged in, and can see "MY ACCOUNT" Screen		  
	 Assert.assertEquals("MY ACCOUNT", pageHeading);          
	 }
	 
	 @Test (priority=2)
     public void nonRegisteredUserValidPassword(){
	 Email.sendKeys(nonRegisteredUser);
     Password.sendKeys(validPassword); 
     SignInButton.click();         
     String authenticationError = driver.findElement(By.xpath("//*[@id='center_column']/h1")).getText();
     System.out.println(authenticationError);
     //Verify that the User is not able to log in and Authentication Error message is displayed
     Assert.assertEquals("AUTHENTICATION", authenticationError);
	 }
	 
	 @Test (priority=3)
     public void emailValidation(){
	 Email.sendKeys("invalidemailaddress@email");
     Password.sendKeys(validPassword); 
     SignInButton.click();         
     String emailAuthenticationError = driver.findElement(By.xpath("//*[@id='center_column']/div[1]/ol/li")).getText();
     System.out.println(emailAuthenticationError);
     //Verify that the invalid Email Authentication Error is displayed
     Assert.assertEquals("Invalid email address.", emailAuthenticationError);
	 }
	 
	 @Test (priority=4)
     public void nonRegisteredUserInvalidPassword(){
	 Email.sendKeys(nonRegisteredUser);
     Password.sendKeys(invalidPassword); 
     SignInButton.click();         
     String authenticationError = driver.findElement(By.xpath("//*[@id='center_column']/h1")).getText();
     System.out.println(authenticationError);
     //Verify that the User is not able to log in and Authentication Error message is displayed
     Assert.assertEquals("AUTHENTICATION", authenticationError);
	 }
	 
	 @Test (priority=5)
     public void registeredUserInvalidPassword(){
	 Email.sendKeys(registeredUser);
     Password.sendKeys(invalidPassword); 
     SignInButton.click();         
     String authenticationError = driver.findElement(By.xpath("//*[@id='center_column']/h1")).getText();
     System.out.println(authenticationError);
     //Verify that the User is not able to log in and Authentication Error message is displayed
     Assert.assertEquals("AUTHENTICATION", authenticationError); 
	 }
	 
	 @Test (priority=6)
     public void blankUsernameBlankPassword(){
	 Email.sendKeys("");
     Password.sendKeys(""); 
     SignInButton.click();         
     String authenticationError = driver.findElement(By.xpath("//*[@id='center_column']/h1")).getText();
     System.out.println(authenticationError);
     //Verify that the User is not able to log in and Authentication Error message is displayed
     Assert.assertEquals("AUTHENTICATION", authenticationError); 
	 }
	 
	 @Test (priority=7)
     public void forgotPasswordPageNavigation(){
     ForgotPassword.click();         
     String forgotPassword = driver.findElement(By.xpath("//*[@id='center_column']/div/h1")).getText();
     //Verify that the User navigated to the Forgot Password screen
     Assert.assertEquals("FORGOT YOUR PASSWORD?", forgotPassword); 
	 }
	 
	 @Test (priority=8)
     public void forgotPasswordValidRegisteredUser(){
	 ForgotPassword.click();         
     driver.findElement(By.xpath("//*[@id='email']")).sendKeys(registeredUser);
     driver.findElement(By.xpath("//p[@class='submit']/button")).click();
     String successMessage = driver.findElement(By.xpath("//*[@id='center_column']/div/p")).getText();
     System.out.println(successMessage);
     //Verify that a success message is displayed on entering a valid registered User
     Assert.assertEquals("A confirmation email has been sent to your address: "+registeredUser, successMessage); 
	 }
	 
	 @Test (priority=9)
     public void forgotPasswordUnregisteredUser(){
	 ForgotPassword.click();         
     driver.findElement(By.xpath("//*[@id='email']")).sendKeys(nonRegisteredUser);
     driver.findElement(By.xpath("//p[@class='submit']/button")).click();
     String errorMessage = driver.findElement(By.xpath("//*[@id='center_column']/div/div/ol/li")).getText();
     //Verify that a error message is displayed on entering a valid registered User
     Assert.assertEquals("There is no account registered for this email address.", errorMessage); 
	 }
	 
	 @Test (priority=10)
     public void forgotPasswordInvalidEmail(){
	 ForgotPassword.click();         
     driver.findElement(By.xpath("//*[@id='email']")).sendKeys("invalidemailaddress@email");
     driver.findElement(By.xpath("//p[@class='submit']/button")).click();
     String errorMessageInvalidEmail = driver.findElement(By.xpath("//*[@id='center_column']/div/div/ol/li")).getText();
     //Verify that a error message is displayed on entering an invalid email
     Assert.assertEquals("Invalid email address.", errorMessageInvalidEmail); 
	 }
	 
	 @Test (priority=11)
     public void forgotPasswordBackToLoginButton(){
	 ForgotPassword.click();         
     driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=authentication']")).click();
     String alreadyRegistered= driver.findElement(By.xpath("//*[@id='login_form']/h3")).getText();
     //Verify that the User is navigated back to the Sign-in Screen
     Assert.assertEquals("ALREADY REGISTERED?", alreadyRegistered); 
	 }
	 
	 @AfterMethod
	 public void tearDown() {
	 driver.quit();
		}
	}