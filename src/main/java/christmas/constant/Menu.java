package christmas.constant;


public enum Menu {
    MUSHROOM_SOUP(6000, Category.APPETIZER),
    TAPAS(5500, Category.APPETIZER),
    CAESAR_SALAD(8000, Category.APPETIZER),
    T_BONE_STEAK(55000, Category.MAIN_COURSE),
    BBQ_RIBS(54000, Category.MAIN_COURSE),
    SEAFOOD_PASTA(35000, Category.MAIN_COURSE),
    CHRISTMAS_PASTA(25000, Category.MAIN_COURSE),
    CHOCOLATE_CAKE(15000, Category.DESSERT),
    ICE_CREAM(5000, Category.DESSERT),
    ZERO_COLA(3000, Category.BEVERAGE),
    RED_WINE(60000, Category.BEVERAGE),
    CHAMPAGNE(25000, Category.BEVERAGE);
    private final int price;
    private final Category category;

    Menu(int price, Category category) {
        this.price = price;
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public boolean isInCategory(Category category) {
        return this.category == category;
    }
}
