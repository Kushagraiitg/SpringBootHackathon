
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

INSERT INTO Stocks (stockTicker, price, volume, buyOrSell, statusCode, totalPrice) VALUES ('AAPL', 1500.99, 10, 'BUY', 0, 15009.90);