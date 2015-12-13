package tech.allegro.wildsnake.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tech.allegro.wildsnake.product.model.Product;
import tech.allegro.wildsnake.product.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Szymon on 13.12.2015.
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //@Override
    public List<Product> findAll(Optional<String> order, Optional<Integer> limit) {

        Sort.Direction direction = Sort.Direction.DESC;

        if (order.isPresent() || limit.isPresent()) {
            Integer lim = Integer.MAX_VALUE;

            if (order.isPresent()) {
                if ("asc".equals(order.get().toLowerCase())) {
                    direction = Sort.Direction.ASC;
                } else {
                    direction = Sort.Direction.DESC;
                }
            }

            if (limit.isPresent()) {
                lim = limit.get();
            }

            final PageRequest page = new PageRequest(
                    0,
                    lim,
                    direction,
                    "id"
            );
            return productRepository.findAll(page);
        }

        return (List<Product>) productRepository.findAll();

    }

    @Override
    public Product findOne(Optional<Long> productId) {

        if (productId.isPresent()) {
            return productRepository.findOne(productId.get());
        }

        return null;

    }
}
