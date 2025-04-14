import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {
    
    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of( "1" );
        
        //WHEN
        Order actual = shopService.addOrder( productsIds, OrderStatus.PROCESSING );
        
        //THEN
        Order expected = new Order( "-1", List.of( new Product( "1", "Apfel" ) ), OrderStatus.PROCESSING );
        assertEquals( expected.products(), actual.products() );
        assertNotNull( expected.id() );
    }
    
    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of( "1", "2" );
        
        //WHEN
        Order actual = shopService.addOrder( productsIds, OrderStatus.PROCESSING );
        
        //THEN
        assertNull( actual );
    }
    
    @Test
    void getOrdersByOrderStatusTest() {
        ShopService shopService = new ShopService();
        shopService.addOrder( List.of( "1" ), OrderStatus.PROCESSING );
        shopService.addOrder( List.of( "1" ), OrderStatus.PROCESSING );
        
        List<Order> expected = List.of( new Order( "-1", List.of( new Product( "1", "Apfel" ) ), OrderStatus.PROCESSING ) );
        
        List<Order> actual = shopService.getOrdersByOrderStatus( OrderStatus.PROCESSING );
        
        assertTrue( actual.stream().allMatch( a -> expected.stream().allMatch( e -> e.orderStatus() == a.orderStatus() ) ) );
    }
}
