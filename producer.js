var kafka = require('kafka-node');
var MTransaction = require('./server/mongomodels/MTransaction');
var HighLevelProducer = kafka.HighLevelProducer;
var KeyedMessage = kafka.KeyedMessage;
var Client = kafka.Client;


var client = new Client('40.121.107.150:2181', 'my-client-id', {   //40.121.107.150
    sessionTimeout: 300,
    spinDelay: 100,
    retries: 2
  });
  
  // For this demo we just log client errors to the console.
  client.on('error', function(error) {
    console.error(error);
  });

  var event = new MTransaction();
  event.id = '12';
  event.store_id = '45';
  event.merchant_id = '01';
  event.trantype ='CREDIT';
  event.refnum = '45';
  event.refdate = '2018-08-22 16:00:00';
  event.amount = 12.00;
  
/*
* resources,training, 40 topics (broker properties, compression, manual 50 consumers 

6 web ui)
*/

const buffer = new Buffer.from(JSON.stringify(event));
var producer = new HighLevelProducer(client);

producer.on('ready', function() {
  // Create message and encode to Avro buffer
  

  // Create a new payload
  var payload = [{
    topic: 'silver-topic-1',       // topic-name
    messages: buffer,
    attributes: 1 /* Use GZip compression for the payload */
  }];

  //Send payload to Kafka and log result/error
  producer.send(payload, function(error, result) {
    console.info('Sent payload to Kafka: ', payload);
    if (error) {
      console.error(error);
    } else {
      var formattedResult = result[0];
      console.log('result: ', result)
    }
  });
});

// For this demo we just log producer errors to the console.
producer.on('error', function(error) {
  console.error(error);
});







