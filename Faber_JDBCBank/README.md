# Jacob Faber's DataBank(tm)

Admin credentials:
: admin1 password
: admin2 password
**It is possible to change the username/password of an admin account! Check logs if you do this and forget the admin login credentials. You can also change the username/password of the admin account you are currently using.**

### From the main menu, there are three options:
| Option No. | Option Name | Description |
| ---------- | ----------- | ----------- |
| 1  | Login | Log in to an existing account w/ username and password |
| 2  | Register | Create a new account |
| 3  | Quit | Exit the app |

### To access the Customer menu, log in with a valid account created in the app.
### The customer menu has the following options:
| Option No. | Option Name | Description |
| ---------- | ----------- | ----------- |
| 1  |  Create new account | Create a new individual or joint account |
| 2  | View accounts | View all accounts owned by you |
| 3  | Withdraw from account | Withdraw money from an account you own |
| 4  | Deposit to account | Deposit money into an account you own |
| 5  | Delete account | Delete an account you own if it is empty |
| 6  | Logout | Log out and return to the Main Menu |

### The admin menu has the following options:
| Option No. | Option Name | Description |
| ---------- | ----------- | ----------- |
| 1  | View all users | View all users in the database and their information |
| 2  | View all accounts | View all bank accounts in the database and their balances |
| 3  | Create new user | Register a new user account |
| 4  | Update user | Update the username, password, legal name, or age of a user |
| 5  | Delete user | Delete a user, which will also delete all acounts they solely or partially own even if they have money in them |
| 6  | Withdraw from account | Withdraw money from any account |
| 7  | Deposit to account | Deposit money into any account |
| 8  | Delete account | Delete a bank account if it is empty |
| 9  | Logout | Log out and return to the Main Menu |
# Extra notes:
If your internet is not active when trying to perform an action, it should fail and treat the situation as if no results were found, eg. if you entered an incorrect value. There should be an explicit print statement saying you could not connect to the database, so ignore any text following any such message.
Logging is implemented log to file as of Monday. I may implement logging to database if I can get JDBCAppender to work on Tuesday.