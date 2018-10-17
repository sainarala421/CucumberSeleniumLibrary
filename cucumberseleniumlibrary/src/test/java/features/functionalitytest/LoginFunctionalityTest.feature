Feature: Login With Valid and Invalid Credentials
 @AutomationPractice @FunctionalityTest @EndToEndTest
  Scenario: User login with valid credentials
  	When User clicks "Login Button"
  	Then User should be forwarded to "Login Page"
  	When User inputs "abc@234.com" in "Email Field"
  	And User inputs password "Test@123" in "Password Field"
  	And User clicks "Signin Button"
  	Then User should be forwarded to "User Home Page"

  @AutomationPractice @FunctionalityTest
  Scenario: User login with invalid credentials
  	When User clicks "Login Button"
  	Then User should be forwarded to "Login Page"
  	When User inputs "abc@234.com" in "Email Field"
  	And User inputs password "Test@1234" in "Password Field"
  	And User clicks "Signin Button"
  	Then The "Authentication Failed Alert" should be displayed
  