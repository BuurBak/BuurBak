start:
	docker compose up -d

down:
	docker compose down

logs:
	docker compose logs

start-frontend:
	docker compose -f docker-compose.yml -f docker-compose.frontend.yml up --build