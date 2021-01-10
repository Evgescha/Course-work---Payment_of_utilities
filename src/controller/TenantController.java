package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.TenantDAO;
import defaultOperation.StandartFrameOperation;
import entity.Tenant;
import forms.PaymentFrame;
import forms.TenantFrame;
import tableModal.TenantTableModal;

public class TenantController extends StandartFrameOperation {

	TenantDAO DAO;

	public TenantController(JFrame frame) {
		super(frame);
		try {
			DAO = new TenantDAO();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void actionSearchButton(String name, JTable table) {
		try {
			List<Tenant> list = null;

			if (name != null && name.trim().length() > 0)
				list = DAO.search(name);
			else
				list = DAO.readAll();

			TenantTableModal model = new TenantTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(String fio) {
			Tenant mt = new Tenant(fio);
			try {
				DAO.create(mt);
				((TenantFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
	}

	public void actionUpdateButton(String fioNew, Long id) {
			try {
				DAO.update(fioNew,id);
				((TenantFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
	}

	public void actionDeleteButton(Long id) {
		
			try {
				DAO.Delete(id);
				((TenantFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице" , "Ошибка", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
	}

	public TenantDAO getDAO() {
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
		((TenantFrame) getFrame()).refreshView();
	}
}
