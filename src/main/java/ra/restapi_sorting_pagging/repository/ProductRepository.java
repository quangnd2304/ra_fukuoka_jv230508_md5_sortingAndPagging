package ra.restapi_sorting_pagging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.restapi_sorting_pagging.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
