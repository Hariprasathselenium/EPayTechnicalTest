Feature: Verify Currency Convertor using xe converter site

  Background:
    Given Open the Browser and Verify Site is launched
    
  @verifyCurrencyConversion
  Scenario Outline: Verify Currency Conversion for GBP to EUR
  When Click Convert form button
  When Clear value in Amount Input Field
  Then Verify Error message when amount is cleared in input field
  And Verify From field contains INR - "<From Field Value>" option is available
  Then Verify Convert button is displayed
  And Enter Value in Amount field
  Then Choose GBP - british pound in From dropdown
  Then Click Convert button
  Then Verify Currency Conversion from GBP to EUR
  
   Examples:
    |From Field Value|
    |Indian Rupee|
    
   @verifyRegistration
  Scenario Outline: Verify Registration button functionality
  When Scroll down the page and Click on signIn and Send brightbile button
  Then Enter "<UserName>" under Email input box
  Then Enter "<Password>" under Password input box
  Then Verify the registration button is enabled
  
  Examples:
    |UserName|Password|
    |standard_user@123.com|Test@1234|