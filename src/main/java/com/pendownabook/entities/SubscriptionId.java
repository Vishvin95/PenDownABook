package com.pendownabook.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SubscriptionId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private Long serviceId;

	public SubscriptionId() {

	}

	public SubscriptionId(Long userId, Long serviceId) {
		super();
		this.userId = userId;
		this.serviceId = serviceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	
	@Override
	public boolean equals(Object obj) {
		SubscriptionId subscriptionId = (SubscriptionId) obj;
		if(this.userId == subscriptionId.getUserId() 
				&& this.serviceId == subscriptionId.getServiceId())
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
}
