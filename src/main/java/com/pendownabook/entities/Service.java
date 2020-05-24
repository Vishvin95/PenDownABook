package com.pendownabook.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "serviceCode")})
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String serviceCode;

	private String serviceTitle;

	@Column(length = 4096)
	private String serviceDescription;

	private int servicePeriod;
	private double serviceCost;

	@JsonManagedReference(value = "service_subscription")
	@OneToMany(mappedBy = "service")
	private Set<Subscription> subscriptions;

	public Service() {

	}

	public Service(Long id) {
		this.id = id;
	}

	public Service(String serviceCode, String serviceTitle, String serviceDescription, int servicePeriod,
			double serviceCost) {
		this.serviceCode = serviceCode;
		this.serviceTitle = serviceTitle;
		this.serviceDescription = serviceDescription;
		this.servicePeriod = servicePeriod;
		this.serviceCost = serviceCost;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public String getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public int getServicePeriod() {
		return servicePeriod;
	}

	public void setServicePeriod(int servicePeriod) {
		this.servicePeriod = servicePeriod;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
	}
}
