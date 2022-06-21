package com.learn.test.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Test1 {

	public static void main(String[] args) throws ClientProtocolException, IOException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException {

		String URI_DEV = "";
		String URI_PROD = "";

		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				builder.build());

		CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(
				sslsf).build();

		HttpPost httpPost = new HttpPost(URI_PROD);

		JsonObject body = new JsonObject();
		JsonObject jsonObject = new JsonObject();

		jsonObject.addProperty("userIdentity", "66875003567");
		jsonObject.addProperty("userIdentityType", 0);
		jsonObject.addProperty("EventID", 10133);
		jsonObject.addProperty("OrigPlatformID", "OMR");
		jsonObject.addProperty("OrigPlatformNode", "OMR");

		JsonArray parameter = new JsonArray();
		JsonObject param = new JsonObject();
		param.addProperty("ParameterType", "10550");
		param.addProperty("ParameterValue", "4");
		parameter.add(param);
		jsonObject.add("Parameter", parameter);
		body.add("EventIndication", jsonObject);

		System.out.println(body);

		StringEntity entity = new StringEntity(body.toString());
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-type", "application/json");

		CloseableHttpResponse response = client.execute(httpPost);

		System.out.println(response.getStatusLine().getStatusCode());

	}

}
