package testCase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Cookie;

import common.TUtil;
import page.LoginPage;
import page.MemberIllustPage;

public class MemberIllustCase extends TestCaseBase {

	private String memberId = "974003";

	public void testCase001() throws Exception {
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
		TUtil.sleep(1000);
		illustPage.clickLayoutThumbnail();
		TUtil.sleep(1000);

		String src = illustPage.getOriginalIllustSrc();
		Set<Cookie> cookies = driver.manage().getCookies();
		System.out.println(cookies);
		System.out.println(src);

		TUtil.sleep(1000);
		driver.get(src);
		// String cookieString = "Set-Cookie: " +
		// cookies.toString().replaceAll("^\\[", "").replaceAll("\\]$", "") +
		// ";";

		// String cookieString = "Set-Cookie: ";
		String cookieString = "";
		Iterator<Cookie> cookie = cookies.iterator();
		while (cookie.hasNext()) {
			Cookie c = cookie.next();
			cookieString = cookieString + c.getName() + "=" + c.getValue() + ";";
		}
		System.out.println(cookieString);
		downloadFileFromURL(src, cookieString, "test.jpg");
	}

	public static boolean downloadFileFromURL(String fetchUrl, String cookie, String savePathAndFilename)
			throws IOException, FileNotFoundException, IOException {

		HttpURLConnection c;

		// save file
		URL url = new URL(fetchUrl);
		c = (HttpURLConnection) url.openConnection();

		// set cache and request method settings
		c.setUseCaches(true);
		c.setDoOutput(true);

		// set other headers
		c.setRequestProperty("Content-Type", "image/jpeg");
		c.setRequestProperty("Cookie", cookie);

		// connect
		c.connect();

		BufferedInputStream in = new BufferedInputStream(c.getInputStream());

		OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(savePathAndFilename)));
		byte[] buf = new byte[256];
		int n = 0;
		while ((n = in.read(buf)) >= 0) {
			out.write(buf, 0, n);
		}
		out.flush();
		out.close();

		return true;
	}
}
