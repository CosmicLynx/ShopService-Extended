import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {
    
    @Test
    void addOrderTest() throws Exception {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of( "1" );
        
        //WHEN
        Order actual = shopService.addOrder( productsIds );
        
        //THEN
        Order expected = new Order( "-1", List.of( new Product( "1", "Apfel" ) ), OrderStatus.PROCESSING );
        assertEquals( expected.products(), actual.products() );
        assertNotNull( expected.id() );
    }
    
    @Test
    void addOrderTest_whenInvalidProductId_expectProductNotFoundException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of( "1", "2" );
        
        //THEN
        assertThrows( ProductNotFoundException.class, () -> shopService.addOrder( productsIds ) );
    }
    
    @Test
    void getOrdersByOrderStatusTest() throws Exception {
        ShopService shopService = new ShopService();
        shopService.addOrder( List.of( "1" ) );
        
        List<Order> expected = List.of( new Order( "-1", List.of( new Product( "1", "Apfel" ) ), OrderStatus.PROCESSING ) );
        
        List<Order> actual = shopService.getOrdersByOrderStatus( OrderStatus.PROCESSING );
        
        assertTrue( actual.stream().allMatch( a -> expected.stream().allMatch( e -> e.orderStatus() == a.orderStatus() ) ) );
    }
    
    @Test
    void updateOrderTest() throws Exception {
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of( "1" );
        Order order = shopService.addOrder( productsIds );
        
        assertEquals( OrderStatus.IN_DELIVERY, shopService.updateOrder( order.id(), OrderStatus.IN_DELIVERY ).orderStatus() );
    }
}
