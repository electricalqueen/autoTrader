Feature: To Verify autoTrader.com home page 

@run
Scenario: Verify page elements on home page
   Given User navigates to autotrader page
   When The autoTrader home page loads
   Then user verifies following links are present
   |Browse by Make|Browse by Style|Advanced Search|
   And user verifies "search" Button is present
   And user verifies the dropdowns are present
   
   
@run   
Scenario: Verify Advanced Search functionality 
   Given User navigates to autotrader page
   And User is on Advanced Search page 
   When user Enter "30004" in Zip Code textbox
   And  select "Certified" check box under Condition
   And select "Convertible" check box under Style
   And select "2017" as From year and select "2020" as To year 
   And Select Make as "BMW" under Make, Model & Trim section
   And clicks the button "Search" at the bottom of the page
   Then user will only see "BMW" cars in the results page   
      