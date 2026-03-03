# PulsePath Backend (Spring Boot)

## Requirements
- Java 17+
- Maven
- Docker

## Run Fully in Docker (recommended for frontend developers)
```powershell
docker compose up --build -d
```

Backend will be available at `http://localhost:8080`.

Useful commands:
```powershell
docker compose logs -f backend
docker compose down
```

## Run Locally (optional)
Start only Postgres in Docker:
```powershell
docker compose up -d db
```

Then run backend with Maven:
```powershell
mvn spring-boot:run
```

## API Endpoints
- `GET /api/health`
- `GET /api/days/{yyyy-MM-dd}`
- `PUT /api/days/{yyyy-MM-dd}/checklist`
- `GET /api/days/{yyyy-MM-dd}/sleep`
- `POST /api/days/{yyyy-MM-dd}/sleep`

## Example Requests
```bash
curl http://localhost:8080/api/health
```

```bash
curl -X PUT http://localhost:8080/api/days/2026-02-23/checklist \
  -H "Content-Type: application/json" \
  -d '{"breakfastDone":true,"lunchDone":false,"dinnerDone":true,"skipsDone":true,"pushupsDone":false}'
```

```bash
curl -X POST http://localhost:8080/api/days/2026-02-23/sleep \
  -H "Content-Type: application/json" \
  -d '{"hours":7.5,"quality":"Rested"}'
```
