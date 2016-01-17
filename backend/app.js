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

mongo_addr = "localhost"

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

app.get('/restlist', function(req, res) {
	res.setHeader('Content-Type', 'application/json');
	var collection = db.get().collection('restaurantsf')
	var fields = {"name" : 1, "_id" : 0}
	collection.find({}, fields).toArray(function(err, docs) {
		response = {'restlist' : []}
		for (doc_id in docs) {
			response['restlist'].push(docs[doc_id].name)
		}
		res.send(response)
	})
});

app.post('/restaurants', function (req, res) {
	res.setHeader('Content-Type', 'application/json');
	var collection = db.get().collection('restaurantsf')
	var fields = {'fields' : {'name' : 1, 'cuisine' : 1, 'free_spots' : 1, 'address' : 1,
							  'telefon' : 1, 'price' : 1, 'website' : 1}}

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

app.post('/login', function (req, res) {
	res.setHeader('Content-Type', 'application/json');
	var collection = db.get().collection('users')
	var fields = {'_id' : 0, "password" : 0}
	

	request = req.body
	query_object = {'email' : 'e', 'password' : 'p'}
	if ('email' in request) {
		query_object['email'] = request['email']
	}
	if ('password' in request) {
		query_object['password'] = request['password']
	}

	collection.findOne(query_object, fields, function(err, doc) {
		if (err || doc == null) {
			console.log("LOGIN failed")
			res.send({'response' : 'fail'})
		} else {
			doc['response'] = 'success'
			console.log("LOGIN: " + doc)
			res.send(doc)
		}
	})
});

app.post('/newuser', function(req, res) {
	res.setHeader('Content-Type', 'application/json');
	var collection = db.get().collection('users')
	request = req.body
	query_object = {}

	if (!('name' in request) || !('type' in request) || !('password' in request) ||
		!('phone' in request) || !('email' in request)) { 
		res.send({"response" : "fail"})
	} else {
		query_object = {'email' : request['email']}
		collection.findOne(query_object, function(err, doc) {
			if (!err && doc == null) {
				request['id_restaurant'] = ''
				collection.insertOne(request, function(err, r) {
					if (!err && r.insertedCount == 1) {
						res.send({"response" : "success"})
					} else {
						res.send({"response" : "fail"})
					}
				})
			} else {
				res.send({"response" : "fail"})
			}
		})
	}
});

app.post('/book', function (req, res) {
	res.setHeader('Content-Type', 'application/json');
	var collection = db.get().collection('restaurantsf')

	request = req.query
	query_object = {}
	if ('name' in request) {
		query_object["name"] = request['name']
	}
	if ('numSpots' in request) {
		spots = request['numSpots']
	}
	//console.log('BOOK LOG: ' + JSON.stringify(query_object))
	collection.findOne(query_object, function(err, doc) {
		if (err || doc == null) {
			res.send({'response' : 'fail'})
			console.log("BOOK LOG: error find")
		} else {
			freeSpots = doc['free_spots']
			collection.update(query_object, { "$set" : {"free_spots" : freeSpots - spots}})
			res.send({'response' : 'success'})
		}
	})
})

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
})

