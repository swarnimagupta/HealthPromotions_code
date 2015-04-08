/**
 * 
 */
package com.acc.qrcodeaddon.component.renderer;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.cms2lib.model.components.UCOIDOnOrderConfirmationComponentModel;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author swarnima.gupta
 * 
 */
public class UCOIDOnOrderConfirmationRenderer<C extends UCOIDOnOrderConfirmationComponentModel> extends
		DefaultAddOnCMSComponentRenderer<C>
{
	private static final Logger LOG = Logger.getLogger(UCOIDOnOrderConfirmationRenderer.class);
	private CheckoutCustomerStrategy checkoutCustomerStrategy;
	private UserService userService;
	private CustomerAccountService customerAccountService;
	private BaseStoreService baseStoreService;
	private SessionService sessionService;

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @param checkoutCustomerStrategy
	 *           the checkoutCustomerStrategy to set
	 */
	public void setCheckoutCustomerStrategy(final CheckoutCustomerStrategy checkoutCustomerStrategy)
	{
		this.checkoutCustomerStrategy = checkoutCustomerStrategy;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @param customerAccountService
	 *           the customerAccountService to set
	 */
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final String orderCode = sessionService.getAttribute("orderCode");
		sessionService.removeAttribute("orderCode");
		LOG.info("::::::::::::::: Inside UCOIDOnOrderConfirmationRenderer getVariablesToExpose :::::::::::::::::");
		final Map<String, Object> variables = new HashMap<String, Object>();

		final BaseStoreModel baseStoreModel = baseStoreService.getCurrentBaseStore();
		final OrderModel orderModel = checkoutCustomerStrategy.isAnonymousCheckout() ? customerAccountService
				.getOrderDetailsForGUID(orderCode, baseStoreModel) : customerAccountService.getOrderForCode(
				(CustomerModel) userService.getCurrentUser(), orderCode, baseStoreModel);
		if (StringUtils.isNotEmpty(orderModel.getUCOID()))
		{
			variables.put("UCOID", orderModel.getUCOID());
			LOG.info("generated ucoid is " + orderModel.getUCOID());
		}
		return variables;
	}
}
