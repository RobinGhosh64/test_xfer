# tccc_int_poc
tcc integration project



General Integration Project using REST API

Use cases to demonstrate the following:-

1) JWT Token Management (user check via either AAD or database driven)
2) Add two users (bottlers). User add/User login to get the token is required
3) Using their auth-token, make get product calls and show them their products in isolation
4) Post a simple transaction into the system, first into SQL database, then into JMS (Event Hub/Kafka). Have a consumer on the other side that reads and writes into COSMOS-DB. Provide API calls to view transactions by 1 hour,3 hour, 6 hour, 12 hour and 24 hour buckets

5) Allow application insights and app. monitoring as add on features

6) Read from a FTP source and demonstrate a Serverless action

Program details:

Show an option to use  KAFKA Messaging system or Azure Event Hub (Flip the TransactionDAO.js)


Event hub receiver is via receive.js


Kafka topic receiver is via consumer.js
