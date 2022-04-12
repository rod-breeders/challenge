package com.baluwo.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String description;


    private BigDecimal price;

    @Override
    public String toString() {
        return "ApplicationProduct [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
    }
}
