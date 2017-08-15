package com.kafeneio.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="link")
public class LinkModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,unique=true,nullable=false )
	private Long Id;
	@Column(name="page_title")
	private String pageTitle;
	@Column(name="page_link")
	private String pageLink;
	    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 	@JoinColumn(name="link_id")
	 	private WorkAssignmentModel workAssignment;
	    
	    
	    
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
		public WorkAssignmentModel getWorkAssignment() {
			return workAssignment;
		}
		public void setWorkAssignment(WorkAssignmentModel workAssignment) {
			this.workAssignment = workAssignment;
		}
}
