package shop.mtcoding.mall.model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import({
        ProductRepository.class,
        SellerRepository.class
})
@DataJpaTest // T -> DS -> C -> ( R -> DB )
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Test
    public void findById_test(){
        // given (테스트를 하기 위해서 필요한 데이터 만들기)
        productRepository.save("바나나", 5000, 50);

        // when (테스트 진행)
        Product product = productRepository.findById(1);

        // then (테스트 확인)
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
    } @Test
    public void findByIdJoinSeller_test(){
        // given (테스트를 하기 위해서 필요한 데이터 만들기)
        sellerRepository.save("홍길동", "ssar@nate.com");

        productRepository.saveWithFK("바나나", 5000, 50, 1);

        // when (테스트 진행)
       Product product = productRepository.findByIdJoinSeller(1);

        // then (테스트 확인)
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
        System.out.println("==========="+product.getSeller().getId());
        System.out.println("==========="+product.getSeller().getName());
        System.out.println("==========="+product.getSeller().getEmail());

    }
@Test
    public void findByIdDTO_test(){
    // given (테스트를 하기 위해서 필요한 데이터 만들기)
    productRepository.save("바나나", 5000, 50);

    // when (테스트 진행)
    ProductDTO productDTO = productRepository.findByIdDTO(1);

    // then (테스트 확인)
    System.out.println(productDTO.getId());
    System.out.println(productDTO.getName());
    System.out.println(productDTO.getPrice());
    System.out.println(productDTO.getQty());
    System.out.println(productDTO.getDes());

}
    @Test
    public void findAll_test(){
        // given (테스트를 하기 위해서 필요한 데이터 만들기)
        productRepository.save("바나나", 5000, 50);
        productRepository.save("딸기", 5000, 50);

        // when
       List<Product> productList = productRepository.findAll();

       // then
        for (Product product : productList) {
            System.out.println("=================");
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getQty());

        }



    }
}