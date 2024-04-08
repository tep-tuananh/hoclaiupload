package com.ra.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String categoryName;
    private Boolean status=true;
    @OneToMany(mappedBy = "category") // mapper này dùng để map sang 1 bảng có tên là category
    private List<Product> products;
}
