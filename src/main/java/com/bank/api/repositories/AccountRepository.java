package com.bank.api.repositories;

import org.springframework.stereotype.Repository;

import com.bank.api.domain.BaseRepository;
import com.bank.api.entities.Account;

@Repository
public interface AccountRepository extends BaseRepository<Account> {

}
