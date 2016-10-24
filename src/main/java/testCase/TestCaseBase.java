package testCase;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Const;
import common.TUtil;
import junit.framework.TestCase;

public abstract class TestCaseBase extends TestCase {

	private static final long WAIT_TIMEOUT = Const.default_wait_time;

	public WebDriver driver;

	public Wait<WebDriver> wait;

	public interface WebDriverFactory {
		public WebDriver create();
	}

	public WebDriverFactory getDriverFactories() {
		return new WebDriverFactory() {
			@Override
			public WebDriver create() {
				System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
				return new FirefoxDriver();
			}
		};
	}

	/**
	 * 前準備.
	 * 
	 * <pre>
	 * WebDriver を生成。
	 * </pre>
	 */
	protected void setUp() {
		driver = getDriverFactories().create();
		wait = new WebDriverWait(driver, WAIT_TIMEOUT);
	}

	/**
	 * 後処理.
	 * 
	 * <pre>
	 * エビデンスを残す
	 * </pre>
	 */
	protected void tearDown() {
		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File("evidence/" + getClass().getSimpleName() + "/" + getName() + ".png"));
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	/**
	 * 値取得
	 * 
	 * @param id
	 *            id属性値
	 * @return 値
	 */
	protected String getVal(String id) {
		return TUtil.getVal(id, driver, wait);
	}

	/**
	 * 値取得
	 * 
	 * @param name
	 *            name属性値
	 * @return 値
	 */
	protected String getValByName(String name) {
		return TUtil.getValByName(name, driver, wait);
	}
}
