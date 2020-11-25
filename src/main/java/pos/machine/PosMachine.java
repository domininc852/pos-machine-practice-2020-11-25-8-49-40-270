package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        ArrayList<ProductInfo> itemsInformation = processBarcodes(barcodes);
        Receipt receipt=new Receipt(itemsInformation);
        return receipt.generateReceipt();
    }

    private ArrayList<ProductInfo> processBarcodes(List<String> barcodes) {
        ArrayList<ProductInfo> itemsInformation = new ArrayList<ProductInfo>();
        List<String> unique_barcodes = barcodes.stream().distinct().collect(Collectors.toList());
        unique_barcodes.forEach(barcode -> itemsInformation.add(retrieveItemDetail(barcode)));
        return findQuantityOfEachItem(barcodes, itemsInformation);
    }

    private ProductInfo retrieveItemDetail(String barcode) {
        List<ItemInfo> itemInfos = ItemDataLoader.loadAllItemInfos();
        ItemInfo item = itemInfos.stream().filter(ItemInfo -> ItemInfo.getBarcode().equals(barcode)).findFirst().get();
        return new ProductInfo(item.getBarcode(), item.getName(), item.getPrice(), 0, 0);
    }

    private ArrayList<ProductInfo> findQuantityOfEachItem(List<String> barcodes, ArrayList<ProductInfo> itemsInformation) {
        itemsInformation.forEach(itemInfo -> {
            itemInfo.setQuantity(Collections.frequency(barcodes, itemInfo.getBarcode()));
        });
        return itemsInformation;
    }

}
