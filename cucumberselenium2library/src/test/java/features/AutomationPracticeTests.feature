Feature: Automation Practice
@AutomationPractice
  Scenario: Homepage availability
  	Then User should be forwarded to "Base URL"
  	
@AutomationPractice
  Scenario: User login
  	When User clicks "Login Button"
  	Then User should be forwarded to "Login Page"
  	When User inputs "abc@234.com" in "Email Field"
  	And User inputs password "Test@123" in "Password Field"
  	And User clicks "Signin Button"
  	Then User should be forwarded to "User Home Page"