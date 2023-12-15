package ra.restapi_sorting_pagging.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.restapi_sorting_pagging.model.Product;
import ra.restapi_sorting_pagging.repository.ProductRepository;
import ra.restapi_sorting_pagging.service.ProductService;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> sortByName(String direction) {
        if (direction.equals("asc")){
            return productRepository.findAll(Sort.by("name").ascending());
        }
        return productRepository.findAll(Sort.by("name").descending());
    }

    @Override
    public List<Product> sortByPriceAndName(String priceDirection, String nameDirection) {
        List<Sort.Order> listOrders = new ArrayList<>();
        Sort.Order orderPrice;
        if (priceDirection.equals("asc")){
            orderPrice = new Sort.Order(Sort.Direction.ASC,"price");
        }else{
            orderPrice = new Sort.Order(Sort.Direction.DESC,"price");
        }
        listOrders.add(orderPrice);
        Sort.Order orderName;
        if (nameDirection.equals("asc")){
            orderName = new Sort.Order(Sort.Direction.ASC,"name");
        }else{
            orderName = new Sort.Order(Sort.Direction.DESC,"name");
        }
        listOrders.add(orderName);
        return productRepository.findAll(Sort.by(listOrders));
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
