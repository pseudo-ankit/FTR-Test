package com.infosys.ftr.workitems.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ftr_workitems")
public class WorkItemEntity {

	@Id
	@GenericGenerator(name = "generator_workitemid", strategy = "com.infosys.ftr.workitems.utility.WorkItemIdGenerator")
	@GeneratedValue(generator = "generator_workitemid")
	@Column(name="workitem_id")
	private String workItemId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="item_type")
	private String itemType;
	
	@Column(name="item_description")
	private String itemDescription;
	
	@Column(name="message_to_recipient")
	private String messageToRecipient;
	
	private String quantity;
	
	@Column(name="source_country")
	private String sourceCountry;
	
	@Column(name="destination_country")
	private String destinationCountry;
	
	@Column(name="available_harbor_location")
	private String availableHarborLocations;
	
	@Column(name="shipping_date")
	private LocalDate shippingDate;
	
	private Long amount;

	/**
	 * @return the workItemId
	 */
	public String getWorkItemId() {
		return workItemId;
	}

	/**
	 * @param workItemId the workItemId to set
	 */
	public void setWorkItemId(String workItemId) {
		this.workItemId = workItemId;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * @return the messageToRecipient
	 */
	public String getMessageToRecipient() {
		return messageToRecipient;
	}

	/**
	 * @param messageToRecipient the messageToRecipient to set
	 */
	public void setMessageToRecipient(String messageToRecipient) {
		this.messageToRecipient = messageToRecipient;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the sourceCountry
	 */
	public String getSourceCountry() {
		return sourceCountry;
	}

	/**
	 * @param sourceCountry the sourceCountry to set
	 */
	public void setSourceCountry(String sourceCountry) {
		this.sourceCountry = sourceCountry;
	}

	/**
	 * @return the destinationCountry
	 */
	public String getDestinationCountry() {
		return destinationCountry;
	}

	/**
	 * @param destinationCountry the destinationCountry to set
	 */
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	/**
	 * @return the availableHarborLocations
	 */
	public String getAvailableHarborLocations() {
		return availableHarborLocations;
	}

	/**
	 * @param availableHarborLocations the availableHarborLocations to set
	 */
	public void setAvailableHarborLocations(String availableHarborLocations) {
		this.availableHarborLocations = availableHarborLocations;
	}

	/**
	 * @return the shippingDate
	 */
	public LocalDate getShippingDate() {
		return shippingDate;
	}

	/**
	 * @param shippingDate the shippingDate to set
	 */
	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	/**
	 * @return the amount
	 */
	public Long getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "WorkItemEntity [workItemId=" + workItemId + ", userId=" + userId + ", itemName=" + itemName
				+ ", itemType=" + itemType + ", itemDescription=" + itemDescription + ", messageToRecipient="
				+ messageToRecipient + ", quantity=" + quantity + ", sourceCountry=" + sourceCountry
				+ ", destinationCountry=" + destinationCountry + ", availableHarborLocations=" + availableHarborLocations
				+ ", shippingDate=" + shippingDate + ", amount=" + amount + "]";
	}
	
}
