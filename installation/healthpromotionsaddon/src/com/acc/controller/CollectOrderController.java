/**
 *
 */
package com.acc.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.data.CollectOrderDataList;
import com.acc.services.CustomerCollectOrderService;


/**
 * @author swarnima.gupta
 * 
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/collectorders")
public class CollectOrderController
{
	private static final Logger LOG = Logger.getLogger(CollectOrderController.class);

	@Autowired
	private CustomerCollectOrderService collectOrderService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public CollectOrderDataList getCustomerCollectOrders(final HttpServletRequest request) throws UnsupportedEncodingException,
			IOException, ParseException
	{
		LOG.info("::::::: in getCustomerCollectOrders GET request method :::::::");
		final StringBuffer sbuf = getJsonBodyString(request);
		LOG.info("::::::: json object string is :::::::" + sbuf);
		final CollectOrderDataList collectOrdersList = new CollectOrderDataList();
		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final String customerId = String.valueOf(obj.get("uid"));
			if (StringUtils.isNotEmpty(customerId))
			{
				collectOrdersList.setCollectOrders(collectOrderService.getCustomerListOrders(customerId));
			}
		}
		return collectOrdersList;
	}

	/**
	 * @param request
	 * @return StringBuffer
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private StringBuffer getJsonBodyString(final HttpServletRequest request) throws IOException, UnsupportedEncodingException
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
}
