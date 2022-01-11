import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant restaurant= new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    public void extracted() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        LocalTime isOpen = LocalTime.of(12,00);
        Mockito.when(restaurant.getCurrentTime()).thenReturn(isOpen);
        assertFalse ( restaurant.isRestaurantOpen(restaurant.getCurrentTime()) );
        //WRITE UNIT TEST CASE HERE
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant restaurant = Mockito.mock(Restaurant.class);
       // LocalTime isClosed = LocalTime.MAX;
        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.MIDNIGHT);
        assertFalse ( restaurant.isRestaurantOpen(restaurant.getCurrentTime()) );
        //WRITE UNIT TEST CASE HERE

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        extracted();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        extracted();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        extracted();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void adding_item_to_cart_should_increase_cart_size_by_1() throws cartIsEmptyException{
        /*extracted();
        restaurant.addToMenu("Sweet soup",100);
        restaurant.addToMenu("Lasagne", 200);
        restaurant.addToCart (restaurant.addItemByName("Sweet corn soup"),restaurant.addItemPrice("Sweet corn soup"));
       */ restaurant.addToCart(restaurant.addItemByName("Lasagne"),restaurant.addItemPrice("Lasagne"));
        int initialCartSize = restaurant.getCart().size();
        restaurant.addToCart(restaurant.addItemByName("Sweet soup"),restaurant.addItemPrice("Sweet soup"));
        assertEquals(initialCartSize+1,restaurant.getCart().size());
    }
    @Test
    public void display_total_should_add_item_prices_and_display_total() throws cartIsEmptyException {
        restaurant.addToCart("Sweet corn soup",119);
        restaurant.addToCart("Vegetable lasagne", 269);
        assertEquals(119+269, restaurant.getCartTotal());
    }

    @Test
    public void displaying_cart_items() throws cartIsEmptyException {
      /*  extracted();
        restaurant.addToCart("Sweet soup", 100);
        restaurant.addToCart("Lasagne", 200);
        restaurant.showCart();*/
        assertEquals(2,restaurant.getCart().size());
        assertEquals("Sweet soup",restaurant.getCart().get(0).getName());
        assertEquals("Lasagne",restaurant.getCart().get(1).getName());
    }
}
