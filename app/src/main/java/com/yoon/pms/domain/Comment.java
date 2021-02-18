package com.yoon.pms.domain;

public class Comment {
  private String commentWriter;
  private String comment;
  private int commentId;
  private int commentNumber;
  private int nestedCommentNumber;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + nestedCommentNumber;
    result = prime * result + commentNumber;
    result = prime * result + commentId;
    result = prime * result + ((commentWriter == null) ? 0 : commentWriter.hashCode());
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
    Comment other = (Comment) obj;
    if (commentId != other.commentId)
      return false;
    if (commentNumber != other.commentNumber)
      return false;
    if (nestedCommentNumber != other.nestedCommentNumber)
      return false;
    if (commentWriter == null) {
      if (other.commentWriter != null)
        return false;
    } else if (!commentWriter.equals(other.commentWriter))
      return false;
    return true;
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
