package ar.edu.um.comidar.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name="CATEGORIES")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7789781476530056964L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CATEGORY_ID")
	private Long categoryId;

	@NotNull
	@Column(name="NAME")
	private String name;

	@Transient
	@JsonIgnore
	private FileContainer categoryImage;

	@Column(name = "IMAGE_URL")
	@JsonIgnore
	private String imageUrl;
	
	@Transient
	private String imageTemporaryUrl;	
}
