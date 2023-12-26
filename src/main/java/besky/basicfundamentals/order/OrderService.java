package besky.basicfundamentals.order;

public interface OrderService {
    Order createOrder(Long id, String item, int itemPrice);

}
