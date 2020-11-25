package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<ProductInfo> itemsInformation;
    private int total;

    public Receipt(List<ProductInfo> itemsInformation) {
        this.itemsInformation = itemsInformation;
        total=itemsInformation.stream().mapToInt(ProductInfo::getSubtotal).sum();
    }
    public  String generateReceipt() {
        String receipt = "***<store earning no money>Receipt***\n";
        for (ProductInfo itemInformation : this.itemsInformation) {
            receipt += String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", itemInformation.getName(), itemInformation.getQuantity(), itemInformation.getUnitPrice(), itemInformation.getSubtotal());
        }
        receipt += "----------------------\n";
        receipt += String.format("Total: %d (yuan)\n", total);
        receipt += "**********************";
        return receipt;
    }
}
