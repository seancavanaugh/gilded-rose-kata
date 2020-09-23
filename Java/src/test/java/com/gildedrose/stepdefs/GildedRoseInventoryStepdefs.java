package com.gildedrose.stepdefs;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GildedRoseInventoryStepdefs {
    private static final String NAME = "name";
    private static final String SELLIN = "sellin";
    private static final String QUALITY = "quality";
    private GildedRose gildedRose;

    @Given("^we have a list of items in stock$")
    public void weHaveAListOfItemsInStock(List<Map<String, String>> data) {
        Item[] items = data.stream()
                .map(row -> new Item(row.get(NAME), parseInt(row.get(SELLIN)), parseInt(row.get(QUALITY))))
                .toArray(Item[]::new);
        gildedRose = new GildedRose(items);
    }

    @When("^(\\d+) days passes$")
    public void daysPasses(int days) {
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }
    }

    @Then("^we should have the following updated items in stock$")
    public void weShouldHaveTheFollowingItemsInStock(List<Map<String, String>> data) {
        Item[] actualItems = gildedRose.getItems();
        assertEquals(data.size(), actualItems.length);
        assertTrue(allItemsAndRowsMatch(data, actualItems));
    }

    private boolean allItemsAndRowsMatch(List<Map<String, String>> expectedItems, Item[] actualItems) {
        return expectedItems.stream().anyMatch(expectedItem ->
                stream(actualItems).anyMatch(actualItem -> rowMatchesItem(expectedItem, actualItem)));
    }

    private boolean rowMatchesItem(Map<String, String> expectedItem, Item actualItem) {
        return expectedItem.get(NAME).equals(actualItem.name)
                && parseInt(expectedItem.get(QUALITY)) == actualItem.quality
                && parseInt(expectedItem.get(SELLIN)) == actualItem.sellIn;
    }
}
