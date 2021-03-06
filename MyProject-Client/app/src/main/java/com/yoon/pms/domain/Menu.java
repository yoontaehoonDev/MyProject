package com.yoon.pms.domain;

public class Menu {

  private int id; // 메뉴 고유번호
  private int categoryId;
  private int number; // 메뉴 인덱스
  private String owner; // 상호명
  private String name; // 메뉴 이름
  private int price; // 메뉴 가격
  private String explain; // 메뉴 설명
  private int count; // 개수 세기

  public Menu() {}

  public Menu(String csv) {
    String[] fields = csv.split(",");
    this.setId(Integer.parseInt(fields[0]));
    this.setNumber(Integer.parseInt(fields[1]));
    this.setOwner(fields[2]);
    this.setName(fields[3]);
    this.setPrice(Integer.parseInt(fields[4]));
    this.setExplain(fields[5]);
  }

  public String toCsvString() {
    return String.format("%d,%d,%s,%s,%d,%s", 
        this.getId(),
        this.getNumber(),
        this.getOwner(),
        this.getName(),
        this.getPrice(),
        this.getExplain());
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + number;
    result = prime * result + ((owner == null) ? 0 : owner.hashCode());
    result = prime * result + price;
    result = prime * result + categoryId;
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
    Menu other = (Menu) obj;
    if (id != other.id)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (number != other.number)
      return false;
    if(categoryId != other.categoryId)
      return false;
    if (owner == null) {
      if (other.owner != null)
        return false;
    } else if (!owner.equals(other.owner))
      return false;
    if (price != other.price)
      return false;
    return true;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getCategoryId() {
    return categoryId;
  }
  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
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
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getExplain() {
    return explain;
  }
  public void setExplain(String explain) {
    this.explain = explain;
  }
  public String getOwner() {
    return owner;
  }
  public void setOwner(String owner) {
    this.owner = owner;
  }

}
