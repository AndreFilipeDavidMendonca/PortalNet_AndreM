package com.polarising.PortalNet.model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Services {

	@javax.persistence.Id
    private Long serviceID; //serviceID
    private String name; //serviceName
    private String internet; //internet
    private String tv; //tv
    private String phone; //phone
    private String mobilePhone; //mobilePhone
    private int loyalty; //loyalty
    private float price; //price
    private String creationDate; //creationDate
    private boolean status; //active
    private String imgUrl; //ImageURL
    private String imgName; 
    
    
    public Services() {}

    public Services(Long serviceID, String name, String internet, String tv, String phone, String mobilePhone, int loyalty, float price, String creationDate, boolean status, String imgUrl, String imgName) {
    	this.serviceID = serviceID;
    	this.imgName = imgUrl;
    	this.imgUrl = imgUrl;
    	this.name = name;
        this.internet = internet;
        this.tv = tv;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.loyalty = loyalty;
        this.price = price;
        this.creationDate = creationDate;
        this.status = status;
    }
    
	public Services(String name, String internet, String tv, String phone, String mobilePhone, int loyalty, float price,
			String creationDate, boolean status, String imgUrl, String imgName) {
		super();
		this.name = name;
		this.internet = internet;
		this.tv = tv;
		this.phone = phone;
		this.mobilePhone = mobilePhone;
		this.loyalty = loyalty;
		this.price = price;
		this.creationDate = creationDate;
		this.status = status;
		this.imgUrl = imgUrl;
		this.imgName = imgUrl;

		
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getServiceID() {
		return serviceID;
	}

	public String getName() {
		return name;
	}

	public String getInternet() {
		return internet;
	}

	public String getTv() {
		return tv;
	}

	public String getPhone() {
		return phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public int getLoyalty() {
		return loyalty;
	}

	public float getPrice() {
		return price;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setServiceID(Long serviceID) {
		this.serviceID = serviceID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInternet(String internet) {
		this.internet = internet;
	}

	public void setTv(String tv) {
		this.tv = tv;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Services [serviceID=" + serviceID + ", name=" + name + ", internet=" + internet + ", tv=" + tv
				+ ", phone=" + phone + ", mobilePhone=" + mobilePhone + ", loyalty=" + loyalty + ", price=" + price
				+ ", creationDate=" + creationDate + ", status=" + status + ", imgUrl=" + imgUrl + ", imgName="
				+ imgName + "]";
	}
}

