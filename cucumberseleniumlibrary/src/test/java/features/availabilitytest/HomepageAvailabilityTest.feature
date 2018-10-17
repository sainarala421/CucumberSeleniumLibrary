Feature: Login Availability Test
  @AutomationPractice @AvailabilityTest @Homepage
  Scenario: Homepage availability
  	Then User should be forwarded to "Base URL"
  	Then Page title should be "My Store"
