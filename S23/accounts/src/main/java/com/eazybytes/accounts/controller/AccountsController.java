package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.impl.AccountService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.eazybytes.accounts.constants.AccountsConstants.ACCOUNT_CREATED_SUCCESSFULLY;
import static com.eazybytes.accounts.constants.AccountsConstants.STATUS_201;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {
    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    private final AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createAcount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        ResponseDto body = new ResponseDto(STATUS_201, ACCOUNT_CREATED_SUCCESSFULLY);
        return ResponseEntity
                .ok(body);
    }

    @GetMapping("fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        return ResponseEntity.ok(accountService.fetchAccount(mobileNumber));
    }

}
