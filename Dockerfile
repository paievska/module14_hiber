FROM postgres
ENV POSTGRES_USER dipvsk
ENV POSTGRES_PASSWORD diana13
EXPOSE 5432:5432
ENV POSTGRES_DB myDb2
#docker build -t pg/dipvsk:v1 .
#docker run --name pg_dipvsk -d -p 15432:5432 pg/dipvsk:v1