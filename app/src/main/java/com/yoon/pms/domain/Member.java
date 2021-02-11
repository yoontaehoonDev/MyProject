package com.yoon.pms.domain;

import java.sql.Date;

public class Member {
	// 구매자, 판매자 번호 및 해쉬값
	private int buyerNumber;
	private int sellerNumber;
	private int hash;

	// 구매자와 판매자 공통 필드
	private boolean division;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;
	private Date RegisteredDate;

	// 구매자 필드
	private String nickname;


	// 판매자 필드
	private String businessName;
	private String businessNumber;
	private String businessHour;
	private String category;
	private String menu;
	private String menuExplaination;
	private String explaination;
	private int price;



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((RegisteredDate == null) ? 0 : RegisteredDate.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((businessHour == null) ? 0 : businessHour.hashCode());
		result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
		result = prime * result + ((businessNumber == null) ? 0 : businessNumber.hashCode());
		result = prime * result + buyerNumber;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((explaination == null) ? 0 : explaination.hashCode());
		result = prime * result + hash;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((menuExplaination == null) ? 0 : menuExplaination.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + price;
		result = prime * result + sellerNumber;

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
		Member other = (Member) obj;
		if (RegisteredDate == null) {
			if (other.RegisteredDate != null)
				return false;
		} else if (!RegisteredDate.equals(other.RegisteredDate))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (businessHour == null) {
			if (other.businessHour != null)
				return false;
		} else if (!businessHour.equals(other.businessHour))
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
		if (buyerNumber != other.buyerNumber)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (explaination == null) {
			if (other.explaination != null)
				return false;
		} else if (!explaination.equals(other.explaination))
			return false;
		if (hash != other.hash)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (menu == null) {
			if (other.menu != null)
				return false;
		} else if (!menu.equals(other.menu))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (menuExplaination == null) {
			if (other.menuExplaination != null)
				return false;
		} else if (!menuExplaination.equals(other.menuExplaination))
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
		if (price != other.price)
			return false;
		if (sellerNumber != other.sellerNumber)
			return false;
		return true;
	}
	public int getBuyerNumber() {
		return buyerNumber;
	}
	public void setBuyerNumber(int buyerNumber) {
		this.buyerNumber = buyerNumber;
	}
	public int getSellerNumber() {
		return sellerNumber;
	}
	public void setSellerNumber(int sellerNumber) {
		this.sellerNumber = sellerNumber;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public void setCategory(String Category) {
		this.category = Category;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getMenuExplaination() {
		return menuExplaination;
	}
	public void setMenuExplaination(String menuExplaination) {
		this.menuExplaination = menuExplaination;
	}
	public String getExplaination() {
		return explaination;
	}
	public void setExplaination(String explaination) {
		this.explaination = explaination;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isDivision() {
		return division;
	}
	public void setDivision(boolean division) {
		this.division = division;
	}

}
