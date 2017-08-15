package com.kafeneio.DTO;

public class LinkDto {
private Long Id;
private String pageTitle;
private String pageLink;
private Boolean status;


public Boolean getStatus() {
	return status;
}
public void setStatus(Boolean status) {
	this.status = status;
}
public Long getId() {
	return Id;
}
public void setId(Long id) {
	Id = id;
}
public String getPageTitle() {
	return pageTitle;
}
public void setPageTitle(String pageTitle) {
	this.pageTitle = pageTitle;
}
public String getPageLink() {
	return pageLink;
}
public void setPageLink(String pageLink) {
	this.pageLink = pageLink;
}
}
