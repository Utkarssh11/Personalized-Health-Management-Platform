# Personalized-Health-Management-Platform


A comprehensive health management platform that allows patients, doctors, and caregivers to interact, manage health data, and receive notifications regarding medication, lifestyle changes, and health improvements.

## Features

- User Authentication with JWT
- Role-based access control (Patient, Doctor, Caregiver)
- Health record management
- Medication scheduling and reminders
- Doctor-patient relationship management
- Real-time health monitoring
- Secure data storage and transmission

## Technology Stack

### Backend
- Java
- Spring Boot 
- Spring Security with JWT
- Spring Data JPA
- PostgreSQL
- Maven

### Frontend (To be implemented)
- React.js


## Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd health-management-platform
```

2. Create a PostgreSQL database:
```sql
CREATE DATABASE health_platform;
```

3. Update application.properties with database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/health_platform
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
```

4. Build and run the backend:
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Authentication
- POST `/api/auth/register` - Register a new user
- POST `/api/auth/login` - Login and get JWT token

### Health Records
- GET `/api/health-records` - Get paginated health records
- POST `/api/health-records` - Create a new health record
- GET `/api/health-records/{id}` - Get a specific health record
- PUT `/api/health-records/{id}` - Update a health record
- DELETE `/api/health-records/{id}` - Delete a health record
- GET `/api/health-records/date-range` - Get health records within a date range
- GET `/api/health-records/latest` - Get latest health records


## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 
