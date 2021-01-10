package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Service;
public class ServiceTableModal extends AbstractTableModel {

	public static final int COL_ID = 0;
	public static final int COL_NAME = 1;

	private final String[] columnNames = {"ИД","Наименование услуги"};
	private List<Service> list;

	public ServiceTableModal(List<Service> list) {
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

		Service temp = list.get(row);
		switch (col) {
		case COL_ID:			
			return temp.getId();
		case COL_NAME:			
			return temp.getName();
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
