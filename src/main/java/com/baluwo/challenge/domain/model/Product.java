package com.baluwo.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Products")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "product_sequence")
	@SequenceGenerator(name="product_sequence", sequenceName="product_seq")
	private Long id;

	@NotBlank(message = "Name is required")
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank(message = "Description is required")
	@Column(name = "description", nullable = false)
	private String description;

	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.01", message = "Price should be higher than 0.01")
	@Column(name = "price", nullable = false)
	private BigDecimal  price;

	@Override
	public String toString() {
		return "ApplicationProduct [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}
}
