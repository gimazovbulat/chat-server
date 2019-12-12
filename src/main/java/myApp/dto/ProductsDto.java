package myApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto extends ResponseDto {
    private List<ProductDto> products;

    public ProductsDto(Type type, List<ProductDto> products, String name) {
        super(name, type);
        this.products = products;
    }
}
