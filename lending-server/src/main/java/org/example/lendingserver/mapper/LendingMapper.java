package org.example.lendingserver.mapper;


import org.example.lendingserver.dto.LendingRequestDTO;
import org.example.lendingserver.dto.LendingResponseDTO;
import org.example.lendingserver.entity.Lending;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.BeanMapping;

@Mapper(componentModel = "spring")
public interface LendingMapper {
    LendingResponseDTO toResponseDto(Lending lending);

    Lending toEntity(LendingRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(LendingRequestDTO dto, @MappingTarget Lending lending);

}
