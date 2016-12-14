INSERT INTO `falemais`.`Plan` (`addition_tax`, `franchise`, `name`) VALUES ('10.00', '30', 'FaleMais 30');
INSERT INTO `falemais`.`Plan` (`addition_tax`, `franchise`, `name`) VALUES ('10.00', '60', 'FaleMais 60');
INSERT INTO `falemais`.`Plan` (`addition_tax`, `franchise`, `name`) VALUES ('10.00', '120', 'FaleMais 120');


INSERT INTO `falemais`.`CityCode` (`code`) VALUES ('11');
INSERT INTO `falemais`.`CityCode` (`code`) VALUES ('16');
INSERT INTO `falemais`.`CityCode` (`code`) VALUES ('17');
INSERT INTO `falemais`.`CityCode` (`code`) VALUES ('18');


set @ddd11 := (select id from falemais.CityCode where code = 11);
set @ddd16 := (select id from falemais.CityCode where code = 16);
set @ddd17 := (select id from falemais.CityCode where code = 17);
set @ddd18 := (select id from falemais.CityCode where code = 18);

INSERT INTO `falemais`.`CallTax` (`origin_city_code_id`, `destination_city_code_id`, `tax_per_minute`) VALUES (@ddd11, @ddd16, '1.90');
INSERT INTO `falemais`.`CallTax` (`origin_city_code_id`, `destination_city_code_id`, `tax_per_minute`) VALUES (@ddd16, @ddd11, '2.90');
INSERT INTO `falemais`.`CallTax` (`origin_city_code_id`, `destination_city_code_id`, `tax_per_minute`) VALUES (@ddd11, @ddd17, '1.70');
INSERT INTO `falemais`.`CallTax` (`origin_city_code_id`, `destination_city_code_id`, `tax_per_minute`) VALUES (@ddd17, @ddd11, '2.70');
INSERT INTO `falemais`.`CallTax` (`origin_city_code_id`, `destination_city_code_id`, `tax_per_minute`) VALUES (@ddd11, @ddd18, '0.90');
INSERT INTO `falemais`.`CallTax` (`origin_city_code_id`, `destination_city_code_id`, `tax_per_minute`) VALUES (@ddd18, @ddd11, '1.90');