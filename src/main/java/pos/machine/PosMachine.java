package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        ArrayList<ProductInfo> itemsInformation = BarcodeProcessor.generateReceiptItems(barcodes);
        Receipt receipt = new Receipt(itemsInformation);
        return receipt.generateReceipt();
    }


}
