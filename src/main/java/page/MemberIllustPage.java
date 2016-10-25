package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.TUtil;

public class MemberIllustPage extends PageBase {

	public static String URL = "http://www.pixiv.net/member_illust.php";

	public static String createURL(String memberId, int page) {
		return URL + "?id=" + memberId + "&type=all&p=" + String.valueOf(page);
	}

	public MemberIllustPage(WebDriver driver) {
		super(driver);
	}

	public void clickThumbnail(int index) {
		WebElement element = findElementByXPath("(//img[@class='_thumbnail'])[" + index + "]");
		element.click();
		TUtil.sleep();
	}

}
