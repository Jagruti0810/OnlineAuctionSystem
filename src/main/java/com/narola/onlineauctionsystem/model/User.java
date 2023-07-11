package com.narola.onlineauctionsystem.model;

public class User {
    private String username;
    private String password;
    private String email;
    private String mobileNo;
    private String address;
    private String verificationCode;
    private boolean isVerified;
    private int roleId;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int role;
    private String roleType;
//private int userId;
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public User(int role, String username, String email) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.address = address;
    }

    public User() {

    }



    public int getRoleId() {
        return roleId;
    }

    public int setRoleId(int roleId) {
        this.roleId = roleId;
        return roleId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean setVerified(boolean isVerified) {
        this.isVerified = isVerified;
        return isVerified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}