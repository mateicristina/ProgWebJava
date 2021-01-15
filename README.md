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

## Dto Classes
### LoginDto
```$xslt
    @NotNull
    private String email;
    @NotNull
    private String password;
```
### MatchInvitationDto
```$xslt
    private int id;
    @NotNull
    private int senderUserId;
    @NotNull
    private int receiverUserId;
    private TennisCourtReservation reservation;
```
### NewUserDto
```$xslt
    private int id;
    @NotNull
    private String name;
    @NotNull
    private Integer age;
    @NotNull
    private String city;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @NotNull
    private String password;
```
### PracticeDto
```$xslt
    private int id;
    @NotNull
    private int userId;
    private TennisCourtReservation reservation;
```
### TennisBaseDto
```$xslt
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String address;
```
### TennisCourtDto
```$xslt
    private int id;
    @NotNull
    private int number;
    @NotNull
    private int baseId;
```
### TennisCourtListReservationsDto
```$xslt
    @NotNull
    private int courtNumber;
    private List<TennisCourtReservation> reservations;
```
### UserDto
```$xslt
    private int id;
    @NotNull
    private String name;
    @NotNull
    private Integer age;
    @NotNull
    private String city;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
```

## Endpoints
### POST */register-user*
**Description:**  Register a new user

**Request body:** NewUserDto

**Response:** UserDto

### POST */login*
**Description:** Log into the platform

**Request body:** LoginDto

**Response:** UserDto

### PUT */edit/profile*
**Description:** Let's a user edit their profile details

**Request body:** UserDto

**Response:** UserDto

### GET */practice/get-practices?userId={id}*
**Description:** Get a list with all the tennis practices and tennis court reservations a user has

**Response:** List of PracticeDto

### POST */practice/add-practices*
**Description:** Create a new tennis practice and reserve a tennis court

**Request body:** PracticeDto

**Response:** PracticeDto

### GET */tennis-base/get-all*
**Description:** Get a list with details about all the tennis bases registered in the app

**Response:** List of TennisBaseDto

### GET */tennis-base/get-court-reservations/{tennisBaseId}*
**Description:** For a certain tennis base, get all the reservations in all the courts. It can be used to create a calendar to view the time intervals where a court is free

**Response:** List of TennisCourtListReservationsDto

### POST */tennis-base/create*
**Description:** Register a new tennis base into the app

**Request body:** TennisBaseDto

**Response:** TennisBaseDto

### POST */tennis-base/add-court/{baseId}/{courtNumber}*
**Description:** Register a new tennis court for a tennis base

**Response:** TennisCourtDto

### POST */tennis-match/create-invitation*
**Description:** Create a match invitation and send it to another player. This will also reserve the tennis court

**Request body:** MatchInvitationDto

**Response:** MatchInvitationDto

### GET */tennis-match/get-invitations/{userId}*
**Description:** Get all match invitations you received through the app

**Response:** List of MatchInvitationDto

### POST */tennis-match/accept-invitation/{invitationId}*
**Description:** Accept a match invitation

**Response:** String "Message sent"

### POST */tennis-match/refuse-invitation/{invitationId}*
**Description:** Refuse a match invitation. This will also change the tennis court from reserved to free

**Response:** String "Message sent"

## Testing
To test the endpoints you can use Postman. The collection is saved here: https://www.getpostman.com/collections/dfdaf7a19114d011cd85