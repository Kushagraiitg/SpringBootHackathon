CREATE database hackathon;

USE hackathon;

CREATE TABLE IF NOT EXISTS Stocks(
       id int auto_increment,
       stockTicker varchar(40),
       price decimal(10,2),
       volume int,
       buyOrSell varchar(30),
       statusCode int,
       orderDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
       totalPrice decimal(10,2),
       PRIMARY KEY (id)
);