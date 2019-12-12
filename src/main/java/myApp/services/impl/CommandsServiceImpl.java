package myApp.services.impl;

import lombok.Getter;
import myApp.dao.repositories.MessagesRepository;
import myApp.dao.repositories.ProductsRepository;
import myApp.dao.repositories.UsersRepository;
import myApp.dto.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myApp.models.Message;
import myApp.models.Product;
import myApp.services.interfaces.CommandService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Setter
@Getter
public class CommandsServiceImpl implements CommandService {
    private MessagesRepository messagesRepository;
    private ProductsRepository productsRepository;
    private UsersRepository usersRepository;

    public ResponseDto commandGetMessages(int size, int page) {
        List<Message> messages = messagesRepository.getMessages(size, page);
        List<MessageDto> dtoMessages = new ArrayList<>();
        for (Message m : messages) {
            MessageDto mDto = MessageDto.builder()
                    .date(m.getDate())
                    .text(m.getText())
                    .senderNickname(m.getNickname())
                    .build();
            dtoMessages.add(mDto);
        }
        return new MessagesDto(Type.ONE, dtoMessages, "get messages");
    }


    public ProductsDto commandGetProducts(int size, int page) {
        List<Product> products = productsRepository.getAll(page, size);
        List<ProductDto> dtoProducts = new ArrayList<>();
        for (Product p : products) {
            dtoProducts.add(ProductDto.builder()
                    .name(p.getName())
                    .price(p.getPrice())
                    .build()
            );
        }
        return new ProductsDto(Type.ONE, dtoProducts, "get products");
    }

    public ActionStatusDto addProduct(String productName, float price) {
        productsRepository.place(productName, price);
        return new ActionStatusDto(Type.ONE, "product successfully added!", "status");
    }

    public ActionStatusDto removeProduct(String name) {
        productsRepository.delete(name);
        return new ActionStatusDto(Type.ONE, "product was successfully removed!", "status");
    }

    public ResponseDto purchase(String productName, Integer userId) {
        Optional<Product> optionalProduct = productsRepository.getByName(productName);
        Product product;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
            usersRepository.purchase(userId, product.getId());
            return new ActionStatusDto(Type.ONE, "product successfully purchased", "purchase");
        }
        throw new IllegalStateException();
    }

    public ActionStatusDto authorityFail() {
        return new ActionStatusDto(Type.ONE, "you don't have enough authorities(", "status");
    }

}
