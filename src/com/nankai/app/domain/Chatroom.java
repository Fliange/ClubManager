package com.nankai.app.domain;

public class Chatroom {
	private int messageId;
	private int messageAuthor;
	private String messageContent;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getMessageAuthor() {
		return messageAuthor;
	}
	public void setMessageAuthor(int messageAuthor) {
		this.messageAuthor = messageAuthor;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
}
