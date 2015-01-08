/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.acc.storefront.controllers.pages;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acc.storefront.controllers.ControllerConstants;
import com.acc.storefront.forms.CsrLoginForm;


/**
 * Login Controller. Handles login and register for the account flow.
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/login")
public class CSRLoginPageController extends AbstractAddOnPageController
{
	private static final String ACCOUNT_CMS_PAGE = "account";
	private static final String REDIRECT_TO_CUSTOMER_DETAILS = REDIRECT_PREFIX + "/customerlist/customerdeatils";
	@Autowired
	private UserService userService;
	@Autowired
	private FlexibleSearchService flexibleSearchService;
	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = "/csrLogin", method = RequestMethod.GET)
	public String csrLogin(final CsrLoginForm form, final Model model) throws CMSItemNotFoundException
	{
		System.out.println("in controller");
		if (null != sessionService.getAttribute("CSR_USER") && null != sessionService.getAttribute("POINT_OF_SERVICE"))
		{
			return REDIRECT_TO_CUSTOMER_DETAILS;
		}

		model.addAttribute("csrLoginForm", form);
		storeCmsPageInModel(model, getContentPageForLabelOrId(ACCOUNT_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ACCOUNT_CMS_PAGE));
		return ControllerConstants.Views.Pages.Account.csrLoginPage;
	}

	@RequestMapping(value = "/csrLogin", method = RequestMethod.POST)
	public String csrLogin(final CsrLoginForm form, final Model model, final BindingResult bindingResult)
			throws CMSItemNotFoundException
	{
		String returnAction = ControllerConstants.Views.Pages.Account.csrLoginPage;
		try
		{
			final PointOfServiceModel tempPointOfService = new PointOfServiceModel();
			tempPointOfService.setName(form.getPos());
			final PointOfServiceModel pointOfServiceModel = flexibleSearchService.getModelByExample(tempPointOfService);
			System.out.println("Point of service [ " + pointOfServiceModel + " ].");
			final UserModel userModel = userService.getUserForUID(form.getUsername());
			final UserGroupModel userGroupModel = userService.getUserGroupForUID("csrGroup");
			if ((null != userGroupModel) && (userService.isMemberOfGroup(userModel, userGroupModel)))
			{
				final String pwd = userService.getPassword(userModel);
				if (null != pwd && pwd.equals(form.getPassword()))
				{
					sessionService.setAttribute("POINT_OF_SERVICE", form.getPos());
					sessionService.setAttribute("CSR_USER", form.getUsername());
					return REDIRECT_TO_CUSTOMER_DETAILS;
				}
				else
				{

					bindingResult.rejectValue("password", "login.error.account.not.found.title", new Object[] {},
							"login.error.account.not.found.title");
				}
			}
			else
			{
				bindingResult.rejectValue("username", "login.error.group.not.found", new Object[] {}, "login.error.group.not.found");
			}
		}
		catch (final UnknownIdentifierException e)
		{
			bindingResult.rejectValue("username", "login.error.user.not.found", new Object[] {}, "login.error.user.not.found");
		}
		catch (final ModelNotFoundException e)
		{
			bindingResult.rejectValue("pos", "login.error.pos.not.found", new Object[] {}, "login.error.pos.not.found");
		}
		if (bindingResult.hasErrors())
		{
			returnAction = csrPageError(model);
		}
		else
		{
			storeCmsPageInModel(model, getContentPageForLabelOrId(ACCOUNT_CMS_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ACCOUNT_CMS_PAGE));
		}
		model.addAttribute("csrLoginForm", form);
		return returnAction;
	}

	protected String csrPageError(final Model model) throws CMSItemNotFoundException
	{
		final String returnAction;
		storeCmsPageInModel(model, getContentPageForLabelOrId(ACCOUNT_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ACCOUNT_CMS_PAGE));
		returnAction = ControllerConstants.Views.Pages.Account.csrLoginPage;
		return returnAction;
	}
}
