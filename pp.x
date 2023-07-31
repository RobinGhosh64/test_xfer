 findProductsByCategory : function (request, response) {
             
    var decoded = jwt.decode(request.headers.authorization, "superSecret");
        
    console.log('session_id=' + decoded.session_id + 'id=' + decoded.id + 'username=' + decoded.username + ',bottler_id=' + decoded.bottler_id);
    var bottler_id = decoded.bottler_id;
    if (bottler_id == '65') {
        var json =
        [
            {
              "id": 12,
              "epid": 12,
              "product_id": 12,
              "bottler_id" : 65,
              "customer_id" : 1,
              "store_id" :  12,
              "name": "Fanta",
              "retail_price": 14,
              "sale_price": 14,
              "skuid": "24-WG085",
              "active": 1,
              "availability": "",
              "description": "",
              "created_at": "2018-07-26 08:23:01",
              "promotion": 1,
              "size": "Standard",
              "configuration": "16 GB",
              "color": "",
              "picture": "",
              "contract": "24 months",
              "store": "T-Mobile",
              "small_image": "http://35.237.53.25/ecomm_magento/pub/media/softdrinks/fanta.jpg"
            },
            {
              "id": 34,
              "epid": 34,
              "product_id": "34",
              "name": "Mirinda",
              "retail_price": 17,
              "sale_price": 17,
              "skuid": "24-WG086",
              "active": 1,
              "availability": "",
              "description": "",
              "created_at": "2018-07-26 08:23:02",
              "promotion": 1,
              "size": "Standard",
              "configuration": "16 GB",
              "color": "",
              "picture": "",
              "contract": "24 months",
              "store": "T-Mobile",
              "small_image": "http://35.237.53.25/ecomm_magento/pub/media/softdrinks/mirinda.jpg"
            }
          ];
          response(json).code(200);
    }
    else {
        var json =
        [
            {
              "id": 31,
              "epid": 31,
              "name": "Sprite",
              "retail_price": 32,
              "sale_price": 32,
              "skuid": "24-WG083-pink",
              "active": 1,
              "availability": "",
              "description": "",
              "created_at": "2018-07-26 08:23:00",
              "promotion": 1,
              "size": "Standard",
              "configuration": "16 GB",
              "color": "",
              "picture": "",
              "contract": "24 months",
              "store": "T-Mobile",
              "small_image": "http://35.237.53.25/ecomm_magento/pub/media/softdrinks/sprite.png"
            },
            {
              "id": 32,
              "epid": 32,
              "name": "Coca Cola",
              "retail_price": 32,
              "sale_price": 32,
              "skuid": "24-WG083-blue",
              "active": 1,
              "availability": "",
              "description": "",
              "created_at": "2018-07-26 08:23:01",
              "promotion": 1,
              "size": "Standard",
              "configuration": "16 GB",
              "color": "",
              "picture": "",
              "contract": "24 months",
              "store": "T-Mobile",
              "small_image": "http://35.237.53.25/ecomm_magento/pub/media/softdrinks/cocacola.jpeg"
            },
            {
              "id": 12,
              "epid": 12,
              "name": "Fanta",
              "retail_price": 14,
              "sale_price": 14,
              "skuid": "24-WG085",
              "active": 1,
              "availability": "",
              "description": "",
              "created_at": "2018-07-26 08:23:01",
              "promotion": 1,
              "size": "Standard",
              "configuration": "16 GB",
              "color": "",
              "picture": "",
              "contract": "24 months",
              "store": "T-Mobile",
              "small_image": "http://35.237.53.25/ecomm_magento/pub/media/softdrinks/fanta.jpg"
            },
            {
              "id": 34,
              "epid": 34,
              "name": "Mirinda",
              "retail_price": 17,
              "sale_price": 17,
              "skuid": "24-WG086",
              "active": 1,
              "availability": "",
              "description": "",
              "created_at": "2018-07-26 08:23:02",
              "promotion": 1,
              "size": "Standard",
              "configuration": "16 GB",
              "color": "",
              "picture": "",
              "contract": "24 months",
              "store": "T-Mobile",
              "small_image": "http://35.237.53.25/ecomm_magento/pub/media/softdrinks/mirinda.jpg"
            },
            {
              "id": 35,
              "epid": 35,
              "name": "Pepsi",
              "retail_price": 17,
              "sale_price": 17,
              "skuid": "25-WG086",
              "active": 1,
              "availability": "",
              "description": "",
              "created_at": "2018-07-26 08:23:02",
              "promotion": 1,
              "size": "Standard",
              "configuration": "16 GB",
              "color": "",
              "picture": "",
              "contract": "24 months",
              "store": "T-Mobile",
              "small_image": "http://35.237.53.25/ecomm_magento/pub/media/softdrinks/pepsi.jpeg"
            },
            {
              "id": 36,
              "epid": 36,
              "name": "Thumps Up",
              "retail_price": 21,
              "sale_price": 21,
              "skuid": "24-WG087",
              "active": 1,
              "availability": "",
              "description": "",
              "created_at": "2018-07-26 08:23:03",
              "promotion": 1,
              "size": "Standard",
              "configuration": "16 GB",
              "color": "",
              "picture": "",
              "contract": "24 months",
              "store": "T-Mobile",
              "small_image": "http://35.237.53.25/ecomm_magento/pub/media/softdrinks/thumpsup.jpg"
            }
          ]
          response(json).code(200);

    }