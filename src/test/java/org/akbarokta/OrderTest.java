package org.akbarokta;
import org.akbarokta.model.Product;
import org.akbarokta.model.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private List<Product> productList;
    private Order order;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Initialize a list of products for testing
        productList = new ArrayList<>();
        productList.add(new Product("Product1", 100));
        productList.add(new Product("Product2", 200));

        order = new Order();

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void addItem_Positive() {
        order.addItem("Product1", 2, 100);
        assertEquals(1, order.getItems().size());
        assertEquals(2, order.getItems().get(0).getItemQty());
        assertEquals(100, order.getItems().get(0).getItemPrice());
    }

    @Test
    void addItem_ItemAlreadyExists_Positive() {
        order.addItem("Product1", 1, 100);
        order.addItem("Product1", 2, 150);
        assertEquals(1, order.getItems().size());
        assertEquals(2, order.getItems().get(0).getItemQty());
        assertEquals(150, order.getItems().get(0).getItemPrice());
    }

    @Test
    void addItem_Negative_InvalidItem() {
        assertThrows(IllegalArgumentException.class, () -> order.addItem("", 1, 100));
    }

    @Test
    void printOrderDetails_Positive() {
        order.addItem("Product1", 2, 100);
        order.addItem("Product2", 1, 200);

        outContent.reset();
        order.printOrderDetails();
        String printedOutput = outContent.toString().trim();

        // Assuming the format of the output and manually verifying
        assertTrue(printedOutput.contains("Konfirmasi & Pembayaran"));
        assertTrue(printedOutput.contains("Total"));
    }

    @Test
    void printReceipt_Positive() {
        order.addItem("Product1", 2, 100);
        order.addItem("Product2", 1, 200);

        outContent.reset();
        order.printReceipt();
        String printedOutput = outContent.toString().trim();

        // Assuming the format of the output and manually verifying
        assertTrue(printedOutput.contains("Struk berhasil disimpan"));
    }

    @Test
    void displayMenu_Positive() {

        outContent.reset();
        Order.displayMenu(productList);
        String printedOutput = outContent.toString().trim();

        // Assuming the format of the output and manually verifying
        assertTrue(printedOutput.contains("Selamat Datang di Binar Food"));
        assertTrue(printedOutput.contains("99. Pesan dan Bayar"));
    }
}