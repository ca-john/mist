# Features

1. Information about the program
    
    There are for types of user. Administer, Seller, Buyer, and Full-standard user that could both buy and sell a game.
    For all the users, there are some general function that work for everyone. Base on their property, they will have different ability.

    The store supports buying, selling, adding credit, refunding and creating user accounts.
    Users can sell games to other users and make money.
    GUI to handle user input and display information in a pleasing manner.
    Transactions can be executed from the GUI.

    The store supports Administrator user accounts that can initiate deletion of an account and create more account.
    Different account types offer different actions, which is reflected by different elements of the GUI that appear depending on the user-type.
    All the transactions are logged and viewable in the specified format.

    We use Backendservice class to handle the transactions.
    Provide account information and related operations (add, delete, search) for the front end
    Provide game information and related operations for the front end (search)
    Associated with the user's game function
    Read / write user, game, user game to file function

    The transaction class is the parent class of all xxtransactions. 
    It provides the function of a factory method, and the abstract method Dotranslation (the operation of each transaction class).

2. How to use
   
   Run main.java and login in, for testing, use admin user id: DVE, password:1234.
   Userinfo.txt provide user id and password, all the id can be used for test.
   Example:
   Aimee,SS,802
   Alice,FS,134
   Karen,BS,535
   
   For enter the Game name, follow the Gameinfo.txt
   Example:
   HeroesI,94
   HeroesII,74
   HeroesIII,28
   StarCraftI,28
   StarCraftII,16

3. Function specification
   1. Log in
      Input the user id and password to log in.
      If the id or password is wrong, it will ask for a retry.
      
   2. Log out
      Press "LOGOUT" at the bottom of the window, will let user log out and end the process.
      
   3. User information display
      After log in, the user info will show on the window, include account balance and game library.
      The lower part is the operation area, which varies according to the type of user.
      
   4. Basic function - work for all the users
      a) Remove game
            User could remove a game from it own game library.
            Press "Remove" and enter the game name.
      b) Send a gift 
            User could send a gift to another user. 
            The game will add to recipient's inventory, and remove from the donor's inventory.
            Press "GIFT", enter the gift Game and the recipient's name.
      
   5. Special function for admin user
      a) Create or remove an account
            Admin user could create or delete an account
            For creating an account, Press"CREATE", set Username, user type, password, and credits.
                *All the information can be changed in userinfo.txt.
            For delete an account, press "DELETE", enter the Username to delete.
      b) Manage credits in user account.
            Admin user could manage credits in user account, by press "ADD".
            To add credits, input positive value.
            To remove credits, input negative value.
      c) Manage games in user's inventory.
            Admin user could remove a game from users' inventory.
            Press "REMOVE", enter the User name and the game name.
      d) Activate discounts on all games for sale.
            Administer could activate discount on all the games at one time.
            Press "SALE", input the game name and discount rate.
                *if enter the seller name, this discount will activate on the specific game that the seller put to sell.
                *if do not enter the seller, this discount will actibite on all the game it choose.
      
   6. Special function for Seller 
      Seller could sell a game that in his inventory and set discount.
      Press "SELL", set Game name, Seller name, and discount rate.
      
   7. Special function for Buyer
      Buyer could buy a game from other users. 
      Press "BUY", enter Game name, Seller name, and Buyer name.
      
   8. Error message
      If the user's action is not allowed, user will be refused and receive a notice about the reason.
      For example, user are not allowed to hold two identical games. 
      If one is trying to send a user a game that already in his inventory, will receive the notice "XXX already in the inventory".
      
   9. Transaction information
      All the transactions will have a log show on the program. 
      If there's any error, the notice will send to the user, and the message will show on the program.
    
