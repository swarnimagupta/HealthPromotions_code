##########################################
#Steps for installation of the addon
##########################################

#######################################################################
#Pre-requisites:
1.	Hybris version 5+
2.	Storefront should be up and running 
########################################################################

########################################################################
#Steps for installation:
1. Place the healthdataremovaladdon extension in custom folder of hybris (location:bin/custom)

2. Now perform following command to uninstall the healthpromotionsaddon from storefront.
	
	ant addonuninstall -Daddonnames="healthpromotionsaddon" -DaddonStorefront.yacceleratorstorefront="<<storefront name e.g. bncstorefront>>"
	
3. After build is successful, perform following command to uninstall the healthpromotionsaddon from webservices.
	
	ant addonuninstall -Daddonnames="healthpromotionsaddon" -DaddonStorefront.ycommercewebservices="<<webservices name e.g. bncwebservices>>"
	
4. Include the following in localextensions.xml file:

	<extension name="healthdataremovaladdon" />
	
	
	And remove the following entries from the localextensions.xml file:
	
	<extension name="qrcodeaddon" />
	<extension name="healthpromotionsaddon" />
	<extension name="servicecore" />

5. Now perform following command to include and build healthdataremovaladdon in the project.
	
	ant addoninstall -Daddonnames="healthdataremovaladdon" -DaddonStorefront.yacceleratorstorefront="<<storefront name e.g. bncstorefront>>"

6. Run ant clean all. This will build the entire project once again including your module extensions.
7.	Once the build is successful, start the hybris server using hybrisserver.bat command
8.	Once the hybris server is started, then system update is required.Update  the hybris Commerce Suite.
	The Update page opens with preconfigured settings for update. Perform the following:
	a. Deselect the checkbox stating “Create essential data”
	b. Select the checkboxes next to healthdataremovaladdon
	c. Without changing any more settings, click the Update button. The update process starts and can take several minutes
	
9.	Once update is completed, click on continue button.
10.	Go to HMC 
	1)	Access HMC using url: http://localhost:9001/hmc/hybris
	Login: admin
	Password: nimda
	
11.	Click on Catalog -> Catalog Management Tools on left hand side navigation menu and click on “Synchronization”
12.	A pop-up window will open. Select electronicsContentCatalog Staged->Target and click on Next
13.	Click on start for starting the synchronization
14.	After synchronization has finished, click on Done
15.	In the HMC expand the Systems Tab and expand the Facet Search folder and Select the Indexer operation wizard
16.	A new window will open -> select solr configuration as electronicsIndex and click on start
17. After indexing is complete. Stop the server, and run the following command
	
	ant addonuninstall -Daddonnames="healthdataremovaladdon" -DaddonStorefront.yacceleratorstorefront="<<storefront name e.g. bncstorefront>>"
	
18. Remove the following entry from localextensions.xml

	<extension name="healthdataremovaladdon" />
	
18. Delete the healthdataremovaladdon extension from hybris custom folder and run ant clean all
######################################################################################################

