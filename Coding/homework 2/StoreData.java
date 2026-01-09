/**
 * Merged data from three APIs
 */
public class StoreData {
    private String productData;
    private String reviewData;
    private String inventoryData;

    public StoreData(String productData, String reviewData, String inventoryData) {
        this.productData = productData;
        this.reviewData = reviewData;
        this.inventoryData = inventoryData;
    }

    public String getProductData() {
        return productData;
    }

    public String getReviewData() {
        return reviewData;
    }

    public String getInventoryData() {
        return inventoryData;
    }

    @Override
    public String toString() {
        return "StoreData {\n" +
                "  Products API Response:\n    " + productData + "\n\n" +
                "  Reviews API Response:\n    " + reviewData + "\n\n" +
                "  Inventory API Response:\n    " + inventoryData + "\n" +
                "}";
    }
}