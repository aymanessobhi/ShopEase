package com.ideracloud.salewell.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity<T> implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	Long id;
	
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	Date createdDate;
	
	@LastModifiedDate
	Date lastModifiedDate;
	
	@CreatedBy
	String createdBy;
	
	@LastModifiedBy
	String lastModifiedBy;
	
	

}
