package com.yidatec.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClientHelper {

	protected static final Log logger = LogFactory
			.getLog(HttpClientHelper.class);

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			return httpClient;

		} catch(Exception ex){
			logger.error(ex);
		}
		return HttpClients.createDefault();
	}

	public static String callTrustHttps(String url, String reqBody) {
		try {
			CloseableHttpClient httpClient = createSSLClientDefault();
			HttpPost post = new HttpPost(url);
			if (reqBody != null) {
				
				StringEntity reqEntity = new StringEntity(reqBody,"UTF-8");

				post.setEntity(reqEntity);
			}
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity,"UTF-8")
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			String responseBody = httpClient.execute(post, responseHandler);
			if(responseBody.contains("errcode")){
				logger.error("request url : "+url);
				logger.error("request method : post");
				logger.error("request body : "+reqBody);
				logger.error("response body : "+responseBody);
			}
			//System.out.println(responseBody);
			return responseBody;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public static String getTrustHttps(String url) {
		try {
			CloseableHttpClient httpClient = createSSLClientDefault();
			HttpGet get = new HttpGet(url);
//			if (reqBody != null) {
//
//				StringEntity reqEntity = new StringEntity(reqBody,"UTF-8");
//
//
//				get..setEntity(reqEntity);
//			}
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			String responseBody = httpClient.execute(get, responseHandler);

			if(!responseBody.contains("ok")){
				logger.error("request url : "+url);
				logger.error("request method : get");
				logger.error("response body : "+responseBody);
			}
			return responseBody;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public static String callTrustHttps(String url) {
		return callTrustHttps(url, null);
	}
	
	/**
	 * 下载二进制文件
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException
	 */
	public static byte[] downloadFile(String url) throws Exception {
		
		byte[] downloadData = null;
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {  
            HttpGet httpGet = new HttpGet(url);
            
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
            	
            	if (response1.getStatusLine().getStatusCode() != 200) {
            		// 响应失败
            		return null;
            	}
                HttpEntity entity1 = response1.getEntity();
                if (entity1 != null) {  
					  
					byte[] _tmp = new byte[1024];  
					int count = 0;  
					InputStream in = response1.getEntity().getContent();  
					while((count = in.read(_tmp, 0 , 1024)) != -1)  
						out.write(_tmp, 0, count);
					
					downloadData = out.toByteArray();
					_tmp = null;  
                } 
                // 销毁响应实体,释放内存
                EntityUtils.consume(entity1);
            } finally {  
            	out.close();
                response1.close();  
            }  
		} finally {  
            httpclient.close();  
        } 
		
		
		return downloadData;
	}
	
}
