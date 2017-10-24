package com.kafeneio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="KAFENEIO_UNITS")
public class Units {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="id" ,unique=true,nullable=false )
		private Long id;
		@Column(unique = true, name="code")
		private String code;
		@Column(name="description")
		private String description;
		@Column(name="status")
		private boolean status;
		@Column(name="creation_date")
		private Date date;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		
}