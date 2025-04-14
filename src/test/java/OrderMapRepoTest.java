import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderMapRepoTest {
    
    ZonedDateTime testingTime = ZonedDateTime.of( 2025, 4, 4, 14, 30, 2, 0, ZoneId.systemDefault() );
    
    @Test
    void getOrders() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
        
        Product product = new Product( "1", "Apfel" );
        Order newOrder = new Order( "1", List.of( product ), OrderStatus.PROCESSING, testingTime );
        repo.addOrder( newOrder );
        
        //WHEN
        List<Order> actual = repo.getOrders();
        
        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product( "1", "Apfel" );
        expected.add( new Order( "1", List.of( product1 ), OrderStatus.PROCESSING, testingTime ) );
        
        assertEquals( actual, expected );
    }
    
    @Test
    void getOrderById() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
        
        Product product = new Product( "1", "Apfel" );
        Order newOrder = new Order( "1", List.of( product ), OrderStatus.PROCESSING, testingTime );
        repo.addOrder( newOrder );
        
        //WHEN
        Order actual = repo.getOrderById( "1" );
        
        //THEN
        Product product1 = new Product( "1", "Apfel" );
        Order expected = new Order( "1", List.of( product1 ), OrderStatus.PROCESSING, testingTime );
        
        assertEquals( actual, expected );
    }
    
    @Test
    void addOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
        Product product = new Product( "1", "Apfel" );
        Order newOrder = new Order( "1", List.of( product ), OrderStatus.PROCESSING, testingTime );
        
        //WHEN
        Order actual = repo.addOrder( newOrder );
        
        //THEN
        Product product1 = new Product( "1", "Apfel" );
        Order expected = new Order( "1", List.of( product1 ), OrderStatus.PROCESSING, testingTime );
        assertEquals( actual, expected );
        assertEquals( repo.getOrderById( "1" ), expected );
    }
    
    @Test
    void removeOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
        
        //WHEN
        repo.removeOrder( "1" );
        
        //THEN
        assertNull( repo.getOrderById( "1" ) );
    }
}
