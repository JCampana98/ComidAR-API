package ar.edu.um.comidar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PAYMENT_METHODS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PaymentMethod implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1688183184176746662L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PAYMENT_METHOD_ID")
	private Long paymentMethodId;

	@NotNull
	@Column(name="NAME")
	private String name;
}
