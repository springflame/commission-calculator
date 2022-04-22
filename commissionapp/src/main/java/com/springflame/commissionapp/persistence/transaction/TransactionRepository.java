package com.springflame.commissionapp.persistence.transaction;

import com.springflame.commissionapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

	@Query(
			name = "findAllByMonth",
			value = "SELECT * FROM Transaction transaction " +
					"WHERE YEAR(transaction.date) = :yearValue " +
					"AND MONTH(transaction.date) = :monthValue " +
					"AND transaction.id <> :originalId"
	)
	List<Transaction> doFindOtherTransactionsByMonth(@Param("originalId") UUID originalId, @Param("yearValue") Integer yearValue, @Param("monthValue") Integer monthValue);

	default List<Transaction> findOtherTransactionsByMonth(Transaction transaction) {
		return this.doFindOtherTransactionsByMonth(transaction.getId(), transaction.getDate().getYear(), transaction.getDate().getMonth().getValue());
	}
}
