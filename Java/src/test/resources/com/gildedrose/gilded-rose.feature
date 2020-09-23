Feature: Gilded Rose Inventory System

  Scenario: Common Items decrease in value over time
    Given we have a list of items in stock
      | name               | sellin | quality |
      | +5 Dexterity Vest  | 10     | 20      |
      | +5 Strength Helmet | 5      | 10      |
    When 1 days passes
    Then we should have the following updated items in stock
      | name               | sellin | quality |
      | +5 Dexterity Vest  | 9      | 19      |
      | +5 Strength Helmet | 4      | 9       |

  Scenario: Common Items decrease repeatedly in value over multiple days
    Given we have a list of items in stock
      | name               | sellin | quality |
      | +5 Dexterity Vest  | 10     | 20      |
      | +5 Strength Helmet | 5      | 10      |
    When 3 days passes
    Then we should have the following updated items in stock
      | name               | sellin | quality |
      | +5 Dexterity Vest  | 7      | 17      |
      | +5 Strength Helmet | 2      | 7       |

  Scenario: Common Items decrease twice as fast after sell in date
    Given we have a list of items in stock
      | name               | sellin | quality |
      | +5 Dexterity Vest  | 2      | 20      |
      | +5 Strength Helmet | 3      | 10      |
    When 4 days passes
    Then we should have the following updated items in stock
      | name               | sellin | quality |
      | +5 Dexterity Vest  | -2     | 14      |
      | +5 Strength Helmet | -1     | 5       |

  Scenario: Common Items quality never goes negative
    Given we have a list of items in stock
      | name              | sellin | quality |
      | +5 Dexterity Vest | 2      | 1       |
    When 3 days passes
    Then we should have the following updated items in stock
      | name              | sellin | quality |
      | +5 Dexterity Vest | -1     | 0       |

  Scenario: Aged Brie increases in quality as it gets older
    Given we have a list of items in stock
      | name      | sellin | quality |
      | Aged Brie | 5      | 10      |
    When 3 days passes
    Then we should have the following updated items in stock
      | name      | sellin | quality |
      | Aged Brie | 2      | 13      |

  Scenario: Quality of an item never goes over 50
    Given we have a list of items in stock
      | name      | sellin | quality |
      | Aged Brie | 5      | 48      |
    When 3 days passes
    Then we should have the following updated items in stock
      | name      | sellin | quality |
      | Aged Brie | 2      | 50      |

  Scenario: Sulfuras, Hand of Ragnaros never decreases in value
    Given we have a list of items in stock
      | name                       | sellin | quality |
      | Sulfuras, Hand of Ragnaros | 5      | 30      |
    When 3 days passes
    Then we should have the following updated items in stock
      | name                       | sellin | quality |
      | Sulfuras, Hand of Ragnaros | 5      | 30      |

  Scenario: Backstage passes to a TAFKAL80ETC concert quality increases by 2 between sell in days 10 to 5
    Given we have a list of items in stock
      | name                                      | sellin | quality |
      | Backstage passes to a TAFKAL80ETC concert | 10     | 10      |
    When 5 days passes
    Then we should have the following updated items in stock
      | name                                      | sellin | quality |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 20      |

  Scenario: Backstage passes to a TAFKAL80ETC concert quality increases by 3 between sell in days 5 to 0
    Given we have a list of items in stock
      | name                                      | sellin | quality |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 10      |
    When 5 days passes
    Then we should have the following updated items in stock
      | name                                      | sellin | quality |
      | Backstage passes to a TAFKAL80ETC concert | 0      | 25      |

  Scenario: Backstage passes to a TAFKAL80ETC concert quality goes to zero after concert
    Given we have a list of items in stock
      | name                                      | sellin | quality |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 10      |
    When 6 days passes
    Then we should have the following updated items in stock
      | name                                      | sellin | quality |
      | Backstage passes to a TAFKAL80ETC concert | -1     | 0       |

  Scenario: Conjured items degrade in quality twice as fast
    Given we have a list of items in stock
      | name               | sellin | quality |
      | Conjured Mana Cake | 10     | 10      |
    When 2 days passes
    Then we should have the following updated items in stock
      | name               | sellin | quality |
      | Conjured Mana Cake | 8      | 6       |

  Scenario: Conjured items degrade in quality four times as fast after sellin
    Given we have a list of items in stock
      | name               | sellin | quality |
      | Conjured Mana Cake | 2      | 20      |
    When 3 days passes
    Then we should have the following updated items in stock
      | name               | sellin | quality |
      | Conjured Mana Cake | -1     | 12      |
