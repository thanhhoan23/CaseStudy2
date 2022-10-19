package views;

import models.ItemOrder;
import models.Order;
import models.Product;
import services.ItemOrderService;
import services.OrderService;
import services.ProductService;
import services.UserService;
import shoeshop.Menu;
import utils.AppUtils;
import utils.InstantUtils;
import utils.ValidateUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    public List<Order> orders;
    private static UserService userService = new UserService();
    private static OrderService orderService = new OrderService();
    private static ProductService productService = new ProductService();
    private static ItemOrderService itemOrderService = new ItemOrderService();
    private static ProductView productView = new ProductView();
    private static final Scanner input = new Scanner(System.in);

    public OrderView() {
        orderService = OrderService.getInstanceOrder();
        productService = ProductService.getInstanceProduct();
        itemOrderService = ItemOrderService.instanceItemOrder();
    }

    public static void addOrder() {
        try {
            itemOrderService.findAllItemOrder();
            Long idOrder = System.currentTimeMillis() / 1000;
            System.out.println("Nhập tên người đặt hàng: (Vd: Thanh Hoan)");
            System.out.print("=> ");
            String fullName;
            while (!ValidateUtils.isNameInValid(fullName = input.nextLine())) {
                System.out.println("Tên " + fullName + " không đúng định dạng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.print("=> ");
            }
            System.out.println("Nhập số điện thoại: ");
            System.out.print("=> ");
            String numberPhone = input.nextLine();
            while (!ValidateUtils.isPhoneNumberInValid(numberPhone) || numberPhone.trim().isEmpty()) {
                System.out.println("Số điện thoại: " + numberPhone + " Không đúng đinh dạng, vui lòng nhập lại.");
                System.out.println("Nhập số điện thoại (Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.print("=> ");
                numberPhone = input.nextLine();
            }
            System.out.println("Nhập địa chỉ của bạn: ");
            System.out.print("=> ");
            String address = input.nextLine();
            while (address.trim().isEmpty()) {
                System.out.println("Địa chỉ của bạn không được để trống, vui lòng nhập lại.");
                System.out.print("=> ");
                address = input.nextLine();
            }

            Order order = new Order(idOrder, fullName, numberPhone, address, Instant.now().toString());
            List<ItemOrder> itemOrders = addItemsOrder(idOrder);
            for (ItemOrder itemOrder : itemOrders) {
                itemOrderService.addItemOrder(itemOrder);
            }
            orderService.add(order);
            System.out.println("Đã tạo order thành công:");
//            showInvoice();
        } catch (Exception e) {
            System.out.println("Server lỗi. Bạn đã quay lại trang.");
            addOrder();
        }
    }

    public static List<ItemOrder> addItemsOrder(Long id) {
        List<ItemOrder> itemOrders = new ArrayList<>();
        productView.showProductListForUser();
        System.out.println("Nhập số sản phẩm đơn hàng mà bạn muốn mua");
        System.out.print("=> ");
        int choice = Integer.parseInt(input.nextLine());
        while (choice < 0) {
            System.out.println("Số sản phẩm đơn hàng không hợp lệ (không được nhỏ hơn 0 )");
            System.out.print("=> ");
            choice = Integer.parseInt(input.nextLine());
        }
        int count = 0;
        do {
            try {
                itemOrders.add(addItemOrders(id));
                count++;
            } catch (Exception e) {
                System.out.println("Không đúng, vui lòng nhập lại.");
            }
        } while (count < choice);
        return itemOrders;
    }

    public static ItemOrder addItemOrders(Long idOrder) {

                productView.showProductListForUser();
                Long id = System.currentTimeMillis() / 1000;
                System.out.println("Nhập ID sản phẩm bạn muốn mua");
                System.out.print("=> ");
                Long idProduct = Long.parseLong(input.nextLine());
                while (!productService.exitsById(idProduct)) {
                    System.out.println("ID sản phẩm không tồn tại, vui lòng hãy nhập lại");
                    System.out.print("=> ");
                    idProduct = Long.parseLong(input.nextLine());
                }
                Product product = productService.findProductById(idProduct);
                Double price = product.getPrice();
                System.out.println("Nhập số lượng bạn muốn mua");
                System.out.print("=> ");
                Double quantity = Double.parseDouble(input.nextLine());
                while (!checkQuantityProduct(product, quantity)) {
                    System.out.println("Số lượng sản phẩm không đủ, vui lòng hãy nhập lại:");
                    System.out.print("=> ");
                    quantity = Double.parseDouble(input.nextLine());
                    if (product.getQuantity() == 0) {
                        System.out.println("Sản phẩm hết hàng.");
                        int choice;
                        do {
                            System.out.println("Nhấn 0 để quay lại quản lý sản phẩm.");
                            choice = AppUtils.retryParseInt();
                        } while (choice != 0);
                        Menu.menuManagerOrder();
                    }
                }
                String nameProduct = product.getNameProduct();
//                tiền sản phẩm
                Double total = quantity * price;
                ItemOrder itemOrder = new ItemOrder(id, price, quantity, idOrder, idProduct, nameProduct, total);
                productService.updateQuantity(idProduct, quantity);
                return itemOrder;
    }

    public static boolean checkQuantityProduct(Product product, Double quantity) {
        if (quantity <= product.getQuantity()) {
            return true;
        }
        return false;
    }

    public static void showListOrder() {
        try {
            List<Order> orders = orderService.findAllOrder();
            int count = 0;
            int total = 0;

            System.out.println("\t⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹ Danh sách hóa đơn ⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
            for (Order order : orders) {
                List<ItemOrder> itemOrders = itemOrderService.findAllItemOrder();
                double sum = 0;
                for (ItemOrder itemOrder1 : itemOrders) {
                    if (itemOrder1.getIdOrder().equals(order.getIdOrder())) {
                        sum += itemOrder1.getTotalItem();
                    }
                }
                System.out.printf("\t⇅\t%-15s %-15s %-20s %-20s %-20s %-30s %-20s %-15s║\n", "Mã đơn hàng: ", order.getIdOrder(), "Tên khách hàng :", order.getFullName(), "Ngày tạo đơn hàng:", order.getTimeCreatOrders(), "Tổng tiền đơn hàng:", InstantUtils.convertVND(sum));
                count++;
                total += sum;
            }
            System.out.println();
            System.out.printf("\t-----------------> Tổng đơn hàng:  %-5s Tổng thu nhập: %-15s <-------------------", count, InstantUtils.convertVND(total));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
