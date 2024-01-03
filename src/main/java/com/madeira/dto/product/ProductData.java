package com.madeira.dto.product;

import java.util.List;
import java.util.UUID;

import com.madeira.dto.employee.EmployeeData;
import com.madeira.entity.Tag;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductData {
    
    private UUID productId;

    private String name;

    private String description;

    private List<Tag> tags;

    private List<EmployeeData> employees;

}
