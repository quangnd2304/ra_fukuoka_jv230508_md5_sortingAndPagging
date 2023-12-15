package ra.restapi_sorting_pagging.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.restapi_sorting_pagging.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> sortByName(String direction);
    List<Product> sortByPriceAndName(String priceDirection,String nameDirection);
    Page<Product> findAll(Pageable pageable);
}
