package com.springflame.commissionapp.persistence.transaction;

import com.springflame.commissionapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

	@Query(
			name = "findAllByMonth",
			value = "SELECT transaction FROM Transaction transaction " +
					"WHERE YEAR(transaction.date) = :yearValue " +
					"AND MONTH(transaction.date) = :monthValue " +
					"AND transaction.clientId = :clientId"
	)
	List<Transaction> doFindTransactionsByMonth(@Param("yearValue") Integer yearValue, @Param("monthValue") Integer monthValue, String clientId);

	default List<Transaction> findTransactionsByMonth(Transaction transaction) {
		return this.doFindTransactionsByMonth(transaction.getDate().getYear(),
				transaction.getDate().getMonth().getValue(), transaction.getClientId());
	}
}
