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
import javax.validation.constraints.NotNull;

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
	
}
