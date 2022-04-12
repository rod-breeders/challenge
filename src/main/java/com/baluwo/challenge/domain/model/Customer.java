package com.baluwo.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import java.math.BigDecimal;

@Entity
@Table(name = "Customers")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customer_sequence")
	@SequenceGenerator(name="customer_sequence", sequenceName="customer_seq")
	private Long id;

	@NotBlank(message = "Name is required")
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull(message = "Balance is required")
	@Column(name = "balance", nullable = false)
	private BigDecimal  balance;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name +  "balance=" + balance + "]";
	}
}
