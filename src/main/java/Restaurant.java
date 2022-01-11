import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Cart> cart = new ArrayList<Cart>();
    public int price;
    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen(LocalTime now) {
        getCurrentTime();
        boolean status = false;
        if ( LocalTime.now().isAfter(this.openingTime) ){
            if ( LocalTime.now().isBefore(this.closingTime) ){
                status = true;
            }
        } else if ( LocalTime.now().isAfter(this.closingTime) ) {
            if ( LocalTime.now().isBefore(this.openingTime) ) {
                status = false;
            }
        } else {
            status = false;
        }
        return status; //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        for ( Item item:menu){
            return menu;
        }
        return null;//DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }
    private Item findItemByName(String itemName)throws itemNotFoundException{
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public int addItemPrice(String itemName)throws cartIsEmptyException{
        for(Item item: menu) {
            if(item.getName().equals(itemName));
            return item.getPrice();
        }
        return 0;
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }
    public String getName() {;
        return name;
    }

    public List<Cart> getCart() throws cartIsEmptyException{
        for ( Cart Cart:cart) {
            return cart;
        }
        throw new cartIsEmptyException();//DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }
    public String addItemByName(String itemName)throws cartIsEmptyException{
        for(Item item: menu) {
            if(item.getName().equals(itemName)) {
                return itemName;
            }
        }
        return null;
    }
    public void addToCart(String name,int price) {
        Cart newItem = new Cart(name,price);
        cart.add(newItem);
    }
    public int getCartTotal() throws cartIsEmptyException {
        int x=0;
        for(Cart cart:cart) {
            price = getCart().get(x).getPrice()+price;
            x=x+1;
        }
        return price;
    }

    public void showCart() {
        for (Cart cart:cart){
            System.out.println(cart.getName()+"  "+cart.getPrice());
        }
    }
}
