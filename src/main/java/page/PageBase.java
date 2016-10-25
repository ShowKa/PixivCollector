package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Const;
import common.SetFormUtil;
import common.TUtil;

public abstract class PageBase {

	protected WebDriver driver;

	protected Wait<WebDriver> wait;

	public PageBase(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Const.default_wait_time);
	}

	public WebElement findElement(String id) {
		return TUtil.findElement(id, driver, wait);
	}

	public WebElement findElementByClass(String className) {
		return TUtil.findElementByClassName(className, driver, wait);
	}

	public WebElement findElementByXPath(String xPath) {
		return TUtil.findElementByXPath(xPath, driver, wait);
	}

	public void clickLink(String id) {
		TUtil.clickLink(id, driver, wait);
	}

	public void clickButton(String id) {
		TUtil.clickButton(id, driver, wait);
	}

	public String getVal(String id) {
		return TUtil.getVal(id, driver, wait);
	}

	public String getValByName(String name) {
		return TUtil.getValByName(name, driver, wait);
	}

	public void setValByName(String name, String value) {
		TUtil.setValByName(name, value, driver, wait);
	}

	public void submitByName(String name) {
		TUtil.submitByName(name, driver, wait);
	}

	public void submit(String id) {
		TUtil.submit(id, driver, wait);
	}

	public void submitByClassName(String className) {
		TUtil.submitByClassName(className, driver, wait);
	}

	public void setFormValues(Object form) {
		SetFormUtil.set(form).exec(driver, wait);
	}

	public void setFormValues(Object form, String... includes) {
		SetFormUtil.set(form).includes(includes).exec(driver, wait);
	}

	public void setFormValuesExcludes(Object form, String... excludes) {
		SetFormUtil.set(form).excludes(excludes).exec(driver, wait);
	}
}
