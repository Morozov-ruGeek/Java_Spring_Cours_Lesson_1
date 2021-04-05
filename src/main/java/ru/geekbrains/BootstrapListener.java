package ru.geekbrains;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();

        productRepository.saveOrUpdate(new Product(null, "Product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product 2", new BigDecimal(150)));
        productRepository.saveOrUpdate(new Product(null, "Product 3", new BigDecimal(30)));
        productRepository.saveOrUpdate(new Product(null, "Product 4", new BigDecimal(120)));
        productRepository.saveOrUpdate(new Product(null, "Product 5", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product 6", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product 7", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product 8", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product 9", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product 10", new BigDecimal(200)));

        sce.getServletContext().setAttribute("productRepository", productRepository);
    }

}
