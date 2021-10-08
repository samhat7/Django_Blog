/*
 * Salesorder.java
 *
 * Created on Nov 22, 2007, 6:07:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.embed;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;

/**
 *
 * @author pogi
 */
@Embeddable
public class AbstractSalesOrPurchase implements Serializable {
    public int partnerId;
    public String referenceNumber;
    public String contactPerson;
    public String currency;
    public String status;
    @Temporal(value = TemporalType.DATE)
    public Date postingDate;
    @Temporal(value = TemporalType.DATE)
    public Date deliveryDate;
    @Temporal(value = TemporalType.DATE)
    public Date documentDate;
    public String itemType;	//this may be service or item
    public int employeeId;
    public double totalBeforeDiscount;
    public double discountPercentage;
    public double discountAmount;
    public double freightAmount;
    public int rounding;
    public double taxAmount;
    public double totalAmount;
    public int invoiceId;
    @Embedded
    public AbstractLogistics logistics;
    @Embedded
    public AbstractAccounting accounting;

	public AbstractLogistics getLogistics() {
		return logistics;
	}

	public void setLogistics(AbstractLogistics logistics) {
		this.logistics = logistics;
	}

	public AbstractAccounting getAccounting() {
		return accounting;
	}

	public void setAccounting(AbstractAccounting accounting) {
		this.accounting = accounting;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public double getTotalBeforeDiscount() {
		return totalBeforeDiscount;
	}

	public void setTotalBeforeDiscount(double totalBeforeDiscount) {
		this.totalBeforeDiscount = totalBeforeDiscount;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getFreightAmount() {
		return freightAmount;
	}

	public void setFreightAmount(double freightAmount) {
		this.freightAmount = freightAmount;
	}

	public int getRounding() {
		return rounding;
	}

	public void setRounding(int rounding) {
		this.rounding = rounding;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}
