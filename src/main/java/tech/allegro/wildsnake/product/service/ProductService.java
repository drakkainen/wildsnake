package tech.allegro.wildsnake.product.service;

import tech.allegro.wildsnake.product.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Created by Szymon on 13.12.2015.
 */
public interface ProductService {

    List<Product> findAll(Optional<String> order, Optional<Integer> limit);

    Product findOne(Optional<Long> productId);
}
