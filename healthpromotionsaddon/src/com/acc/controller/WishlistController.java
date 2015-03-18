/**
 *
 */
package com.acc.controller;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.wishlist2.Wishlist2Service;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
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
@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/wishlist")
public class WishlistController
{
	private static final String PRODUCT_ID = "productId";
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
	@Autowired
	private ProductService productService;
	@Autowired
	private CatalogService catalogService;

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public ProductDataList getWishlistProducts(final HttpServletRequest request) throws IOException, ParseException
	{
		catalogService.setSessionCatalogVersion("electronicsProductCatalog", "Online");
		LOG.info("::::::: in getWishlistProducts POST request method :::::::");
		final StringBuffer sbuf = getJsonBodyString(request);
		LOG.info("::::::: json object string is :::::::" + sbuf);
		final ProductDataList productDataList = new ProductDataList();
		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final String uid = String.valueOf(obj.get(UID));
			final UserModel userModel = userService.getUserForUID(uid);
			final Wishlist2Model wishList = wishlistService.hasDefaultWishlist(userModel) ? wishlistService
					.getDefaultWishlist(userModel) : wishlistService.createDefaultWishlist(userModel, WISHLIST,
					ADD_TO_WISHLIST_FUNCTIONALITY);
			if (CollectionUtils.isNotEmpty(wishList.getEntries()))
			{
				productDataList.setProducts(getWishlistProductsList(wishList));
				LOG.info("get all wishlist product" + productDataList.getProducts());
			}
		}
		return productDataList;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProductDataList addWishlistProducts(final HttpServletRequest request) throws IOException, ParseException
	{
		catalogService.setSessionCatalogVersion("electronicsProductCatalog", "Online");
		LOG.info("::::::: in addWishlistProducts POST request method :::::::");
		final StringBuffer sbuf = getJsonBodyString(request);
		LOG.info("::::::: json object string is :::::::" + sbuf);
		final ProductDataList productDataList = new ProductDataList();
		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final ProductModel productModel = productService.getProductForCode(String.valueOf(obj.get(PRODUCT_ID)));
			final String uid = String.valueOf(obj.get(UID));
			final UserModel userModel = userService.getUserForUID(uid);
			Boolean flag = Boolean.FALSE;
			final Boolean newWishlistFlag = wishlistService.hasDefaultWishlist(userModel) ? Boolean.FALSE : Boolean.TRUE;
			final Wishlist2Model wishList = wishlistService.hasDefaultWishlist(userModel) ? wishlistService
					.getDefaultWishlist(userModel) : wishlistService.createDefaultWishlist(userModel, WISHLIST,
					ADD_TO_WISHLIST_FUNCTIONALITY);
			if (CollectionUtils.isNotEmpty(wishList.getEntries()))
			{
				final List<Wishlist2EntryModel> entries = wishList.getEntries();
				for (final Wishlist2EntryModel entry : entries)
				{
					if (entry.getProduct().getCode().equals(productModel.getCode()))
					{
						flag = Boolean.TRUE;
						break;
					}
				}
			}
			if (!flag || newWishlistFlag)
			{
				final Wishlist2EntryModel productEntry = new Wishlist2EntryModel();
				productEntry.setProduct(productModel);
				productEntry.setPriority(Wishlist2EntryPriority.LOW);
				productEntry.setAddedDate(new Date());
				wishlistService.addWishlistEntry(wishList, productEntry);
				LOG.info("product added" + productEntry.getProduct());
			}
			productDataList.setProducts(getWishlistProductsList(wishList));
		}
		return productDataList;
	}


	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public ProductDataList removeWishlistProducts(final HttpServletRequest request) throws IOException, ParseException
	{
		catalogService.setSessionCatalogVersion("electronicsProductCatalog", "Online");

		LOG.info("::::::: in removeWishlistProducts POST request method :::::::");
		final StringBuffer sbuf = getJsonBodyString(request);
		LOG.info("::::::: json object string is :::::::" + sbuf);
		final ProductDataList productDataList = new ProductDataList();
		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final ProductModel productModel = productService.getProductForCode(String.valueOf(obj.get(PRODUCT_ID)));
			final String uid = String.valueOf(obj.get(UID));
			final UserModel userModel = userService.getUserForUID(uid);
			final Wishlist2Model wishLists = wishlistService.getDefaultWishlist(userModel);
			final Wishlist2EntryModel productEnteries = new Wishlist2EntryModel();
			productEnteries.setProduct(productModel);
			productEnteries.setPriority(Wishlist2EntryPriority.LOW);
			productEnteries.setAddedDate(new Date());
			wishlistService.removeWishlistEntryForProduct(productModel, wishLists);

			productDataList.setProducts(getWishlistProductsList(wishLists));
			LOG.info("::::::: after removal :::::::" + productDataList.getProducts());



		}
		return productDataList;
	}

	/**
	 * @param wishList
	 * @return List<ProductData>
	 */
	private List<ProductData> getWishlistProductsList(final Wishlist2Model wishList)
	{
		final List<Wishlist2EntryModel> entries = wishList.getEntries();
		final List<ProductData> productsData = new ArrayList<ProductData>();
		for (final Wishlist2EntryModel entry : entries)
		{
			productsData.add(productConverter.convert(entry.getProduct()));
		}
		return productsData;
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
