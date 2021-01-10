package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;

import defaultOperation.StandartFrameOperation;

public class MainController extends StandartFrameOperation {

	public MainController(JFrame frame) {
		super(frame);
	}

	public void btnTenantAction() {
		switchVisible();
		ApplicationController.tenantController.switchVisible();
	}

	public void btnServiceAction() {
		switchVisible();
		ApplicationController.serviceController.switchVisible();

	}

	public void btnPaymentAction() {
		switchVisible();
		ApplicationController.paymentController.switchVisible();
	}

	public void back() {

	}

	public void saveToFile(JTable table) {
		try {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();

				int ColC = table.getColumnCount(); // Определяем кол-во столбцов
				int ItemC = table.getRowCount(); // и элементов (строк)
				StringBuilder sb;
				FileWriter fw = null;
				fw = new FileWriter(fileToSave);
				for (int i = 0; i < ItemC; i++) { // проходим все строки
					sb = new StringBuilder();
					for (int j = 0; j < ColC; j++) { // собираем одну строку из множества столбцов
						sb.append(table.getValueAt(i, j).toString());
						if (j < ColC - 1)
							sb.append(',');
						if (j == ColC - 1)
							sb.append("\r\n");
					}
					fw.write(sb.toString()); // записывем собранную строку в файл
					fw.flush();
				}
				fw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
