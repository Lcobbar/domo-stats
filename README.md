# domo-stats
API REST para domótica

Se ha realizado cierto control de errores:  
- si algún elemento del array es nulo o vacío lanzamos una excepción
- con @Valid validamos si el array es nulo para devolver un Bad Request

## CURLs para testear la API
curl --location --request POST 'http://localhost:8080/v2/stats/compute' \
--header 'Accept: application/json' \
--data-raw '{
    "readings": [10, 15, 18, 1, 8]
}'  

**Resultado esperado**: 8

curl --location --request POST 'http://localhost:8080/v2/stats/compute' \
--header 'Accept: application/json' \
--data-raw '{
    "readings": [1,2,3]
}'  

**Resultado esperado**: 1

curl --location --request POST 'http://localhost:8080/v2/stats/compute' \
--header 'Accept: application/json' \
--data-raw '{
    "readings": [1, 1, 1]
}'  

**Resultado esperado**: 0

curl --location --request POST 'http://localhost:8080/v2/stats/compute' \
--header 'Accept: application/json' \
--data-raw '{
    "readings": [3,2,1]
}'  

**Resultado esperado**: 0

curl --location --request POST 'http://localhost:8080/v2/stats/compute' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "readings": [1,2,-3]
}'  

**Resultado esperado**: 1

curl --location --request POST 'http://localhost:8080/v2/stats/compute' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "readings": null
}'

**Resultado esperado**: error 400

curl --location --request POST 'http://localhost:8080/v2/stats/compute' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "readings": [1,"",2]
}'

**Resultado esperado**: error 500

curl --location --request POST 'http://localhost:8080/v2/stats/compute' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "readings": [1,null,2]
}'

**Resultado esperado**: error 500
