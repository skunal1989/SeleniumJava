package TestCases;




import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class FirstTestCase1 {
	
	
	
	
	
	    public static void main(String[] args) {
	        // Set the path for the ChromeDriver executable
	       

	        // Initialize the ChromeDriver and Actions
	    	ChromeDriver driver = new ChromeDriver();
	        Actions action = new Actions(driver);

	        try {
	            // Navigate to the website
	            driver.get("https://automationexercise.com/");
	            driver.manage().window().maximize();
	            driver.manage().deleteAllCookies();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	            // Initialize WebDriverWait
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	            // List categories and print them
	            List<WebElement> categories = driver.findElements(By.xpath("//div[@class='panel-group category-products']/div"));
	            System.out.println("-----The categories are as below-------");
	            for (WebElement category : categories) {
	                System.out.println("Category text: " + category.getText());
	            }

	            // Click on the 'Women' link
	            driver.findElement(By.xpath("//a[@href='#Women']")).click();

	            // Wait for 'Dress' link to be clickable and click it
	            WebElement dressLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Women']//li//a[contains(text(),'Dress')]")));
	            dressLink.click();

	            // Find and print Dress names and Prices
	            List<WebElement> dresses = driver.findElements(By.xpath("//div[@class='productinfo text-center']//p"));
	            List<WebElement> prices = driver.findElements(By.xpath("//div[@class='productinfo text-center']//h2"));

	            System.out.println("-----Dresses and Prices-----");   
	            for (WebElement price : prices) {
	                System.out.println("Price: " + price.getText().trim());
	            }

	            for (WebElement dress : dresses) {
	                System.out.println("Name of Dress: " + dress.getText().trim());
	                if (dress.getText().contains("Stylish")) {
	                    action.moveToElement(dress).perform();

	                    // Ensure the add-to-cart button is visible and clickable
	                    WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='btn btn-default add-to-cart'])[4]")));
	                    addToCartButton.click();

	                    // Wait for and click the 'Continue Shopping' button
	                    WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue Shopping')]")));
	                    continueShoppingButton.click();
	                }
	            }

	            // Click on 'Men Tshirt' link
	            WebElement menTshirtLink = driver.findElement(By.xpath("//div[@class='brands-name']//li[2]"));
	            action.moveToElement(menTshirtLink).click().perform();

	            WebElement cloth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(text(),'Men Tshirt')])[1]")));
	            action.moveToElement(cloth).perform();

	            WebElement addMenTshirtButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@data-product-id='2'])[2]")));
	            addMenTshirtButton.click();

	            // Wait for and click the 'Continue Shopping' button
	            WebElement continueShoppingButtonMen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue Shopping')]")));
	            continueShoppingButtonMen.click();

	            // View Cart
	            driver.findElement(By.xpath("//a[@href='/view_cart' and contains(text(),' Cart')]")).click();    

	            // Find cart details
	            List<WebElement> clothdesc = driver.findElements(By.xpath("//div[@id='cart_info']/table/tbody/tr/td[@class='cart_description']"));
	            List<WebElement> clothprice = driver.findElements(By.xpath("//div[@id='cart_info']/table/tbody/tr/td[@class='cart_price']/p"));
	            List<WebElement> clothquantity = driver.findElements(By.xpath("//div[@id='cart_info']/table/tbody/tr/td[@class='cart_quantity']/button"));
	            List<WebElement> clothtotal = driver.findElements(By.xpath("//div[@id='cart_info']/table/tbody/tr/td[@class='cart_total']/p"));

	            // Initialize arrays with the size of the lists
	            int[] priceArray = new int[clothprice.size()];
	            int[] quantityArray = new int[clothquantity.size()];

	            for (int j = 0; j < clothdesc.size(); j++) {
	                System.out.println("The cloth description is " + clothdesc.get(j).getText());
	                System.out.println("The cloth price is " + clothprice.get(j).getText().trim().substring(4));
	                System.out.println("The cloth quantity is " + clothquantity.get(j).getText().trim());
	                System.out.println("The cloth total is " + clothtotal.get(j).getText().trim().substring(4));
	                
	                priceArray[j] = Integer.parseInt(clothprice.get(j).getText().trim().substring(4));
	                quantityArray[j] = Integer.parseInt(clothquantity.get(j).getText().trim());
	            }

	            // Validate total amount
	            for (int k = 0; k < priceArray.length; k++) {
	                int totalValidatedAmount = priceArray[k] * quantityArray[k];
	                System.out.println("Total validated amount: " + totalValidatedAmount);
	            }

	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	    }
	}



