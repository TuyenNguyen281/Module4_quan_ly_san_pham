package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ModelAndView listProducts(@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam("page") Optional<String> page) {
        Page<Product> products;
        int t = 0;
        if (page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 4 );
        if (s.isPresent()) {
            products = productService.findAllByNameContaining( s.get(), pageable );
        } else {
            products = productService.findAll( pageable );
        }
        ModelAndView modelAndView = new ModelAndView( "/product/list" );
        modelAndView.addObject( "products", products );
        return modelAndView;

    }
    @GetMapping("/create-product")
    public String showCreateForm(Model model){
        model.addAttribute( "product", new Product() );
        return "/product/create";

    }
    @PostMapping("/create-product")
    public String saveProduct (@ModelAttribute("product") Product product, Model model) {
        productService.save( product );
        model.addAttribute( "product", new Product() );
        model.addAttribute( "message","New customer created successfully" );
        return "/product/create";
    }

}
