package org.akbarokta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Data
public class Order {
    private List<OrderItem> items = new ArrayList<>();
    private Integer totalQty = 0;
    private Integer totalPrice = 0;
    private static DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private static List<Product> productList = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem {
        private String itemName;
        private Integer itemQty;
        private Integer itemPrice;
    }

    public static void displayMenu(List<Product> productList) {
        System.out.println("========================================");
        System.out.println("Selamat Datang di Binar Food");
        System.out.println("========================================\n");
        System.out.println("Silahkan pilih makanan : ");

        IntStream.range(0, productList.size())
                .forEach(i -> {
                    Product item = productList.get(i);
                    System.out.print((i + 1) + ". " + item.getProductName() + " | Rp. " + decimalFormat.format(item.getPrice()) + "\n");
                });

        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi");
    }

    public boolean errorHandling() {
        System.out.println("=================================");
        System.out.println("Mohon masukan input \npilihan anda");
        System.out.println("=================================");
        System.out.println("(Y) untuk lanjut");
        System.out.println("(n) untuk keluar");
        return false;
    }

    public void addItem(String itemName, Integer itemQty, Integer itemPrice) {
        if (itemQty <= 0) {
            System.out.println("Jumlah makanan harus lebih dari 0.");
            return;
        }

        if (itemName.isEmpty()) {
            throw new IllegalArgumentException("Nama makanan tidak boleh kosong.");
        }

        Optional<OrderItem> existingItem = items.stream()
                .filter(item -> item.getItemName().equals(itemName))
                .findFirst();

        existingItem.ifPresent(item -> {
            item.setItemQty(itemQty);
            item.setItemPrice(itemPrice);
        });

        if (!existingItem.isPresent()) {
            OrderItem newItem = new OrderItem(itemName, itemQty, itemPrice);
            items.add(newItem);
        }
    }

    public void printOrderDetails() {
        AtomicReference<String> formattedTotalPrice = new AtomicReference<>("");
        totalPrice = 0;
        totalQty = 0;
        System.out.println("\n=============================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("=============================\n");
        System.out.println("Rincian Pesanan:");

        items.forEach(item -> {
            String formattedPrice = decimalFormat.format((long) item.getItemPrice() * item.getItemQty());
            System.out.println((items.indexOf(item) + 1) + ". " + item.getItemName() + "\t" + item.getItemQty() + "\t" + "Rp. " + formattedPrice);
            totalQty += item.getItemQty();
            totalPrice += item.getItemPrice() * item.getItemQty();
            formattedTotalPrice.set(decimalFormat.format(totalPrice));
        });

        System.out.println("----------------------------------+");
        System.out.println("Total \t\t\t\t" + totalQty + "\t" + "Rp. " + formattedTotalPrice + "\n");
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
    }

    public void printReceipt() {
        int counter = 1;
        String newFileName = "Struct.txt";

        while (new File(newFileName).exists()) {
            newFileName = "Struct_" + counter + ".txt";
            counter++;
        }
        File file = new File(newFileName);

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter StructWr = new BufferedWriter(fw)
        ) {
            StructWr.write("=============================\n");
            StructWr.write("Binar Food\n");
            StructWr.write("=============================\n\n");
            StructWr.write("Terima kasih sudah memesan \ndi Binar Food\n\n");
            StructWr.write("Dibawah ini adalah pesanan anda\n\n");

            items.forEach(item -> {
                long subtotal = (long) item.getItemPrice() * item.getItemQty();
                String formattedPrice = decimalFormat.format((long) item.getItemPrice() * item.getItemQty());
                String strukItem = item.getItemName() + "\t" + item.getItemQty() + "\t" + "Rp. " + formattedPrice;
                try {
                    StructWr.write(strukItem);
                    StructWr.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            StructWr.write("-------------------------------+\n");
            StructWr.write("Total \t\t\t" + totalQty + "\t" + "Rp. " + totalPrice);
            StructWr.newLine();
            StructWr.write("\n\nPembayaran : BinarCash\n\n");

            StructWr.write("=============================\n");
            StructWr.write("Simpan Struk ini sebagai \nbukti pembayaran\n");
            StructWr.write("=============================");

            StructWr.flush();

            System.out.println("Struk berhasil disimpan di " + newFileName);
        } catch (IOException e) {
            System.out.print("Terjadi kesalahan saat menyimpan struk : ");
            e.getCause();
        }
    }


}
