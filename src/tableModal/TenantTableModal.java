package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Tenant;
public class TenantTableModal extends AbstractTableModel {

	public static final int COL_ID = 0;
	public static final int COL_FIO = 1;

	private final String[] columnNames = {"ИД","ФИО Квартиросъемщика"};
	private List<Tenant> list;

	public TenantTableModal(List<Tenant> list) {
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

		Tenant temp = list.get(row);
		switch (col) {
		case COL_ID:			
			return temp.getId();
		case COL_FIO:			
			return temp.getFio();
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
