# Stage 1: Build ứng dụng
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Copy file pom.xml và tải dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy toàn bộ mã nguồn
COPY src ./src

# Build ứng dụng (tạo file JAR)
RUN mvn clean package -DskipTests

# Stage 2: Chạy ứng dụng
FROM eclipse-temurin:21-jdk

# Thiết lập thư mục làm việc
WORKDIR /app

# Copy file JAR từ stage builder
COPY --from=builder /app/target/EmployeeAssessment-BE-0.0.1-SNAPSHOT.jar app.jar

# Copy file .env (dùng cho spring-dotenv)
# COPY .env /app/.env

# Thiết lập biến môi trường (port mặc định của Spring Boot)
ENV PORT=8080

# Mở port
EXPOSE ${PORT}

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]