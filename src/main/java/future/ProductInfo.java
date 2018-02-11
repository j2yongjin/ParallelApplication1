package future;

/**
 * Created by yjlee on 2018-01-21.
 */

public class ProductInfo {

    public ProductInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name = "";
    Double amount=0.0;
}
