/**
 * 
 */
package com.acc.storefront.controllers.pages;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;




import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.enums.ProductReferenceTypeEnum;
import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.wishlist2.Wishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import sun.org.mozilla.javascript.internal.json.JsonParser;

import com.acc.core.collectorder.facade.CustomerCollectOrderFacade;
import com.acc.data.CustomerGeoData;
import com.acc.facades.CSRCustomerDetails.data.CSRCustomerDetailsData;
import com.acc.facades.storecustomer.StoreCustomerFacade;
import com.acc.facades.wishlist.data.Wishlist2Data;
import com.acc.model.TrackLatLongModel;
import com.acc.storefront.controllers.ControllerConstants;
import com.acc.storefront.util.CustomerOrderData;
import com.acc.storefront.util.ProfileInformationDto;
import com.acc.storefront.util.StoreCustomerData;
import com.acc.util.WeatherUtil;
import com.acc.util.WebservicesUtil;
import com.accenture.enums.CSRStoreStatus;
import com.accenture.model.CSRCustomerDetailsModel;
import com.accenture.model.ConfigModel;

import java.net.HttpURLConnection;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.net.MalformedURLException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * @author prasun.a.kumar
 * 
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/customerlist")
public class CustomerListController extends AbstractAddOnPageController
{
	private static final String REDIRECT_TO_CUSTOMER_DETAILS = REDIRECT_PREFIX + "/customerlist/customerdeatils";
	private static final String ACCOUNT_CMS_PAGE = "account";
	private static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);
	private static final String ORDER_CODE_PATH_VARIABLE_PATTERN = "{orderCode:.*}";

	private static final Logger LOG = Logger.getLogger(CustomerListController.class);
	private static final int MAX_PAGE_LIMIT = 100;
	private static final String TEMP_C = "temp_C";
	private static final String PRECIP_MM = "precipMM";
	private static final String CLOUDCOVER = "cloudcover";
	private static final String CURRENT_CONDITION = "current_condition";
	private static final String DATA = "data";

	@Autowired
	private StoreCustomerFacade StoreCustomerFacade;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelService modelService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private Wishlist2Service wishlistService;
	@Autowired
	private ProductFacade productFacade;
	@Autowired
	private Converter<Wishlist2Model, Wishlist2Data> Wishlist2Converter;
	@Autowired
	private EventService eventService;
	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
	@Autowired
	private CustomerCollectOrderFacade customerCollectOrderFacade;
	@Autowired
	private ProductService productService;
	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@RequestMapping(value = "/assistcustomer", method = RequestMethod.GET, produces = "application/json")
	public String customerHistory(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException,  IOException, ParseException,MalformedURLException, ParserConfigurationException,SAXException
	{
		final ProfileInformationDto informationDto = new ProfileInformationDto();
		final List<CustomerOrderData> customerOrderDataList = new ArrayList<CustomerOrderData>();
		final StoreCustomerData storecustomerData = new StoreCustomerData();
		if (null != request.getParameter("customerPK"))
		{
			final CSRCustomerDetailsModel csrCustomerDetailsModel = modelService.get(PK.parse(request.getParameter("customerPK")));
			assistCustomerRecord(csrCustomerDetailsModel, storecustomerData, informationDto, customerOrderDataList, model);
			getGeoLocationNClimateData(csrCustomerDetailsModel.getCustomerId(), model);
		}
		model.addAttribute("storecustomerData", storecustomerData);
		model.addAttribute("informationDto", informationDto);
		model.addAttribute("customerOrderDataList", customerOrderDataList);
		final OrderData orderData = CollectionUtils.isEmpty(customerOrderDataList) ? new OrderData() : customerCollectOrderFacade
				.getOrderDetailsForCode(customerOrderDataList.get(0).getOrderCode());
		model.addAttribute("orderData", orderData);
		model.addAttribute("CSR_USER", sessionService.getAttribute("CSR_USER"));
		return ControllerConstants.Views.Fragments.Cart.CustomerDetailsFragment;

	}

	/**
	 * This helper method is used to return the Customer list of records like Order history, Contact Address and his/her
	 * details.
	 * 
	 * @param csrCustomerDetailsModel
	 * @param storecustomerData
	 *           Indicates Customer LoggedIn/NoThanks/Inservice status data.
	 * @param informationDto
	 *           Indicates his/her information.
	 * @param customerOrderDataList
	 *           Indicates their list of Orders.
	 */
	private void assistCustomerRecord(final CSRCustomerDetailsModel csrCustomerDetailsModel,
			final StoreCustomerData storecustomerData, final ProfileInformationDto informationDto,
			final List<CustomerOrderData> customerOrderDataList, final Model model)
	{
		final UserModel userModel = userService.getUserForUID(csrCustomerDetailsModel.getCustomerId());
		final List<ProductData> recommendedProducts = new ArrayList<ProductData>();
		if (userModel instanceof CustomerModel)
		{
			final CustomerModel customerModel = (CustomerModel) userModel;
			//retrieving wishlist entries
			Wishlist2Data wishlistData = null;
			try
			{
				final Wishlist2Model wishlistModel = wishlistService.hasDefaultWishlist(userModel) ? wishlistService
						.getDefaultWishlist(userModel) : wishlistService.createDefaultWishlist(userModel, "wishlist",
						"add to wishlist functionality");
				wishlistData = null != wishlistModel.getEntries() ? Wishlist2Converter.convert(wishlistModel) : null;
			}
			catch(Exception e)
			{
				LOG.warn(e.getMessage(), e);
			}
			//end ** wishlist entries**
			//retrieving recently viewed products
			final List<ProductData> products = new ArrayList<ProductData>();
			if (null != customerModel.getRecentlyviewedproducts())
			{
				for (final ProductModel productModel : customerModel.getRecentlyviewedproducts())
				{
					products.add(productFacade.getProductForOptions(productModel, PRODUCT_OPTIONS));
				}
			}
			Collections.reverse(products);
			model.addAttribute("wishlist", wishlistData);
			model.addAttribute("productData", products);
			//end ** recently viewed products**
			//CSR Customer data
			storecustomerData.setCustomerName(csrCustomerDetailsModel.getCustomerName());
			final String time = returnLoginFromTime(csrCustomerDetailsModel.getLoginTime());
			final String loggedTime = returnLoggedInTime(csrCustomerDetailsModel.getLoginTime());
			storecustomerData.setWaitingTime(time);
			storecustomerData.setLoginTime(loggedTime);
			storecustomerData.setProfilePictureURL((null == customerModel.getProfilePicture() ? StringUtils.EMPTY : customerModel
					.getProfilePicture().getURL2()));
			storecustomerData.setCustomerId(customerModel.getUid());
			storecustomerData.setProcessedBy((null == csrCustomerDetailsModel.getProcessedBy() ? "" : csrCustomerDetailsModel
					.getProcessedBy()));
			storecustomerData.setCustStatus(csrCustomerDetailsModel.getStatus().getCode());
			informationDto.setName(csrCustomerDetailsModel.getCustomerName());
			final Collection<AddressModel> addressList = customerModel.getAddresses();
			if (null != addressList)
			{
				for (final AddressModel addressModel : addressList)
				{
					if (addressModel.getContactAddress())
					{
						final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

						informationDto.setDob(null == addressModel.getDateOfBirth() ? "" : dateFormat.format(addressModel
								.getDateOfBirth()));
						informationDto.setLine1(addressModel.getLine1());
						informationDto.setLine2(addressModel.getLine2());
						informationDto.setApartment(addressModel.getAppartment());
						informationDto.setPostalCode(addressModel.getPostalcode());
					}
				}
			}

			final Collection<OrderModel> orderModel = customerModel.getOrders();
			if (null != orderModel)
			{
				for (final OrderModel orderModel2 : orderModel)
				{
					final CustomerOrderData customerOrderData = new CustomerOrderData();
					customerOrderData.setOrderCode(orderModel2.getCode());
					final DateFormat dateFormat = new SimpleDateFormat("dd MMM,yyyy");
					customerOrderData.setPlacedDate(dateFormat.format(orderModel2.getCreationtime()));
					if (null != orderModel2.getTotalPrice())
					{
						customerOrderData.setTotal(orderModel2.getTotalPrice().toString());
					}
					customerOrderDataList.add(customerOrderData);
				}
			}
			
			//recommended products
			if (null != customerModel.getRecentlyviewedproducts())
			{
				final List<ProductModel> productslist = (List<ProductModel>) customerModel.getRecentlyviewedproducts();
				for (final ProductModel productModel : productslist)
				{
					final Iterator<ProductReferenceModel> iterator = productModel.getProductReferences().iterator();
					while (iterator.hasNext())
					{
						final ProductReferenceModel productReferenceModel = iterator.next();
						final ProductReferenceTypeEnum productReferenceTypeEnum = productReferenceModel.getReferenceType();
						final ProductData productData = productFacade.getProductForOptions(productReferenceModel.getTarget(),
								PRODUCT_OPTIONS);
						if (productReferenceTypeEnum.equals(ProductReferenceTypeEnum.SIMILAR) && !(productslist.contains(productData)))
						{
							recommendedProducts.add(productData);
							break;
						}
					}
				}
			}
			model.addAttribute("recommendedProductsData", recommendedProducts);
		}
	}

	@SuppressWarnings("boxing")
	@RequestMapping(value = "/customerdeatils", method = RequestMethod.GET)
	public String customerListDetails(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException
	{
		final List<StoreCustomerData> customerStatusDataList = new ArrayList<StoreCustomerData>();
		final List<StoreCustomerData> customerInServiceDataList = new ArrayList<StoreCustomerData>();
		final List<StoreCustomerData> customerNoThanxDataList = new ArrayList<StoreCustomerData>();
		final List<CSRCustomerDetailsModel> csrCustomerDetailsList = StoreCustomerFacade.getCSRCustomerDetails();
		final String status = request.getParameter("status");
		final List<CSRCustomerDetailsModel> csrCustomerDetailsByStatusList = StoreCustomerFacade
				.getCSRCustomerDetailsByStatus(StringUtils.isEmpty(status) ? CSRStoreStatus.LOGGEDIN : CSRStoreStatus.valueOf(status));
		try
		{
			if (null != csrCustomerDetailsByStatusList)
			{
				for (final CSRCustomerDetailsModel customerDetails : csrCustomerDetailsByStatusList)
				{
					final UserModel userModel = userService.getUserForUID(customerDetails.getCustomerId());
					String profilePictureURL = "";
					if (null != userModel && userModel instanceof CustomerModel)
					{
						final CustomerModel customerModel = (CustomerModel) userModel;
						profilePictureURL = (null == customerModel.getProfilePicture() ? StringUtils.EMPTY : customerModel
								.getProfilePicture().getURL2());
					}
					final StoreCustomerData storecustomerData = new StoreCustomerData();
					final String time = returnLoginFromTime(customerDetails.getLoginTime());
					final String loggedTime = returnLoggedInTime(customerDetails.getLoginTime());
					storecustomerData.setCustomerId(customerDetails.getCustomerId());
					storecustomerData.setCustomerName(customerDetails.getCustomerName());
					storecustomerData.setStoreCustomerPK(customerDetails.getPk().getLongValueAsString());
					storecustomerData.setWaitingTime(time);
					storecustomerData.setLoginTime(loggedTime);
					storecustomerData.setProfilePictureURL(profilePictureURL);
					storecustomerData
							.setProcessedBy((null == customerDetails.getProcessedBy() ? "" : customerDetails.getProcessedBy()));
					customerStatusDataList.add(storecustomerData);
				}
			}
		}
		catch (final UnknownIdentifierException e)
		{
			//
		}
		model.addAttribute("customerLoggedInDataList", customerStatusDataList);
		model.addAttribute("csrCustomerDetailsByStatusList", csrCustomerDetailsByStatusList);
		model.addAttribute("Queued", getStatusCount(csrCustomerDetailsList, CSRStoreStatus.LOGGEDIN));
		model.addAttribute("Active", getStatusCount(csrCustomerDetailsList, CSRStoreStatus.INSERVICE));
		model.addAttribute("Serviced", getStatusCount(csrCustomerDetailsList, CSRStoreStatus.COMPLETED));
		model.addAttribute("CSR_USER", sessionService.getAttribute("CSR_USER"));
		return ControllerConstants.Views.Pages.Account.customerDetailsPage;

	}
	
	
	@SuppressWarnings("boxing")
	@RequestMapping(value = "/customerdetails", method = RequestMethod.GET)
	public String ajaxCustomerListDetails(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException
	{
		final List<StoreCustomerData> customerStatusDataList = new ArrayList<StoreCustomerData>();
		final List<StoreCustomerData> customerInServiceDataList = new ArrayList<StoreCustomerData>();
		final List<StoreCustomerData> customerNoThanxDataList = new ArrayList<StoreCustomerData>();
		final List<CSRCustomerDetailsModel> csrCustomerDetailsList = StoreCustomerFacade.getCSRCustomerDetails();
		final String status = request.getParameter("status");
		final List<CSRCustomerDetailsModel> csrCustomerDetailsByStatusList = StoreCustomerFacade
				.getCSRCustomerDetailsByStatus(StringUtils.isEmpty(status) ? CSRStoreStatus.LOGGEDIN : CSRStoreStatus.valueOf(status));
		try
		{
			if (null != csrCustomerDetailsByStatusList)
			{
				for (final CSRCustomerDetailsModel customerDetails : csrCustomerDetailsByStatusList)
				{
					final UserModel userModel = userService.getUserForUID(customerDetails.getCustomerId());
					String profilePictureURL = "";
					if (null != userModel && userModel instanceof CustomerModel)
					{
						final CustomerModel customerModel = (CustomerModel) userModel;
						profilePictureURL = (null == customerModel.getProfilePicture() ? StringUtils.EMPTY : customerModel
								.getProfilePicture().getURL2());
					}
					final StoreCustomerData storecustomerData = new StoreCustomerData();
					final String time = returnLoginFromTime(customerDetails.getLoginTime());
					final String loggedTime = returnLoggedInTime(customerDetails.getLoginTime());
					storecustomerData.setCustomerId(customerDetails.getCustomerId());
					storecustomerData.setCustomerName(customerDetails.getCustomerName());
					storecustomerData.setStoreCustomerPK(customerDetails.getPk().getLongValueAsString());
					storecustomerData.setWaitingTime(time);
					storecustomerData.setLoginTime(loggedTime);
					storecustomerData.setProfilePictureURL(profilePictureURL);
					storecustomerData
							.setProcessedBy((null == customerDetails.getProcessedBy() ? "" : customerDetails.getProcessedBy()));
					customerStatusDataList.add(storecustomerData);
				}
			}
		}
		catch (final UnknownIdentifierException e)
		{
			//
		}
		model.addAttribute("customerLoggedInDataList", customerStatusDataList);
		model.addAttribute("csrCustomerDetailsByStatusList", csrCustomerDetailsByStatusList);
		model.addAttribute("Queue", getStatusCount(csrCustomerDetailsList, CSRStoreStatus.LOGGEDIN));
		model.addAttribute("Active", getStatusCount(csrCustomerDetailsList, CSRStoreStatus.INSERVICE));
		model.addAttribute("Serviced", getStatusCount(csrCustomerDetailsList, CSRStoreStatus.COMPLETED));
		model.addAttribute("CSR_USER", sessionService.getAttribute("CSR_USER"));
		return ControllerConstants.Views.Fragments.Cart.CustomerListFragment;

	}
	
	@SuppressWarnings("boxing")
	@RequestMapping(value = "/getChartFragment", method = RequestMethod.GET)
	public String getChartFragment(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException
	{
		final List<StoreCustomerData> customerStatusDataList = new ArrayList<StoreCustomerData>();
		final List<StoreCustomerData> customerInServiceDataList = new ArrayList<StoreCustomerData>();
		final List<StoreCustomerData> customerNoThanxDataList = new ArrayList<StoreCustomerData>();
		final List<CSRCustomerDetailsModel> csrCustomerDetailsList = StoreCustomerFacade.getCSRCustomerDetails();
		final String status = request.getParameter("status");
		final List<CSRCustomerDetailsModel> csrCustomerDetailsByStatusList = StoreCustomerFacade
				.getCSRCustomerDetailsByStatus(StringUtils.isEmpty(status) ? CSRStoreStatus.LOGGEDIN : CSRStoreStatus.valueOf(status));
		try
		{
			if (null != csrCustomerDetailsByStatusList)
			{
				for (final CSRCustomerDetailsModel customerDetails : csrCustomerDetailsByStatusList)
				{
					final UserModel userModel = userService.getUserForUID(customerDetails.getCustomerId());
					String profilePictureURL = "";
					if (null != userModel && userModel instanceof CustomerModel)
					{
						final CustomerModel customerModel = (CustomerModel) userModel;
						profilePictureURL = (null == customerModel.getProfilePicture() ? StringUtils.EMPTY : customerModel
								.getProfilePicture().getURL2());
					}
					final StoreCustomerData storecustomerData = new StoreCustomerData();
					final String time = returnLoginFromTime(customerDetails.getLoginTime());
					final String loggedTime = returnLoggedInTime(customerDetails.getLoginTime());
					storecustomerData.setCustomerId(customerDetails.getCustomerId());
					storecustomerData.setCustomerName(customerDetails.getCustomerName());
					storecustomerData.setStoreCustomerPK(customerDetails.getPk().getLongValueAsString());
					storecustomerData.setWaitingTime(time);
					storecustomerData.setLoginTime(loggedTime);
					storecustomerData.setProfilePictureURL(profilePictureURL);
					storecustomerData
							.setProcessedBy((null == customerDetails.getProcessedBy() ? "" : customerDetails.getProcessedBy()));
					customerStatusDataList.add(storecustomerData);
				}
			}
		}
		catch (final UnknownIdentifierException e)
		{
			//
		}
		model.addAttribute("customerLoggedInDataList", customerStatusDataList);
		model.addAttribute("csrCustomerDetailsByStatusList", csrCustomerDetailsByStatusList);
		model.addAttribute("Queue", getStatusCount(csrCustomerDetailsList, CSRStoreStatus.LOGGEDIN));
		model.addAttribute("Activ", getStatusCount(csrCustomerDetailsList, CSRStoreStatus.INSERVICE));
		model.addAttribute("Service", getStatusCount(csrCustomerDetailsList, CSRStoreStatus.COMPLETED));
		model.addAttribute("CSR_USER", sessionService.getAttribute("CSR_USER"));
		return ControllerConstants.Views.Fragments.Cart.ChartsRefresh;

	}

	/**
	 * @param cSRCustomerDetailsModelsList
	 */
	private int getStatusCount(final List<CSRCustomerDetailsModel> cSRCustomerDetailsModelsList, final CSRStoreStatus status)
	{
		int statusCount = 0;
		if (CollectionUtils.isNotEmpty(cSRCustomerDetailsModelsList))
		{
			for (final CSRCustomerDetailsModel cSRCustomerDetailsModel : cSRCustomerDetailsModelsList)
			{
				if (status.equals(cSRCustomerDetailsModel.getStatus()))
				{
					statusCount++;
				}
			}
		}
		return statusCount;
	}




	@RequestMapping(value = "/csrlogout", method = RequestMethod.GET)
	public String csrlogout()
	{
		sessionService.removeAttribute("POINT_OF_SERVICE");
		sessionService.removeAttribute("CSR_USER");
		return REDIRECT_PREFIX + ROOT;
	}

	@RequestMapping(value = "/statusUpdate", method = RequestMethod.GET)
	public String customerStatusChanged(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException
	{
		final String customerPK = request.getParameter("customerPK");
		final String status = request.getParameter("status");
		final String csrID = sessionService.getAttribute("CSR_USER");
		StoreCustomerFacade.updateCSRCustomerDetail(csrID, customerPK, status);
		return REDIRECT_TO_CUSTOMER_DETAILS;
	}


	/**
	 * @param loginTime
	 * @return Logged in by time.
	 */
	private String returnLoggedInTime(final Date loginTime)
	{
		final SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm a");
		final String formattedDate = "Logged In by " + formatDate.format(loginTime).toString();
		return formattedDate;
	}

	/**
	 * @param loginTime
	 * @return Waiting time of User
	 */
	private String returnLoginFromTime(final Date loginTime)
	{
		int waitTime = 0;
		String time = "";
		final Calendar start = Calendar.getInstance();
		start.setTime(loginTime);
		final Calendar end = Calendar.getInstance();
		end.setTime(new Date());
		final Calendar clone = (Calendar) start.clone();
		final int min = (int) (end.getTimeInMillis() - clone.getTimeInMillis()) / 60000;
		if ((min >= 60) && ((min % 60) > 0))
		{
			waitTime = min / 60;
			time = waitTime + " Hours " + min % 60 + " Minutes";
		}
		else
		{
			waitTime = min;
			time = waitTime + " Minutes";
		}
		return time;
	}

	@RequestMapping(value = "/customerName", method = RequestMethod.GET, produces = "application/json")
	public String SearchByCustomerName(@RequestParam("customername") final String customerName, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException

	{
		final List<CSRCustomerDetailsData> csrCustomerDetailsData = StoreCustomerFacade.getCollectOrderByCustomerName(customerName);
		if (null != csrCustomerDetailsData)
		{
			LOG.info("customer data in csutomer list controller" + csrCustomerDetailsData);

			LOG.info("customer name in csutomer list controller" + csrCustomerDetailsData.get(0).getCustomerName());
			LOG.info("customer id in csutomer list controller" + csrCustomerDetailsData.get(0).getCustomerId());
		}
		model.addAttribute("collectOrderDataByCustomerName", csrCustomerDetailsData);
		LOG.info("In controller of SearchByCustomerName");
		return ControllerConstants.Views.Fragments.Cart.OrderByCustomerName;
	}


	@RequestMapping(value = "/datetime", method = RequestMethod.GET, produces = "application/json")
	public String searchByDateTime(@RequestParam("fdate") final String fromDate, @RequestParam("tdate") final String toDate,
			@RequestParam("ftime") final String fromTime, @RequestParam("ttime") final String toTime, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException

	{
		final List<CSRCustomerDetailsData> csrCustomerDataList = StoreCustomerFacade.getCustomerDetailsByDateAndTime(fromDate,
				toDate, fromTime, toTime);
		model.addAttribute("csrCustomerDataList", csrCustomerDataList);
		model.addAttribute("CSRCustomerDetailsData", CollectionUtils.isEmpty(csrCustomerDataList) ? new CSRCustomerDetailsData()
				: StoreCustomerFacade.getCollectOrderByCustomerName(csrCustomerDataList.get(0).getCustomerName()));
		return ControllerConstants.Views.Fragments.Cart.CustomerByDateTime;
	}

	@RequestMapping(value = "/order/" + ORDER_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET, produces = "application/json")
	public String postOrderDetails(@RequestParam("orderCode") final String orderCode, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
	{
		final OrderData orderData = customerCollectOrderFacade.getOrderDetailsForCode(orderCode);
		model.addAttribute("orderData", orderData);
		return ControllerConstants.Views.Fragments.Cart.OrderDetailsAccordion;
	}
	
	@RequestMapping(value = "/addrecentlyviewed", method = RequestMethod.GET, produces = "application/json")
	public void addToRecentlyViewedProducts(@RequestParam("code") final String productCode, final Model model,
			final HttpServletRequest request, final HttpServletResponse response)
	{
		LOG.info("********inside product page controller************");
		final ProductModel productModel = productService.getProductForCode(productCode);
		if (!userService.isAnonymousUser(userService.getCurrentUser()))
		{
			final UserModel userModel = userService.getCurrentUser();
			List<ProductModel> list = new ArrayList<ProductModel>();
			final List<ProductModel> listlatest = new ArrayList<ProductModel>();
			list = (List<ProductModel>) userModel.getRecentlyviewedproducts();
			if (!list.contains(productModel))
			{
				for (final ProductModel productModel2 : list)
				{
					listlatest.add(productModel2);
				}
				final SearchResult<ConfigModel> searchResult = flexibleSearchService.search("select {pk} from {Config}");
				if (list.size() < searchResult.getResult().get(0).getValueHolder().intValue())
				{
					listlatest.add(productModel);
					userModel.setRecentlyviewedproducts(listlatest);
					modelService.save(userModel);
					LOG.info("******** Product saved to recent list in if condition ************");
				}
				else
				{
					listlatest.remove(0);
					listlatest.add(productModel);
					userModel.setRecentlyviewedproducts(listlatest);
					modelService.save(userModel);
					LOG.info("******** Product saved to recent list in else condition ************");
				}
			}

		}
	}
	
	public void getGeoLocationNClimateData(final String customerId, final Model model) throws IOException, ParseException,MalformedURLException, ParserConfigurationException,SAXException
	{
		LOG.info("inside getGeoLocationNClimateData ");
		final CustomerModel customerModel = (CustomerModel) userService.getUserForUID(customerId);
		List<TrackLatLongModel> trackLatLongModels = customerModel.getTrackLatLongList();
		if(CollectionUtils.isNotEmpty(trackLatLongModels))
		{
			final List<TrackLatLongModel> trackLatLongModelNewList = new ArrayList<TrackLatLongModel>();
			trackLatLongModelNewList.addAll(trackLatLongModels);
			Collections.sort(trackLatLongModelNewList, new Comparator<TrackLatLongModel>() 
			{
				@Override
				public int compare(TrackLatLongModel o1, TrackLatLongModel o2) 
				{
					long l1 = o1.getCreationtime().getTime();
					long l2 = o2.getCreationtime().getTime();
					if (l1 < l2) 
					{
						return 1;
					} 
					else if (l1 > l2) 
					{
						return -1;
					} 
					else 
					{
						return 0;
					}
				}
			});
			
			CustomerGeoData customerGeoData = null;
			final List<CustomerGeoData> customerGeo = new ArrayList<CustomerGeoData>();
			int size = trackLatLongModelNewList.size();
			for(int index = 0; index<size;index++)
			{
				customerGeoData = new CustomerGeoData();
	      	String zipValue = null;
	      	String cityValue = null;
	      	String countryValue = null;
	      	String stateValue = null;
	      	Date date =	trackLatLongModelNewList.get(index).getCreationtime();
	      	
	         final URL url = new URL("http://api.wunderground.com/auto/wui/geo/GeoLookupXML/index.xml?query=" + trackLatLongModelNewList.get(index).getLatitude() 
	         		+ ","+ trackLatLongModelNewList.get(index).getLongitude());
	         LOG.info(":::::::url::::::::" + url);
	         final WebservicesUtil webservicesUtil = new WebservicesUtil();
				final HttpURLConnection connection = webservicesUtil.getHttpConnection(url);
	         final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	         final DocumentBuilder db = dbf.newDocumentBuilder();
	         final Document doc = db.parse(connection.getInputStream());
	         doc.getDocumentElement().normalize();
	         final NodeList nodeLst = doc.getElementsByTagName("location");
	         for (int s = 0; s < nodeLst.getLength(); s++)
	         {
	         	final Node fstNode = nodeLst.item(s);
	         	if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	         	{
	         		final Element fstElmnt = (Element) fstNode;
	               final NodeList city = fstElmnt.getElementsByTagName("city");
	               final Element cityname = (Element) city.item(0);
	               if (null != cityname.getFirstChild())
	               {
	               	final Text citytext = (Text) cityname.getFirstChild();
	               	cityValue = citytext.getNodeValue();
	               	LOG.info(":::::::cityValue:::::::" + cityValue);
	               }
	               else
	               {
	               	cityValue = StringUtils.EMPTY;
	               }
	               final NodeList country = fstElmnt.getElementsByTagName("country");
	               final Element countryname = (Element) country.item(0);
	               if (null != countryname.getFirstChild())
	               {
	               	final Text countrytext = (Text) countryname.getFirstChild();
	               	countryValue = countrytext.getNodeValue();
	               	LOG.info(":::::::countryname:::::::" + countryValue);
	               }
	               else
	               {
	               	countryValue = StringUtils.EMPTY;
	               }
	               final NodeList state = fstElmnt.getElementsByTagName("state");
	               final Element statename = (Element) state.item(0);
	               if (null != statename.getFirstChild())
	               {
	               	final Text statetext = (Text) statename.getFirstChild();
	               	stateValue = statetext.getNodeValue();
	               	LOG.info(":::::::state:::::::" + stateValue);
	               }
	               else
	               {
	               	stateValue = StringUtils.EMPTY;
	               }
	               final NodeList zip = fstElmnt.getElementsByTagName("zip");
	               final Element zipname = (Element) zip.item(0);
	               if (null != zipname.getFirstChild())
	               {
	               	final Text ziptext = (Text) zipname.getFirstChild();
	               	zipValue = ziptext.getNodeValue();
	               	LOG.info(":::::::zipValue:::::::" + zipValue);
	               }
	               else
	               {
	               	zipValue = StringUtils.EMPTY;
	               }
	         	}
	         }
	         String string = cityValue + ","+ stateValue + ", " + countryValue + " " + zipValue;
	   		customerGeoData.setDate(date.toString());
	   		customerGeoData.setString(string);
	   		customerGeo.add(customerGeoData);
	     	}
			model.addAttribute("geoLocationDetails", customerGeo);
			if(CollectionUtils.isNotEmpty(trackLatLongModelNewList))
			{
				model.addAttribute("climate", WeatherUtil.executeClimateWebservice(new JSONParser(), trackLatLongModelNewList.get(0).getLatitude(), trackLatLongModelNewList.get(0).getLongitude()));
				model.addAttribute("latestLatitude", trackLatLongModelNewList.get(0).getLatitude());
				model.addAttribute("latestLongitude", trackLatLongModelNewList.get(0).getLongitude());
			}
		}
   }
}