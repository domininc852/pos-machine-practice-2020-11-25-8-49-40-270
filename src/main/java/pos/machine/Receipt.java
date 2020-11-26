package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<ProductInfo> productInfos;
    private final String FIRSTLINE="***<store earning no money>Receipt***\n";
    private final String HORIZONTAL="----------------------\n";
    private final String ENDLINE = "**********************";
    private final int total;

    public Receipt(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
        total=productInfos.stream().mapToInt(ProductInfo::getSubtotal).sum();
    }
    public  String generateReceipt() {
        String receipt = FIRSTLINE;
        for (ProductInfo productInfo : this.productInfos) {
            receipt += String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", productInfo.getName(), productInfo.getQuantity(), productInfo.getUnitPrice(), productInfo.getSubtotal());
        }
        receipt += HORIZONTAL;
        receipt += String.format("Total: %d (yuan)\n", total);
        receipt +=ENDLINE;

        return receipt;
    }
}
