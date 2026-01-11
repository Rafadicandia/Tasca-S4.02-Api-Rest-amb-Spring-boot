package cat.itacademy.s04.t02.n02.fruit.provider.exception;

public class ProviderNameDuplicatedException extends RuntimeException {
    public ProviderNameDuplicatedException(String message) {
        super(message);
    }
}
