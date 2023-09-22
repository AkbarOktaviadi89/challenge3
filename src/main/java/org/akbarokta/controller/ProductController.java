//package org.akbarokta.controller;
//
//import org.akbarokta.model.Order;
//import org.akbarokta.model.Product;
//
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class ProductController {
//    Order order = new Order();
//    private Optional<Integer> lastInput = Optional.empty();
//    private List<Product> productList;
//    public void viewProduct(List<Product> productList) {
//        AtomicInteger count = new AtomicInteger(1);
//        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
//        String separator = "===============================\n";
//
//        System.out.print(separator);
//        System.out.println("Selamat datang di BinarFud");
//        System.out.println(separator);
//
//        System.out.println("Silahkan Pilih Makanan : ");
//        productList.stream()
//                .limit(5)
//                .forEach(val -> System.out.println(count.getAndIncrement() + ". " + val.getProductName() + " \t| " + decimalFormat.format(val.getPrice())));
//        System.out.println("99. Pesan dan Bayar");
//        System.out.println("0. Keluar Aplikasi");
//    }
//
//    public Integer selectProduct() {
//        if (lastInput.isPresent()) {
//            return lastInput.get();
//        }
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("=> ");
//        try {
//            int userInput = scanner.nextInt();
//            if (userInput >= 0) {
//                lastInput = Optional.of(userInput);
//                return userInput;
//            } else {
//                System.out.println("Input harus lebih dari 0, silahkan coba lagi");
//                return selectProduct();
//            }
//        } catch (InputMismatchException ime) {
//            System.out.println("Input tidak valid, silahkan coba lagi");
//            scanner.next();
//            return selectProduct();
//        }
//    }
//
//
//    public void getSelectProduct() {
//        while (true) {
//            Integer input = selectProduct();
//            switch (input) {
//                case 99:
//                    System.out.println("Pesan dan Bayar");
//                    order.printOrderDetails();
//                    break;
//                case 0:
//                    System.out.println("Terima kasih, sampai jumpa kembali!");
//                    System.exit(1);
//                    break;
//                default:
//                    if (input > 0 && input <= 5) {
//                        // Assuming productList is a class variable
//                        Product selectedProduct = productList.get(input - 1);
//                        order.addItem(selectedProduct);
//                    } else {
//                        System.out.println("Input tidak valid, silahkan coba lagi");
//                    }
//                    break;
//            }
//        }
//    }
//}
