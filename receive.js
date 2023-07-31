
var mongoose = require('mongoose');

var assert = require('assert');
var MTransaction = require('./server/mongomodels/MTransaction');


mongoose.connect('mongodb://rghosh-cosmos:k597qbDb4h77cxTfZ3yH0z686TqZC6gsI8XFEup9MQVrniPcVCicm7QkXbn8uvddzL5Z4hCQto3uuwdk9YCQeA==@rghosh-cosmos.documents.azure.com:10255/?ssl=true'

 , function(err) {
    if (err) { console.log(err) };
      console.log('Connected to Cosmos-DB');
});


var insertDocument = function(json, callback) {
       var mtransaction =  new MTransaction(json);
       console.log('refdate='  + json.refdate);
        mtransaction.save(function(err,data) {
          if (err) console.log(err);
          console.log('Object saved');
        });
        callback();
    };

const { EventHubClient, EventPosition } = require('azure-event-hubs');


const client = EventHubClient.createFromConnectionString("Endpoint=sb://rsg-eventhub.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=+U6nGaEcvp/5saUN+s3UdyXfhAIw6ro1Jf0fGqCg7zc=","rsg-event-hub-name");


async function main() {
  const onError = (err) => {
    console.log("An error occurred on the receiver ", err);
  };
  
  const onMessage = (eventData) => {
    console.log(eventData.body);
    const enqueuedTime = eventData.annotations["x-opt-enqueued-time"];
    console.log("Enqueued Time: ", enqueuedTime);
    console.log("Writing to Cosmos-DB now..");
    insertDocument(eventData.body, function() {

    });
    console.log('....Done.');
  };

  const receiveHandler = client.receive("1", onMessage, onError, { eventPosition: EventPosition.fromEnqueuedTime(Date.now()) });

  // To stop receiving events later on...
  await receiveHandler.stop();
}

main().catch((err) => {
  console.log(err);
});