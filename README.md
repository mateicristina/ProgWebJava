# Documentation TennisMatch

## Introduction
TennisMatch is a backend application, written in Java using Spring Boot. The scope of this application is to help tennis 
players to handle their everyday activities. Using this app, they can create practices, reserve courts and even invite
other players to play a tennis match.

## Functionalities
#### 1. Authentication + Profile
- Login
- Create users
- Edit users profile

#### 2. Tennis bases
- Create tennis base
- Get all tennis bases
- Add tennis courts
- Get a list with all the court reservations at a base

#### 3. Manage tennis practices
- Add a tennis practice with a reserved court
- Get all practices for a user

#### 4. Send tennis match invitations
- Create a invitation for a tennis match
- Get all the invitations you received
- Accept a match invitation
- Refuse a match invitation

## Database
#### Table "practices"
- *id* - Primary Key, Unique
- *senderUserId* - Foreign Key (references table "users" - id), Not null 
- *receiverUserId* - Foreign Key (references table "users" - id), Not null 
- *reservationId* - Foreign Key (references table "tennis_court_reservations" - id), Not null 

#### Table "tennis_bases"
- *id* - Primary Key, Unique
- *name* - Varchar(20), Not null, represents a tennis bases name
- *address* - Varchar(20), Not null, represents a tennis bases address
#### Table "tennis_courts"
- *id* - Primary Key, Unique
- *number* - int, tennis courts inside a tennis base have a number to identify them
- *baseId* - Foreign Key (references table "tennis_bases" - id), Not null 
#### Table "tennis_court_reservations"
- *id* - Primary Key, Unique
- *userId1* - Foreign Key (references table "users" - id), Not null 
- *userId2* - Foreign Key (references table "users" - id), Not null 
- *courtId* - Foreign Key (references table "tennis_courts" - id), Not null 
- *date* - Date type
- *startHour* - Time type
- *endHour* - Time type
#### Table "tennis_matches"
- *id* - Primary Key, Unique
- *userId1* - Foreign Key (references table "users" - id), Not null 
- *userId2* - Foreign Key (references table "users" - id), Not null 
- *courtReservationId* - Foreign Key (references table "tennis_court_reservations" - id), Not null 
#### Table "tennis_match_pending_invitations"
- *id* - Primary Key, Unique
- *senderUserId* - Foreign Key (references table "users" - id), Not null 
- *receiverUserId* - Foreign Key (references table "users" - id), Not null 
- *reservationId* - Foreign Key (references table "tennis_court_reservations" - id), Not null 
#### Table "users"
- *id* - Primary Key, Unique
- *name* - String type, Not null
- *age* - Int type
- *city* - String type
- *phoneNumber* - String type
- *email* - String type, not null
- *password* - String type, not null

## Endpoints
#### */register-user*

#### */login*
#### */edit/profile*
#### */practice/get-practices?userId={id}*
#### */practice/add-practices*
#### */tennis-base/get-all*
#### */tennis-base/get-court-reservations/{courtId}*
#### */tennis-base/create*
#### */tennis-base/add-court/{baseId}/{courtNumber}*
#### */tennis-match/create-invitation*
#### */tennis-match/get-invitations/{userId}*
#### */tennis-match/accept-invitation/{invitationId}*
#### */tennis-match/refuse-invitation/{invitationId}*