# Spring Boot with Docker PostgreSQL Setup Guide

## 🚀 Quick Start

```
# Start Docker service
sudo systemctl start docker

# Build and start containers
docker-compose up -d

# Run the application
mvn spring-boot:run

```
**🔄 Daily Development Workflow**

Start Docker (if not running):
```
sudo systemctl start docker
```
Clean previous containers:
```
docker-compose down
```
Check for port conflicts (PostgreSQL default: 5432):

```
sudo lsof -i :5432
```
Start services:

```
docker-compose up -d
```
Verify PostgreSQL:

```
docker-compose exec db psql -U postgres -c "\l"
```

**🛠 Configuration Files**

**docker-compose.yml**
```
yaml
version: '3.8'
services:
  db:
    image: postgres:latest
    ports: ["5432:5432"]
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: changemeinprod!
      POSTGRES_DB: blogdb
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres"]
      interval: 5s
      timeout: 5s
      retries: 5

  adminer:
    image: adminer:latest
    ports: ["8888:8080"]
    depends_on:
      db:
        condition: service_healthy
```

**application.properties**
```
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
spring.datasource.username=postgres
spring.datasource.password=changemeinprod!
spring.jpa.hibernate.ddl-auto=update
```

**⚠️ Troubleshooting**

**Issue	Solution**

Port 5432 in use	
```sudo systemctl stop postgresql ``` or change port in docker-compose.yml

Authentication failures	
Verify credentials match in both config files

Database not initializing `docker-compose down -v `then `docker-compose up -d`

Connection refused	
Check if PostgreSQL is running: `docker-compose ps`

🧹 Cleanup Commands
```
# Stop and remove all containers
docker-compose down -v

# Remove unused Docker objects
docker system prune -a --volumes

# List all containers
docker ps -a
```
🆘 When All Else Fails

Full reset:

```
docker system prune -a --volumes
Reclone the project
```
Fresh start:

```
docker-compose up -d
```

Note: Always verify your Docker containers are running before starting the application!
