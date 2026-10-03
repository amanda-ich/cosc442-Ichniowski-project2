import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VendingMachineTest {
    VendingMachine vm;
    VendingMachineItem coke;
    VendingMachineItem drpepper;
    VendingMachineItem pepsi;
    VendingMachineItem sprite;
    VendingMachineItem nullItem;

    @BeforeEach
    void setUp() {
        vm = new VendingMachine();
        coke = new VendingMachineItem("Coke", 1.5);
        drpepper = new VendingMachineItem("DrPepper", 1.75);
        pepsi = new VendingMachineItem("Pepsi", 0);
        nullItem = new VendingMachineItem(null, 1.25);
        vm.addItem(drpepper, "B");
    }

    @AfterEach
    void tearDown() {
        vm = null;
    }

    @Test
    void testValidAddItem() {
        vm.addItem(coke, "A");
        assertEquals(coke, vm.getItem("A"));
    }

    @Test
    void testInvalidAddItem() {
        assertThrows(VendingMachineException.class, () -> vm.addItem(coke, "b"));
    }

    /*
    @Test
    void testInvalidAddNullItem() {
        assertThrows(VendingMachineException.class, () -> vm.addItem(nullItem, "A"));
    }

    @Test
    void testInvalidAddNullItemNull() {
        assertThrows(VendingMachineException.class, () -> vm.addItem(null, "A"));
    }
    */

    @Test
    void testGetBalance() {
        vm.insertMoney(2.0);
        vm.makePurchase("B");
        assertEquals(0.25, vm.getBalance(), 0.01);
    }

    @Test
    void testValidGetItem() {
        assertEquals(drpepper, vm.getItem("B"));
    }

    @Test
    void testInvalidGetItem() {
        assertThrows(VendingMachineException.class, () -> vm.getItem("b"));
    }

    @ParameterizedTest 
    @ValueSource(doubles = {1.0, 0.75})
    void testValidInsertMoney(double amount) {
        vm.insertMoney(amount);
        assertEquals(amount, vm.getBalance(), 0.01);
    }

    @ParameterizedTest 
    @ValueSource(doubles = {0.001, -0.75, -0.001})
    void testInvalidInsertMoney(double invalidAmount) {
        assertThrows(VendingMachineException.class, () -> vm.insertMoney(invalidAmount));
    }

    @Test
    void testValidMakePurchase() {
        vm.insertMoney(1.75);
        assertTrue(vm.makePurchase("B"));
    }

    @ParameterizedTest 
    @ValueSource(strings = {"A", "B", "C"})
    void testInvalidMakePurchase(String product) {
        vm.addItem(pepsi, "C");
        vm.removeItem("C");
        vm.insertMoney(1.5);
        assertFalse(vm.makePurchase(product));
    }

    @Test
    void testValidRemoveItem() {
        assertEquals(drpepper, vm.removeItem("B"));
    }

    @Test
    void testInvalidRemoveItem() {
        assertThrows(VendingMachineException.class, () -> vm.removeItem("A"));
    }

    @Test
    void testReturnChange() {
        vm.insertMoney(1.0);
        assertEquals(1.0, vm.returnChange(), 0.01);
    }
}
