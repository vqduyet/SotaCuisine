/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Duyet
 */
public class ShippingInfo {
    private int memberId;
    private String customerName;
    private int districtId;
    private String address;
    private String phone;    

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ShippingInfo() {
    }

    public ShippingInfo(int memberId, String customerName, int districtId, String address, String phone) {
        this.memberId = memberId;
        this.customerName = customerName;
        this.districtId = districtId;
        this.address = address;
        this.phone = phone;
    }
    
    
}
