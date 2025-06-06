package network.tcp.autocloseable;

public class ResourceV1 {

    private String name;

    public ResourceV1(String name) {
        this.name = name;
    }

    public void call() {
        System.out.println(name + " call");

    }
}
