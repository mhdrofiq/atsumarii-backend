# Atsumarii API - Postman Testing Guide

## Prerequisites
1. MySQL server running on localhost:3307 with username: root, password: test
2. Maven installed
3. Java 21 installed

## Starting the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Sample API Endpoints for Postman Testing

### 1. User Management

#### Create User
```
POST http://localhost:8080/api/users
Content-Type: application/json

{
  "username": "john_doe",
  "fullname": "John Doe",
  "email": "john@example.com",
  "dateofbirth": "1990-01-15 00:00:00",
  "bio": "Software developer and tech enthusiast",
  "profileimgurl": "https://example.com/profiles/john.jpg"
}
```

#### Get All Users
```
GET http://localhost:8080/api/users
```

#### Get User by ID
```
GET http://localhost:8080/api/users/1
```

#### Update User
```
PUT http://localhost:8080/api/users/1
Content-Type: application/json

{
  "username": "john_doe_updated",
  "fullname": "John Doe Updated",
  "email": "john.updated@example.com",
  "bio": "Updated bio"
}
```

#### Delete User
```
DELETE http://localhost:8080/api/users/1
```

### 2. Group Management

#### Create Group
```
POST http://localhost:8080/api/groups?creatorId=1
Content-Type: application/json

{
  "groupname": "Tech Meetup Group",
  "description": "A group for local tech enthusiasts to meet and share knowledge",
  "groupimgurl": "https://example.com/groups/tech-meetup.jpg"
}
```

#### Get All Groups
```
GET http://localhost:8080/api/groups
```

#### Get Group by ID
```
GET http://localhost:8080/api/groups/1
```

#### Update Group
```
PUT http://localhost:8080/api/groups/1
Content-Type: application/json

{
  "groupname": "Updated Tech Meetup Group",
  "description": "Updated description for the tech meetup group"
}
```

#### Delete Group
```
DELETE http://localhost:8080/api/groups/1
```

### 3. Group Membership

#### Create Membership (Add User to Group)
```
POST http://localhost:8080/api/memberships
Content-Type: application/json

{
  "userid": 1,
  "groupid": 1
}
```

#### Get All Memberships
```
GET http://localhost:8080/api/memberships
```

#### Get Membership by ID
```
GET http://localhost:8080/api/memberships/1
```

#### Update Membership
```
PUT http://localhost:8080/api/memberships/1
Content-Type: application/json

{
  "userid": 1,
  "groupid": 1
}
```

#### Delete Membership (Remove User from Group)
```
DELETE http://localhost:8080/api/memberships/1
```

### 4. Event Management

#### Create Event
```
POST http://localhost:8080/api/events?groupId=1
Content-Type: application/json

{
  "eventname": "Spring Boot Workshop",
  "tags": "spring,boot,workshop,java",
  "description": "Learn Spring Boot fundamentals and best practices",
  "eventimgurl": "https://example.com/events/spring-workshop.jpg",
  "location": "Tech Hub Conference Room A",
  "start": "2025-12-15 14:00:00",
  "end": "2025-12-15 17:00:00"
}
```

#### Get All Events
```
GET http://localhost:8080/api/events
```

#### Get Event by ID
```
GET http://localhost:8080/api/events/1
```

#### Update Event
```
PUT http://localhost:8080/api/events/1
Content-Type: application/json

{
  "eventname": "Updated Spring Boot Workshop",
  "description": "Updated description for the workshop",
  "location": "Updated location"
}
```

#### Delete Event
```
DELETE http://localhost:8080/api/events/1
```

### 5. Event Attendance

#### Register for Event
```
POST http://localhost:8080/api/attendances
Content-Type: application/json

{
  "userid": 1,
  "eventid": 1,
  "attendanceStatus": "CONFIRMED"
}
```

#### Get All Attendances
```
GET http://localhost:8080/api/attendances
```

#### Get Attendance by ID
```
GET http://localhost:8080/api/attendances/1
```

#### Update Attendance Status
```
PUT http://localhost:8080/api/attendances/1
Content-Type: application/json

{
  "attendanceStatus": "DECLINED"
}
```

#### Cancel Registration (Delete Attendance)
```
DELETE http://localhost:8080/api/attendances/1
```

## Sample Data
The application automatically populates the database with sample data on startup including:
- 5 sample users (alice_smith, bob_jones, carol_wilson, david_brown, eva_garcia)
- 5 sample groups (Tech Enthusiasts, Photography Club, Study Group CS, Project Management, Data Science Network)
- 7 sample events (AI Workshop, Photo Walk, Algorithm Study Session, etc.)
- 12 group memberships
- 22 event attendances

## Testing Flow
1. Start the application
2. Check that sample data is loaded: `GET /api/users`
3. Create a new user: `POST /api/users`
4. Create a new group with the user as creator: `POST /api/groups?creatorId={newUserId}`
5. Add the user to the group: `POST /api/memberships`
6. Create an event in the group: `POST /api/events?groupId={newGroupId}`
7. Register the user for the event: `POST /api/attendances`
8. Test update and delete operations on all entities

## Available Status Values
- **Event Attendance**: CONFIRMED, PENDING, DECLINED

## Important Notes
- All IDs are auto-generated (Integer values starting from 6 for new records)
- All date/time fields use format: "yyyy-MM-dd HH:mm:ss"
- Email addresses must be unique for users
- Group names must be unique
- Users cannot register for the same event twice
- Users cannot join the same group twice

## Database
- The application uses MySQL database
- Tables are auto-created on startup with proper auto-increment
- Sample data is inserted automatically
- Database: `atsumarii_db` on localhost:3307

## Error Handling
All endpoints return appropriate HTTP status codes:
- 200 OK: Successful GET requests
- 201 Created: Successful POST requests
- 204 No Content: Successful DELETE requests
- 400 Bad Request: Validation errors (duplicate email, invalid data, etc.)
- 404 Not Found: Resource not found
- 500 Internal Server Error: Unexpected errors