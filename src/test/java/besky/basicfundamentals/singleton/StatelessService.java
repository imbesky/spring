package besky.basicfundamentals.singleton;

public class StatelessService {

    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        return price; // 지역 변수로 처리해서 유저마다 다른 값을 가지도록 함
    }
}
