package com.lml.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpRequest {
	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return URL ������Զ����Դ����Ӧ���
	 */
	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line + "\\r\\n";
			}
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ��ָ�� URL ����POST����������
	 * 
	 * @param url
	 *            ��������� URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return ������Զ����Դ����Ӧ���
	 * @throws IOException 
	 */
	public static String sendPost(String url, String bodyMsg) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(bodyMsg));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			/*
			 * System.out.println(response2.getStatusLine()); HttpEntity entity2
			 * = response2.getEntity(); InputStream content =
			 * entity2.getContent(); // do something useful with the response
			 * body // and ensure it is fully consumed
			 * EntityUtils.consume(entity2);
			 */
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println("result:" + result);
			return result.toString();
		} finally {
			response.close();
		}
	}

	/**
	 * Put ����
	 */
	public static String sendPut(String url, String bodyMsg) throws Exception {
		/*
		 * HttpClient client = HttpClientBuilder.create().build(); HttpPut put =
		 * new HttpPut(url); put.setHeader("Content-type", "application/json");
		 * 
		 * StringEntity params = new StringEntity(bodyMsg);
		 * put.setEntity(params);
		 * 
		 * HttpResponse response = client.execute(put); System.out.println(
		 * "Response Code:" + response.getStatusLine().getStatusCode());
		 * BufferedReader rd = new BufferedReader(new
		 * InputStreamReader(response.getEntity().getContent()));
		 * 
		 * StringBuffer result = new StringBuffer(); String line = ""; while
		 * ((line = rd.readLine()) != null) { result.append(line); }
		 * System.out.println("result:" + result);
		 */
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut put = new HttpPut(url);
		put.setEntity(new StringEntity(bodyMsg));
		CloseableHttpResponse response = httpclient.execute(put);
		try {
			/*
			 * System.out.println(response2.getStatusLine()); HttpEntity entity2
			 * = response2.getEntity(); InputStream content =
			 * entity2.getContent(); // do something useful with the response
			 * body // and ensure it is fully consumed
			 * EntityUtils.consume(entity2);
			 */
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println("result:" + result);
			return result.toString();
		} finally {
			response.close();
		}
	}
}
