package com.pendownabook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pendownabook.entities.Subscription;
import com.pendownabook.entities.SubscriptionId;
import com.pendownabook.entities.User;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId>{
	List<Subscription> findByUser(User user);

	Subscription findByOrderId(String orderId);

	void deleteByOrderId(String orderId);
}
