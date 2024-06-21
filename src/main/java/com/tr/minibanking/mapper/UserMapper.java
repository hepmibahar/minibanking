package com.tr.minibanking.mapper;

import com.tr.minibanking.dto.UserDto;
import com.tr.minibanking.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toDto(User user);

  User toEntity(UserDto userDTO);

  void updateEntityFromDto(UserDto userDTO, @MappingTarget User user);
}
