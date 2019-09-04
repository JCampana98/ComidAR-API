package ar.edu.um.comidar.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.edu.um.comidar.utils.FileContainer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Indexed
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RESTAURANT_ID")
	private Long restaurantId;

	@NotNull
	@Field(termVector = TermVector.YES)
	@Column(name = "NAME")
	private String name;

	@NotNull
	@Field(termVector = TermVector.YES)
	@Column(name = "DESCRIPTION")
	private String description;

	@NotNull
	@Field(termVector = TermVector.YES)
	@Column(name = "DIRECTION")
	private String direction;

	@NotNull
	@Field(termVector = TermVector.YES)
	@Column(name = "TELEPHONE")
	private String telephone;

	@NotNull
	@Email
	@Column(name = "EMAIL")
	private String email;
	
	@ManyToMany
	private List<Category> categoryList;
	
	@Transient
	@JsonIgnore
	private FileContainer restaurantImage;
	
	@Column(name = "IMAGE_URL")
	private String imageUrl;
	
	@Transient
	private String imageTemporaryUrl;

	@JsonIgnore
	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@JsonIgnore
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
}
