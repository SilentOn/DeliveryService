-- -----------------------------------------------------
-- Data for table `delivery_service`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `delivery_service`.`role` (`id`, `name`) VALUES (DEFAULT, 'user');
INSERT INTO `delivery_service`.`role` (`id`, `name`) VALUES (DEFAULT, 'manager');
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`receipt_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `delivery_service`.`receipt_status` VALUES (DEFAULT, 'paid');
INSERT INTO `delivery_service`.`receipt_status` VALUES (DEFAULT, 'not paid');
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`invoice_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `invoice_status` VALUES (DEFAULT, 'new');
INSERT INTO `invoice_status` VALUES (DEFAULT, 'canceled');
INSERT INTO `invoice_status` VALUES (DEFAULT, 'processed');
INSERT INTO `invoice_status` VALUES (DEFAULT, 'paid');
INSERT INTO `invoice_status` VALUES (DEFAULT, 'delivered');
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`cargo`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `cargo` VALUES (DEFAULT, 'parcels and cargoes');
INSERT INTO `cargo` VALUES (DEFAULT, 'documents');
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`tariff_zone`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `tariff_zone` VALUES (DEFAULT, '1', 1);
INSERT INTO `tariff_zone` VALUES (DEFAULT, '2', 2);
INSERT INTO `tariff_zone` VALUES (DEFAULT, '3', 2);
INSERT INTO `tariff_zone` VALUES (DEFAULT, '4', 3);
INSERT INTO `tariff_zone` VALUES (DEFAULT, 'city', 1);
INSERT INTO `tariff_zone` VALUES (DEFAULT, 'region', 1);
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `user` VALUES (DEFAULT, '+380 (11) 111-11-11', 'passAdmin', 2);
INSERT INTO `user` VALUES (DEFAULT, '+380 (22) 222-22-22', 'passAdmin', 2);
INSERT INTO `user` VALUES (DEFAULT, '+380 (33) 333-33-33', 'passAdmin', 2);
INSERT INTO `user` VALUES (DEFAULT, '+380 (44) 444-44-44', 'passAdmin', 2);
INSERT INTO `user` VALUES (DEFAULT, '+380 (55) 555-55-55', 'passAdmin', 2);
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`user_details`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `user_details` VALUES (1, 'manager_james@gmail.com', 'James', 'Smith');
INSERT INTO `user_details` VALUES (2, 'manager_john@gmail.com', 'John', 'Johnson');
INSERT INTO `user_details` VALUES (3, 'manager_robert@gmail.com', 'Robert', 'Williams');
INSERT INTO `user_details` VALUES (4, 'manager_michael@gmail.com', 'Michael', 'Brown');
INSERT INTO `user_details` VALUES (5, 'manager_William@gmail.com', 'William', 'Miller');
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`region`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `region` VALUES (DEFAULT, 'Вінницька');
INSERT INTO `region` VALUES (DEFAULT, 'Волинська');
INSERT INTO `region` VALUES (DEFAULT, 'Дніпропетровська');
INSERT INTO `region` VALUES (DEFAULT, 'Донецька');
INSERT INTO `region` VALUES (DEFAULT, 'Житомирська');
INSERT INTO `region` VALUES (DEFAULT, 'Закарпатська');
INSERT INTO `region` VALUES (DEFAULT, 'Запорізька');
INSERT INTO `region` VALUES (DEFAULT, 'Івано-Франківська');
INSERT INTO `region` VALUES (DEFAULT, 'Київська');
INSERT INTO `region` VALUES (DEFAULT, 'Кіровоградська');
INSERT INTO `region` VALUES (DEFAULT, 'Луганська');
INSERT INTO `region` VALUES (DEFAULT, 'Львівська');
INSERT INTO `region` VALUES (DEFAULT, 'Миколавївська');
INSERT INTO `region` VALUES (DEFAULT, 'Одеська');
INSERT INTO `region` VALUES (DEFAULT, 'Полтавська');
INSERT INTO `region` VALUES (DEFAULT, 'Рівненська');
INSERT INTO `region` VALUES (DEFAULT, 'Сумська');
INSERT INTO `region` VALUES (DEFAULT, 'Тернопільська');
INSERT INTO `region` VALUES (DEFAULT, 'Харківська');
INSERT INTO `region` VALUES (DEFAULT, 'Херсонська');
INSERT INTO `region` VALUES (DEFAULT, 'Хмельницька');
INSERT INTO `region` VALUES (DEFAULT, 'Черкаська');
INSERT INTO `region` VALUES (DEFAULT, 'Чернівецька');
INSERT INTO `region` VALUES (DEFAULT, 'Чернігівська');
INSERT INTO `region` VALUES (DEFAULT, 'АР Крим');
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`city`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `city` VALUES (DEFAULT, 'Вінниця', 1);
INSERT INTO `city` VALUES (DEFAULT, 'Луцьк', 2);
INSERT INTO `city` VALUES (DEFAULT, 'Дніпро', 3);
INSERT INTO `city` VALUES (DEFAULT, 'Донецьк', 4);
INSERT INTO `city` VALUES (DEFAULT, 'Житомир', 5);
INSERT INTO `city` VALUES (DEFAULT, 'Ужгород', 6);
INSERT INTO `city` VALUES (DEFAULT, 'Запоріжжя', 7);
INSERT INTO `city` VALUES (DEFAULT, 'Івано-Франківськ', 8);
INSERT INTO `city` VALUES (DEFAULT, 'Київ', 9);
INSERT INTO `city` VALUES (DEFAULT, 'Кіровоград', 10);
INSERT INTO `city` VALUES (DEFAULT, 'Луганськ', 11);
INSERT INTO `city` VALUES (DEFAULT, 'Львів', 12);
INSERT INTO `city` VALUES (DEFAULT, 'Миколавїв', 13);
INSERT INTO `city` VALUES (DEFAULT, 'Одеса', 14);
INSERT INTO `city` VALUES (DEFAULT, 'Полтава', 15);
INSERT INTO `city` VALUES (DEFAULT, 'Рівне', 16);
INSERT INTO `city` VALUES (DEFAULT, 'Суми', 17);
INSERT INTO `city` VALUES (DEFAULT, 'Тернопіль', 18);
INSERT INTO `city` VALUES (DEFAULT, 'Харків', 19);
INSERT INTO `city` VALUES (DEFAULT, 'Херсон', 20);
INSERT INTO `city` VALUES (DEFAULT, 'Хмельницьк', 21);
INSERT INTO `city` VALUES (DEFAULT, 'Черкаси', 22);
INSERT INTO `city` VALUES (DEFAULT, 'Чернівці', 23);
INSERT INTO `city` VALUES (DEFAULT, 'Чернігів', 24);
INSERT INTO `city` VALUES (DEFAULT, 'Сімферополь', 25);
INSERT INTO `city` VALUES (DEFAULT, 'Ізюм', 19);
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`address`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 1);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 1);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 1);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 1);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 1);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 2);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 2);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 2);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 2);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 2);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 3);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 3);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 3);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 3);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 3);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 4);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 4);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 4);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 4);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 4);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 5);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 5);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 5);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 5);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 5);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 6);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 6);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 6);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 6);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 6);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 7);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 7);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 7);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 7);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 7);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 8);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 8);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 8);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 8);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 8);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 9);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 9);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 9);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 9);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 9);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 10);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 10);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 10);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 10);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 10);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 11);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 11);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 11);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 11);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 11);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 12);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 12);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 12);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 12);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 12);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 13);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 13);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 13);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 13);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 13);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 14);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 14);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 14);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 14);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 14);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 15);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 15);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 15);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 15);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 15);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 16);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 16);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 16);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 16);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 16);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 17);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 17);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 17);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 17);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 17);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 18);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 18);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 18);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 18);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 18);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 19);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 19);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 19);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 19);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 19);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 20);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 20);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 20);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 20);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 20);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 21);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 21);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 21);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 21);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 21);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 22);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 22);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 22);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 22);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 22);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 23);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 23);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 23);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 23);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 23);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 24);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 24);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 24);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 24);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 24);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 25);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 25);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 25);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 25);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 25);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Пушкінська, буд.1', 26);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Сумська, буд.1', 26);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Козацька, буд.1', 26);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Соборна, буд.1', 26);
INSERT INTO `address` VALUES (DEFAULT, 'вул. Тобольська, буд.1', 26);
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`region_has_region`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `region_has_region` VALUES (1, 2, 3);
INSERT INTO `region_has_region` VALUES (1, 3, 3);
INSERT INTO `region_has_region` VALUES (1, 4, 4);
INSERT INTO `region_has_region` VALUES (1, 5, 1);
INSERT INTO `region_has_region` VALUES (1, 6, 3);
INSERT INTO `region_has_region` VALUES (1, 7, 3);
INSERT INTO `region_has_region` VALUES (1, 8, 3);
INSERT INTO `region_has_region` VALUES (1, 9, 3);
INSERT INTO `region_has_region` VALUES (1, 10, 3);
INSERT INTO `region_has_region` VALUES (1, 11, 4);
INSERT INTO `region_has_region` VALUES (1, 12, 3);
INSERT INTO `region_has_region` VALUES (1, 13, 3);
INSERT INTO `region_has_region` VALUES (1, 14, 3);
INSERT INTO `region_has_region` VALUES (1, 15, 3);
INSERT INTO `region_has_region` VALUES (1, 16, 3);
INSERT INTO `region_has_region` VALUES (1, 17, 3);
INSERT INTO `region_has_region` VALUES (1, 18, 2);
INSERT INTO `region_has_region` VALUES (1, 19, 3);
INSERT INTO `region_has_region` VALUES (1, 20, 3);
INSERT INTO `region_has_region` VALUES (1, 21, 2);
INSERT INTO `region_has_region` VALUES (1, 22, 3);
INSERT INTO `region_has_region` VALUES (1, 23, 2);
INSERT INTO `region_has_region` VALUES (1, 24, 3);
INSERT INTO `region_has_region` VALUES (1, 25, 4);

INSERT INTO `region_has_region` VALUES (2, 3, 4);
INSERT INTO `region_has_region` VALUES (2, 4, 4);
INSERT INTO `region_has_region` VALUES (2, 5, 2);
INSERT INTO `region_has_region` VALUES (2, 6, 3);
INSERT INTO `region_has_region` VALUES (2, 7, 4);
INSERT INTO `region_has_region` VALUES (2, 8, 3);
INSERT INTO `region_has_region` VALUES (2, 9, 3);
INSERT INTO `region_has_region` VALUES (2, 10, 3);
INSERT INTO `region_has_region` VALUES (2, 11, 4);
INSERT INTO `region_has_region` VALUES (2, 12, 1);
INSERT INTO `region_has_region` VALUES (2, 13, 4);
INSERT INTO `region_has_region` VALUES (2, 14, 4);
INSERT INTO `region_has_region` VALUES (2, 15, 4);
INSERT INTO `region_has_region` VALUES (2, 16, 1);
INSERT INTO `region_has_region` VALUES (2, 17, 4);
INSERT INTO `region_has_region` VALUES (2, 18, 2);
INSERT INTO `region_has_region` VALUES (2, 19, 4);
INSERT INTO `region_has_region` VALUES (2, 20, 4);
INSERT INTO `region_has_region` VALUES (2, 21, 2);
INSERT INTO `region_has_region` VALUES (2, 22, 3);
INSERT INTO `region_has_region` VALUES (2, 23, 3);
INSERT INTO `region_has_region` VALUES (2, 24, 3);
INSERT INTO `region_has_region` VALUES (2, 25, 4);

INSERT INTO `region_has_region` VALUES (3, 4, 2);
INSERT INTO `region_has_region` VALUES (3, 5, 3);
INSERT INTO `region_has_region` VALUES (3, 6, 4);
INSERT INTO `region_has_region` VALUES (3, 7, 1);
INSERT INTO `region_has_region` VALUES (3, 8, 4);
INSERT INTO `region_has_region` VALUES (3, 9, 3);
INSERT INTO `region_has_region` VALUES (3, 10, 2);
INSERT INTO `region_has_region` VALUES (3, 11, 3);
INSERT INTO `region_has_region` VALUES (3, 12, 4);
INSERT INTO `region_has_region` VALUES (3, 13, 3);
INSERT INTO `region_has_region` VALUES (3, 14, 3);
INSERT INTO `region_has_region` VALUES (3, 15, 2);
INSERT INTO `region_has_region` VALUES (3, 16, 4);
INSERT INTO `region_has_region` VALUES (3, 17, 3);
INSERT INTO `region_has_region` VALUES (3, 18, 4);
INSERT INTO `region_has_region` VALUES (3, 19, 2);
INSERT INTO `region_has_region` VALUES (3, 20, 3);
INSERT INTO `region_has_region` VALUES (3, 21, 3);
INSERT INTO `region_has_region` VALUES (3, 22, 3);
INSERT INTO `region_has_region` VALUES (3, 23, 4);
INSERT INTO `region_has_region` VALUES (3, 24, 3);
INSERT INTO `region_has_region` VALUES (3, 25, 4);


INSERT INTO `region_has_region` VALUES (4, 5, 4);
INSERT INTO `region_has_region` VALUES (4, 6, 4);
INSERT INTO `region_has_region` VALUES (4, 7, 1);
INSERT INTO `region_has_region` VALUES (4, 8, 4);
INSERT INTO `region_has_region` VALUES (4, 9, 4);
INSERT INTO `region_has_region` VALUES (4, 10, 3);
INSERT INTO `region_has_region` VALUES (4, 11, 1);
INSERT INTO `region_has_region` VALUES (4, 12, 4);
INSERT INTO `region_has_region` VALUES (4, 13, 3);
INSERT INTO `region_has_region` VALUES (4, 14, 3);
INSERT INTO `region_has_region` VALUES (4, 15, 3);
INSERT INTO `region_has_region` VALUES (4, 16, 4);
INSERT INTO `region_has_region` VALUES (4, 17, 3);
INSERT INTO `region_has_region` VALUES (4, 18, 4);
INSERT INTO `region_has_region` VALUES (4, 19, 3);
INSERT INTO `region_has_region` VALUES (4, 20, 3);
INSERT INTO `region_has_region` VALUES (4, 21, 4);
INSERT INTO `region_has_region` VALUES (4, 22, 3);
INSERT INTO `region_has_region` VALUES (4, 23, 4);
INSERT INTO `region_has_region` VALUES (4, 24, 4);
INSERT INTO `region_has_region` VALUES (4, 25, 4);

INSERT INTO `region_has_region` VALUES (5, 6, 3);
INSERT INTO `region_has_region` VALUES (5, 7, 3);
INSERT INTO `region_has_region` VALUES (5, 8, 3);
INSERT INTO `region_has_region` VALUES (5, 9, 1);
INSERT INTO `region_has_region` VALUES (5, 10, 3);
INSERT INTO `region_has_region` VALUES (5, 11, 4);
INSERT INTO `region_has_region` VALUES (5, 12, 4);
INSERT INTO `region_has_region` VALUES (5, 13, 3);
INSERT INTO `region_has_region` VALUES (5, 14, 3);
INSERT INTO `region_has_region` VALUES (5, 15, 3);
INSERT INTO `region_has_region` VALUES (5, 16, 3);
INSERT INTO `region_has_region` VALUES (5, 17, 1);
INSERT INTO `region_has_region` VALUES (5, 18, 3);
INSERT INTO `region_has_region` VALUES (5, 19, 3);
INSERT INTO `region_has_region` VALUES (5, 20, 3);
INSERT INTO `region_has_region` VALUES (5, 21, 2);
INSERT INTO `region_has_region` VALUES (5, 22, 3);
INSERT INTO `region_has_region` VALUES (5, 23, 3);
INSERT INTO `region_has_region` VALUES (5, 24, 2);
INSERT INTO `region_has_region` VALUES (5, 25, 4);

INSERT INTO `region_has_region` VALUES (6, 7, 4);
INSERT INTO `region_has_region` VALUES (6, 8, 3);
INSERT INTO `region_has_region` VALUES (6, 9, 4);
INSERT INTO `region_has_region` VALUES (6, 10, 4);
INSERT INTO `region_has_region` VALUES (6, 11, 4);
INSERT INTO `region_has_region` VALUES (6, 12, 1);
INSERT INTO `region_has_region` VALUES (6, 13, 4);
INSERT INTO `region_has_region` VALUES (6, 14, 4);
INSERT INTO `region_has_region` VALUES (6, 15, 4);
INSERT INTO `region_has_region` VALUES (6, 16, 3);
INSERT INTO `region_has_region` VALUES (6, 17, 4);
INSERT INTO `region_has_region` VALUES (6, 18, 3);
INSERT INTO `region_has_region` VALUES (6, 19, 4);
INSERT INTO `region_has_region` VALUES (6, 20, 4);
INSERT INTO `region_has_region` VALUES (6, 21, 3);
INSERT INTO `region_has_region` VALUES (6, 22, 4);
INSERT INTO `region_has_region` VALUES (6, 23, 3);
INSERT INTO `region_has_region` VALUES (6, 24, 4);
INSERT INTO `region_has_region` VALUES (6, 25, 4);

INSERT INTO `region_has_region` VALUES (7, 8, 4);
INSERT INTO `region_has_region` VALUES (7, 9, 3);
INSERT INTO `region_has_region` VALUES (7, 10, 3);
INSERT INTO `region_has_region` VALUES (7, 11, 3);
INSERT INTO `region_has_region` VALUES (7, 12, 4);
INSERT INTO `region_has_region` VALUES (7, 13, 3);
INSERT INTO `region_has_region` VALUES (7, 14, 3);
INSERT INTO `region_has_region` VALUES (7, 15, 2);
INSERT INTO `region_has_region` VALUES (7, 16, 4);
INSERT INTO `region_has_region` VALUES (7, 17, 3);
INSERT INTO `region_has_region` VALUES (7, 18, 4);
INSERT INTO `region_has_region` VALUES (7, 19, 3);
INSERT INTO `region_has_region` VALUES (7, 20, 3);
INSERT INTO `region_has_region` VALUES (7, 21, 4);
INSERT INTO `region_has_region` VALUES (7, 22, 3);
INSERT INTO `region_has_region` VALUES (7, 23, 4);
INSERT INTO `region_has_region` VALUES (7, 24, 3);
INSERT INTO `region_has_region` VALUES (7, 25, 4);

INSERT INTO `region_has_region` VALUES (8, 9, 3);
INSERT INTO `region_has_region` VALUES (8, 10, 3);
INSERT INTO `region_has_region` VALUES (8, 11, 4);
INSERT INTO `region_has_region` VALUES (8, 12, 1);
INSERT INTO `region_has_region` VALUES (8, 13, 4);
INSERT INTO `region_has_region` VALUES (8, 14, 4);
INSERT INTO `region_has_region` VALUES (8, 15, 4);
INSERT INTO `region_has_region` VALUES (8, 16, 3);
INSERT INTO `region_has_region` VALUES (8, 17, 4);
INSERT INTO `region_has_region` VALUES (8, 18, 1);
INSERT INTO `region_has_region` VALUES (8, 19, 4);
INSERT INTO `region_has_region` VALUES (8, 20, 4);
INSERT INTO `region_has_region` VALUES (8, 21, 1);
INSERT INTO `region_has_region` VALUES (8, 22, 4);
INSERT INTO `region_has_region` VALUES (8, 23, 1);
INSERT INTO `region_has_region` VALUES (8, 24, 3);
INSERT INTO `region_has_region` VALUES (8, 25, 4);

INSERT INTO `region_has_region` VALUES (9, 10, 3);
INSERT INTO `region_has_region` VALUES (9, 11, 3);
INSERT INTO `region_has_region` VALUES (9, 12, 3);
INSERT INTO `region_has_region` VALUES (9, 13, 3);
INSERT INTO `region_has_region` VALUES (9, 14, 3);
INSERT INTO `region_has_region` VALUES (9, 15, 3);
INSERT INTO `region_has_region` VALUES (9, 16, 3);
INSERT INTO `region_has_region` VALUES (9, 17, 3);
INSERT INTO `region_has_region` VALUES (9, 18, 3);
INSERT INTO `region_has_region` VALUES (9, 19, 3);
INSERT INTO `region_has_region` VALUES (9, 20, 3);
INSERT INTO `region_has_region` VALUES (9, 21, 3);
INSERT INTO `region_has_region` VALUES (9, 22, 1);
INSERT INTO `region_has_region` VALUES (9, 23, 3);
INSERT INTO `region_has_region` VALUES (9, 24, 1);
INSERT INTO `region_has_region` VALUES (9, 25, 4);

INSERT INTO `region_has_region` VALUES (10, 11, 3);
INSERT INTO `region_has_region` VALUES (10, 12, 4);
INSERT INTO `region_has_region` VALUES (10, 13, 2);
INSERT INTO `region_has_region` VALUES (10, 14, 3);
INSERT INTO `region_has_region` VALUES (10, 15, 2);
INSERT INTO `region_has_region` VALUES (10, 16, 3);
INSERT INTO `region_has_region` VALUES (10, 17, 3);
INSERT INTO `region_has_region` VALUES (10, 18, 3);
INSERT INTO `region_has_region` VALUES (10, 19, 3);
INSERT INTO `region_has_region` VALUES (10, 20, 2);
INSERT INTO `region_has_region` VALUES (10, 21, 3);
INSERT INTO `region_has_region` VALUES (10, 22, 1);
INSERT INTO `region_has_region` VALUES (10, 23, 3);
INSERT INTO `region_has_region` VALUES (10, 24, 3);
INSERT INTO `region_has_region` VALUES (10, 25, 4);

INSERT INTO `region_has_region` VALUES (11, 12, 4);
INSERT INTO `region_has_region` VALUES (11, 13, 3);
INSERT INTO `region_has_region` VALUES (11, 14, 4);
INSERT INTO `region_has_region` VALUES (11, 15, 3);
INSERT INTO `region_has_region` VALUES (11, 16, 4);
INSERT INTO `region_has_region` VALUES (11, 17, 3);
INSERT INTO `region_has_region` VALUES (11, 18, 4);
INSERT INTO `region_has_region` VALUES (11, 19, 3);
INSERT INTO `region_has_region` VALUES (11, 20, 4);
INSERT INTO `region_has_region` VALUES (11, 21, 4);
INSERT INTO `region_has_region` VALUES (11, 22, 3);
INSERT INTO `region_has_region` VALUES (11, 23, 4);
INSERT INTO `region_has_region` VALUES (11, 24, 4);
INSERT INTO `region_has_region` VALUES (11, 25, 4);

INSERT INTO `region_has_region` VALUES (12, 13, 4);
INSERT INTO `region_has_region` VALUES (12, 14, 4);
INSERT INTO `region_has_region` VALUES (12, 15, 4);
INSERT INTO `region_has_region` VALUES (12, 16, 1);
INSERT INTO `region_has_region` VALUES (12, 17, 4);
INSERT INTO `region_has_region` VALUES (12, 18, 1);
INSERT INTO `region_has_region` VALUES (12, 19, 4);
INSERT INTO `region_has_region` VALUES (12, 20, 4);
INSERT INTO `region_has_region` VALUES (12, 21, 2);
INSERT INTO `region_has_region` VALUES (12, 22, 4);
INSERT INTO `region_has_region` VALUES (12, 23, 3);
INSERT INTO `region_has_region` VALUES (12, 24, 3);
INSERT INTO `region_has_region` VALUES (12, 25, 4);

INSERT INTO `region_has_region` VALUES (13, 14, 1);
INSERT INTO `region_has_region` VALUES (13, 15, 3);
INSERT INTO `region_has_region` VALUES (13, 16, 4);
INSERT INTO `region_has_region` VALUES (13, 17, 3);
INSERT INTO `region_has_region` VALUES (13, 18, 3);
INSERT INTO `region_has_region` VALUES (13, 19, 3);
INSERT INTO `region_has_region` VALUES (13, 20, 3);
INSERT INTO `region_has_region` VALUES (13, 21, 3);
INSERT INTO `region_has_region` VALUES (13, 22, 3);
INSERT INTO `region_has_region` VALUES (13, 23, 4);
INSERT INTO `region_has_region` VALUES (13, 24, 4);
INSERT INTO `region_has_region` VALUES (13, 25, 4);

INSERT INTO `region_has_region` VALUES (14, 15, 3);
INSERT INTO `region_has_region` VALUES (14, 16, 4);
INSERT INTO `region_has_region` VALUES (14, 17, 4);
INSERT INTO `region_has_region` VALUES (14, 18, 4);
INSERT INTO `region_has_region` VALUES (14, 19, 3);
INSERT INTO `region_has_region` VALUES (14, 20, 2);
INSERT INTO `region_has_region` VALUES (14, 21, 4);
INSERT INTO `region_has_region` VALUES (14, 22, 3);
INSERT INTO `region_has_region` VALUES (14, 23, 4);
INSERT INTO `region_has_region` VALUES (14, 24, 3);
INSERT INTO `region_has_region` VALUES (14, 25, 4);

INSERT INTO `region_has_region` VALUES (15, 16, 3);
INSERT INTO `region_has_region` VALUES (15, 17, 1);
INSERT INTO `region_has_region` VALUES (15, 18, 4);
INSERT INTO `region_has_region` VALUES (15, 19, 1);
INSERT INTO `region_has_region` VALUES (15, 20, 3);
INSERT INTO `region_has_region` VALUES (15, 21, 3);
INSERT INTO `region_has_region` VALUES (15, 22, 1);
INSERT INTO `region_has_region` VALUES (15, 23, 4);
INSERT INTO `region_has_region` VALUES (15, 24, 3);
INSERT INTO `region_has_region` VALUES (15, 25, 4);

INSERT INTO `region_has_region` VALUES (16, 17, 3);
INSERT INTO `region_has_region` VALUES (16, 18, 1);
INSERT INTO `region_has_region` VALUES (16, 19, 4);
INSERT INTO `region_has_region` VALUES (16, 20, 4);
INSERT INTO `region_has_region` VALUES (16, 21, 1);
INSERT INTO `region_has_region` VALUES (16, 22, 3);
INSERT INTO `region_has_region` VALUES (16, 23, 3);
INSERT INTO `region_has_region` VALUES (16, 24, 3);
INSERT INTO `region_has_region` VALUES (16, 25, 4);

INSERT INTO `region_has_region` VALUES (17, 18, 4);
INSERT INTO `region_has_region` VALUES (17, 19, 1);
INSERT INTO `region_has_region` VALUES (17, 20, 3);
INSERT INTO `region_has_region` VALUES (17, 21, 3);
INSERT INTO `region_has_region` VALUES (17, 22, 3);
INSERT INTO `region_has_region` VALUES (17, 23, 4);
INSERT INTO `region_has_region` VALUES (17, 24, 3);
INSERT INTO `region_has_region` VALUES (17, 25, 4);

INSERT INTO `region_has_region` VALUES (18, 19, 4);
INSERT INTO `region_has_region` VALUES (18, 20, 4);
INSERT INTO `region_has_region` VALUES (18, 21, 1);
INSERT INTO `region_has_region` VALUES (18, 22, 3);
INSERT INTO `region_has_region` VALUES (18, 23, 1);
INSERT INTO `region_has_region` VALUES (18, 24, 3);
INSERT INTO `region_has_region` VALUES (18, 25, 4);

INSERT INTO `region_has_region` VALUES (19, 20, 3);
INSERT INTO `region_has_region` VALUES (19, 21, 4);
INSERT INTO `region_has_region` VALUES (19, 22, 3);
INSERT INTO `region_has_region` VALUES (19, 23, 4);
INSERT INTO `region_has_region` VALUES (19, 24, 3);
INSERT INTO `region_has_region` VALUES (19, 25, 4);

INSERT INTO `region_has_region` VALUES (20, 21, 4);
INSERT INTO `region_has_region` VALUES (20, 22, 3);
INSERT INTO `region_has_region` VALUES (20, 23, 4);
INSERT INTO `region_has_region` VALUES (20, 24, 3);
INSERT INTO `region_has_region` VALUES (20, 25, 4);

INSERT INTO `region_has_region` VALUES (21, 22, 3);
INSERT INTO `region_has_region` VALUES (21, 23, 1);
INSERT INTO `region_has_region` VALUES (21, 24, 3);
INSERT INTO `region_has_region` VALUES (21, 25, 4);

INSERT INTO `region_has_region` VALUES (22, 23, 4);
INSERT INTO `region_has_region` VALUES (22, 24, 3);
INSERT INTO `region_has_region` VALUES (22, 25, 4);

INSERT INTO `region_has_region` VALUES (23, 24, 4);
INSERT INTO `region_has_region` VALUES (23, 25, 4);

INSERT INTO `region_has_region` VALUES (24, 25, 4);
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`weight_tariff`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `weight_tariff` VALUES (DEFAULT, 0.5);
INSERT INTO `weight_tariff` VALUES (DEFAULT, 1);
INSERT INTO `weight_tariff` VALUES (DEFAULT, 2);
INSERT INTO `weight_tariff` VALUES (DEFAULT, 5);
INSERT INTO `weight_tariff` VALUES (DEFAULT, 10);
INSERT INTO `weight_tariff` VALUES (DEFAULT, 20);
INSERT INTO `weight_tariff` VALUES (DEFAULT, 30);
INSERT INTO `weight_tariff` VALUES (DEFAULT, 2000000000);
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`tariff_zone_has_weight_tariff`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (1, 1, 45);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (1, 2, 50);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (1, 3, 55);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (1, 4, 60);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (1, 5, 70);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (1, 6, 90);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (1, 7, 110);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (1, 8, 3);

INSERT INTO `tariff_zone_has_weight_tariff` VALUES (2, 1, 45);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (2, 2, 50);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (2, 3, 55);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (2, 4, 60);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (2, 5, 70);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (2, 6, 90);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (2, 7, 110);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (2, 8, 3.5);

INSERT INTO `tariff_zone_has_weight_tariff` VALUES (3, 1, 45);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (3, 2, 50);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (3, 3, 55);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (3, 4, 60);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (3, 5, 70);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (3, 6, 90);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (3, 7, 110);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (3, 8, 4.5);

INSERT INTO `tariff_zone_has_weight_tariff` VALUES (4, 1, 45);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (4, 2, 50);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (4, 3, 55);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (4, 4, 60);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (4, 5, 70);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (4, 6, 90);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (4, 7, 110);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (4, 8, 6);

INSERT INTO `tariff_zone_has_weight_tariff` VALUES (5, 1, 35);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (5, 2, 40);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (5, 3, 45);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (5, 4, 50);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (5, 5, 60);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (5, 6, 80);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (5, 7, 100);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (5, 8, 1.5);

INSERT INTO `tariff_zone_has_weight_tariff` VALUES (6, 1, 40);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (6, 2, 45);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (6, 3, 50);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (6, 4, 55);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (6, 5, 65);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (6, 6, 85);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (6, 7, 105);
INSERT INTO `tariff_zone_has_weight_tariff` VALUES (6, 8, 2);
COMMIT;

-- -----------------------------------------------------
-- Data for table `delivery_service`.`volumetric_tariff`
-- -----------------------------------------------------
START TRANSACTION;
USE `delivery_service`;
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 500, 1);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 1000, 2);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 2000, 5);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 5000, 10);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 10000, 15);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 20000, 20);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 50000, 25);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 75000, 35);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 100000, 40);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 200000, 45);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 500000, 50);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 750000, 70);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 1000000, 100);
INSERT INTO `volumetric_tariff` VALUES (DEFAULT, 2000000000, 2);
COMMIT;