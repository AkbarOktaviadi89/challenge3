package org.akbarokta;

import org.akbarokta.model.Product;
import org.akbarokta.model.Order;

import java.util.*;

public class Main {
    static boolean continueLoop = true;
    private static final Scanner input = new Scanner(System.in);
    static final Order order = new Order();
    private static final List<Product> productList = new ArrayList<>(Arrays.asList(
            Product.builder().productName("Nasi Goreng\t").price(15000).build(),
            Product.builder().productName("Mie Goreng\t").price(13000).build(),
            Product.builder().productName("Nasi + Ayam\t").price(18000).build(),
            Product.builder().productName("Es Teh Manis\t").price(3000).build(),
            Product.builder().productName("Es Jeruk  \t").price(5000).build()
    ));

    public static void main(String[] args) {
        while (continueLoop) {
            order.displayMenu(productList);

            System.out.print("=> ");
            int choice = getInputInt();

            switch (choice) {
                case 0:
                    System.out.println("Terima kasih, selamat berkunjung kembali!");
                    System.exit(1);
                    break;
                case 99:
                    order.printOrderDetails();
                    processOrderConfirmation();
                    break;
                default:
                    processFoodOrder(choice);
                    break;
            }
        }
    }

    static void processOrderConfirmation() {
        System.out.print("=> ");
        int confirm = getInputInt();

        if (confirm == 1) {
            order.printReceipt();
            continueLoop = false;
        } else if (confirm == 2) {
            // Continue
        } else if (confirm == 0) {
            System.exit(1);
        }
    }

    static void processFoodOrder(int choice) {
        if (choice >= 1 && choice <= productList.size()) {
            Product selectedProduct = productList.get(choice - 1);
            String itemName = selectedProduct.getProductName();
            Integer itemPrice = selectedProduct.getPrice();

            if (choice != 0 || choice != 99) {
                System.out.println("\n=============================");
                System.out.println("Berapa Pesanan anda");
                System.out.println("=============================\n");
                System.out.println(productList.get(choice - 1).getProductName() + " \t| " + "Rp. " + productList.get(choice - 1).getPrice());
                System.out.println("(input 0 untuk kembali)");
            }

            while (true) {
                System.out.print("qty => ");
                int quantity = getInputInt();
                if (quantity > 0) {
                    if (itemName.isEmpty())
                    {
                        throw new IllegalArgumentException("Nama makanan tidak sesuai");
                    }
                        order.addItem(itemName, quantity, itemPrice);
                        System.out.println("Pesanan telah ditambahkan ke dalam keranjang.");
                } else if (quantity == 0) {
                    break;
                } else {
                    System.out.println("==============================");
                    System.out.println("Minimal 1 jumlah Pesanan");
                    System.out.println("==============================");
                }
            }
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    static int getInputInt() {
        int choice = 0;
        try {
            choice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            choice = handleInputError();
        }
        return choice;
    }

    static Integer handleInputError() {
        order.errorHandling();
        System.out.print("=> ");
        Optional<String> errorChoice = Optional.of(Optional.of(input.nextLine()).orElse("N"));
        if (errorChoice.get().equalsIgnoreCase("Y")) {
        return -1;
        } else {
            return 0;
        }
    }
}
