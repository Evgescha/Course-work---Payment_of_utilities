package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.ServiceDAO;
import defaultOperation.StandartFrameOperation;
import entity.Service;
import forms.PaymentFrame;
import forms.ServiceFrame;
import tableModal.ServiceTableModal;

public class ServiceController extends StandartFrameOperation {

	ServiceDAO DAO;

	public ServiceController(JFrame frame) {
		super(frame);
		try {
			DAO = new ServiceDAO();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void actionSearchButton(String name, JTable table) {
		try {
			List<Service> list = null;

			if (name != null && name.trim().length() > 0)
				list = DAO.search(name);
			else
				list = DAO.readAll();

			ServiceTableModal model = new ServiceTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(String fio) {
			Service mt = new Service(fio);
			try {
				DAO.create(mt);
				((ServiceFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
	}

	public void actionUpdateButton(String fioNew, Long id) {
			try {
				DAO.update(fioNew,id);
				((ServiceFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
	}

	public void actionDeleteButton(Long id) {
		
			try {
				DAO.Delete(id);
				((ServiceFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице" , "Ошибка", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
	}

	public ServiceDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainController.switchVisible();	
		
	}
	@Override
	public void switchVisible() {
		super.switchVisible();
		refrechView();
	}
	public void refrechView() {
		((ServiceFrame) getFrame()).refreshView();
	}

}
