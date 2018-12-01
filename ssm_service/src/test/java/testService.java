import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testService {
    @Test
    public void teseSservie(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
       /* IProductService bean = ac.getBean(IProductService.class);
        List<Product> allProduct = bean.findAllProduct();
        for (Product product : allProduct) {
            System.out.println(product);
        }*/
    }
}
