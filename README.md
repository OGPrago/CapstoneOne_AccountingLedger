# CapstoneOne_AccountingLedger

## Table of Contents

- [Features](#features)
- [Menus](#menus)
- [Interesting Code](#interesting-code)

## Features 
- Add payment and deposits
- Generate reports by:
  - All transactions
  - Payments
  - Deposits
  - Month to date
  - Previous month
  - Year to date
  - Previous Year
  - Search by vendor

### Menus
<details>
<summary>Click to Expand</summary>

### Home Screen
![HomeScreen.png](images/HomeScreen.png)

<details>
<summary>Add Deposit</summary>

![Deposit.png](images/Deposit.png)

</details>

<details>
<summary>Make Payment (Debit)</summary>

![Payment.png](images/Payment.png)

</details>


<details>
<summary>Ledger</summary>

### Ledger Screen
![LedgerScreen.png](images/LedgerScreen.png)

<details>
<summary>All Entries</summary>

![AllTransactions.png](images/AllTransactions.png)

</details>

<details>
<summary>Deposits</summary>

![AllDeposits.png](images/AllDeposits.png)

</details>

<details>
<summary>Payments</summary>

![AllPayments.png](images/AllPayments.png)

</details>

<details>
<summary>Reports</summary>

### Reports Screen
![ReportsScreen.png](images/ReportsScreen.png)

<details>
<summary>Month To Date</summary>

![MonthToDate.png](images/MonthToDate.png)

</details>

<details>
<summary>Previous Month</summary>

![PreviousMonth.png](images/PreviousMonth.png)

</details>

<details>
<summary>Year To Date</summary>

![YearToDate.png](images/YearToDate.png)

</details>

<details>
<summary>Previous Year</summary>

![PreviousYear.png](images/PreviousYear.png)

</details>

<details>
<summary>Search by Vendor</summary>

![SearchByVendor.png](images/SearchByVendor.png)

</details>

<details>
<summary>Custom Search</summary>

![CustomSearch.png](images/CustomSearch.png)

<details>
<summary>Amount</summary>

![AmountSearch.png](images/AmountSearch.png)

</details>

</details>

</details>

</details>

</details>

## Interesting Code
![InterestingCode.png](images/InterestingCode.png)

Made it that if the user doesn't put a "-" when asking for the amount for Payment, it will check if the sign was entered. If yes, then continue, but if not, then it adds the sing to transasction.txt