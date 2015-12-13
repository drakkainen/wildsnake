package tech.allegro.wildsnake.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.allegro.wildsnake.product.model.Product;
import tech.allegro.wildsnake.product.repository.ProductRepository;
import tech.allegro.wildsnake.product.service.ProductService;
import tech.allegro.wildsnake.product.service.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ApiRestController {

    private ProductRepository productRepository;
    private ProductService productService;

    @Autowired
    public ApiRestController(ProductRepository productRepository, ProductServiceImpl
            productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(value = "order", required = false) Optional<String> order,
            @RequestParam(value = "limit", required = false) Optional<Integer> limit
    ) {
        List<Product> resultLit = productService.findAll(order, limit);
        if (!resultLit.isEmpty()) {
            return new ResponseEntity<List<Product>>(resultLit, HttpStatus.OK);
        } else return new ResponseEntity<List<Product>>(HttpStatus.BAD_GATEWAY);

    }

    @RequestMapping(value = "/products/product_{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> getOneProduct(
            @PathVariable("productId") Optional<Long> productId) {
        Product product = productService.findOne(productId);
        if (product != null)
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        else
            return new ResponseEntity<Product>(HttpStatus.NOT_IMPLEMENTED);


    }
}
