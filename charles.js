var mongoose = require('mongoose');

var url = 'mongodb://rghosh-cosmos:k597qbDb4h77cxTfZ3yH0z686TqZC6gsI8XFEup9MQVrniPcVCicm7QkXbn8uvddzL5Z4hCQto3uuwdk9YCQeA==@rghosh-cosmos.documents.azure.com:10255/?ssl=true';
var db;
var fs = require("fs");

mongoose.connect('mongodb://rghosh-cosmos:k597qbDb4h77cxTfZ3yH0z686TqZC6gsI8XFEup9MQVrniPcVCicm7QkXbn8uvddzL5Z4hCQto3uuwdk9YCQeA==@rghosh-cosmos.documents.azure.com:10255/?ssl=true'

 , function(err) {
    if (err) { console.log(err) };
      console.log('Connected to Cosmos-DB');
});


Schema = mongoose.Schema;



var KpiSchema = new Schema({
    name: String,
    kpi_details: Object
});

var RecognizedSchema = new Schema({
    scene_uid: String,
    store_area_code : String,
    task_name : String,
    task_code : String,
    items : Object
});


var SceneImageSchema = new Schema({
    scene_uid: String,
    store_area_code : String,
    task_name : String,
    task_code : String,
    scene_images : Object
});

var QuestionnaireSchema = new Schema({
    scene_uid: String,
    store_area_code : String,
    task_name : String,
    task_code : String,
    items : Object
});

var CategorySchema = new Schema({
    scene_uid: String,
    store_area_code : String,
    task_name : String,
    task_code : String,
    items : Object
});



//Add our models

mongoose.model('Kpi', KpiSchema);
var Kpi = mongoose.model('Kpi');

mongoose.model('RecognizedItem', RecognizedSchema);
var RecognizedItem = mongoose.model('RecognizedItem');

mongoose.model('Category', CategorySchema);
var Category = mongoose.model('Category');

mongoose.model('Questionnaire', QuestionnaireSchema);
var Questionnaire = mongoose.model('Questionnaire');

mongoose.model('SceneImage', SceneImageSchema);
var SceneImage = mongoose.model('SceneImage');


/*
* Read the trax data json file
*/

var contents = fs.readFileSync("trax_data" + ".json");
var jsonContent = JSON.parse(contents);


// Work on KPI calculations

var calculations = jsonContent.details.calculations;
for (var item in calculations) {
    var obj = calculations[item];
    console.log('name=' + obj.name);
    console.log('details=' + obj.kpi_details);
    var kpi = new Kpi();
    kpi.name = obj.name;
    kpi.kpi_details = obj.kpi_details;
    kpi.save(function(err,k) {
        console.log('Writing kpi with id=' + k.id);
     });
}


var recognized_items = jsonContent.details.recognized_items;
for (var item in recognized_items) {
    var obj = recognized_items[item];
    console.log('scene_uid=' + obj.scene_uid);
    console.log('items=' + obj.items);
    console.log('store_area_code=' + obj.store_area_code);
    console.log('task_name=' + obj.task_name);
    console.log('task_code=' + obj.task_code);
    var recognizedItem = new RecognizedItem();
    recognizedItem.scene_uid = obj.scene_uid;
    recognizedItem.items = obj.items;
    recognizedItem.store_area_code = obj.store_area_code;
    recognizedItem.task_name = obj.task_name;
    recognizedItem.task_code = obj.task_code;
    recognizedItem.save(function(err,d) {
        console.log('Writing recognized item with id=' + d.id);
     });

}

// Work on processing scene images

console.log('Processing scene images...');
var images = jsonContent.details.images;
for (var item in images) {
    var obj = images[item];
    console.log('scene_uid=' + obj.scene_uid);
    console.log('scene_images=' + obj.scene_images);
    console.log('store_area_code=' + obj.store_area_code);
    console.log('task_name=' + obj.task_name);
    console.log('task_code=' + obj.task_code);
    var sceneImage = new SceneImage();
    sceneImage.scene_uid;
    sceneImage.scene_images = obj.scene_images;
    sceneImage.store_area_code = obj.store_area_code;
    sceneImage.task_name = obj.task_name;
    sceneImage.task_code = obj.task_code;
    sceneImage.save(function(err,d) {
        console.log('Writing scene image with id=' + d.id);
     });

}


// Work on processing questionnaires

console.log('Processing questionnaires...');
var questionnaires = jsonContent.details.questionnaires;
for (var item in questionnaires) {
    var obj = questionnaires[item];
    console.log('id=' + obj.id);
    console.log('linked_entity=' + obj.linked_entity);
    console.log('questionnaire=' + obj.questionnaire);
    var questionnaire = new Questionnaire();
    questionnaire.id = obj.id;
    questionnaire.linked_entity = obj.linked_entity;
    questionnaire.questionnaire = obj.questionnaire;
    questionnaire.save(function(err,d) {
        console.log('Writing questionnaire with id=' + d.id);
     });

}

// Work on processing categories


console.log('Processing categories...');
var categories = jsonContent.details.categories;
for (var item in categories) {
    var obj = categories[item];
    console.log('id=' + obj.id);
    console.log('name=' + obj.name);
    console.log('anamolies=' + obj.anamolies);      //data has null object list
    var category = new Category();
    category.id = obj.id;
    category.name = obj.name;
    category.anamolies = obj.anamolies;
    category.save(function(err,d) {
        console.log('Writing category with id=' + d.id);
     });

}




