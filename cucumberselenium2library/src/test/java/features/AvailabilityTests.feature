Feature: Robotframework test cases
@AvailabilityTest
  Scenario: User should open webpage in firefox
  	When User opens url "https://www.facebook.com" in "firefox" browser
  	When Get system info

@FunctionalityTest
  Scenario: User should open webpage in chrome
  	When User opens url "https://www.facebook.com" in "chrome" browser
  	When Get system info

@SampleTest
  Scenario: Sample test
  	When User clicks "css=#m-global-toolbar__nav__utility__tools-list__login"
  	And User clicks "css=.m-global-toolbar__nav__menu__item--y2d__login"
  	Then User should be forwarded to "/2d/mobile/common/login/login.jsp"
