package myApp.services.interfaces;

import myApp.dto.ResponseDto;
import myApp.protocol.Request;

public interface RequestDispatcher  {
    ResponseDto dispatch(Request request);
}
