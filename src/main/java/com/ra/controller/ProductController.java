package com.ra.controller;

import com.ra.models.entity.Category;
import com.ra.models.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Value("${path-upload}")
    private String path;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product")
    public String home(Model model){
        List<Product> productList = productService.getAll();
        model.addAttribute("productList",productList);
        return "product/index";
    }
    @GetMapping("add-product")
    public String add(Model model){
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList",categoryList);
        Product product =  new Product();
        model.addAttribute("product",product);
        return "product/add";
    }
    @PostMapping("add-product")
    public String create(
            @ModelAttribute("product") Product product,
            @RequestParam("img")MultipartFile file){
        // upload file
        String fileName  = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),new File(path+fileName));
            // lưu tên file vào database
            product.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productService.save(product);
        return "redirect:/product";
    }

    // get layout
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id
                        ,Model model
    ){
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList",categoryList);
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "product/edit";
    }


    @PostMapping("/edit")
    public String update(@ModelAttribute("product") Product product,
                            @RequestParam("img") MultipartFile file){
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),new File(path+fileName));
            // lưu tên file vào database
            product.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productService.save(product);
        return "redirect:/product";
    }
}
