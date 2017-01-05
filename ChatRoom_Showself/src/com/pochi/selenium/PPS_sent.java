package com.pochi.selenium;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PPS_sent implements Runnable {
	// webdriver��Selenium����
	private WebDriver driver;
	// anchor_url��get��ַ����
	private String anchor_url;

	public PPS_sent(String anchor_url) {
		// �ڳ�ʼ����ʱ����ж�һ�£���ַ��ֱ��������ֻ��ǣ�����url�����������
		try {
			Integer.parseInt(anchor_url);
			this.anchor_url = "http://x.pps.tv/room/" + anchor_url;
		} catch (Exception e) {
			if (anchor_url.startsWith("http://x.pps.tv/room/")) {
				this.anchor_url = anchor_url;
			} else {
				throw new RuntimeException("��Ϲjb��д");
			}
		}
	}

	public void run() {
		// �����ҵ�firefox���Ƿ���ָ����λ�õģ��������Ҳ��Ҫ����һ��
		System.setProperty("webdriver.firefox.bin",
				"F:\\�������� Firefox 20.0.1 ��ľ������ǿ��\\�������� Firefox 20.0.1 ��ľ������ǿ��\\Mozilla\\Firefox\\firefox.exe");
		// �������
		driver = new FirefoxDriver();
		// ������ַ
		driver.get(anchor_url);
		// driver.get("http://www.showself.com");
		// ������ʵû���˻ῴ��
		System.out.println("��ʼ��½������ʱ40�롭��");
		try {
			// �ȴ�40s���ñ������˺š������¼
			Thread.sleep(40000);
			while (true) {
				// ����Ϣ
				sentMsg();
				// ���ǹ�
				sentStart();
				// ������
				sentGift();
				// ÿ��15�����߸�ѭ��
				Thread.sleep(1000*60*15);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void sentGift() throws InterruptedException {
		// ������
		driver.findElement(By.cssSelector("img[alt=\"��װ�\"]")).click();
		// �������
		driver.findElement(By.linkText("����")).click();
		// �ȴ�2s
		Thread.sleep(2000);
	}

	private void sentStart() throws InterruptedException {
		// ����ǹ�
		driver.findElement(By.cssSelector("a.free-gift")).click();
		// ���1
		driver.findElement(By.xpath("//li[3]/a/strong")).click();
		driver.findElement(By.xpath("//li[3]/a/strong")).click();
		// ���ǹ�ص���Ϊ���´��ٵ�
		driver.findElement(By.cssSelector("a.free-gift")).click();
		// �ȴ�5s
		Thread.sleep(5000);
	}

	public void sentMsg() throws InterruptedException {
		// �õ�����title
		String title = driver.getTitle();
		// �ⲿ�ֱ�����Ҫ���ղ�ͬ���������֣�Ȼ��˵���ģ�����Ҳû���������
		int start_index = title.indexOf("ֱ����");
		String anchorName = title.substring(start_index - 3, start_index);
		// ���Ҳ�ǵ��Դ��룬�������ǿ�������
		System.out.println("��ʼ������Ϣ...");
		// �����������������ó���������
		String[] str_arr = { "������Ԩ���㣬�����˶�������", "��������ֱ���ۣ�Ī���޻�����֦��", "������ֻ�������������籯���ȡ� ���б�ȴ�����ģ�ȴ���������ױ䡣 ",
				"������������գ��˺޲��ط����¡�", "���Ƕ��������У���ɽ�ֱֿ���ǣ����䰲��˫ȫ�����������������䡣", "������ˣ������٣�����Ī����",
				"��ò��������˱�ɲ������� ��ò���֪����˱�ɲ���˼��", "ôô�գ�", "һֱ����", "[����]", "[ɫɫ]", "[��]", "[��]", "[��Ц]", "[ɵ��]", "[����]",
				"[����]", "�ö����ˣ���һֱ���ҵ��˿����ľӣ��ҷ��¹���أ�ȴ��δ���¹��㣬�������е�ǧɽ��ˮ������һһ���", "Ц�Ǹ����価 ��ɫ��ϴ Ц����Ȼ���� �ɻ���յ", "[��]", "[ץ��]",
				"[����]", "�ž��ж��⣬�����������Ը��һ���ˣ����ײ�����", "[��į]", "[��]", "�������������ӳ�˵��ִ��֮�֣��������ϡ�", "[�ٱ�]", "[����]", "[��ü]",
				"[����]", "[��]", "[����]", "[�ټ�]" };
		// Ūһ���������
		Random random = new Random();
		// ������������Ϊ��ǰ�����仰��һ��
		int old_num = 0;
		int new_num = 0;

		for (int i = 0; i < 1; i++) {
			// �õ�һ���µ�����ű�
			new_num = random.nextInt(str_arr.length);
			// �������ű���ϵ�һ���Ļ�������ѡһ��
			while (old_num == new_num) {
				new_num = random.nextInt(str_arr.length);
			}
			// ��һ���Ļ������µĸ����ϵ�
			old_num = new_num;
			// ������Ի���Ķ���ɾ��
			driver.findElement(By.cssSelector("div.say-input > input[type=\"text\"]")).clear();
			// �����ֵķ������
			// driver.findElement(By.cssSelector("div.say-input >
			// input[type=\"text\"]"))
			// .sendKeys(anchorName + str_arr[old_num]);
			// �����������ַ������
			driver.findElement(By.cssSelector("div.say-input > input[type=\"text\"]")).sendKeys(str_arr[old_num]);
			// ������Ϣ
			driver.findElement(By.linkText("����")).click();

			//Thread.sleep(60000);
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

	}

	public PPS_sent() {
		super();
	}
}
