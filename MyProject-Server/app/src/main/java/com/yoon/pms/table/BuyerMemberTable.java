package com.yoon.pms.table;

import java.io.File;
import java.sql.Date;
import java.util.List;

import com.yoon.pms.domain.BuyerMember;
import com.yoon.util.JsonFileHandler;
import com.yoon.util.Request;
import com.yoon.util.Response;

public class BuyerMemberTable implements DataTable {

	File jsonFile = new File("buyerMembers.json");
	List<BuyerMember> list;

	public BuyerMemberTable() {
		list = JsonFileHandler.loadObjects(jsonFile, BuyerMember.class);

	}

	@Override
	public void service(Request request, Response response) throws Exception {

		BuyerMember buyer = null;
		String[] fields = null;

		switch(request.getCommand()) {
		case "buyerMember/insert":

			fields = request.getDataList().get(0).split(",");

			buyer = new BuyerMember();

			if(list.size() > 0) {
				buyer.setHash(list.get(list.size() - 1).getNumber() + 1);
				buyer.setNumber(list.get(list.size() - 1).getNumber() + 1);
			}
			else {
				buyer.setHash(1);
				buyer.setNumber(1);
			}

			buyer.setId(fields[1]);
			buyer.setPassword(fields[2]);
			buyer.setName(fields[3]);
			buyer.setNickname(fields[4]);
			buyer.setEmail(fields[5]);
			buyer.setPhone(fields[6]);
			buyer.setRegisteredDate(new Date(System.currentTimeMillis()));

			list.add(buyer);

			JsonFileHandler.saveObjects(jsonFile, list);
			break;
		case "buyerMember/selectall":
			for(BuyerMember b : list) {
				response.appendData(String.format("%d,%s,%s,%s,%s,%s", 
						b.getNumber(),
						b.getId(),
						b.getName(),
						b.getNickname(),
						b.getEmail(),
						b.getPhone(),
						b.getRegisteredDate()));
			}
			break;

		case "buyerMember/count":
			int count = countBuyerMember();

			if(count > 0) {

			}
			else {

			}
		case "buyerMember/verifyId":
			String id = request.getDataList().get(1);

			buyer = verifyBuyerId(id);

			if(buyer != null) {
				response.appendData(String.format("%d,%s,%s,%s,%s,%s", 
						buyer.getNumber(),
						buyer.getId(),
						buyer.getName(),
						buyer.getNickname(),
						buyer.getEmail(),
						buyer.getPhone(),
						buyer.getRegisteredDate()));
			}
			else {
				throw new Exception("존재하지 않는 ID입니다.");
			}

			break;
		case "buyerMember/select":
			int num = Integer.parseInt(request.getDataList().get(0));

			buyer = getBuyerNumber(num);

			if(buyer != null) {
				response.appendData(String.format("%d,%s,%s,%s,%s,%s", 
						buyer.getNumber(),
						buyer.getId(),
						buyer.getName(),
						buyer.getNickname(),
						buyer.getEmail(),
						buyer.getPhone(),
						buyer.getRegisteredDate()));
			}
			else {
				throw new Exception("해당 번호의 Buyer가 없습니다.");
			}
			break;
		case "buyerMember/update":
			fields = request.getDataList().get(0).split(",");

			buyer = getBuyerNumber(Integer.parseInt(fields[0]));
			if(buyer == null) {
				throw new Exception("해당 번호의 Buyer가 없습니다.");
			}

			buyer.setId(fields[1]);
			buyer.setPassword(fields[2]);
			buyer.setName(fields[3]);
			buyer.setNickname(fields[4]);
			buyer.setEmail(fields[5]);
			buyer.setPhone(fields[6]);
			JsonFileHandler.saveObjects(jsonFile, list);
			break;
		case "buyerMember/delete":
			num = Integer.parseInt(request.getDataList().get(0));
			buyer = getBuyerNumber(num);
			if(buyer == null) {
				throw new Exception("해당 번호의 Buyer가 없습니다.");
			}
			list.remove(buyer);
			JsonFileHandler.saveObjects(jsonFile, list);
			break;
		default:
			throw new Exception("해당 명령을 처리할 수 없습니다.");
		}
	}

	private BuyerMember getBuyerNumber(int number) {
		for(BuyerMember b : list) {
			if(b.getNumber() == number) {
				return b;
			}
		}
		return null;
	}

	private int countBuyerMember() {
		for(BuyerMember b : list) {
			if(b.getHash() != 0) {
				return 1;
			}
		}
		return 0;
	}

	private BuyerMember verifyBuyerId(String id) {
		for(BuyerMember b : list) {
			if(id.equalsIgnoreCase(b.getId())) {
				return b;
			}
		}
		return null;
	}

}
