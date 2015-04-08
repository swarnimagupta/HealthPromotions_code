##########################################
#Steps for installation of the addon
##########################################

#######################################################################
#Pre-requisites:
1.	Hybris version 5+
2.	Storefront should be up and running
3.  webservice extension should be configured
########################################################################

########################################################################
#Steps for installation:
1. Place the healthpromotionsaddon, qrcodeaddon, servicecore extensions in custom folder of hybris (location:bin/custom)
2. Include the following in localextensions.xml file:
	
	<extension name="qrcodeaddon" />
	<extension name="servicecore" />
	<extension name="healthpromotionsaddon" />

3. Now perform following command to include and build qrcodeaddon in the storefront.
	
	ant addoninstall -Daddonnames="qrcodeaddon" -DaddonStorefront.yacceleratorstorefront="<<storefront name e.g. bncstorefront>>"
	
4. After build is successful, perform following command to include and build healthpromotionsaddon in the storefront.
	
	ant addoninstall -Daddonnames="healthpromotionsaddon" -DaddonStorefront.yacceleratorstorefront="<<storefront name e.g. bncstorefront>>"
	
5. After build is successful, perform following command to include and build healthpromotionsaddon in the webservices extension.
	
	ant addoninstall -Daddonnames="healthpromotionsaddon" -DaddonStorefront.ycommercewebservices="<<webservices name e.g. bncwebservices>>"

6. Add the following entry in storefront’s spring-cms-config.xml

	<entry key="UCOIDOnOrderConfirmationComponent" value-ref="uCOIDOnOrderConfirmationComponentRenderer"/>
	
	In the following bean entry:
	
	<alias alias="cmsComponentRendererRegistry" name="acceleratorCMSComponentRendererRegistry"/>
	 <bean id="acceleratorCMSComponentRendererRegistry" parent="defaultCMSComponentRendererRegistry">
	  <property name="renderers">
	   <map merge="true">
		<entry key="CMSLinkComponent" value-ref="cmsLinkComponentRenderer"/>
		<entry key="UCOIDOnOrderConfirmationComponent" value-ref="uCOIDOnOrderConfirmationComponentRenderer"/>
	   </map>
	  </property>
	 </bean>

7. Run ant clean all. This will build the entire project once again including your module extensions.
8.	Once the build is successful, start the hybris server using hybrisserver.bat command
9.	Once the hybris server is started, then system update is required.Update  the hybris Commerce Suite.
	The Update page opens with preconfigured settings for update. Perform the following:
	a. Deselect the checkbox stating “Create essential data”
	b. Select the checkboxes next to qrcodeaddon, healthpromotionsaddon, servicecore
	c. Without changing any more settings, click the Update button. The update process starts and can take several minutes
	
10.	Once update is completed, click on continue button.
11.	Go to HMC 
	1)	Access HMC using url: http://localhost:9001/hmc/hybris
	Login: admin
	Password: nimda
	
12.	Click on Catalog -> Catalog Management Tools on left hand side navigation menu and click on “Synchronization”
13.	A pop-up window will open. Select electronicsContentCatalog Staged->Target and click on Next
14.	Click on start for starting the synchronization
15.	After synchronization has finished, click on Done
16.	In the HMC expand the Systems Tab and expand the Facet Search folder and Select the Indexer operation wizard
17.	A new window will open -> select solr configuration as electronicsIndex and click on start
######################################################################################################

