<?php

//the user class to create objects for ease of use
class user {

    private $name, $email, $password, $id, $password_hash;

    public function __construct($name, $email, $password, $password_hash, $id = 0) {
        $this->name = $name;
        $this->email = $email;
        $this->password = $password;
        $this->password_hash = $password_hash;
        $this->id = $id;
    }

    public function getId() {
        return $this->id;
    }

    public function setId($id): void {
        $this->id = $id;
    }

    public function getName() {
        return $this->name;
    }

    public function getEmail() {
        return $this->email;
    }

    public function getPassword() {
        return $this->password;
    }
    
    public function getPasswordHash() {
        return $this->password_hash;
    }

    public function setName($name): void {
        $this->name = $name;
    }

    public function setEmail($email): void {
        $this->email = $email;
    }

    public function setPassword($password): void {
        $this->password = $password;
    }

}

//INSERTs a user into the users table
function insert($user) {
    global $database;

    $query = "INSERT INTO users (name, email_address, password, password_hash) "
            . "VALUES (:name, :email, :password, :password_hash)";
    $statement = $database->prepare($query);
    $statement->bindValue(":name", $user->getName());
    $statement->bindValue(":email", $user->getEmail());
    $statement->bindValue(":password", $user->getPassword());
    $statement->bindValue(":password_hash", $user->getPasswordHash());
    $statement->execute();
    $statement->closeCursor();
}

//returns true for successful validation, false if not 
function login($email, $password) {
    global $database; //PDO

    $query = 'SELECT email_address, password_hash FROM users WHERE :email = email_address';
    $statement = $database->prepare($query);
    $statement->bindValue(":email", $email);
    $statement->execute();
    $user = $statement->fetch();
    $statement->closeCursor();

    if ($user == NULL) {
        return false;
    }

    $password_hash = $user['password_hash'];

    return password_verify($password, $password_hash);
}

//returns the user's stored name based on email
function sessionUserName($email) {
    global $database;

    $query = 'SELECT name FROM users WHERE :email = email_address';
    $statement = $database->prepare($query);
    $statement->bindValue(":email", $email);
    $statement->execute();
    $name = $statement->fetch();
    $statement->closeCursor();
    return $name[0];
}

//returns the user's stored id based on email
function sessionUserID($email) {
    global $database;

    $query = 'SELECT id FROM users WHERE :email = email_address';
    $statement = $database->prepare($query);
    $statement->bindValue(":email", $email);
    $statement->execute();
    $id = $statement->fetch();
    $statement->closeCursor();
    return $id[0];
}
