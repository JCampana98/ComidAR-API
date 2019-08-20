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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="RESTAURANTS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Restaurant implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2914412949573579783L;

	@Id
	@GeneratedValue(generator = "restaurant_gen")
	@SequenceGenerator(
			name = "restaurant_gen",
			sequenceName = "restaurant_seq",
			initialValue = 1)
	@Column(name="RESTAURANT_ID")
	private Long restaurantId;

	@NotNull
	@Column(name = "NAME")
	private String name;

	@NotNull
	@Column(name = "DESCRIPTION")
	private String description;

	@NotNull
	@Column(name = "DIRECTION")
	private String direction;

	@NotNull
	@Column(name = "TELEPHONE")
	private String telephone;

	@NotNull
	@Email
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
}
