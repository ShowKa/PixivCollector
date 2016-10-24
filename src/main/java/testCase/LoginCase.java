package testCase;

import java.util.HashMap;

import common.Const;
import common.TUtil;
import page.LoginPage;

public class LoginCase extends TestCaseBase {

	public void testCase001() {
		// login
		driver.get(LoginPage.url);
		LoginPage loginPage = new LoginPage(driver);

		@SuppressWarnings("unchecked")
		HashMap<String, String> user = TUtil.loadDataYaml(HashMap.class, "user.yaml");

		loginPage.login(user.get("id"), user.get("password"));

		// test
		assertEquals(Const.id, getVal("userId"));
	}
}
