| method/behavior | valid case(s) | exception/invalid case(s) | boundary case | oracle/expected result | related JUnit test(s) |
|-----------------|---------------|---------------------------|---------------|------------------------|-----------------------|
| getSlotIndex()  | code A - D = 0 - 3 | else throws exception | codes != 0 - 3 | returns 0 - 3 (index) based on selection | code is single capital letter |
| addItem() | specified slot = null | else throws exception | slot != null | itemArray[slotIndex] = item | add an item called null, add item that is null |
| getItem() | getSlotIndex() returns 0 - 3 | n/a | if getSlotIndex() throws exception | returns item at slot index | n/a |
| removeItem() | return item in selected slot | if item = null throw exception | item slot is null | return item in selected slot | "null" != null  |
| insertMoney() | int amount >= 1 | else throws exception | int amount < 1 | balance += amount | amount data type != double, amount !< 0.01, initial input minus rounded input by hundreths == 0 |
| getBalance() | all | n/a | n/a | returns balance as double | balance != double data type |
| makePurchase() | (item != null) && (balance >= item.getPrice()) | else returns false | n/a | returns boolean | "null" != null |
| returnChange() | all | n/a | n/a | returns change as double | balance < 0 |

boundary testing via parameter tests for both valid and invalid tests of insertMoney() and for testInvalidMakePurchase()

The fault you introduced: allowed 0 to be valid as input for insertMoney()
The test or tests that failed: testInvalidInsertMoney()
The relevant JUnit failure message: "java.lang.AssertionError: expected VendingMachineException to be thrown, but nothing was thrown"
Why the test detected the fault: only inputs less than 0 are thrown by removing the equal sign, the amount of 0 is an unexceptable input because it is not possible