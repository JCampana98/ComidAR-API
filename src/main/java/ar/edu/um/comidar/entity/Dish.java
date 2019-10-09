package ar.edu.um.comidar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.edu.um.comidar.utils.FileContainer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="DISHES")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Dish implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2397554997009542760L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DISH_ID")
	private Long dishId;
	
	@NotNull
	@Column(name="NAME")
	private String name;

	@NotNull
	@Column(name="DESCRIPTION")
	private String description;

	@NotNull
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Restaurant restaurant;

	@NotNull
	@Column(name="PRICE")
	private BigDecimal price;

	@Column(name="ENABLE")
	private Boolean enable;

	@Column(name="CREATION_DATE")
	@JsonIgnore
	private Date creationDate;

	@Column(name="LAST_UPDATE_DATE")
	@JsonIgnore
	private Date lastUpdateDate;

	@Transient
	@JsonIgnore
	private FileContainer dishImage;

	@Column(name = "IMAGE_URL")
	@JsonIgnore
	private String imageUrl;
	
	@Transient
	private String imageTemporaryUrl;
}
