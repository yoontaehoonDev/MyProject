package com.yoon.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {

  private static final long serialVersionUID = 1L;
  private int firstId;
  private int secondId;
  private int number;
  private String name;
  private String address;
  private String phone;
  private Date orderTime;
  private Date servingTime;
  private int time;
  private int sum;
  private String foodList;
  // 영수증, 영수증 ID, 금액 등

  private int koreanId = 1;
  private int westernId = 2;
  private int japaneseId = 3;
  private int chineseId = 4;
  private int snackId = 5;
  private int chickenId = 6;
  private int pizzaId = 7;
  private int dessertId = 8;
  private int nightSnackId = 9;



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + chickenId;
    result = prime * result + chineseId;
    result = prime * result + dessertId;
    result = prime * result + firstId;
    result = prime * result + japaneseId;
    result = prime * result + koreanId;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + nightSnackId;
    result = prime * result + number;
    result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
    result = prime * result + ((phone == null) ? 0 : phone.hashCode());
    result = prime * result + pizzaId;
    result = prime * result + secondId;
    result = prime * result + ((servingTime == null) ? 0 : servingTime.hashCode());
    result = prime * result + snackId;
    result = prime * result + sum;
    result = prime * result + time;
    result = prime * result + westernId;
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
    Order other = (Order) obj;
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
      return false;
    if (chickenId != other.chickenId)
      return false;
    if (chineseId != other.chineseId)
      return false;
    if (dessertId != other.dessertId)
      return false;
    if (firstId != other.firstId)
      return false;
    if (japaneseId != other.japaneseId)
      return false;
    if (koreanId != other.koreanId)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (nightSnackId != other.nightSnackId)
      return false;
    if (number != other.number)
      return false;
    if (orderTime == null) {
      if (other.orderTime != null)
        return false;
    } else if (!orderTime.equals(other.orderTime))
      return false;
    if (phone == null) {
      if (other.phone != null)
        return false;
    } else if (!phone.equals(other.phone))
      return false;
    if (pizzaId != other.pizzaId)
      return false;
    if (secondId != other.secondId)
      return false;
    if (servingTime == null) {
      if (other.servingTime != null)
        return false;
    } else if (!servingTime.equals(other.servingTime))
      return false;
    if (snackId != other.snackId)
      return false;
    if (sum != other.sum)
      return false;
    if (time != other.time)
      return false;
    if (westernId != other.westernId)
      return false;
    return true;
  }



  public Date getServingTime() {
    return servingTime;
  }

  public void setServingTime(Date servingTime) {
    this.servingTime = servingTime;
  }

  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }

  public int getKoreanId() {
    return koreanId;
  }

  public void setKoreanId(int koreanId) {
    this.koreanId = koreanId;
  }

  public int getWesternId() {
    return westernId;
  }

  public void setWesternId(int westernId) {
    this.westernId = westernId;
  }

  public int getJapaneseId() {
    return japaneseId;
  }

  public void setJapaneseId(int japaneseId) {
    this.japaneseId = japaneseId;
  }

  public int getChineseId() {
    return chineseId;
  }

  public void setChineseId(int chineseId) {
    this.chineseId = chineseId;
  }

  public int getSnackId() {
    return snackId;
  }

  public void setSnackId(int snackId) {
    this.snackId = snackId;
  }

  public int getChickenId() {
    return chickenId;
  }

  public void setChickenId(int chickenId) {
    this.chickenId = chickenId;
  }

  public int getPizzaId() {
    return pizzaId;
  }

  public void setPizzaId(int pizzaId) {
    this.pizzaId = pizzaId;
  }

  public int getDessertId() {
    return dessertId;
  }

  public void setDessertId(int dessertId) {
    this.dessertId = dessertId;
  }

  public int getNightSnackId() {
    return nightSnackId;
  }

  public void setNightSnackId(int nightSnackId) {
    this.nightSnackId = nightSnackId;
  }

  public int getFirstId() {
    return firstId;
  }
  public void setFirstId(int firstId) {
    this.firstId = firstId;
  }
  public int getSecondId() {
    return secondId;
  }
  public void setSecondId(int secondId) {
    this.secondId = secondId;
  }
  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
  public Date getOrderTime() {
    return orderTime;
  }
  public void setOrderTime(Date orderTime) {
    this.orderTime = orderTime;
  }
  public int getTime() {
    return time;
  }
  public void setTime(int time) {
    this.time = time;
  }

  // 재주문율 시스템

}
