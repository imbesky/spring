package besky.basicfundamentals.order;

public class Order {
    private final Long id;
    private final String item;
    private final int itemPrice;
    private final int discountPrice;

    public Order(Long id, String item, int itemPrice, int discountPrice) {
        this.id = id;
        this.item = item;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public Long getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public int finalPrice(){
        return itemPrice - discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
