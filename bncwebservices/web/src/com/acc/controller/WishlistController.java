/**
 *
 */
package com.acc.controller;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.wishlist2.Wishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

import com.acc.product.data.ProductDataList;


/**
 * @author swarnima.gupta
 *
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/wishlist")
public class WishlistController extends BaseController
{
	private static final String ADD_TO_WISHLIST_FUNCTIONALITY = "add to wishlist functionality";
	private static final String WISHLIST = "wishlist";
	private static final String UID = "uid";

	private static final Logger LOG = Logger.getLogger(WishlistController.class);

	@Autowired
	private Wishlist2Service wishlistService;
	@Autowired
	private UserService userService;
	@Autowired
	private Converter<ProductModel, ProductData> productConverter;

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public ProductDataList getWishlistProducts(final HttpServletRequest request) throws IOException, ParseException
	{
		LOG.info("::::::: in saveCustomerHeathData GET request method :::::::");
		final StringBuffer sbuf = getJsonBodyString(request);
		LOG.info("::::::: json object string is :::::::" + sbuf);
		final ProductDataList productDataList = new ProductDataList();
		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final String uid = String.valueOf(obj.get(UID));
			final UserModel userModel = userService.getUserForUID(uid);

			if (wishlistService.hasDefaultWishlist(userModel))
			{
				final Wishlist2Model wishLists = wishlistService.getDefaultWishlist(userModel);
				final List<ProductData> productsData = new ArrayList<ProductData>();
				final List<Wishlist2EntryModel> entries = wishLists.getEntries();
				for (final Wishlist2EntryModel entry : entries)
				{
					productsData.add(productConverter.convert(entry.getProduct()));
				}
				productDataList.setProducts(productsData);
			}
			else
			{
				wishlistService.createDefaultWishlist(userModel, WISHLIST, ADD_TO_WISHLIST_FUNCTIONALITY);
			}
		}
		return productDataList;
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
