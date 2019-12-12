package myApp.protocol;

import lombok.Getter;

public class Response<T> {
    @Getter
    private T data;

    private Response(T data) {
        this.data = data;
    }

    public static <E> Response<E> build(E data) {
        return new Response<>(data);
    }
}
