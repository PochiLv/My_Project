package com.pochi.selenium;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PPS_sent implements Runnable {
	// webdriver是Selenium有用
	private WebDriver driver;
	// anchor_url是get地址有用
	private String anchor_url;

	public PPS_sent(String anchor_url) {
		// 在初始化的时候就判断一下，地址是直接输的数字还是，完整url，亦或是乱输
		try {
			Integer.parseInt(anchor_url);
			this.anchor_url = "http://x.pps.tv/room/" + anchor_url;
		} catch (Exception e) {
			if (anchor_url.startsWith("http://x.pps.tv/room/")) {
				this.anchor_url = anchor_url;
			} else {
				throw new RuntimeException("别瞎jb乱写");
			}
		}
	}

	public void run() {
		// 由于我的firefox不是放在指定的位置的，所以这个也得要设置一下
		System.setProperty("webdriver.firefox.bin",
				"F:\\火狐浏览器 Firefox 20.0.1 独木成林增强版\\火狐浏览器 Firefox 20.0.1 独木成林增强版\\Mozilla\\Firefox\\firefox.exe");
		// 打开浏览器
		driver = new FirefoxDriver();
		// 输入网址
		driver.get(anchor_url);
		// driver.get("http://www.showself.com");
		// 这条其实没有人会看到
		System.out.println("开始登陆……限时40秒……");
		try {
			// 等待40s，让别人输账号、密码登录
			Thread.sleep(40000);
			while (true) {
				// 发消息
				sentMsg();
				// 送星光
				sentStart();
				// 送礼物
				sentGift();
				// 每过15分钟走个循环
				Thread.sleep(1000*60*15);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void sentGift() throws InterruptedException {
		// 点击大白
		driver.findElement(By.cssSelector("img[alt=\"大白白\"]")).click();
		// 点击赠送
		driver.findElement(By.linkText("赠送")).click();
		// 等待2s
		Thread.sleep(2000);
	}

	private void sentStart() throws InterruptedException {
		// 点击星光
		driver.findElement(By.cssSelector("a.free-gift")).click();
		// 点击1
		driver.findElement(By.xpath("//li[3]/a/strong")).click();
		driver.findElement(By.xpath("//li[3]/a/strong")).click();
		// 把星光关掉，为了下次再点
		driver.findElement(By.cssSelector("a.free-gift")).click();
		// 等待5s
		Thread.sleep(5000);
	}

	public void sentMsg() throws InterruptedException {
		// 得到房间title
		String title = driver.getTitle();
		// 这部分本来是要按照不同的主播名字，然后说话的，现在也没这个需求了
		int start_index = title.indexOf("直播间");
		String anchorName = title.substring(start_index - 3, start_index);
		// 这个也是调试代码，真正的是看不到的
		System.out.println("开始发送信息...");
		// 从这个数组里面随机拿出东西发送
		String[] str_arr = { "与其临渊羡鱼，不如退而结网。", "花开堪折直须折，莫待无花空折枝。", "人生若只如初见，何事秋风悲画扇。 等闲变却故人心，却道故人心易变。 ",
				"人生自是有情痴，此恨不关风与月。", "曾虑多情损梵行，入山又恐别倾城，世间安得双全法，不负如来不负卿。", "浮生如此，别多会少，不如莫遇。",
				"最好不相见，如此便可不相恋。 最好不相知，如此便可不相思。", "么么哒！", "一直陪你", "[开心]", "[色色]", "[呆]", "[哭]", "[大笑]", "[傻乐]", "[可怜]",
				"[无语]", "好多年了，你一直在我的伤口中幽居，我放下过天地，却从未放下过你，我生命中的千山万水，任你一一告别。", "笑那浮华落尽 月色如洗 笑那悄然而逝 飞花万盏", "[汗]", "[抓狂]",
				"[亲亲]", "闻君有二意，故来相决绝。愿得一心人，白首不相离", "[落寞]", "[囧]", "死生契阔，与子成说。执子之手，与子偕老。", "[抠鼻]", "[闭嘴]", "[挑眉]",
				"[惊恐]", "[晕]", "[惊讶]", "[再见]" };
		// 弄一个随机对象
		Random random = new Random();
		// 这两个变量是为了前后两句话不一致
		int old_num = 0;
		int new_num = 0;

		for (int i = 0; i < 1; i++) {
			// 得到一个新的随机脚标
			new_num = random.nextInt(str_arr.length);
			// 如果这个脚标和老的一样的话就重新选一个
			while (old_num == new_num) {
				new_num = random.nextInt(str_arr.length);
			}
			// 不一样的话，把新的赋给老的
			old_num = new_num;
			// 把奇秀对话框的东西删掉
			driver.findElement(By.cssSelector("div.say-input > input[type=\"text\"]")).clear();
			// 带名字的发送语句
			// driver.findElement(By.cssSelector("div.say-input >
			// input[type=\"text\"]"))
			// .sendKeys(anchorName + str_arr[old_num]);
			// 不带主播名字发送语句
			driver.findElement(By.cssSelector("div.say-input > input[type=\"text\"]")).sendKeys(str_arr[old_num]);
			// 发送消息
			driver.findElement(By.linkText("发言")).click();

			//Thread.sleep(60000);
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

	}

	public PPS_sent() {
		super();
	}
}
