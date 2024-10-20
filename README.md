### User Management System



This is a simple user management system that should allow users to register, login, and logout. The system is built using Html/Css, some js, and java with Spring boot and maven.

The system currently uses an in memory database but configured as separate server to allow h2-console and Intellij client to connect at the sametime.

### Authentication

The system uses JWT for authentication. The JWT token is generated when a user logs in and is stored in the local storage.
