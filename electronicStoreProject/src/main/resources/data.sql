-- Вставляем адреса (нужны для поставщиков и клиентов)
INSERT INTO address (street, city, country)
SELECT 'ул. Ленина, 10', 'Москва', 'Россия' WHERE NOT EXISTS (SELECT 1 FROM address WHERE street = 'ул. Ленина, 10');

INSERT INTO address (street, city, country)
SELECT 'пр. Победы, 25', 'Санкт-Петербург', 'Россия' WHERE NOT EXISTS (SELECT 1 FROM address WHERE street = 'пр. Победы, 25');

INSERT INTO address (street, city, country)
SELECT 'ул. Свободы, 5', 'Киев', 'Украина' WHERE NOT EXISTS (SELECT 1 FROM address WHERE street = 'ул. Свободы, 5');

INSERT INTO address (street, city, country)
SELECT 'Rue de la Paix, 15', 'Париж', 'Франция' WHERE NOT EXISTS (SELECT 1 FROM address WHERE street = 'Rue de la Paix, 15');

-- Вставляем поставщиков (связь с адресами)
INSERT INTO supplier (name, phone_number, address_id)
SELECT 'ООО ТехноПром', '+79991234567',
       (SELECT id FROM address WHERE street = 'ул. Ленина, 10')
WHERE NOT EXISTS (SELECT 1 FROM supplier WHERE name = 'ООО ТехноПром');

INSERT INTO supplier (name, phone_number, address_id)
SELECT 'ИП Иванов', '+79119876543',
       (SELECT id FROM address WHERE street = 'пр. Победы, 25')
WHERE NOT EXISTS (SELECT 1 FROM supplier WHERE name = 'ИП Иванов');

INSERT INTO supplier (name, phone_number, address_id)
SELECT 'TechImport GmbH', '+498912345678',
       (SELECT id FROM address WHERE street = 'Rue de la Paix, 15')
WHERE NOT EXISTS (SELECT 1 FROM supplier WHERE name = 'TechImport GmbH');

INSERT INTO supplier (name, phone_number, address_id)
SELECT 'Украинские Технологии', '+380441234567',
       (SELECT id FROM address WHERE street = 'ул. Свободы, 5')
WHERE NOT EXISTS (SELECT 1 FROM supplier WHERE name = 'Украинские Технологии');

-- Вставляем клиентов (связь с адресами)
INSERT INTO client (client_name, client_surname, birthday, gender, registration_date, address_id)
SELECT 'Иван', 'Петров', '1990-05-15', 'M', CURRENT_TIMESTAMP,
       (SELECT id FROM address WHERE street = 'ул. Свободы, 5')
WHERE NOT EXISTS (SELECT 1 FROM client WHERE client_name = 'Иван' AND client_surname = 'Петров');

INSERT INTO client (client_name, client_surname, birthday, gender, registration_date, address_id)
SELECT 'Мария', 'Сидорова', '1985-10-22', 'F', CURRENT_TIMESTAMP,
       (SELECT id FROM address WHERE street = 'ул. Ленина, 10')
WHERE NOT EXISTS (SELECT 1 FROM client WHERE client_name = 'Мария' AND client_surname = 'Сидорова');

INSERT INTO client (client_name, client_surname, birthday, gender, registration_date, address_id)
SELECT 'Пьер', 'Дюпон', '1978-03-30', 'M', CURRENT_TIMESTAMP,
       (SELECT id FROM address WHERE street = 'Rue de la Paix, 15')
WHERE NOT EXISTS (SELECT 1 FROM client WHERE client_name = 'Пьер' AND client_surname = 'Дюпон');

INSERT INTO client (client_name, client_surname, birthday, gender, registration_date, address_id)
SELECT 'Анна', 'Ковальчук', '1995-07-18', 'F', CURRENT_TIMESTAMP,
       (SELECT id FROM address WHERE street = 'пр. Победы, 25')
WHERE NOT EXISTS (SELECT 1 FROM client WHERE client_name = 'Анна' AND client_surname = 'Ковальчук');

-- Вставляем товары (связь с поставщиками)
INSERT INTO product (name, category, price, available_stock, last_update_date, supplier_id)
SELECT 'Ноутбук HP EliteBook', 'Электроника', 89999.99, 8, CURRENT_TIMESTAMP,
       (SELECT id FROM supplier WHERE name = 'ООО ТехноПром')
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Ноутбук HP EliteBook');

INSERT INTO product (name, category, price, available_stock, last_update_date, supplier_id)
SELECT 'Смартфон Samsung Galaxy S23', 'Электроника', 84999.50, 12, CURRENT_TIMESTAMP,
       (SELECT id FROM supplier WHERE name = 'ИП Иванов')
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Смартфон Samsung Galaxy S23');

INSERT INTO product (name, category, price, available_stock, last_update_date, supplier_id)
SELECT 'Кофемашина DeLonghi PrimaDonna', 'Бытовая техника', 124999.00, 3, CURRENT_TIMESTAMP,
       (SELECT id FROM supplier WHERE name = 'TechImport GmbH')
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Кофемашина DeLonghi PrimaDonna');

INSERT INTO product (name, category, price, available_stock, last_update_date, supplier_id)
SELECT 'Умные часы Apple Watch Series 9', 'Гаджеты', 45999.00, 15, CURRENT_TIMESTAMP,
       (SELECT id FROM supplier WHERE name = 'Украинские Технологии')
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Умные часы Apple Watch Series 9');