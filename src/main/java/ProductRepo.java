import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {
    private final List<Product> products;
    
    public ProductRepo() {
        products = new ArrayList<>();
        products.add( new Product( "1", "Apfel" ) );
    }
    
    public List<Product> getProducts() {
        return products;
    }
    
    public Optional<Product> getProductById( String id ) {
        Product foundProduct = null;
        for ( Product product : products ) {
            if ( product.id().equals( id ) ) {
                foundProduct = product;
            }
        }
        return Optional.ofNullable( foundProduct );
    }
    
    public Product addProduct( Product newProduct ) {
        products.add( newProduct );
        return newProduct;
    }
    
    public void removeProduct( String id ) {
        for ( Product product : products ) {
            if ( product.id().equals( id ) ) {
                products.remove( product );
                return;
            }
        }
    }
}
