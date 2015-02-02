/**
 *
 */
package com.acc.accenture.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.acc.model.CustomerPromotionsProcessModel;


/**
 * @author swarnima.gupta
 *
 */
public class CustomerPromotionsEmailContext extends AbstractEmailContext<CustomerPromotionsProcessModel>
{
	private List<PromotionData> promotionsDataList;
	private Converter<AbstractPromotionModel, PromotionData> promotionsConverter;
	private Converter<UserModel, CustomerData> customerConverter;
	private CustomerData customerData;

	@Override
	public void init(final CustomerPromotionsProcessModel customerPromotionsProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(customerPromotionsProcessModel, emailPageModel);
		customerData = getCustomerConverter().convert(getCustomer(customerPromotionsProcessModel));
		promotionsDataList = Converters.convertAll(getPromotions(customerPromotionsProcessModel), getPromotionsConverter());
	}

	@Override
	protected BaseSiteModel getSite(final CustomerPromotionsProcessModel customerPromotionsProcessModel)
	{
		return customerPromotionsProcessModel.getSite();
	}

	@Override
	protected CustomerModel getCustomer(final CustomerPromotionsProcessModel customerPromotionsProcessModel)
	{
		return customerPromotionsProcessModel.getCustomer();
	}

	protected Collection<AbstractPromotionModel> getPromotions(final CustomerPromotionsProcessModel customerPromotionsProcessModel)
	{
		return customerPromotionsProcessModel.getPromotionsList();
	}

	protected Converter<UserModel, CustomerData> getCustomerConverter()
	{
		return customerConverter;
	}

	@Required
	public void setCustomerConverter(final Converter<UserModel, CustomerData> customerConverter)
	{
		this.customerConverter = customerConverter;
	}

	public CustomerData getCustomer()
	{
		return customerData;
	}

	@Override
	protected LanguageModel getEmailLanguage(final CustomerPromotionsProcessModel businessProcessModel)
	{
		return businessProcessModel.getLanguage();
	}

	/**
	 * @return the promotionsDataList
	 */
	public List<PromotionData> getPromotionsDataList()
	{
		return promotionsDataList;
	}

	/**
	 * @return the promotionsConverter
	 */
	public Converter<AbstractPromotionModel, PromotionData> getPromotionsConverter()
	{
		return promotionsConverter;
	}

	/**
	 * @param promotionsConverter
	 *           the promotionsConverter to set
	 */
	public void setPromotionsConverter(final Converter<AbstractPromotionModel, PromotionData> promotionsConverter)
	{
		this.promotionsConverter = promotionsConverter;
	}


}
