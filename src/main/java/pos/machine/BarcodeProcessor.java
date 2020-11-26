package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BarcodeProcessor {
    public static ArrayList<ProductInfo> generateReceiptItems(List<String> barcodes) {
        ArrayList<ProductInfo> itemsInformation = new ArrayList<ProductInfo>();
        List<String> uniqueBarcodes = barcodes.stream().distinct().collect(Collectors.toList());
        uniqueBarcodes.forEach(barcode -> itemsInformation.add(retrieveItemDetail(barcode)));
        return findQuantityOfEachItem(barcodes, itemsInformation);
    }

    private static ProductInfo retrieveItemDetail(String barcode) {
        List<ItemInfo> itemInfos = ItemDataLoader.loadAllItemInfos();
        ItemInfo item = itemInfos.stream().filter(ItemInfo -> ItemInfo.getBarcode().equals(barcode)).findFirst().get();
        return new ProductInfo(item.getBarcode(), item.getName(), item.getPrice());
    }

    private static ArrayList<ProductInfo> findQuantityOfEachItem(List<String> barcodes, ArrayList<ProductInfo> itemsInformation) {
        itemsInformation.forEach(itemInfo -> {
            itemInfo.setQuantity(Collections.frequency(barcodes, itemInfo.getBarcode()));
        });
        return itemsInformation;
    }
}
