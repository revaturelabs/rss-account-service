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

do things

### AccountController

#### Endpoints
VERB | URL | USE
--- | --- | ---
PUT | /account/points | save new point total to db
POST | /account/points/a | add to account's points
POST | /account/new | saves the new account
POST | /account/account | find account by id
POST | /account/accounts | find accounts by user id

#### Methods

do things

### AccountTypeController

#### Endpoints

VERB | URL | USE
--- | --- | ---
POST | /acctype/type | admins can add account types to the database
GET | /acctype/all | returns all account types
PUT | /acctype/type/u | updates a current account type


#### Methods

do things
