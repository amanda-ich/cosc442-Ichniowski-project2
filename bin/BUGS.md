| observed failure | test that exposed it | cause / source-code fault | how diagnosed fault | correction |
|------------------|----------------------|---------------------------|---------------------|------------|
| initialization of vending machine slots | textAddItem() -> out of bounds error | length 4 array has indexes 0 - 3 | for loop iterates i = 0 until i < array length |

