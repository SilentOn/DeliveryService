package com.delivery.dao.mysql.queries;

public class MySqlQueries {
	// for user
	public static final String INSERT_INTO_USER = "INSERT INTO user (`phone_number`, `password`, `role_id`) VALUES (?, ?, ?)";
	public static final String SELECT_ALL_USER = "select * from user";
	public static final String SELECT_USER_BY_ID = "select * from user where id=?";
	public static final String SELECT_USER_BY_PHONE_NUMBER = "select * from user where phone_number=?";
	public static final String SELECT_USER_BY_PHONE_NUMBER_AND_PASSWORD = "select * from user where phone_number=? and password=?";
	public static final String UPDATE_USER = "UPDATE user SET `password`=?, `role_id`=? WHERE `id` = ?";
	public static final String DELETE_USER = "DELETE FROM user WHERE `id` = ?";

	// for role
	public static final String SELECT_ALL_ROLE = "select * from role";
	public static final String SELECT_ROLE_BY_ID = "select * from role where id=?";
	public static final String SELECT_ROLE_BY_NAME = "select * from role where name=?";

	// for user_details
	public static final String INSERT_INTO_USER_DETAILS = "INSERT INTO user_details VALUES (?, ?, ?, ?)";
	public static final String SELECT_USER_DETAILS_BY_ID = "select * from user_details where id=?";
	public static final String SELECT_USER_DETAILS_BY_EMAIL = "select * from user_details where email=?";
	public static final String UPDATE_USER_DETAILS = "UPDATE user_details SET email=?, first_name=?, last_name=? WHERE id = ?";

	// for invoice
	public static final String INSERT_INTO_INVOICE = "INSERT INTO invoice " +
			" VALUES (DEFAULT, ?, ?, ?, ?, null, ?, DEFAULT, ?, ?, ?, ?, ?)";
	public static final String SELECT_ALL_INVOICE = "select * from invoice";
	public static final String SELECT_INVOICE_BY_ID = "select * from invoice where id=?";
	public static final String SELECT_INVOICE_BY_USER_ID = "select * from invoice where user_id=?";
	public static final String UPDATE_INVOICE_BY_PROCESSED = "UPDATE invoice SET" +
			"`processed_by_id`=?, `invoice_status_id`=? WHERE `id` = ?";

	// for invoice_status
	public static final String SELECT_ALL_INVOICE_STATUS = "select * from invoice_status";
	public static final String SELECT_INVOICE_STATUS_BY_ID = "select * from invoice_status where id=?";
	public static final String SELECT_INVOICE_STATUS_BY_NAME = "select * from invoice_status where name=?";

	// for invoice_has_cargo
	public static final String SELECT_ALL_INVOICE_HAS_CARGO = "select * from invoice_has_cargo";
	public static final String SELECT_INVOICE_HAS_CARGO_BY_ID = "select * from invoice_has_cargo where id=?";
	public static final String SELECT_INVOICE_HAS_CARGO_BY_INVOICE_ID = "select * from invoice_has_cargo where invoice_id=?";
	public static final String INSERT_INTO_INVOICE_HAS_CARGO = "INSERT INTO invoice_has_cargo VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_INVOICE_HAS_CARGO =
			"UPDATE invoice_has_cargo SET cargo_id=?, weight=?, length=?, width=?, height=? WHERE id = ?";
	public static final String DELETE_INVOICE_HAS_CARGO = "DELETE FROM invoice_has_cargo WHERE id = ?";

	// for receipt
	public static final String SELECT_ALL_RECEIPT = "select * from receipt";
	public static final String SELECT_RECEIPT_BY_ID = "select * from receipt where id=?";
	public static final String SELECT_RECEIPT_BY_RECEIPT_STATUS_ID = "select * from receipt where receipt_status_id=?";
	public static final String INSERT_INTO_RECEIPT = "INSERT INTO receipt VALUES (?, ?, ?)";
	public static final String UPDATE_RECEIPT = "UPDATE receipt SET to_pay=?, receipt_status_id=? WHERE id = ?";

	// for receipt_status
	public static final String SELECT_ALL_RECEIPT_STATUS = "select * from receipt_status";
	public static final String SELECT_RECEIPT_STATUS_BY_ID = "select * from receipt_status where id=?";
	public static final String SELECT_RECEIPT_STATUS_BY_NAME = "select * from receipt_status where name=?";

	// for cargo
	public static final String SELECT_ALL_CARGO = "select * from cargo";
	public static final String SELECT_CARGO_BY_ID = "select * from cargo where id=?";
	public static final String SELECT_CARGO_BY_TYPE = "select * from cargo where type=?";

	// for region
	public static final String SELECT_ALL_REGION = "select * from region";
	public static final String SELECT_REGION_BY_ID = "select * from region where id=?";
	public static final String SELECT_REGION_BY_REGION_TITLE = "select * from region where region=?";

	// for city
	public static final String SELECT_ALL_CITY = "select * from city";
	public static final String SELECT_CITY_BY_ID = "select * from city where id=?";
	public static final String SELECT_CITY_BY_CITY_TITLE_AND_REGION_ID = "select * from city where city=? and region_id=?";
	public static final String SELECT_CITY_BY_REGION_ID = "select * from city where region_id=?";
	public static final String INSERT_INTO_CITY = "INSERT INTO city VALUES (DEFAULT, ?, ?)";

	// for address
	public static final String SELECT_ALL_ADDRESS = "select * from address";
	public static final String SELECT_ADDRESS_BY_ID = "select * from address where id=?";
	public static final String SELECT_ADDRESS_BY_ADDRESS_TITLE_AND_CITY_ID = "select * from address where address=? and city_id=?";
	public static final String SELECT_ADDRESS_BY_REGION_ID = "select * from address where city_id=?";
	public static final String INSERT_INTO_ADDRESS = "INSERT INTO address VALUES (DEFAULT, ?, ?)";

	// for region_has_region
	public static final String SELECT_ALL_REGION_HAS_REGION = "select * from region_has_region";
	public static final String SELECT_REGION_HAS_REGION_BY_ID =
			"select * from region_has_region where region_1_id=? and region_2_id=?";
	public static final String SELECT_REGION_HAS_REGION_BY_TARIFF_ZONE =
			"select * from region_has_region where tariff_zone_id=?";

	// for tariff_zone
	public static final String SELECT_ALL_TARIFF_ZONE = "select * from tariff_zone";
	public static final String SELECT_TARIFF_ZONE_BY_ID = "select * from tariff_zone where id=?";
	public static final String SELECT_TARIFF_ZONE_BY_TARIFF_ZONE_TITLE = "select * from tariff_zone where tariff_zone=?";

	// for weight_tariff
	public static final String SELECT_ALL_WEIGHT_TARIFF = "select * from weight_tariff";
	public static final String SELECT_WEIGHT_TARIFF_BY_ID = "select * from weight_tariff where id=?";

	// for volumetric_tariff
	public static final String SELECT_ALL_VOLUMETRIC_TARIFF = "select * from volumetric_tariff";
	public static final String SELECT_VOLUMETRIC_TARIFF_BY_ID = "select * from volumetric_tariff where id=?";

	// for tariff_zone_has_weight_tariff
	public static final String SELECT_ALL_TARIFF_ZONE_HAS_WEIGHT_TARIFF = "select * from tariff_zone_has_weight_tariff";
	public static final String SELECT_TARIFF_ZONE_HAS_WEIGHT_TARIFF_BY_ID =
			"select * from tariff_zone_has_weight_tariff where tariff_zone_id=? and weight_tariff_id=?";
}