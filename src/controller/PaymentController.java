package controller;

import java.sql.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.PaymentDAO;
import defaultOperation.StandartFrameOperation;
import entity.Payment;
import entity.Service;
import entity.Tenant;
import forms.PaymentFrame;
import tableModal.PaymentTableModal;

public class PaymentController extends StandartFrameOperation {

	PaymentDAO DAO;

	public PaymentController(JFrame frame) {
		super(frame);
		try {
			DAO = new PaymentDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionSearchButton(String serviceName, JTable table) {
		try {
			List<Payment> list = null;

			if (serviceName != null && serviceName.trim().length() > 0)
				list = DAO.search(serviceName);
			else
				list = DAO.readAll();

			PaymentTableModal model = new PaymentTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(Date dates, Tenant tenant, Service service, float summ, float fine) {
		if (tenant!=null &&service!=null && summ> 0) {
			Payment entity = new Payment ( dates,tenant,service,summ,fine);
			try {
				DAO.create(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionUpdateButton(Long id,Date dates, Tenant tenant, Service service, float summ, float fine) {
		if (tenant!=null &&service!=null && summ> 0) {
			
			try {
				Payment entity = new Payment ( dates,tenant,service,summ,fine);
				entity.setId(id);
				DAO.update(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionDeleteButton(long id) {
		if(id>0)
			try {
				DAO.Delete(id);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице" , "Ошибка", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
	}
	@Override
	public void switchVisible() {
		super.switchVisible();
		refrechView();
	}
	public void refrechView() {
		((PaymentFrame) getFrame()).refreshView();
	}

	public PaymentDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainController.switchVisible();	
		
	}

	
}
