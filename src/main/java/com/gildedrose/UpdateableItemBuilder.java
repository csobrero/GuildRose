package com.gildedrose;

public final class UpdateableItemBuilder {
    
    private Item item;
    
    public UpdateableItemBuilder() {
        this.item = new Item("", 0, 0);
    }
    
    public UpdateableItemBuilder name(String name) {
        this.item.name = name;
        return this;
    }
    
    public UpdateableItemBuilder sellIn(int sellIn) {
        this.item.sellIn = sellIn;
        return this;
    }
    
    public UpdateableItemBuilder quality(int quality) {
        this.item.quality = quality;
        return this;
    }
    
    public UpdateableItem build() throws ItemException {
        if (item.name.equals("Aged Brie")) {
            return new AgedBrieItem(this.item);
        }
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return new SulfurasItem(this.item);
        }
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return new BackstagePassesItem(this.item);
        }
        if (item.name.equals("Conjured Mana Cake")) {
            return new ConjuredItem(this.item);
        }
        return new CommonItem(this.item);
    }
    
    public Item getItem() {
        return item;
    }
    
    public class CommonItem extends Item implements UpdateableItem {
        
        private CommonItem(final Item item) throws ItemException {
            super(item.name, item.sellIn, item.quality);
            if (item.quality > 50) {
                throw new ItemException("Quality higher than 50 not allowed.");
            }
        }
        
        public void updateQuality() {
            sellIn = sellIn - 1;
            quality = quality - 1;
            checkLimits();
        }

        protected void checkLimits() {
            if (quality < 0) {
                quality = 0;
            }
            else if (quality > 50) {
                quality = 50;
            }
        }
        
    }
    
    public class SulfurasItem extends Item implements UpdateableItem {
        
        private SulfurasItem(final Item item) throws ItemException {
            super(item.name, item.sellIn, 80);
            if (item.quality != 80) {
                throw new ItemException("Sulfuras quality must be 80.");
            }
        }
        
        public void updateQuality() {
        }
        
    }
    
    public final class AgedBrieItem extends CommonItem {
        
        private AgedBrieItem(final Item item) throws ItemException {
            super(item);
        }
        
        public void updateQuality() {
            sellIn = sellIn - 1;
            quality = quality + 1;
            if (sellIn < 0) {
                quality = quality + 1;
            }
            checkLimits();
        }
        
    }
    
    public final class BackstagePassesItem extends CommonItem {
        
        private BackstagePassesItem(final Item item) throws ItemException {
            super(item);
        }
        
        public void updateQuality() {
            sellIn = sellIn - 1;
            if (sellIn <= 0) {
                quality = 0;
            }
            else if (sellIn <= 5) {
                quality = quality + 3;
            }
            else if (item.sellIn <= 10) {
                quality = quality + 2;
            }
            else {
                quality = quality + 1;
            }
            checkLimits();
        }
        
    }
    
    public class ConjuredItem extends CommonItem  {
        
        private ConjuredItem(final Item item) throws ItemException {
            super(item);
        }
        
        public void updateQuality() {
            sellIn = sellIn - 1;
            quality = quality - 2;
            checkLimits();
        }
        
    }
    
}
