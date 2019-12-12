package myApp.services.interfaces;

import myApp.config.Component;
import myApp.dao.repositories.MessagesRepository;
import myApp.dao.repositories.ProductsRepository;
import myApp.dao.repositories.UsersRepository;
import myApp.dto.ActionStatusDto;
import myApp.dto.ProductsDto;
import myApp.dto.ResponseDto;

public interface CommandService extends Component {

    ResponseDto commandGetMessages(int size, int page);

    ProductsDto commandGetProducts(int size, int page);

    ActionStatusDto addProduct(String productName, float price);

    ActionStatusDto removeProduct(String name);

    ResponseDto purchase(String productName, Integer userId);

    ActionStatusDto authorityFail();

    ProductsRepository getProductsRepository();
    MessagesRepository getMessagesRepository();
    UsersRepository getUsersRepository();
}
