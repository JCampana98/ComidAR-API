package ar.edu.um.comidar.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	@Column(name="RESTAURANT_ID")
	private Long restaurant_id;

	@NotNull
	@Column(name="PRICE")
	private Double price;

	@NotNull
	@Column(name="ENABLE")
	private Boolean enable;

	@NotNull
	@Column(name="CREATION_DATE")
	private Date creationDate;

	@NotNull
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;
}
