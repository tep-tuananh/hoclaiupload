package com.ra.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String productName;
    private  Float price;
    private String image;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "category_Id", // sẽ tạo thêm 1 côột có tên  category_Id them chiếu đến
                                        // cột id của bảng category
    referencedColumnName = "id"
    )
    private Category category;
}
