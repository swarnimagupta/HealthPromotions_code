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
2. Add the following entries in local.properties file (under hybris/config)

	healthdata.heartrate.thresholdvalue=150.000000
	healthdata.stepcount.thresholdvalue=2285.000000
	healthdata.bodymass.thresholdvalue=120.000000
	healthdata.cholesterol.thresholdvalue=14.000000
	healthdata.fattotal.thresholdvalue=52.000000
	
	welcome.beacon.id=8492E75F-4FD6-469D-B132-043FE94921D8
	welcome.beacon.major.id=6517
	welcome.beacon.minor.id=23023
	
	Modify the values as per requirement
	
3. Include the following in localextensions.xml file:
	
	<extension name="qrcodeaddon" />
	<extension name="servicecore" />
	<extension name="healthpromotionsaddon" />

4. Now perform following command to include and build qrcodeaddon in the storefront.
	
	ant addoninstall -Daddonnames="qrcodeaddon" -DaddonStorefront.yacceleratorstorefront="<<storefront name e.g. bncstorefront>>"
	
5. After build is successful, perform following command to include and build healthpromotionsaddon in the storefront.
	
	ant addoninstall -Daddonnames="healthpromotionsaddon" -DaddonStorefront.yacceleratorstorefront="<<storefront name e.g. bncstorefront>>"
	
6. After build is successful, perform following command to include and build healthpromotionsaddon in the webservices extension.
	
	ant addoninstall -Daddonnames="healthpromotionsaddon" -DaddonStorefront.ycommercewebservices="<<webservices name e.g. bncwebservices>>"

7. Add the following entry in storefront’s spring-cms-config.xml

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

8. Run ant clean all. This will build the entire project once again including your module extensions.
9.	Once the build is successful, start the hybris server using hybrisserver.bat command
10.	Once the hybris server is started, then system update is required.Update  the hybris Commerce Suite.
	The Update page opens with preconfigured settings for update. Perform the following:
	a. Deselect the checkbox stating “Create essential data”
	b. Select the checkboxes next to qrcodeaddon, healthpromotionsaddon, servicecore
	c. Without changing any more settings, click the Update button. The update process starts and can take several minutes
	
11.	Once update is completed, click on continue button.
12.	Go to HMC 
	1)	Access HMC using url: http://localhost:9001/hmc/hybris
	Login: admin
	Password: nimda
	
13.	Click on Catalog -> Catalog Management Tools on left hand side navigation menu and click on “Synchronization”
14.	A pop-up window will open. Select electronicsContentCatalog Staged->Target and click on Next
15.	Click on start for starting the synchronization
16.	After synchronization has finished, click on Done
17.	In the HMC expand the Systems Tab and expand the Facet Search folder and Select the Indexer operation wizard
18.	A new window will open -> select solr configuration as electronicsIndex and click on start
######################################################################################################

