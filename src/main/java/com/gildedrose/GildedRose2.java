package com.gildedrose;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GildedRose2 {
    List<UpdateableItem> items;

    public GildedRose2(Item[] items) {
        this.items = Arrays.asList(items).stream().map(i -> {
            try {
                return new UpdateableItemBuilder()
                        .name(i.name)
                        .sellIn(i.sellIn)
                        .quality(i.quality)
                        .build();
            }
            catch (ItemException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public void updateQuality() {
        for (UpdateableItem item : items) {
            item.updateQuality();
        }
    }

    public List<UpdateableItem> getItems() {
        return items;
    }
}