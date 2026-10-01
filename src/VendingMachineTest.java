import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendingMachineTest {
    VendingMachine vm;
    VendingMachineItem coke, drpepper;

    @BeforeEach
    void setUp() {
        vm = new VendingMachine();
        coke = new VendingMachineItem("Coke", 1.5);
        drpepper = new VendingMachineItem("DrPepper", 1.75);
        vm.addItem(drpepper, "B");
    }

    @AfterEach
    void tearDown() {
        vm = null;
    }

    @Test
    void testAddItem() {
        vm.addItem(coke, "A");
        assertEquals(coke, vm.getItem("A"));
    }

    @Test
    void testGetBalance() {
        vm.insertMoney(1.75);
        vm.makePurchase("B");
        assertEquals(0.0, vm.getBalance(), 0.01);
    }

    @Test
    void testGetItem() {
        assertEquals(drpepper, vm.getItem("B"));
    }

    @Test
    void testInsertMoney() {
        vm.insertMoney(1.0);
        assertEquals(1.0, vm.getBalance(), 0.01);
    }

    @Test
    void testMakePurchase() {
        vm.insertMoney(1.75);
        assertTrue(vm.makePurchase("B"));
    }

    @Test
    void testRemoveItem() {
        assertEquals(drpepper, vm.removeItem("B"));
    }

    @Test
    void testReturnChange() {
        vm.insertMoney(1.0);
        assertEquals(1.0, vm.returnChange(), 0.01);
    }
}
