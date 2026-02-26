# PulsePath Backend (Spring Boot)

## Requirements
- Java 17+
- Maven
- Docker (optional, for Postgres)

## Start Postgres (local)
```powershell
docker compose up -d
```

## Run the API
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
