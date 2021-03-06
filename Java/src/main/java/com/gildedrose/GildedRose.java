package com.gildedrose;

import java.util.List;

import static java.util.Arrays.asList;

public class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final List<String> ITEMS_THAT_INCREASE_OVER_TIME = asList(AGED_BRIE, BACKSTAGE_PASSES);
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
    private static final int MAXIMUM_QUALITY = 50;
    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (SULFURAS_HAND_OF_RAGNAROS.equals(item.name)) {
                continue;
            }
            if (ITEMS_THAT_INCREASE_OVER_TIME.contains(item.name)) {
                updateQualityFor(item, 1);
                if (BACKSTAGE_PASSES.equals(item.name) && item.sellIn < 11) {
                    updateQualityFor(item, 1);
                    if (item.sellIn < 6) {
                        updateQualityFor(item, 1);
                    }
                }
            } else {
                updateQualityFor(item, amountToDecrease(item));
            }
            item.sellIn = item.sellIn - 1;
            if (item.sellIn < 0) {
                if (BACKSTAGE_PASSES.equals(item.name)) {
                    item.quality = 0;
                } else {
                    updateQualityFor(item, AGED_BRIE.equals(item.name) ? 1 : amountToDecrease(item));
                }
            }
        }
    }

    private int amountToDecrease(Item item) {
        return CONJURED_MANA_CAKE.equals(item.name) ? -2 : -1;
    }

    private void updateQualityFor(Item item, int amountToChangeQuality) {
        if (increasingQualityAndQualityIsNotAtMaximum(item, amountToChangeQuality)) {
            item.quality = item.quality + amountToChangeQuality;
        } else if (decreasingQualityAndQualityIsNotAtZero(item, amountToChangeQuality)) {
            item.quality = item.quality + amountToChangeQuality;
        }
    }

    private boolean increasingQualityAndQualityIsNotAtMaximum(Item item, int amountToChangeQuality) {
        return item.quality < MAXIMUM_QUALITY && amountToChangeQuality > 0;
    }

    private boolean decreasingQualityAndQualityIsNotAtZero(Item item, int amountToChangeQuality) {
        return item.quality > 0 && amountToChangeQuality < 0;
    }
}
