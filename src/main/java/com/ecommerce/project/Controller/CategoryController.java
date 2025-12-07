package com.ecommerce.project.Controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



   @GetMapping("/public/categories")
//    @RequestMapping(value ="/public/categories" , method = RequestMethod.GET)
    public ResponseEntity <List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

   @PostMapping("/public/categories")
//@RequestMapping(value ="/public/categories" , method = RequestMethod.POST)
    public ResponseEntity <String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);   // this will NOT be null now
        return new ResponseEntity<>("Category created successfully",HttpStatus.CREATED);
    }


    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String>  deleteCategory(@PathVariable Long categoryId) {

        try {
            String status = categoryService.deleteCategory(categoryId);
           return new ResponseEntity<> (status, HttpStatus.OK);
           // return ResponseEntity.ok(status);
         //   return  ResponseEntity.status( HttpStatus.OK).body(status);
        } catch(ResponseStatusException rse) {
            return new ResponseEntity<>(rse.getMessage(), rse.getStatusCode());
        }
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,
                                                 @PathVariable Long categoryId) {
        try {
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            String msg = "Category with category Id: " +
                    savedCategory.getCategoryId() +
                    " Name: " + savedCategory.getCategoryName();
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (ResponseStatusException rse) {
            return new ResponseEntity<>(rse.getMessage(), rse.getStatusCode());
        }
    }

}

