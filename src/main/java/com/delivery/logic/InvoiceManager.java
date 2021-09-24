package com.delivery.logic;


import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.dao.InvoiceDAO;
import com.delivery.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceManager {
	private static InvoiceManager instance;

	private final DAOFactory daoFactory;
	private final InvoiceDAO invoiceDAO;

	private InvoiceManager(DAOFactory daoFactory) throws DAOException {
		this.daoFactory = daoFactory;
		this.invoiceDAO = daoFactory.getInvoiceDAO();
	}

	public static synchronized InvoiceManager getInstance(DAOFactory daoFactory) throws DAOException {
		if (instance == null) {
			instance = new InvoiceManager(daoFactory);
		}
		return instance;
	}

	public List<Invoice> getAll() throws DAOException {
		List<Invoice> users;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			users = invoiceDAO.getAll(con);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all invoices", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return users;
	}

	public List<Invoice> getByUser(long userId) throws DAOException {
		List<Invoice> users = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			users = invoiceDAO.getByUser(con, userId);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain invoices", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return users;
	}

	public void insertInvoice(Invoice invoice) throws DAOException {
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			invoiceDAO.insert(con, invoice);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't insert invoice", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
	}

	public InvoiceStatus getInvoiceStatus(int id) throws DAOException {
		InvoiceStatus invoiceStatus = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			invoiceStatus = daoFactory.getInvoiceStatusDAO().getByPK(con, id);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain invoiceStatus", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return invoiceStatus;
	}

	public InvoiceStatus getInvoiceStatus(InvoiceStatus.Status name) throws DAOException {
		InvoiceStatus invoiceStatus = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			invoiceStatus = daoFactory.getInvoiceStatusDAO().getByName(con, name);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain invoiceStatus", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return invoiceStatus;
	}

	public List<InvoiceStatus> getAllInvoiceStatuses() throws DAOException {
		List<InvoiceStatus> invoiceStatuses;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			invoiceStatuses = daoFactory.getInvoiceStatusDAO().getAll(con);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all invoiceStatuses", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return invoiceStatuses;
	}

	public ReceiptStatus getReceiptStatus(int id) throws DAOException {
		ReceiptStatus receiptStatus = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			receiptStatus = daoFactory.getReceiptStatusDAO().getByPK(con, id);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain receiptStatus", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return receiptStatus;
	}

	public ReceiptStatus getReceiptStatus(ReceiptStatus.Status name) throws DAOException {
		ReceiptStatus receiptStatus = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			receiptStatus = daoFactory.getReceiptStatusDAO().getByName(con, name);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain receiptStatus", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return receiptStatus;
	}

	public List<ReceiptStatus> getAllReceiptStatuses() throws DAOException {
		List<ReceiptStatus> receiptStatuses;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			receiptStatuses = daoFactory.getReceiptStatusDAO().getAll(con);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all receiptStatuses", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return receiptStatuses;
	}

	public void insertInvoice(User user, String regionFrom, String cityFrom, String addressFrom,
	                          String regionTo, String cityTo, String addressTo, Double estimate, List<InvoiceHasCargo> cargos) throws DAOException {
		Address addressFromEntity = null;
		Address addressToEntity = null;
		TariffZone tariffZone = null;
		InvoiceStatus invoiceStatus = null;
		Invoice invoice = new Invoice();

		float totalW = 0;
		float totalV = 0;
		for (InvoiceHasCargo cargo : cargos) {
			totalW += cargo.getWeight();
			totalV += cargo.getHeight() * cargo.getLength() * cargo.getWidth();
		}

		Connection con = null;
		try {
			con = daoFactory.getConnection();

			addressFromEntity = daoFactory.getAddressDAO().getByTitlesOfRegionCityAddress(con, regionFrom, cityFrom, addressFrom);

			addressToEntity = daoFactory.getAddressDAO().getByTitlesOfRegionCityAddress(con, regionTo, cityTo, addressTo);

			tariffZone = daoFactory.getTariffZoneDAO()
					.getByCityFromIdAndCityToId(con, addressFromEntity.getCityId(), addressToEntity.getCityId());

			invoiceStatus = daoFactory.getInvoiceStatusDAO().getByName(con, InvoiceStatus.Status.NEW);

			invoice.setUserId(user.getId());
			invoice.setAddressFromId(addressFromEntity.getId());
			invoice.setAddressToId(addressToEntity.getId());
			invoice.setTariffZoneId(tariffZone.getId());
			invoice.setInvoiceStatusId(invoiceStatus.getId());
			invoice.setEstimate(estimate);
			invoice.setDeliveryTerm(tariffZone.getTerm());
			invoice.setTotalWeight(totalW);
			invoice.setTotalVolume(totalV);
			invoice.setNumberOfCargo(cargos.size());

			invoice = invoiceDAO.insert(con, invoice);

			for (InvoiceHasCargo cargoItem : cargos) {
				cargoItem.setInvoiceId(invoice.getId());
				daoFactory.getInvoiceHasCargoDAO().insert(con, cargoItem);
			}

			//daoFactory.getInvoiceHasCargoDAO().insert(con, cargos);

			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't insert invoice", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
	}

	public Receipt calculateReceipt(String regionFrom, String cityFrom, String addressFrom,
	                                String regionTo, String cityTo, String addressTo, Double estimate, List<InvoiceHasCargo> cargos) throws DAOException {
		Address addressFromEntity = null;
		Address addressToEntity = null;
		TariffZone tariffZone = null;
		InvoiceStatus invoiceStatus = null;
		Invoice invoice = new Invoice();
		Receipt receipt;

		float totalW = 0;
		float totalV = 0;
		for (InvoiceHasCargo cargo : cargos) {
			totalW += cargo.getWeight();
			totalV += cargo.getHeight() * cargo.getLength() * cargo.getWidth();
		}

		Connection con = null;
		try {
			con = daoFactory.getConnection();

			addressFromEntity = daoFactory.getAddressDAO().getByTitlesOfRegionCityAddress(con, regionFrom, cityFrom, addressFrom);

			addressToEntity = daoFactory.getAddressDAO().getByTitlesOfRegionCityAddress(con, regionTo, cityTo, addressTo);

			tariffZone = daoFactory.getTariffZoneDAO()
					.getByCityFromIdAndCityToId(con, addressFromEntity.getCityId(), addressToEntity.getCityId());

			invoiceStatus = daoFactory.getInvoiceStatusDAO().getByName(con, InvoiceStatus.Status.NEW);

			invoice.setAddressFromId(addressFromEntity.getId());
			invoice.setAddressToId(addressToEntity.getId());
			invoice.setTariffZoneId(tariffZone.getId());
			invoice.setInvoiceStatusId(invoiceStatus.getId());
			invoice.setEstimate(estimate);
			invoice.setDeliveryTerm(tariffZone.getTerm());
			invoice.setTotalWeight(totalW);
			invoice.setTotalVolume(totalV);
			invoice.setNumberOfCargo(cargos.size());

			receipt = getByInvoice(con, invoice);

			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't create invoice", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return receipt;
	}

	public List<Invoice> getAllInvoiceByUser(User user) throws DAOException {
		List<Invoice> invoices;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			invoices = daoFactory.getInvoiceDAO().getByUser(con, user.getId());
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all invoices", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return invoices;
	}

	public Receipt getByInvoice(Invoice invoice) throws DAOException {
		Receipt receipt = new Receipt();
		Connection con = null;
		try {
			con = daoFactory.getConnection();

			receipt = getByInvoice(con, invoice);

			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all invoices", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return receipt;
	}

	private Receipt getByInvoice(Connection con, Invoice invoice) throws SQLException {
		Receipt receipt = new Receipt();
		TariffZone tariffZone = null;
		WeightTariff wTariff = null;
		VolumetricTariff vTariff = null;
		TariffZoneHasWeightTariff tariffZoneHasWeightTariff = null;
		List<WeightTariff> weightTariffs = new ArrayList<>();
		List<VolumetricTariff> volumetricTariffs = new ArrayList<>();
		double toPay = 0;

		tariffZone = daoFactory.getTariffZoneDAO().getByPK(con, invoice.getTariffZoneId());
		weightTariffs = daoFactory.getWeightTariffDAO().getAll(con);
		volumetricTariffs = daoFactory.getVolumetricTariffDAO().getAll(con);

		wTariff = weightTariffs.get(weightTariffs.size() - 1);
		for (WeightTariff wT : weightTariffs) {
			if (invoice.getTotalWeight() < wT.getWeight()) {
				wTariff = wT;
				break;
			}
		}
		tariffZoneHasWeightTariff = daoFactory
				.getTariffZoneHasWeightTariffDAO().getByPK(con, tariffZone.getId(), wTariff.getId());

		vTariff = volumetricTariffs.get(volumetricTariffs.size() - 1);
		for (VolumetricTariff vT : volumetricTariffs) {
			if (invoice.getTotalVolume() < vT.getVolume()) {
				vTariff = vT;
				break;
			}
		}

		toPay = invoice.getEstimate() * 0.01;
		if (wTariff.getId() == weightTariffs.size()) {
			toPay += tariffZoneHasWeightTariff.getPrice() * invoice.getTotalWeight();
		} else {
			toPay += tariffZoneHasWeightTariff.getPrice();
		}
		if (vTariff.getId() == volumetricTariffs.size()) {
			toPay += vTariff.getPrice() * invoice.getTotalVolume() / 10.000;
		} else {
			toPay += vTariff.getPrice();
		}

		receipt.setId(invoice.getId());
		receipt.setReceiptStatusId(daoFactory.getReceiptStatusDAO().getByName(con, ReceiptStatus.Status.NOT_PAID).getId());
		receipt.setToPay(toPay);

		System.out.println("receipt ==> " + receipt);
		System.out.println("tariffZone ==> " + tariffZone);
		System.out.println("wTariff ==> " + wTariff);
		System.out.println("vTariff ==> " + vTariff);
		System.out.println("tariffZoneHasWeightTariff ==> " + tariffZoneHasWeightTariff);

		return receipt;
	}

	public Receipt createReceipt(User user, long invoiceId) throws DAOException {
		Receipt createdReceipt = null;
		Invoice invoice = null;

		Connection con = null;
		try {
			con = daoFactory.getConnection();

			invoice = daoFactory.getInvoiceDAO().getByPK(con, invoiceId);

			createdReceipt = getByInvoice(con, invoice);
			daoFactory.getReceiptDAO().insert(con, createdReceipt);

			invoice.setProcessedById(user.getId());
			invoice.setInvoiceStatusId(daoFactory.getInvoiceStatusDAO().getByName(con, InvoiceStatus.Status.PROCESSED).getId());

			daoFactory.getInvoiceDAO().process(con, invoice);

			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't create receipt", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return createdReceipt;
	}

	public List<Receipt> getAllReceipts(User user) throws DAOException {
		List<Receipt> receipts = new ArrayList<>();
		Receipt receiptTmp;
		List<Invoice> invoices;
		Connection con = null;
		try {
			con = daoFactory.getConnection();

			invoices = daoFactory.getInvoiceDAO().getByUser(con, user.getId());
			for (Invoice inv : invoices) {
				receiptTmp = daoFactory.getReceiptDAO().getByPK(con, inv.getId());
				if (receiptTmp != null) {
					receipts.add(receiptTmp);
				}
			}
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all receipts", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return receipts;
	}

	public List<Receipt> getAllReceipts() throws DAOException {
		List<Receipt> receipts;
		Connection con = null;
		try {
			con = daoFactory.getConnection();

			receipts = daoFactory.getReceiptDAO().getAll(con);

			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all receipts", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return receipts;
	}

	public Receipt payReceipt(User user, long receiptId) throws DAOException {
		Receipt receipt = null;

		Connection con = null;
		try {
			con = daoFactory.getConnection();

			receipt = daoFactory.getReceiptDAO().getByPK(con, receiptId);

			receipt.setReceiptStatusId(daoFactory.getReceiptStatusDAO().getByName(con, ReceiptStatus.Status.PAID).getId());

			daoFactory.getReceiptDAO().update(con, receipt);

			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't create paidReceipt", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return receipt;
	}
}
