package cat.itacademy.s04.t02.n02.fruit.provider.exception;

public class ProviderNameCantBeEmpty extends RuntimeException {
    public ProviderNameCantBeEmpty(String message) {
        super(message);
    }
}
