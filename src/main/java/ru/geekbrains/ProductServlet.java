package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(String.valueOf(ProductServlet.class));

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) {
            throw new ServletException("ProductRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id =null;
        try {
            String strId = req.getParameter("id");
            if(strId != null && !strId.isEmpty()){
                id = Long.parseLong(req.getParameter("id"));
            }
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        BigDecimal price;
        try {
            price = new BigDecimal(req.getParameter("cost"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        Product product = new Product(id, req.getParameter("name"), price);
        productRepository.saveOrUpdate(product);
        resp.sendRedirect(getServletContext().getContextPath() + "/products");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
