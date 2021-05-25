### The program designed by CSED, Faculty of Engineering, Alexandria University students.

# QuadCore Mail Server Features

object-oriented project developed in three layers :

-   Database layer: using Mysql database and Hibernate library as ORM (Object Relational Mapper), Database designed in reverse engineering by code first mechanism.
    
-   Backend layer: developed by spring boot framework
    
-   Frontend layer: developed in Vue framework with vuetify and javascript.
    

Our team has also hosted QuadCore Mail Server using Heroku cloud application platform and Navisite for database server.

We invite you to visit our project here  [https://quadcore-mail.herokuapp.com/](https://quadcore-mail.herokuapp.com/)

  

Database Schema

![](https://lh4.googleusercontent.com/PMVt5B24JUN2gQnovdKjDtX-Dd3N5E_hmpIR69aCvksGzhncNXdeN8k-kcZGE-3nOdkUNMOkVBAmewhr08D9eX4UrKyCGjd59LPH6NTcb4BDtW5F3akJwOmoo3uxduOzsKMVXvwt)

# How to run

-   You can visit our project on [https://quadcore-mail.herokuapp.com/](https://quadcore-mail.herokuapp.com/)
    

Or you could run it manually

-   First you need to have mysql database installed on your device. You can download it from [here](https://dev.mysql.com/downloads/installer/)
    
-   You need to run your mysql service.
    
-   Before the spring boot app you need to configure your database.
    
-   Go to <backend-directory>\src\main\resources
    
-   Open both application.properties and hibernate.cfg.xml
    

![](https://lh4.googleusercontent.com/d3W1KvF3GXXaEKN1gWpqsvZF7ScHk-6QnBGd8X0wrtr78BAt6WwKreQk5PsM2-hEoRYaczpVu8SlNBAgNifZUlYfP8KcnpfMnynDJi4NafmlOMUSAOa24SHAlizXYc_QpeE5du0w)

-   Put your database credentials here
    

![](https://lh6.googleusercontent.com/ZaBrOoN8Hjfkgc2Tc1gqGiYIiobdZAcWSQpyZvmvsrtVbau_Gj63zHIMOq3iHPOsW_lnG6n5rGMoVOUZOdMXE7sQt9noRcmTXLEw5ykxW-MWEkGvTgYY9gORnFX8hQrmK5lTifET)

-   And one last step also here
    

![](https://lh5.googleusercontent.com/7eYuYZn2HlL0H16WWEbUjd7NSJIC74028hEaM4DNuGMUq_75h0hS6X7hLJFD-8wfeDh9YyQrumjPJqmLW9bFtoGF-8mhunNkhK9UNw_EQu3rpd9B7W8Y0NI55RV4QEAfjcHs_s6M)

-   Now run EmailClientApplication in backend project :
    

  
  

![](https://lh3.googleusercontent.com/E3w-W9MyOqVOIJ3xiyaKH7_JDg3JiLqdmTxHcYCISiZSyXGvuhdmy4RMBmB00jQT3QpUTWGUD5od7Z1kAo6Pca3SmmCgc5fFnrFgRaLa4gifU5uYz83LRUp_KtL1lAwy0wcK9eP5)

  
  

-   Write npm run serve in frontend project directory:
    

![](https://lh3.googleusercontent.com/raVlQunItbOXcArbzlTlNJiyvvIQ1iOttZW41OoM2P_fvmqMQSYB_T-D8-XvB53sBz0V49nXbc9VyJs-2VhJnuONCYCVAhlj7GFoyuFbViW_NJ29-c2_KedD7s-husZSds5VWV-P)

# UML Diagram

![](https://lh4.googleusercontent.com/XGHaTG3CxDkth0dNUxnwLWbrGY2YBihSkL9kLpPbVbwP-CjqwrUmKJm2iYnYovi0_4CwL7FmkeUSMdI8-3TSPzL6vEjDu5nlkA-0WPXRl-zPuP-DEY2Cxpo0Wmmr6Lah59AHr1uH)

  
  
  

# User Manual
## Sign Up

![](https://lh5.googleusercontent.com/C9yNHZpKigPEkRSxm2CahoHr46dYnIIUxaLdRXitbn2r3E8CUtHNrDTazX-UdD4tDpTxEmBBXlDw-dylUACLipZ3jLE4EyDusXTU98In0Eevkh6AysuDOR_DjOqlGMBlhksPTChj)

Head to [https://quadcore-mail.herokuapp.com/](https://quadcore-mail.herokuapp.com/) and click SIGN-UP in the top right corner. Enter your first and last names, your birthday, your email of choice and your password, then hit sign-up at the bottom of the form. Your email address must be unique so the website may prompt you to change your chosen address in case it was already taken by someone else. It is worth mentioning that all fields are required, email addresses shall not contain ‘@’ character, and the password is 8 characters minimum. Make sure to choose a hard-to-guess password for your email security. You will be redirected to sign in to your newly created QuadCore account.

  
  

## Sign In
![](https://lh3.googleusercontent.com/f_ZMjXn4IWC8TouXigYRBiCQcX88XlMatG_V8DXLgQQbqreNfeSXtFrsaNg7j1-j8HLOy04UK-VWcxtLlo7F7dGKh2DnGF6v8Ss15cL0RNh7YKqUXLOBirDSituyE99GzM6maVhP)

Enter your email address -don’t forget ‘@quadcore.com’- and your password then hit sign-in. Your home page will open up in case you entered the right email and password and will prompt you to re-enter them otherwise.

  

# ![](https://lh3.googleusercontent.com/0R6mLtbZxQzM-hTK6iMcxyH-WFKyL7cPKLdQJv4cai0UU-d_-0zJQGhCs-Ds9WobKWXd4hzhj2jD7DOYT7ksTuxupUW0rlE4Hj4pf6_u2svQcz2Xar_G8OoyJC6JjUgKe4w-E49e)

## Home

1.  ### Navigation
    

	It is located below your name and address on the left side of the screen. This is where you can choose to compose a new email, navigate between your default and user-defined folders (we’ll get to that later), as well as managing your folders and contacts. Inbox is chosen by default.![](https://lh4.googleusercontent.com/jkXz9Mb33Jf-YXwO5v9ps766-agA42bj74cyKYaaXnwbjDe6wHdlQECuzj7aKlpEvcAperjfg_xGD5gb0ADSCrzvtPp5CzCAh1IUbZ0XORpeRWzKXsjMIrDpaE4ZXUUxTM1Yc7Up)

2.  ### Compose
    

	To create a new email, click on compose. You can add a subject in the subject field. To add a receiver for your email, type their address in the To field then hit space or enter. You can add multiple receivers. Choose the priority of your email, this will be viewed by your receivers and will show up in their inbox if they sort by priority. Write something beautiful in the body of mail and add any attachments you want. You can add multiple file attachments. Click send or save as draft in case you want to continue just where you left-off later on.

  

3.  ### Folders
    

	#### Default Folders
    

	-  Inbox: is where you receive your new emails![](https://lh6.googleusercontent.com/fchj5uYY5cQKyvzdHxVGVAI_Tpc1rA9LAKtOgk2C2ZxlX-WQxS79F0Rqlhs4dod-W_grznrFL3xgSFn98x5cpgJHlxfhOnZEgvgFe9P-rAOP_vjPNl3Jcjr2ptp-rV6qjmkOZS8K)
    
	- Sent: is where you can find the emails you sent
    
	- Draft: is the folder containing the drafts you saved. Drafts are opened in the compose view. Make sure to save them as drafts again if you make any changes.
    
	- Trash: where you can find the emails you delete. Emails in trash are permanently deleted after 30 days.
    
	- Archive: is the folder to which you can move the emails from your inbox in order to archive them
    

  

	#### User-defined Folders
    

	Click on the folders button at the bottom of the navigation drawer to view the window shown. Click add to create a new folder then enter the name of this folder. The folder will be added to the list and to the navigation drawer. You can click the folder (as in the second picture shown) to edit its name or to delete it.
![](https://lh4.googleusercontent.com/YvBqtoZTlOt7qn7ndi3UbOcdYKe9FXgccFY5ddnNvo46ij90T63sUod3K_srUCtZ9EwiJ0aLtnrDQ_6REKnRPX6pcccOSYtAhAjlI3jbkPROYFVIt6V0h6hsJg1B9XgVSxBcb6uv)![](https://lh3.googleusercontent.com/DXwaa2XLERSzk5mCL5UxN870CsCx0yk0d4LxxdH_TYR--UainnQLSxhr8BfLCmOIg-0QaqyxRO6eSfyms5Bi8zgA7OOmFHIA4O6jLaey4mYO50k_fRGz2QNTGuiVZ0d5CXRy1Ogc)

  
![](https://lh6.googleusercontent.com/kSr2QZt3DVkgaYUK2i-VSVEySAgFp8l1ktd1iXcMs-XgXanNlknju99uxERvnkZ0sGxq0AQW7CgXC4GRrwhegI1hDgXJn_Vyn-GnHpCsCjTnERWRO_VKEfiC3le5StZlF8MS8iyu)

4.  ### Contacts
    

	Contacts are just the same as folders in the way they are created, edited and deleted. You can add multiple emails to your contacts.

  
  

5.  ### Emails![](https://lh4.googleusercontent.com/b1zECmOg0_RhvsaU1V_81AX0spQYpME9F3Cp3IRIycLOJHJCFlsazFgihwu81BxgtkgCr1u1M1zfL_HoYA6fOxiGBa5O_vqGkXZwebfXuQ4q1CWYB69XMNOS_vtx7Mj9y8KKnSgW)
    

	This is a test mail that I received in my inbox. To open the mail, click preview. Clicking on the mail will select it for bulk operations like move, delete, and copy. Inside the mail, you can click on the attachments below to download them.

6.  ### Search
    

	Use the search box above to search the subjects and the senders of the mails according to the criteria you enter. Click on the icon to the right to apply search or the ‘x’ icon to revert to original state.

7.  ### Filter![](https://lh3.googleusercontent.com/WM2KHM6c08Ix3wQlpMdVgDdBIIx6nRmfQF6pfvZtlBGKYXnso0ZRP0hHxGkskQ0BPovAK7lFG0skXRygDQnbX7BRkaP8Ddhvug8f1V8IpV1YuhjS782EHE7OqsrE1o8KctNWvioC)![](https://lh3.googleusercontent.com/1HIel4a8mmx1WtlVI3HNZ8gVByrjMUOEIh7aqv0y4ArGvyYjv8r9GVSuBrwT0oilYsmMHP4thbzy_vLlFORl8cR4tbYQSIEf5qlW3knlJzaTSom0Jtg37HPzUPnJqykmxbyhBfJ-)
    

	Click on the filter icon to filter emails based upon the date, title, or sender.

8.  ### Sort
    

	The right end of the bar at the top of your mail list you can sort by date, priority, sender, or title of the current folder. Click on the arrows at the very right to reverse the sorting.

9.  ### Bulk Operations
    

	Select multiple emails by simply clicking on each of them. You can then choose to move, copy, or delete the selected emails. You will be prompted to choose one of the existing folders to which you want to move or copy the selected emails. Deleted emails are moved to the trash.  
  

  
  
  
  

10.  ### Other
    

		-  #### Log out
			You can find this button on the top right corner of the page. This is the only way (apart from closing the window/tab) to log out of your account. Going back or refreshing won’t do so.

		-  #### Dark Mode
    

			You can always toggle dark mode from the switch on the left upper corner of the page. Your preference is saved as long as you are on the same window/tab.

		-  #### Refresh
    

			You can refresh your current mailbox by clicking on the refresh icon beside your mailbox name.

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

# Design Patterns

1.  Singleton.
    
2.  Proxy.
    
3.  Facade.
    
4.  Filter.
    
5.  Immutable.
    
6.  Read-only.
    

  

## Singleton

SecurityFilter class applies Singleton design pattern as during runtime we need only one instance of it. It has an instance of the session factory.

  

## Proxy

SecurityFilter applies the proxy design pattern as It authenticates the login and sign up for users. It has an instance of user session for the active user. The request is sent with a key for this active user.

  
  

## Facade

UserSession class applies the facade design pattern. It supports most of the mail operations: move, copy, delete mails, add, edit, remove folders or contacts. It has an instance from the current logged in user. All other classes communicate with this UserSession class.

  

## Filter

Filter design pattern is used in filtering and sorting according to a certain criteria.

  
  
  

## Immutable

Immutable design pattern is used in EmailHeaderImmutable, EmailImmutable and ContactImmutable classes. We wanted to separate the database entities from the objects to be sent to the front end. Immutable has only getters as the values are set only on construction.

  

## Read-only

Email headers return a read-only interface of the user for security as we don’t want to view passwords for example, It returns only needed information.
