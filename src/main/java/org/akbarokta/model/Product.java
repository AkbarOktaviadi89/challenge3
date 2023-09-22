package org.akbarokta.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productName;
    private Integer price;
}
