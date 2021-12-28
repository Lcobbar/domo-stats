# domo-stats
API REST para domótica

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
