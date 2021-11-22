INSERT INTO DEVICES (uuid, manufacturer, model, serial_Number, failure_Description) VALUES
 ('9b4018ac-bac2-4afe-8ac6-055612ba9d40', 'ASUS', 'X509FA', 'ASD551AS', 'Broken screen'),
 ('9b4018ac-bac2-4afe-8ac6-055612ba9d41', 'DELL', 'XPS', 'QW0SDE11', 'keyboard failure'),
 ('9b4018ac-bac2-4afe-8ac6-055612ba9d42', 'DELL', 'Vostro 15', 'AFRE1AS', 'Dont turn on'),
 ('9b4018ac-bac2-4afe-8ac6-055612ba9d43', 'Samsung', 'Galaxy S10', 'ASD551AS', 'Broken Screen'),
 ('9b4018ac-bac2-4afe-8ac6-055612ba9d44', 'ASUS', 'X14ZFR', 'A4S551AS', 'Spill damage'),
 ('9b4018ac-bac2-4afe-8ac6-055612ba9d49', 'ASUS', 'XWZ', 'ASD551AS', 'Broken hinges');

INSERT INTO CUSTOMERS (uuid, first_name, last_name, email, tel_number) VALUES
('114018ac-bac2-4afe-8ac6-055612ba9d40', 'Audrius', 'Lunys', 'vienas@du.lt', '+3705465454'),
('124018ac-bac2-4afe-8ac6-055612ba9d40', 'Dalia', 'Daliene', 'as@du.lt', '+370569844'),
('134018ac-bac2-4afe-8ac6-055612ba9d40', 'Inga', 'Ingaite', 'vd@gmail.com', '+3705865454'),
('144018ac-bac2-4afe-8ac6-055612ba9d40', 'Stasys', 'Stasiulis', 'trys@du.lt', '+370415454'),
('154018ac-bac2-4afe-8ac6-055612ba9d40', 'Jonas', 'Jonka', 'vis@yahoo.com', '+3705488454');

INSERT INTO REPAIR_ORDERS(registration_nr, registration_date, repair_description, customer_id, device_id) VALUES
(123456, '2019-03-01','profilaktika',5,2),
(123478, '2021-12-11','screen repair',1,4),
(123756, '2020-08-15','profilaktika',2,3);