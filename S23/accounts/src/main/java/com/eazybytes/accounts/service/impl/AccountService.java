package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.eazybytes.accounts.constants.AccountsConstants.*;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final CustomerMapper customerMapper;


    @Override
    public void createAccount(CustomerDto customerDto) {
        customerRepository.findByMobileNumber(customerDto.getMobileNumber()).ifPresent( customer -> {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customer.getMobileNumber() + " already exists");
        });

        Customer customer = customerMapper.toEntity(customerDto);
        customer.setCreatedAt(LocalDate.now());
        customer.setCreatedBy("me");
        Customer save = customerRepository.save(customer);
        accountRepository.save(createAccount(save));

    }

    private Accounts createAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        accounts.setAccountType(SAVINGS);
        accounts.setBranchAddress(ADDRESS);
        accounts.setCreatedAt(LocalDate.now());
        accounts.setCreatedBy("me");
        return accounts;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        var accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));

        CustomerDto response = customerMapper.toDto(customer);
        response.setAccountDto(accountMapper.toDto(accounts));
        return response;
    }
}
