var express = require('express');
var app = express();
var MongoClient = require('mongodb').MongoClient;

app.use(express.static('public'));

app.get('/competencies/:ra', function (req, res) {
	var ra = parseInt(req.params.ra);
	
	MongoClient.connect('mongodb://localhost:27017/skills', function(err, db) {
	  if (err) {
		throw err;
	  }
		db.collection('students').find({'ra':ra}).toArray(function(err, result) {
			if (err) {
				throw err;
			}
			
			if(result.length>0){
				res.send([{'competencies': result[0].competencies, 'name': result[0].name}]);
			} else {
				res.send([{'status': 'error'}]);
			}
			
		});
	});
  
  
});

app.get('/login/:username/:password', function (req, res) {
	var username = req.params.username;
	var password = req.params.password;
	
	MongoClient.connect('mongodb://localhost:27017/skills', function(err, db) {
	  if (err) {
		throw err;
	  }
		db.collection('students').find({'username':username, 'password':password}).toArray(function(err, result) {
			if (err) {
				//throw err;
				res.send([{'ra': '0'}]);
			}
			
			if(result){
				res.send([{'ra': result[0].ra, 'question': result[0].question, 'completed': result[0].completed}]);
			}
			
			
		});
	});
  
  
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});

//O BD eh Skills - Uma collection eh students (terei as outras como questions e etc ver diagrama de classes) 
