package com.rrr.realestate.bo;

import java.io.Serializable;
import java.util.Collection;

public class MessageParamsVO implements Serializable {
	private static final long serialVersionUID = 7440297955003302423L;

	/*
	private String name;
	private String to;
	private String dates;
	*/
	private String cc;
	private String bcc;
	private String password;
	private String encodedListingId;

	private String title;
	private String subject;
    private String message;
	private String today;
	private String checkInDate;
	private String checkOutDate;
	private String receiverEmail;
	private String ownerEmail;
	private String paymentAmount;

	private Booking booking;


	private Collection<BookingPayments>	bp;

	public Collection<BookingPayments> getBp() {
		return bp;
	}
	public void setBp(Collection<BookingPayments> bp) {
		this.bp = bp;
	}
	private String bookingId;
    private OwnerDetails ownerDetails;
	private ListingDetails listingDetails;
    private Collection<ListingDirections> listingDirections;
    private Collection<ListingPolicies> listingPolicies;
    private Collection<ListingAmenities> listingAmenities;		//should not be used as a collection
	private Collection<ListingInstructions> listingInstructions;
    private Collection<ListingAttractions> listingAttractions;
    private Collection<ListingFacilities> listingFacilities;

    private String serverName;

    public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	//not a collection
    private ListingAmenities allListingAmenities;

    private ListingPricing pricing;


    public ListingAmenities getAllListingAmenities() {
		return allListingAmenities;
	}
	public void setAllListingAmenities(ListingAmenities allListingAmenities) {
		this.allListingAmenities = allListingAmenities;
	}

    public ListingPricing getPricing() {
		return pricing;
	}
	public void setPricing(ListingPricing pricing) {
		this.pricing = pricing;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getbookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}




	public Collection<ListingFacilities> getListingFacilities() {
		return listingFacilities;
	}
	public void setListingFacilities(Collection<ListingFacilities> listingFacilities) {
		this.listingFacilities = listingFacilities;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public OwnerDetails getOwnerDetails() {
		return ownerDetails;
	}
	public void setOwnerDetails(OwnerDetails ownerDetails) {
		this.ownerDetails = ownerDetails;
	}
	public ListingDetails getListingDetails() {
		return listingDetails;
	}
	public void setListingDetails(ListingDetails listingDetails) {
		this.listingDetails = listingDetails;
	}
	public Collection<ListingDirections> getListingDirections() {
		return listingDirections;
	}
	public void setListingDirections(Collection<ListingDirections> listingDirections) {
		this.listingDirections = listingDirections;
	}
	public Collection<ListingPolicies> getListingPolicies() {
		return listingPolicies;
	}
	public void setListingPolicies(Collection<ListingPolicies> listingPolicies) {
		this.listingPolicies = listingPolicies;
	}
	public Collection<ListingAmenities> getListingAmenities() {
		return listingAmenities;
	}
	public void setListingAmenities(Collection<ListingAmenities> listingAmenities) {
		this.listingAmenities = listingAmenities;
	}
	public Collection<ListingInstructions> getListingInstructions() {
		return listingInstructions;
	}
	public void setListingInstructions(Collection<ListingInstructions> listingInstructions) {
		this.listingInstructions = listingInstructions;
	}
	public Collection<ListingAttractions> getListingAttractions() {
		return listingAttractions;
	}
	public void setListingAttractions(Collection<ListingAttractions> listingAttractions) {
		this.listingAttractions = listingAttractions;
	}



	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncodedListingId() {
		return encodedListingId;
	}
	public void setEncodedListingId(String encodedListingId) {
		this.encodedListingId = encodedListingId;
	}
}
