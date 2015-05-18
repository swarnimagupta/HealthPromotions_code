/**
 *
 */
package com.acc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**
 * @author swarnima.gupta
 *
 */
public class WebservicesUtil
{

	/**
	 * @param request
	 * @return StringBuffer
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public StringBuffer getJsonBodyString(final HttpServletRequest request) throws IOException, UnsupportedEncodingException
	{
		final ServletInputStream input = request.getInputStream();
		final byte[] buf = new byte[201];
		final StringBuffer sbuf = new StringBuffer();
		int result;
		do
		{
			result = input.readLine(buf, 0, buf.length);
			if (result != -1)
			{
				sbuf.append(new String(buf, 0, result, "UTF-8"));
			}
			else
			{
				break;
			}
		}
		while (result == buf.length);
		return sbuf;
	}

	/**
	 * @param url
	 * @return HttpURLConnection
	 * @throws IOException
	 */
	public HttpURLConnection getHttpConnection(final URL url) throws IOException
	{
		final URLConnection urlConnection = url.openConnection();
		HttpURLConnection connection = null;
		if (urlConnection instanceof HttpURLConnection)
		{
			connection = (HttpURLConnection) urlConnection;
		}
		else
		{
			// "::::::::::::::::::::::::::::::URL not an http URL::::::::::::::::::::::::::::::::"
		}
		return connection;
	}

	/**
	 * @param connection
	 * @return String
	 * @throws IOException
	 */
	public String getJsonDataString(final HttpURLConnection connection) throws IOException
	{
		final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String jsonData = StringUtils.EMPTY;
		String line = StringUtils.EMPTY;
		while ((line = in.readLine()) != null)
		{
			jsonData += line + "\n";
		}
		in.close();
		return jsonData;
	}
}
