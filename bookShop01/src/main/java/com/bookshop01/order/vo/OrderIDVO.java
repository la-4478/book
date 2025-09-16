package com.bookshop01.order.vo;

import org.springframework.stereotype.Component;

@Component("OrderIDVO")
public class OrderIDVO {
	 private int id;
	 private String delivery_state;
	 private String member_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDelivery_state() {
		return delivery_state;
	}

	public void setDelivery_state(String delivery_state) {
		this.delivery_state = delivery_state;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

}
