Feature: Robotframework test cases
@AvailabilityTest
  Scenario: User should open webpage in firefox
  	When User opens url "https://www.facebook.com" in "firefox" browser
  	When Get system info

@FunctionalityTest
  Scenario: User should open webpage in chrome
  	When User opens url "https://www.facebook.com" in "chrome" browser
  	When Get system info
