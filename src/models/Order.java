package models;

import com.sun.org.apache.xpath.internal.operations.Or;

public class Order {
    Long idOrder;
    String fullName;
    String phone;
    String address;
    String totalAmount;
    String timeCreatOrders;

    public Order() {
    }
    public Order(Long idOrder, String fullName, String phone, String address) {
        this.idOrder = idOrder;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }


    public Order(Long idOrder, String fullName, String phone, String address, String timeCreatOrders) {
        this.idOrder = idOrder;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.timeCreatOrders = timeCreatOrders;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTimeCreatOrders() {
        return timeCreatOrders;
    }

    public void setTimeCreatOrders(String timeCreatOrders) {
        this.timeCreatOrders = timeCreatOrders;
    }

    public static Order parseOrder(String orderUser) {
        Order order = new Order();
        String[] array = orderUser.split(",");
        order.setIdOrder(Long.parseLong(array[0]));
        order.setFullName(array[1]);
        order.setPhone(array[2]);
        order.setAddress(array[3]);
//        order.setTotalAmount(array[4]);
        order.setTimeCreatOrders(array[4]);
        return order;
    }

    @Override
    public String toString() {
        //1666003768,Nguyen Hoan,0989898978,hue,null
        return String.format("%s,%s,%s,%s,%s",
                idOrder,
                fullName,
                phone,
                address,
                timeCreatOrders);
    }
}
