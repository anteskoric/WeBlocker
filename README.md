## Summer project 2019 - WeBlocker

**`Author: Ante Skoric`**


WeBlocker is open source tool that allows the user to block and unblock websites,
track and delete cookies,as well as web and terms history.

![Screenshot](AppScreenshot.png)


In the main menu of the app, top ten used websites are displayed in a pie diagram.
This feature is only implemented for Google Chrome.

The user can check the Web History and the Searched Terms History, 
the Terms and Web History can be deleted.

There is a option to block websites, this is implemented for all browsers.
The domain of the website is written to the hosts file, this blocks the website.
The user may block a certain websites in two ways:

The first option is to block the website immediately.

The second one is to input **allowed** daily use of the site, after seven days 
(one week) it checks whether the average daily use of the site for the last 
seven days is greater than the allowed use of the site. In this case, the 
website will be blocked.
If this is the case the website will be blocked.
  
After blocking the website the website will be displayed in the Blocked Websites menu.
The user may unblock websites after they have been blocked.

The last feature of the tool is the tracking and display of all cookies. 
The cookie's creation date, expiration date, and last access date are also displayed.

