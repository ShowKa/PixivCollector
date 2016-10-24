package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.TUtil;

public class LoginPage extends PageBase {

	public static String url = "https://accounts.pixiv.net/login?lang=ja&source=pc&view_type=page&ref=wwwtop_accounts_index";

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String id, String password) {

		WebElement container = findElement("container-login");

		// ユーザーID入力
		WebElement userIdElement = container.findElement(By.xpath(".//input[@type='text']"));
		userIdElement.clear();
		userIdElement.sendKeys(id);

		// パスワード入力
		WebElement passwordElement = container.findElement(By.xpath(".//input[@type='password']"));
		passwordElement.clear();
		passwordElement.sendKeys(password);

		// login!!
		WebElement button = container.findElement(By.xpath(".//button"));
		button.click();
	}
}
