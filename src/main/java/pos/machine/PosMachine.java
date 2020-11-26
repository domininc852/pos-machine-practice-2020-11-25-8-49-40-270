package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        BarcodeProcessor barcodeProcessor=new BarcodeProcessor();
        ArrayList<ProductInfo> itemsInformation = barcodeProcessor.processBarcodes(barcodes);
        Receipt receipt=new Receipt(itemsInformation);
        return receipt.generateReceipt();
    }


}
