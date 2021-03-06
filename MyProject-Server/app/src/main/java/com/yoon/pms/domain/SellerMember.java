package com.yoon.pms.domain;

import java.sql.Date;

public class SellerMember {

  private boolean division;
  private int hash;
  private int number;
  private String id;
  private String password;
  private String name;
  private String email;
  private String phone;
  private String address;
  private String businessName;
  private String businessNumber;
  private String businessHour;
  private String category;
  private int categoryId;
  private int categoryNumber;
  private Date RegisteredDate;

  private int count;

  public SellerMember() {}

  public SellerMember(String csv) {
    String[] fields = csv.split(",");

    this.setHash(Integer.parseInt(fields[0]));
    this.setNumber(Integer.parseInt(fields[1]));
    this.setId(fields[2]);
    this.setPassword(fields[3]);
    this.setName(fields[4]);
    this.setEmail(fields[5]);
    this.setPhone(fields[6]);
    this.setAddress(fields[7]);
    this.setBusinessName(fields[8]);
    this.setBusinessNumber(fields[9]);
    this.setBusinessHour(fields[10]);
    this.setCategory(fields[11]);
    this.setCategoryId(Integer.parseInt(fields[12]));
    this.setRegisteredDate(Date.valueOf(fields[13]));
  }

  public String toCsvString() {
    return String.format("%d,%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%d,%s", 
        this.getHash(),
        this.getNumber(),
        this.getId(),
        this.getPassword(),
        this.getName(),
        this.getEmail(),
        this.getPhone(),
        this.getAddress(),
        this.getBusinessName(),
        this.getBusinessNumber(),
        this.getBusinessHour(),
        this.getCategory(),
        this.getCategoryId(),
        this.getRegisteredDate().toString());
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
    result = prime * result + ((businessNumber == null) ? 0 : businessNumber.hashCode());
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + (division ? 1231 : 1237);
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + hash;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + number;
    result = prime * result + categoryNumber;
    result = prime * result + categoryId;
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((phone == null) ? 0 : phone.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SellerMember other = (SellerMember) obj;
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
      return false;
    if (businessName == null) {
      if (other.businessName != null)
        return false;
    } else if (!businessName.equals(other.businessName))
      return false;
    if (businessNumber == null) {
      if (other.businessNumber != null)
        return false;
    } else if (!businessNumber.equals(other.businessNumber))
      return false;
    if (category == null) {
      if (other.category != null)
        return false;
    } else if (!category.equals(other.category))
      return false;
    if (division != other.division)
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (hash != other.hash)
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (number != other.number)
      return false;
    if (categoryId != other.categoryId)
      return false;
    if (categoryNumber != other.categoryNumber)
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (phone == null) {
      if (other.phone != null)
        return false;
    } else if (!phone.equals(other.phone))
      return false;
    return true;
  }
  public boolean isDivision() {
    return division;
  }
  public void setDivision(boolean division) {
    this.division = division;
  }
  public int getCategoryId() {
    return categoryId;
  }
  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }
  public int getCategoryNumber() {
    return categoryNumber;
  }
  public void setCategoryNumber(int categoryNumber) {
    this.categoryNumber = categoryNumber;
  }
  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public int getHash() {
    return hash;
  }
  public void setHash(int hash) {
    this.hash = hash;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public Date getRegisteredDate() {
    return RegisteredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    RegisteredDate = registeredDate;
  }
  public String getBusinessName() {
    return businessName;
  }
  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }
  public String getBusinessNumber() {
    return businessNumber;
  }
  public void setBusinessNumber(String businessNumber) {
    this.businessNumber = businessNumber;
  }
  public String getBusinessHour() {
    return businessHour;
  }
  public void setBusinessHour(String businessHour) {
    this.businessHour = businessHour;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }


}
