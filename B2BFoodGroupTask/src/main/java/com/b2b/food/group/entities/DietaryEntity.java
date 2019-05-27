package com.b2b.food.group.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "dietary_table")
@Data
public class DietaryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7534106848832574436L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "flagName", unique = true)
	private String flagName;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name = "product_dietary", joinColumns = @JoinColumn(name = "dietary_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<ProductEntity> products = new ArrayList<>();
}
