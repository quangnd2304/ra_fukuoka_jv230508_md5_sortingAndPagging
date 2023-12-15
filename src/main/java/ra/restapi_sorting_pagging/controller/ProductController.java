package ra.restapi_sorting_pagging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ra.restapi_sorting_pagging.model.Product;
import ra.restapi_sorting_pagging.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/productController")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/sortByName")
    public ResponseEntity<List<Product>> sortByName(String direction) {
        List<Product> listProduct = productService.sortByName(direction);
        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("/sortByPriceAndName")
    public ResponseEntity<List<Product>> sortByPriceAndName(String priceDirection, String nameDirection) {
        List<Product> listProduct = productService.sortByPriceAndName(priceDirection, nameDirection);
        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("/findAllPagging")
    public ResponseEntity<Map<String, Object>> findAllPagging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> pageProduct = productService.findAll(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("products", pageProduct.getContent());
        data.put("totalProduct", pageProduct.getTotalElements());
        data.put("totalPage", pageProduct.getTotalPages());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/findPaggingAndSorting")
    public ResponseEntity<Map<String, Object>> findPaggingAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            String direction
    ) {
        Pageable pageable;
        if (direction.equals("asc")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        } else {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "name"));
        }
        Page<Product> pageProduct = productService.findAll(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("products", pageProduct.getContent());
        data.put("totalProduct", pageProduct.getTotalElements());
        data.put("totalPage", pageProduct.getTotalPages());
        return ResponseEntity.ok(data);
    }

}
