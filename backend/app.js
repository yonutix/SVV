var express = require('express');
var app = express();
var db = require('./db')
var bodyParser = require('body-parser')

app.use(bodyParser.json() );       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({    // to support URL-encoded bodies
  extended: true
})); 


// Add headers
app.use(function (req, res, next) {

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', '*');
    //res.setHeader('Access-Control-Allow-Origin', 'http://192.168.0.175:8080');
	//res.setHeader('Access-Control-Allow-Origin', 'http://192.168.0.196:8080');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

    // Pass to next layer of middleware
    next();
});

mongo_addr = "192.168.0.173"

// Connect to Mongo on start
db.connect('mongodb://' + mongo_addr + ':27017/bookingapp', function(err) {
  if (err) {
    console.log('Unable to connect to Mongo.')
    process.exit(1)
  } else {
    console.log('Db connection ready')
  }
})

app.get('/', function (req, res) {
  res.send('Hello World!');
});

app.post('/restaurants', function (req, res) {
	res.setHeader('Content-Type', 'application/json');
	var collection = db.get().collection('restaurants')
	var fields = {'fields' : {'name' : 1, 'cuisine' : 1, 'free_spots' : 1}}

	request = req.body
	query_object = {}
	if ('name' in request) {
		query_object["name"] = request['name']
	}
	if ('cuisine' in request) {
		query_object["cuisine"] = request['cuisine']
	}
	if ('numSpots' in request) {
		query_object["free_spots"] = {'$gte' : request['numSpots']}
	}
	console.log(request)

	collection.find(query_object, fields).limit(20).toArray(function(err, docs) {
		res.send(docs);
		console.log(docs)
	})

});

app.post('/book', function (req, res) {
	res.setHeader('Content-Type', 'application/json');
	var collection = db.get().collection('restaurants')
	var fields = {'fields' : {'name' : 1, 'cuisine' : 1, 'free_spots' : 1}}

	request = req.body
	query_object = {}
	if ('name' in request) {
		query_object["name"] = request['name']
	}
	if ('numSpots' in request) {
		query_object["free_spots"] = {'$gte' : request['numSpots']}
	}

})

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
})

