package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    private GildedRose gildedRose;


    @Test
    public void sulfurasDoesNotDecreaseInQualityOrSellIn() throws Exception {
        Item[] items = new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", 0, 80)
        };
        gildedRose = new GildedRose(items);
        passNumberOfDays(2);
        assertItem(items[0], "Sulfuras, Hand of Ragnaros", 0, 80);
    }

    @Test
    public void commonItemsDecreaseOverTime() throws Exception {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 5, 10)
        };
        gildedRose = new GildedRose(items);
        passNumberOfDays(1);
        assertItem(items[0], "+5 Dexterity Vest", 4, 9);
    }

    @Test
    public void commonItemsDecreaseOverTimeAfterMultipleDays() throws Exception {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 5, 10)
        };
        gildedRose = new GildedRose(items);
        passNumberOfDays(3);
        assertItem(items[0], "+5 Dexterity Vest", 2, 7);
    }

    @Test
    public void itemsNeverGoBelowZero() throws Exception {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 0, 0)
        };
        gildedRose = new GildedRose(items);
        passNumberOfDays(1);
        assertItem(items[0], "+5 Dexterity Vest", -1, 0);
    }

    @Test
    public void agedBrieIncreasesInQuality() throws Exception {
        Item[] items = new Item[]{
                new Item("Aged Brie", 5, 5)
        };
        gildedRose = new GildedRose(items);
        passNumberOfDays(1);
        assertItem(items[0], "Aged Brie", 4, 6);
    }

    @Test
    public void backstagePassesIncreaseBy2From5To10DaysOut() throws Exception {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5)
        };
        gildedRose = new GildedRose(items);
        passNumberOfDays(5);
        assertItem(items[0], "Backstage passes to a TAFKAL80ETC concert", 5, 15);
    }

    @Test
    public void backstagePassesIncreaseBy3From5To0DaysOut() throws Exception {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5)
        };
        gildedRose = new GildedRose(items);
        passNumberOfDays(5);
        assertItem(items[0], "Backstage passes to a TAFKAL80ETC concert", 0, 20);
    }

    @Test
    public void backstagePassesGoToZeroAtSellInZero() throws Exception {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5)
        };
        gildedRose = new GildedRose(items);
        passNumberOfDays(1);
        assertItem(items[0], "Backstage passes to a TAFKAL80ETC concert", -1, 0);
    }

    @Test
    public void conjuredItemsDecreaseTwiceAsFast() throws Exception {
        Item[] items = new Item[]{
                new Item("Conjured Mana Cake", 10, 10)
        };
        gildedRose = new GildedRose(items);
        passNumberOfDays(3);
        assertItem(items[0], "Conjured Mana Cake", 7, 4);
    }

    private void assertItem(Item item, String name, int sellIn, int quality) {
        assertEquals(name, item.name);
        assertEquals(sellIn, item.sellIn);
        assertEquals(quality, item.quality);
    }

    private void passNumberOfDays(int days) {
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }
    }

}
