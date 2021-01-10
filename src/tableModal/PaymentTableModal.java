package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Payment;
public class PaymentTableModal extends AbstractTableModel {

	public static final int COL_ID = 0;
	public static final int COL_DATES = 1;
	public static final int COL_TENANT = 2;
	public static final int COL_SERVICE = 3;
	public static final int COL_SUMM = 4;
	public static final int COL_FINE = 5;

	private final String[] columnNames = {"ИД","Дата оплаты","Квартиросъемщи","Услуга","Сумма","Пеня"};
	private List<Payment> list;

	public PaymentTableModal(List<Payment> list) {
		this.list = list;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Payment temp = list.get(row);
		switch (col) {
		case COL_ID:			
			return temp.getId();
		case COL_DATES:			
			return temp.getDates();
		case COL_TENANT:			
			return temp.getTenant();
		case COL_SERVICE:			
			return temp.getService();
		case COL_SUMM:			
			return temp.getSumm();
		case COL_FINE:			
			return temp.getFine();
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
