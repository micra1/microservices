package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.impl.AccountServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.eazybytes.accounts.constants.AccountsConstants.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {
    public AccountsController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    private final AccountServiceImpl accountService;


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
    @PutMapping("update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto) {
        boolean updated = accountService.updateAccount(customerDto);
        if (!updated) {
            return ResponseEntity.internalServerError().body(new ResponseDto(STATUS_500, MESSAGE_500));
        }
        return ResponseEntity.ok(new ResponseDto(STATUS_200, MESSAGE_200));
    }



}
