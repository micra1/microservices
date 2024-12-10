package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    AccountDto toDto(Accounts account);
    void updateEntityFromDto(AccountDto accountsDto, @MappingTarget Accounts accounts);

    @Mapping(target = "customerId", ignore = true)
    Accounts toEntity(AccountDto dto);


}
