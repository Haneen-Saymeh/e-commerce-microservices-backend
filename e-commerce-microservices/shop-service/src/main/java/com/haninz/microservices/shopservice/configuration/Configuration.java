package com.haninz.microservices.shopservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("shop-service")
public class Configuration {
	
	 private String applicationName;
	    private int serverPort;
	    private String datasourceUrl;
	    private String datasourceUsername;
	    private String datasourcePassword;
	    private String jpaDdlAuto;
		public String getApplicationName() {
			return applicationName;
		}
		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}
		public int getServerPort() {
			return serverPort;
		}
		public void setServerPort(int serverPort) {
			this.serverPort = serverPort;
		}
		public String getDatasourceUrl() {
			return datasourceUrl;
		}
		public void setDatasourceUrl(String datasourceUrl) {
			this.datasourceUrl = datasourceUrl;
		}
		public String getDatasourceUsername() {
			return datasourceUsername;
		}
		public void setDatasourceUsername(String datasourceUsername) {
			this.datasourceUsername = datasourceUsername;
		}
		public String getDatasourcePassword() {
			return datasourcePassword;
		}
		public void setDatasourcePassword(String datasourcePassword) {
			this.datasourcePassword = datasourcePassword;
		}
		public String getJpaDdlAuto() {
			return jpaDdlAuto;
		}
		public void setJpaDdlAuto(String jpaDdlAuto) {
			this.jpaDdlAuto = jpaDdlAuto;
		}
	    
	    
	

}
