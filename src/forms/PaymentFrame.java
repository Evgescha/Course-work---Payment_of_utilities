package forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.ApplicationController;
import entity.Payment;
import entity.Service;
import entity.Tenant;
/**
 * форма отображения продуктов
 * @author admin
 *
 */
public class PaymentFrame extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JComboBox<Tenant> comboBoxTenant;
	private JComboBox<Service> comboBoxService;
	private JDateChooser dateChooser;
	private JTextField textField_2;

	public PaymentFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}
		});
		setTitle("Учет оплаты коммунальных услуг");
		setBounds(100, 100, 823, 428);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Введите название услуги для поиска");
		panel.add(lblNewLabel_4);

		textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);

		JButton btnNewButton_1 = new JButton("Поиск");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSearchButton();
			}
		});
		panel.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(3, 3, 3, 3));
		getContentPane().add(panel_1, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Квартиросъемщик");

		textField_1 = new JTextField();
		textField_1.setColumns(10);
	

		JButton btnNewButton_2 = new JButton("Обновить");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionUpdateButton();
			}
		});

		JButton btnNewButton = new JButton("Добавить");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCreateButton();
			}
		});

		JButton btnNewButton_3 = new JButton("Удалить");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDeleteButton();
			}
		});
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		panel_1.add(lblNewLabel);
		
		comboBoxTenant = new JComboBox();
		panel_1.add(comboBoxTenant);
		
		JLabel lblNewLabel_1_1 = new JLabel("Услуга");
		panel_1.add(lblNewLabel_1_1);
		
		comboBoxService = new JComboBox();
		panel_1.add(comboBoxService);
		
		JLabel lblNewLabel_1_2 = new JLabel("Сумма");
		panel_1.add(lblNewLabel_1_2);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Пеня");
		panel_1.add(lblNewLabel_1_2_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_1.add(textField_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Дата оплаты");
		panel_1.add(lblNewLabel_1_2_1);
		
		 dateChooser = new JDateChooser();
		panel_1.add(dateChooser);
		panel_1.add(btnNewButton);
		panel_1.add(btnNewButton_2);
		panel_1.add(btnNewButton_3);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actionTableMouseClicked();
			}
		});
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		 
	}

	private void actionDeleteButton() {
		if (table.getRowCount() > 0 && table.getSelectedRowCount() > 0) {
			int row = table.getSelectedRow();
			String id = table.getModel().getValueAt(row, 0).toString();
			ApplicationController.paymentController.actionDeleteButton(Long.parseLong(id));
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				Payment entity = (Payment) table.getModel().getValueAt(row, 99);
				
				Tenant tenant = entity.getTenant();
				Service service = entity.getService();
				
				comboBoxTenant.getModel().setSelectedItem(tenant);
				comboBoxService.getModel().setSelectedItem(service);
				textField_1.setText(entity.getSumm()+"");
				textField_2.setText(entity.getFine()+"");
				dateChooser.setDate(entity.getDates());
			} catch (Exception e) {
			}
	}

	private void actionUpdateButton() {
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		if(allFieldIsRight()) {
		ApplicationController.paymentController.actionUpdateButton(
				id,
				new Date(dateChooser.getDate().getTime()),
				(Tenant)comboBoxTenant.getSelectedItem(),
				(Service)comboBoxService.getSelectedItem(),
				Integer.parseInt(textField_1.getText()),
				Integer.parseInt(textField_2.getText())
				);
		}
	}

	private void actionCreateButton() {
		if(allFieldIsRight()) 
		ApplicationController.paymentController.actionCreateButton(
				new Date(dateChooser.getDate().getTime()),
				(Tenant)comboBoxTenant.getSelectedItem(),
				(Service)comboBoxService.getSelectedItem(),
				Integer.parseInt(textField_1.getText()),
				Integer.parseInt(textField_2.getText())
				);
	}

	private void actionSearchButton() {
		ApplicationController.paymentController.actionSearchButton(textField.getText().trim(), table);
		refreshComboBoxes();
	}

	public void refreshView() {
		
		ApplicationController.paymentController.actionSearchButton("", table);
		refreshComboBoxes();
	}
	public void refreshComboBoxes() {
		try {
			comboBoxTenant.setModel(
					new DefaultComboBoxModel(ApplicationController.tenantController.getDAO().readAll().toArray()));
			comboBoxService.setModel(
					new DefaultComboBoxModel(ApplicationController.serviceController.getDAO().readAll().toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void back() {
		ApplicationController.paymentController.back();
	}
	private boolean isHasNumbers() {
		Pattern p = Pattern.compile("([0-9])");
		Matcher m = p.matcher(textField.getText());

		if(m.find()){
		    JOptionPane.showMessageDialog(this, "Ошибка. Поле содержат Цифры.");
		    return true;
		}
		return false;
	}
	private boolean isEmpty() {
		boolean flag = false;
		if (textField_1.getText().length()<=0) flag=true;
		if (textField_2.getText().length()<=0) flag=true;
		try {new Date(dateChooser.getDate().getTime());}catch (Exception e) {flag=true;e.printStackTrace();}
		if(flag) JOptionPane.showMessageDialog(this, "Проверьте корректное заполнение всех полей.");
		return flag;
	}
	private boolean allFieldIsRight() {
		return !isEmpty() && !isHasNumbers();
	}
}
