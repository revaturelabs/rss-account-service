# Revature Swagg Shop
This repository holds the account microservice which handles:

- user creation
- user information updates
- user authentication
- retrieving user information
- user account creation
- user account updates
- user account retrieval
- account type creation
- account type updates
- account type retrieval

These requests are handled by three controllers: **UserController, AccountController, and AccountTypeController**

Endpoints and methods are mapped out below.

### UserController

#### Endpoints
VERB | URL | USE
--- | --- | ---
GET | /user/all | get all registered users
POST | /user/new | add new user
POST | /user/login | authentication service
POST | /user/user | find user by id
PUT | /user/info | update user information
PUT | /user/cred | update user password only
PUT | /user/pic | update a user's profile picture
PUT | /user/master | update user to admin

#### Methods

```java
public List<Users> getAllUsers()
```

> Retrieves all users in the database.

```java
public User addNewUser(@RequestBody User user)
```

> Adds a new user to the database upon registration

```java
public User loginUser(@RequestBody User user)
```

> Compares login credentials with what is in the database, will return null is invalid.

```java
public User findUserById(@RequestBody User user)
```

> Will pull a user from database by their ID or email.

``` java
public void updateInformation(@RequestBody User user)
public void updatePassword(@RequestBody User user)
public void updateProfilePic(@RequestBody User user)
public void updateIsAdmin(@RequestBody User user)
```

> Will take in new user info and update the user in the database

### AccountController

#### Endpoints
VERB | URL | USE
--- | --- | ---
PUT | /account/points | save new point total to db
PUT | /account/points/a | add to account's points
POST | /account/new | saves the new account
POST | /account/account | find account by id
POST | /account/accounts | find accounts by user id

#### Methods

```java
public void updatePoints(@RequestBody Account acc)
```

> Takes in the new account point total and saves it to database

``` java
public void addPoints(@RequestBody Account acc)
```

> Will take the new points and add them to the account point total

``` java
public Account addAccount(@RequestBody Account acc)
```

> Takes in the new account and adds it to the database

``` java
public Account getAccountById(@RequestBody Account acc)
```

> Will return the account, from the database, by the id

``` java
public List<Account> findAccountByUserId(@RequestBody User acc)
```

> Will return accounts related to the user id and return a list

### AccountTypeController

#### Endpoints

VERB | URL | USE
--- | --- | ---
POST | /acctype/type | admins can add account types to the database
GET | /acctype/all | returns all account types
PUT | /acctype/type/u | updates a current account type


#### Methods

``` java
public AccountType addAccountType(@RequestBody AccountType accType)
```

> As an admin can add account types to the database

``` java
public List<AccountType> getAllAccountType()
```

> Will pull all of the account types and return a list

``` java
public void updateAccountType(@RequestBody AccountType accType)
```

> Will update a current account type(for spelling errors, ect)
