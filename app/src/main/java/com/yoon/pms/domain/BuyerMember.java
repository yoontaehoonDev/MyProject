package com.yoon.pms.domain;

import java.sql.Date;
import com.yoon.util.CsvObject;

public class BuyerMember implements CsvObject {

  private boolean division;
  private int hash;
  private int number;

  private String id;
  private String password;
  private String name;
  private String nickname;
  private String email;
  private String phone;
  private String address;
  private Date RegisteredDate;
  private int count;

  public BuyerMember() {}

  public BuyerMember(String csv) {

    String[] fields = csv.split(",");

    this.setHash(Integer.parseInt(fields[0]));
    this.setNumber(Integer.parseInt(fields[1]));
    this.setId(fields[2]);
    this.setPassword(fields[3]);
    this.setName(fields[4]);
    this.setNickname(fields[5]);
    this.setEmail(fields[6]);
    this.setPhone(fields[7]);
    this.setRegisteredDate(Date.valueOf(fields[8]));
  }

  @Override
  public String toCsvString() {
    return String.format("%d,%d,%s,%s,%s,%s,%s,%s,%s",
        this.getHash(),
        this.getNumber(),
        this.getId(),
        this.getPassword(),
        this.getName(),
        this.getNickname(),
        this.getEmail(),
        this.getPhone(),
        this.getRegisteredDate().toString());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + (division ? 1231 : 1237);
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((phone == null) ? 0 : phone.hashCode());
    result = prime * result + hash;
    result = prime * result + number;
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
    BuyerMember other = (BuyerMember) obj;
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
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
    if (nickname == null) {
      if (other.nickname != null)
        return false;
    } else if (!nickname.equals(other.nickname))
      return false;
    if (number != other.number)
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
  public int getHash() {
    return hash;
  }
  public void setHash(int hash) {
    this.hash = hash;
  }
  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
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
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
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
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }

}
