public class Cart {
    private String name;
    private int price;
    //private int total;

    public Cart(String name,int price){
       this.name=name;
       this.price =price;
    }
    public String getName() {
    return name;
    }
    public int getPrice() {
        return price;
    }
    @Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }
}
