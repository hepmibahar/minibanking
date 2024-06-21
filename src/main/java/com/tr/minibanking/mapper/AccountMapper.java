package com.tr.minibanking.mapper;

import com.tr.minibanking.dto.AccountDto;
import com.tr.minibanking.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  @Mapping(source = "user.id", target = "userId")
  AccountDto toDto(Account account);

  @Mapping(source = "userId", target = "user.id")
  Account toEntity(AccountDto accountDTO);

  @Mapping(source = "userId", target = "user.id")
  void updateEntityFromDto(AccountDto accountDTO, @MappingTarget Account account);
}
