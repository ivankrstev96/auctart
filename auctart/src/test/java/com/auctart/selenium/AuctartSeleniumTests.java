package com.auctart.selenium;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuctartSeleniumTests {

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ikrstev\\Workspace\\auctart\\auctart\\chromedriver.exe");
    }

    @Test
    public void badLoginTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Login");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));

            WebElement logInButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("bad");
            passwordInput.sendKeys("login123");

            logInButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notifications-wrapper div")));
            WebElement errorNotification = driver.findElement(By.cssSelector(".notifications-wrapper div"));

            assertTrue(errorNotification.getText().contains("Failure"));
        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void goodLoginTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Login");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));

            WebElement logInButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("test");
            passwordInput.sendKeys("123456");

            logInButton.click();

            wait.until(ExpectedConditions.urlToBe("http://localhost:3000/Auctions"));

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void registerFormShorterUsernameLengthTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Register");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement emailInput = driver.findElement(By.id("inputEmail"));
            WebElement confirmEmailInput = driver.findElement(By.id("inputEmailRe"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));
            WebElement confirmPasswordInput = driver.findElement(By.id("inputPasswordRe"));
            WebElement termsInput = driver.findElement(By.id("checkStayLogged"));

            WebElement registerButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("as");
            emailInput.sendKeys("test@test.com");
            confirmEmailInput.sendKeys("test@test.com");
            passwordInput.sendKeys("123456");
            confirmPasswordInput.sendKeys("123456");
            termsInput.click();

            int errNo = driver.findElements(By.className("error")).stream()
                    .filter(x -> x.getText().contains("Username must be")).toArray().length;

            if (errNo < 1 || isClickable(registerButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void registerFormLongerUsernameLengthTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Register");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement emailInput = driver.findElement(By.id("inputEmail"));
            WebElement confirmEmailInput = driver.findElement(By.id("inputEmailRe"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));
            WebElement confirmPasswordInput = driver.findElement(By.id("inputPasswordRe"));
            WebElement termsInput = driver.findElement(By.id("checkStayLogged"));

            WebElement registerButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm");
            emailInput.sendKeys("test@test.com");
            confirmEmailInput.sendKeys("test@test.com");
            passwordInput.sendKeys("123456");
            confirmPasswordInput.sendKeys("123456");
            termsInput.click();

            int errNo = driver.findElements(By.className("error")).stream()
                    .filter(x -> x.getText().contains("Username must be")).toArray().length;

            if (errNo < 1 || isClickable(registerButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void registerFormValidEmailTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Register");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement emailInput = driver.findElement(By.id("inputEmail"));
            WebElement confirmEmailInput = driver.findElement(By.id("inputEmailRe"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));
            WebElement confirmPasswordInput = driver.findElement(By.id("inputPasswordRe"));
            WebElement termsInput = driver.findElement(By.id("checkStayLogged"));

            WebElement registerButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("asdf");
            emailInput.sendKeys("asdf");
            confirmEmailInput.sendKeys("asdf");
            passwordInput.sendKeys("123456");
            confirmPasswordInput.sendKeys("123456");
            termsInput.click();

            int errNo = driver.findElements(By.className("error")).stream()
                    .filter(x -> x.getText().contains("valid e-mail")).toArray().length;

            if (errNo < 1 || isClickable(registerButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void registerFormEmailsMatchTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Register");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement emailInput = driver.findElement(By.id("inputEmail"));
            WebElement confirmEmailInput = driver.findElement(By.id("inputEmailRe"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));
            WebElement confirmPasswordInput = driver.findElement(By.id("inputPasswordRe"));
            WebElement termsInput = driver.findElement(By.id("checkStayLogged"));

            WebElement registerButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("asdf");
            emailInput.sendKeys("test@test.com");
            confirmEmailInput.sendKeys("asdf");
            passwordInput.sendKeys("123456");
            confirmPasswordInput.sendKeys("123456");
            termsInput.click();

            int errNo = driver.findElements(By.className("error")).stream()
                    .filter(x -> x.getText().contains("Email addresses")).toArray().length;

            if (errNo < 1 || isClickable(registerButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void registerFormPasswordLengthTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Register");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement emailInput = driver.findElement(By.id("inputEmail"));
            WebElement confirmEmailInput = driver.findElement(By.id("inputEmailRe"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));
            WebElement confirmPasswordInput = driver.findElement(By.id("inputPasswordRe"));
            WebElement termsInput = driver.findElement(By.id("checkStayLogged"));

            WebElement registerButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("asdf");
            emailInput.sendKeys("test@test.com");
            confirmEmailInput.sendKeys("test@test.com");
            passwordInput.sendKeys("12");
            confirmPasswordInput.sendKeys("12");
            termsInput.click();

            int errNo = driver.findElements(By.className("error")).stream()
                    .filter(x -> x.getText().contains("Password is not")).toArray().length;

            if (errNo < 1 || isClickable(registerButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void registerFormPasswordsMatchTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Register");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement emailInput = driver.findElement(By.id("inputEmail"));
            WebElement confirmEmailInput = driver.findElement(By.id("inputEmailRe"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));
            WebElement confirmPasswordInput = driver.findElement(By.id("inputPasswordRe"));
            WebElement termsInput = driver.findElement(By.id("checkStayLogged"));

            WebElement registerButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("asdf");
            emailInput.sendKeys("test@test.com");
            confirmEmailInput.sendKeys("test@test.com");
            passwordInput.sendKeys("123456");
            confirmPasswordInput.sendKeys("12");
            termsInput.click();

            int errNo = driver.findElements(By.className("error")).stream()
                    .filter(x -> x.getText().contains("Passwords don't match")).toArray().length;

            if (errNo < 1 || isClickable(registerButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void registerFormUncheckedTermsTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Register");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement emailInput = driver.findElement(By.id("inputEmail"));
            WebElement confirmEmailInput = driver.findElement(By.id("inputEmailRe"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));
            WebElement confirmPasswordInput = driver.findElement(By.id("inputPasswordRe"));
            WebElement termsInput = driver.findElement(By.id("checkStayLogged"));

            WebElement registerButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("asdf");
            emailInput.sendKeys("test@test.com");
            confirmEmailInput.sendKeys("test@test.com");
            passwordInput.sendKeys("123456");
            confirmPasswordInput.sendKeys("123456");

            if (isClickable(registerButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void registerFormGoodTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.navigate().to("http://localhost:3000/Register");

            WebElement usernameInput = driver.findElement(By.id("inputUsername"));
            WebElement emailInput = driver.findElement(By.id("inputEmail"));
            WebElement confirmEmailInput = driver.findElement(By.id("inputEmailRe"));
            WebElement passwordInput = driver.findElement(By.id("inputPassword"));
            WebElement confirmPasswordInput = driver.findElement(By.id("inputPasswordRe"));
            WebElement termsInput = driver.findElement(By.id("checkStayLogged"));

            WebElement registerButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

            usernameInput.sendKeys("asdf");
            emailInput.sendKeys("test123@test.com");
            confirmEmailInput.sendKeys("test123@test.com");
            passwordInput.sendKeys("123456");
            confirmPasswordInput.sendKeys("123456");
            termsInput.click();

            registerButton.click();

            wait.until(ExpectedConditions.urlToBe("http://localhost:3000/Login"));
        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void auctionFormNoPictureTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            login(driver, wait);

            driver.navigate().to("http://localhost:3000/UploadAuction");

            WebElement auctionNameInput = driver.findElement(By.id("nameAuction"));
            WebElement authorNameInput = driver.findElement(By.id("authorAuction"));
            WebElement endDateInput = driver.findElement(By.id("enddateAuction"));
            WebElement startingPriceInput = driver.findElement(By.id("startpriceAuction"));

            WebElement uploadButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/button[1]"));

            auctionNameInput.sendKeys("name");
            authorNameInput.sendKeys("name");
            startingPriceInput.sendKeys("1000");
            endDateInput.sendKeys("31/01/2030");

            if (isClickable(uploadButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void auctionFormGoodTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            login(driver, wait);

            driver.navigate().to("http://localhost:3000/UploadAuction");

            WebElement imageInput = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
            WebElement auctionNameInput = driver.findElement(By.id("nameAuction"));
            WebElement authorNameInput = driver.findElement(By.id("authorAuction"));
            WebElement endDateInput = driver.findElement(By.id("enddateAuction"));
            WebElement startingPriceInput = driver.findElement(By.id("startpriceAuction"));

            WebElement uploadButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/button[1]"));

            imageInput.sendKeys("C:\\Users\\ikrstev\\Workspace\\auctart\\auctart\\src\\test\\test.jpg");
            auctionNameInput.sendKeys("name");
            authorNameInput.sendKeys("name");
            startingPriceInput.sendKeys("1000");
            endDateInput.sendKeys("31/01/2030");

            uploadButton.click();

            wait.until(ExpectedConditions.urlToBe("http://localhost:3000/Auctions"));
        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void auctionFormNoAuctionNameTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            login(driver, wait);

            driver.navigate().to("http://localhost:3000/UploadAuction");

            WebElement imageInput = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
            WebElement auctionNameInput = driver.findElement(By.id("nameAuction"));
            WebElement authorNameInput = driver.findElement(By.id("authorAuction"));
            WebElement endDateInput = driver.findElement(By.id("enddateAuction"));
            WebElement startingPriceInput = driver.findElement(By.id("startpriceAuction"));

            WebElement uploadButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/button[1]"));

            imageInput.sendKeys("C:\\Users\\ikrstev\\Workspace\\auctart\\auctart\\src\\test\\test.jpg");
            authorNameInput.sendKeys("name");
            startingPriceInput.sendKeys("1000");
            endDateInput.sendKeys("31/01/2030");

            if (isClickable(uploadButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void auctionFormNoAuthorNameTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            login(driver, wait);

            driver.navigate().to("http://localhost:3000/UploadAuction");

            WebElement imageInput = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
            WebElement auctionNameInput = driver.findElement(By.id("nameAuction"));
            WebElement authorNameInput = driver.findElement(By.id("authorAuction"));
            WebElement endDateInput = driver.findElement(By.id("enddateAuction"));
            WebElement startingPriceInput = driver.findElement(By.id("startpriceAuction"));

            WebElement uploadButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/button[1]"));

            imageInput.sendKeys("C:\\Users\\ikrstev\\Workspace\\auctart\\auctart\\src\\test\\test.jpg");
            auctionNameInput.sendKeys("name");
            startingPriceInput.sendKeys("1000");
            endDateInput.sendKeys("31/01/2030");

            if (isClickable(uploadButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void auctionFormBadEndDateTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            login(driver, wait);

            driver.navigate().to("http://localhost:3000/UploadAuction");

            WebElement imageInput = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
            WebElement auctionNameInput = driver.findElement(By.id("nameAuction"));
            WebElement authorNameInput = driver.findElement(By.id("authorAuction"));
            WebElement endDateInput = driver.findElement(By.id("enddateAuction"));
            WebElement startingPriceInput = driver.findElement(By.id("startpriceAuction"));

            WebElement uploadButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/button[1]"));

            imageInput.sendKeys("C:\\Users\\ikrstev\\Workspace\\auctart\\auctart\\src\\test\\test.jpg");
            auctionNameInput.sendKeys("name");
            authorNameInput.sendKeys("name");
            startingPriceInput.sendKeys("1000");
            endDateInput.sendKeys("asdf");

            if (isClickable(uploadButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void auctionFormNoStartingPriceTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            login(driver, wait);

            driver.navigate().to("http://localhost:3000/UploadAuction");

            WebElement imageInput = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
            WebElement auctionNameInput = driver.findElement(By.id("nameAuction"));
            WebElement authorNameInput = driver.findElement(By.id("authorAuction"));
            WebElement endDateInput = driver.findElement(By.id("enddateAuction"));
            WebElement startingPriceInput = driver.findElement(By.id("startpriceAuction"));

            WebElement uploadButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/button[1]"));

            imageInput.sendKeys("C:\\Users\\ikrstev\\Workspace\\auctart\\auctart\\src\\test\\test.jpg");
            auctionNameInput.sendKeys("name");
            authorNameInput.sendKeys("name");
            endDateInput.sendKeys("31/01/2030");

            if (isClickable(uploadButton, driver)) {
                fail();
            }

        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void bidFormGoodTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            login(driver, wait);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/button[1]")));

            WebElement openAuction = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/button[1]"));
            openAuction.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
            WebElement bidInput = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]"));
            WebElement bidButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]"));

            bidInput.sendKeys("1000");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".notifications-wrapper div")));
            bidButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notifications-wrapper div")));
            WebElement notification = driver.findElement(By.className("notifications-wrapper"));

            if(!notification.getText().contains("Success")){
                fail();
            }
        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    @Test
    public void bidFormBadTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            login(driver, wait);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/button[1]")));

            WebElement openAuction = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/button[1]"));
            openAuction.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
            WebElement bidInput = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]"));
            WebElement bidButton = driver.findElement(
                    By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]"));

            bidInput.sendKeys("-100");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".notifications-wrapper div")));
            bidButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notifications-wrapper div")));
            WebElement notification = driver.findElement(By.className("notifications-wrapper"));

            if(!notification.getText().contains("Error")){
                fail();
            }
        } catch (Exception e) {
            fail();
        } finally {
            driver.close();
        }
    }

    private static boolean isClickable(WebElement el, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void login (WebDriver driver, WebDriverWait wait){
        driver.navigate().to("http://localhost:3000/Login");

        WebElement usernameInput = driver.findElement(By.id("inputUsername"));
        WebElement passwordInput = driver.findElement(By.id("inputPassword"));

        WebElement logInButton = driver.findElement(
                By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

        usernameInput.sendKeys("test");
        passwordInput.sendKeys("123456");

        logInButton.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/Auctions"));
    }

}
