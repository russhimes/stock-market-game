INSERT INTO games (name, organizer_id, end_date, end_time)
VALUES ('Game One', 1, '2021-10-01', '11:59:59');

INSERT INTO games (name, organizer_id, end_date, end_time)
VALUES ('Game Two', 1, '2021-10-02', '11:59:59');

INSERT INTO games (name, organizer_id, end_date, end_time)
VALUES ('Game Three', 1, '2021-10-03', '11:59:59');


INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (1, 1, 100000.00, 'Accepted');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (2, 1, 100000.00, 'Accepted');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (1, 2, 100000.00, 'Accepted');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (2, 2, 100000.00, 'Pending');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (1, 3, 100000.00, 'Accepted');

INSERT INTO game_players (user_id, game_id, available_funds, game_status)
VALUES (2, 3, 100000.00, 'Accepted');

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (1, 'Apple', 'AAPL', 1);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (2, 'Apple', 'AAPL', 1);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (3, 'Apple', 'AAPL', 1);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (4, 'Apple', 'AAPL', 1);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (5, 'Apple', 'AAPL', 1);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (6, 'Apple', 'AAPL', 1);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (7, 'Apple', 'AAPL', 1);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (8, 'Apple', 'AAPL', 1);

INSERT INTO stocks (player_id, stock_name, stock_ticker, total_shares)
VALUES (9, 'Apple', 'AAPL', 1);

INSERT INTO trades (stock_id, shares_traded, buy_or_sell, price, entered_in, date, time)
VALUES (1, 1, 'Buy', 145.36, 'Shares', '2021-9-22', '11:59:59');


