package com.gildedrose;

public class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!AGED_BRIE.equals(items[i].name)
                    && !BACKSTAGE_PASSES.equals(items[i].name)) {
                if (items[i].quality > 0) {
                    if (!SULFURAS_HAND_OF_RAGNAROS.equals(items[i].name)) {
                        int itemQualityDegradingAmount = !CONJURED_MANA_CAKE.equals(items[i].name) ? 1 : 2;
                        items[i].quality = items[i].quality - itemQualityDegradingAmount;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (BACKSTAGE_PASSES.equals(items[i].name)) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!SULFURAS_HAND_OF_RAGNAROS.equals(items[i].name)) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!AGED_BRIE.equals(items[i].name)) {
                    if (!BACKSTAGE_PASSES.equals(items[i].name)) {
                        if (items[i].quality > 0) {
                            if (!SULFURAS_HAND_OF_RAGNAROS.equals(items[i].name)) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = 0;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}
