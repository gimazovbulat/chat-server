package myApp.protocol.products;

import myApp.protocol.AbstractPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myApp.models.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponsePayload extends AbstractPayload {
    private List<Product> products;
}
