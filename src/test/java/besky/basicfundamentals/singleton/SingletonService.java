package besky.basicfundamentals.singleton;

public class SingletonService {
    // 클래스 레벨에 올라가 딱 하나만 존재
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // 외부에서 생성할 수 없도록
    private SingletonService(){}

    public void logic(){
        System.out.println("logic() called");
    }
}
