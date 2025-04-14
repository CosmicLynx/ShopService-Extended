import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ShopService {
    private final ProductRepo productRepo = new ProductRepo();
    private final OrderRepo orderRepo = new OrderMapRepo();
    
    public Order addOrder( List<String> productIds, OrderStatus orderStatus ) throws Exception {
        List<Product> products = new ArrayList<>();
        for ( String productId : productIds ) {
            Optional<Product> productToOrder = productRepo.getProductById( productId );
            if ( productToOrder.isEmpty() ) {
                throw new ProductNotFoundException( "Produkt mit id: " + productId + " nicht gefunden" );
            }
            products.add( productToOrder.get() );
        }
        
        Order newOrder = new Order( UUID.randomUUID().toString(), products, orderStatus );
        
        return orderRepo.addOrder( newOrder );
    }
    
    public List<Order> getOrdersByOrderStatus( OrderStatus orderStatus ) {
        return orderRepo.getOrders().stream().filter( o -> o.orderStatus().equals( orderStatus ) ).toList();
    }
}
