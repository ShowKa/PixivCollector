package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MemberIllustPage extends PageBase {

	public static String URL = "http://www.pixiv.net/member_illust.php";

	public static String createURL(String memberId, int page) {
		return URL + "?id=" + memberId + "&type=all&p=" + String.valueOf(page);
	}

	public static String createIllustURL(String illustId) {
		return URL + "?mode=medium&illust_id=" + illustId;
	}

	public MemberIllustPage(WebDriver driver) {
		super(driver);
	}

	public void clickThumbnail(int index) {
		WebElement element = findElementByXPath("(//img[@class='_thumbnail'])[" + index + "]");
		element.click();
	}

	public void clickLayoutThumbnail() {
		WebElement element = findElementByXPath("//div[@class='_layout-thumbnail ui-modal-trigger']");
		element.click();
	}

	public String getOriginalIllustSrc() {
		WebElement element = findElementByXPath("//img[@class='original-image']");
		return element.getAttribute("src");
	}
}
