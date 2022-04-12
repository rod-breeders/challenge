package com.baluwo.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Transactions")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "transaction_sequence")
	@SequenceGenerator(name="transaction_sequence", sequenceName="transaction_seq")
	private Long id;

	@NotNull(message = "price is required")
	@Column(name = "price", nullable = false)
	private BigDecimal orderTotal;

	@OneToMany(targetEntity = TransactionItem.class,mappedBy="transaction" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TransactionItem> transactionItems;

	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	@Column(name = "is_approved", nullable = false)
	private boolean isApproved;

}
