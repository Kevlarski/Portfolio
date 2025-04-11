<?php
//identifying host and database
$data_source_name = 'mysql:host=localhost;dbname=seg4';

//user credentials
$username = 'user';
$password = 'test';

//the PHP Database Object connected to the database
$database = new PDO($data_source_name, $username, $password);
