package utils;

import models.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    //    dùng chung
    //    ghi file
    public static <T> void write(String path, List<T> items) {
        try {
            PrintWriter printWriter = new PrintWriter(path);
            for (T item : items) {
                printWriter.println(item.toString());
            }
            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(path + "không có dữ liệu");
        }
    }

    //    đọc file
    public static List<String> read(String path) {
        List<String> items = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty())
                    items.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(path + "không có dữ liệu");
        }
        return items;
    }
}


////    Ghi file product.csv
//    public static void saveListProduct(ArrayList<Product> list) {
//        File file = new File("/Users/nguyenthithanhhoan/Downloads/workspace/Case/Case/data/products.csv");
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter(file);
//            fileWriter.write(convertToString(list));
//            fileWriter.close();
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
//    }
//
//    private static String convertToString(ArrayList<Product> list) {
//        String str = "";
//        for (int i = 0; i < list.size(); i++) {
//            str += list.get(i).getIdProduct() + "," +
//                    list.get(i).getNameProduct() + "," +
//                    list.get(i).getPrice() + "," +
//                    list.get(i).getQuantity() + "," + "\n";
//        }
//        return str;
//    }
//
////    Đọc file product.csv
//
//    public static ArrayList<Product> getListProduct () {
//        ArrayList<Product> list = null;
//        try {
//            list = new ArrayList<>();
//            File file =new File("/Users/nguyenthithanhhoan/Downloads/workspace/Case/Case/data/products.csv");
//            FileReader fileReader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line = null;
//            while ((line = bufferedReader.readLine())!= null) {
////                1,addidas,38,4500,5
//                String [] arr = line.split(",");
//                String sId = arr[0];
//                String name = arr[1];
//                String sPrice = arr[2];
//                String sQuantity = arr[3];
//
//                int id =Integer.parseInt(sId);
//                double price = Double.parseDouble(sPrice);
//                int quantity = Integer.parseInt(sQuantity);
//                Product product = new Product(id, name, price, quantity);
//                list.add(product);
//                return list;
//            }
//        }  catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }


