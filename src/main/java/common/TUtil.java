package common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.yaml.snakeyaml.Yaml;

public class TUtil {

	/**
	 * 要素取得
	 * 
	 * @param id
	 *            id属性値
	 * @param driver
	 *            driver
	 * @param wait
	 *            wait
	 * @return 要素
	 */
	public static WebElement findElement(String id, WebDriver driver, Wait<WebDriver> wait) {
		return driver.findElement(By.id(id));
	}

	/**
	 * 
	 * @param className
	 * @param driver
	 * @param wait
	 * @return
	 */
	public static WebElement findElementByClassName(String className, WebDriver driver, Wait<WebDriver> wait) {
		return driver.findElement(By.className(className));
	}

	/**
	 * 
	 * @param xPath
	 * @param driver
	 * @param wait
	 * @return
	 */
	public static WebElement findElementByXPath(String xPath, WebDriver driver, Wait<WebDriver> wait) {
		return driver.findElement(By.xpath(xPath));
	}

	/**
	 * Link(Anchor) クリック
	 * 
	 * @param id
	 *            id属性値
	 * @param driver
	 *            driver
	 * @param wait
	 *            wait
	 */
	public static void clickLink(String id, WebDriver driver, Wait<WebDriver> wait) {
		clickButton(id, driver, wait);
	}

	/**
	 * button クリック
	 * 
	 * @param id
	 *            id属性値
	 * @param driver
	 *            driver
	 * @param wait
	 *            wait
	 */
	public static void clickButton(String id, WebDriver driver, Wait<WebDriver> wait) {
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
	}

	/**
	 * 値を入力する.
	 * 
	 * @param name
	 *            name属性値
	 * @param value
	 *            設定値
	 * @param driver
	 *            driver
	 * @param wait
	 *            wait
	 */
	public static void setValByName(String name, String value, WebDriver driver, Wait<WebDriver> wait) {
		WebElement elm = driver.findElement(By.name(name));
		elm.clear();
		elm.sendKeys(value);
	}

	/**
	 * 値取得
	 * 
	 * @param id
	 *            id属性値
	 * @param driver
	 *            driver
	 * @return
	 */
	public static String getVal(String id, WebDriver driver, Wait<WebDriver> wait) {
		return driver.findElement(By.id(id)).getText();
	}

	/**
	 * 値取得
	 * 
	 * @param name
	 *            name属性値
	 * @param driver
	 *            driver
	 * @return
	 */
	public static String getValByName(String name, WebDriver driver, Wait<WebDriver> wait) {
		return driver.findElement(By.name(name)).getText();
	}

	/**
	 * submit
	 * 
	 * @param name
	 *            name属性値
	 * @param driver
	 *            driver
	 * @param wait
	 *            wait
	 */
	public static void submitByName(String name, WebDriver driver, Wait<WebDriver> wait) {
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
		button.submit();
	}

	/**
	 * submit
	 * 
	 * @param id
	 *            id属性値
	 * @param driver
	 *            driver
	 * @param wait
	 *            wait
	 */
	public static void submit(String id, WebDriver driver, Wait<WebDriver> wait) {
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		button.submit();
	}

	/**
	 * submit
	 * 
	 * @param className
	 *            class属性値
	 * @param driver
	 *            driver
	 * @param wait
	 *            wait
	 */
	public static void submitByClassName(String className, WebDriver driver, Wait<WebDriver> wait) {
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));
		button.submit();
	}

	/**
	 * アクションを実行するURLを取得.
	 * 
	 * @param action
	 *            Actionのメソッド名
	 * @return URL
	 */
	public static String getUrl(String action) {
		return Const.BASE_URL + action;
	}

	/**
	 * form の値を画面に入力する.
	 * 
	 * <pre>
	 * formクラスの field名に対応する画面項目に値を設定する。
	 * 画面項目はname属性がfield名と一致するものが対象となる。
	 * nullのフィールドは無視する（画面上で値を上書きしない）。
	 * </pre>
	 * 
	 * @param form
	 *            form
	 * @param driver
	 *            driver
	 * @param wait
	 *            wait
	 */
	@Deprecated
	public static void setFormValues(Object form, WebDriver driver, Wait<WebDriver> wait) {
		for (Field field : form.getClass().getDeclaredFields()) {
			try {
				String name = field.getName();
				Object _field = field.get(form);
				if (_field == null) {
					continue;
				}
				setValByName(name, _field.toString(), driver, wait);
			} catch (IllegalArgumentException e) {
				e.getMessage();
			} catch (IllegalAccessException e) {
				e.getMessage();
			} catch (NoSuchElementException e) {
				// 画面に対応する要素がない場合でもエラーとはしない。
				e.getMessage();
			}
		}
	}

	/**
	 * 停止.
	 * 
	 * @param time
	 *            停止時間 単位:ミリ秒
	 */
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 3秒停止.
	 */
	public static void sleep() {
		sleep(3000);
	}

	private static Yaml yaml = new Yaml();

	/**
	 * yaml load
	 * 
	 * @param class1
	 * @param path
	 * @return
	 */
	public static <T> T loadDataYaml(Class<T> class1, String path) {
		return (T) yaml.loadAs(ClassLoader.getSystemResourceAsStream(path), class1);
	}

	/**
	 * yaml load all
	 * 
	 * @param formList
	 * @param path
	 * @return
	 */
	public static <T> List<T> loadDataYamlAll(List<T> formList, String path) {
		Iterable<Object> allData = yaml.loadAll(ClassLoader.getSystemResourceAsStream(path));
		for (Object data : allData) {
			// TODO うまく T クラスに cast できない。
			Object form = (T) new Object();
			Map<String, Object> map = (Map<String, Object>) data;
			try {
				BeanUtils.populate(form, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				System.out.println(e.getMessage());
			}
			formList.add((T) form);
		}
		return formList;
	}
}
