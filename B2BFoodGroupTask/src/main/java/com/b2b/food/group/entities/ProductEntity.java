package com.b2b.food.group.entities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "product_table")
@Indexed
@Data
public class ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7493827810934546944L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private String vendorUID;

	@Column(name = "title", unique = true)
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String title;

	@Column(name = "description")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String description;

	private double price;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private ImageEntity imageEntity;

	private Integer numberOfViews;
	private Integer numberOfBadImpressions;
	private Integer numberOfGoodImpressions;

//	Dietary flags: vegan, lactose-free, and more flags to com
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name = "product_dietary", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "dietary_id"))
	private Set<DietaryEntity> dietarySet = new LinkedHashSet<DietaryEntity>();

}
