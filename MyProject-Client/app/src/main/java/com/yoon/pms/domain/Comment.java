package com.yoon.pms.domain;

public class Comment {


	private int division;
	private String commentWriter;
	private String comment;
	private int commentId;
	private int commentNumber;
	private int nestedCommentNumber;


	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	public int getNestedCommentNumber() {
		return nestedCommentNumber;
	}
	public void setNestedCommentNumber(int nestedCommentNumber) {
		this.nestedCommentNumber = nestedCommentNumber;
	}


}
