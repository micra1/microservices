package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    public void testMappingEntityToDto() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setMobileNumber("1234567890");

        // Act
        CustomerDto dto = customerMapper.toDto(customer);

        // Assert
        assertNotNull(dto);
        assertEquals("John Doe", dto.getName());
        assertEquals("john.doe@example.com", dto.getEmail());
        assertEquals("1234567890", dto.getMobileNumber());
    }

    @Test
    public void testMappingDtoToEntity() {
        // Arrange
        CustomerDto dto = new CustomerDto();
        dto.setName("Jane Doe");
        dto.setEmail("jane.doe@example.com");
        dto.setMobileNumber("0987654321");

        // Act
        Customer customer = customerMapper.toEntity(dto);

        // Assert
        assertNotNull(customer);
        assertEquals("Jane Doe", customer.getName());
        assertEquals("jane.doe@example.com", customer.getEmail());
        assertEquals("0987654321", customer.getMobileNumber());
    }

}