package shop.mtcoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mall.model.Product;
import shop.mtcoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller

public class ProductController {

    @Autowired
    private ProductRepository productRespository;


    @PostMapping("/product/delete")
    public String delete(int id){

        productRespository.deleteId(id);
        return "redirect:/";
    }
@PostMapping("/product/update")
public String update(int id, String name, int price, int qty){

        productRespository.updateId(id, name, price, qty);
        return "redirect:/";

    }


    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request){


        Product product = productRespository.findById(id);
        request.setAttribute("product",product);
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
        return "detail";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request) {
       List<Product> productList = productRespository.findAll();
       request.setAttribute("productList", productList);
        return "home";

    }

    @GetMapping("/write")
    public String writepage() {
        return "write";
    }

    @PostMapping("/product")
    public String write(String name, int price, int qty) {
        System.out.println("name :" + name);
        System.out.println("price :" + price);
        System.out.println("qty :" + qty);

        productRespository.save(name, price, qty);

        // 컨트롤러의 메서드를 재호출하는 방법
        return "redirect:/";
        //return "redirect:/";
    }

}
