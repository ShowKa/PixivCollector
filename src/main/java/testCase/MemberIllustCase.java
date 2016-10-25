package testCase;

import java.util.HashMap;

import common.TUtil;
import page.LoginPage;
import page.MemberIllustPage;

public class MemberIllustCase extends TestCaseBase {

	private String memberId = "974003";

	public void testCase001() {
		// login
		driver.get(LoginPage.url);
		LoginPage loginPage = new LoginPage(driver);

		@SuppressWarnings("unchecked")
		HashMap<String, String> user = TUtil.loadDataYaml(HashMap.class, "user.yaml");

		loginPage.login(user.get("id"), user.get("password"));

		TUtil.sleep();

		String url = MemberIllustPage.createURL(memberId, 1);
		driver.get(url);
		MemberIllustPage illustPage = new MemberIllustPage(driver);
		illustPage.clickThumbnail(1);

		TUtil.sleep();
	}
}
