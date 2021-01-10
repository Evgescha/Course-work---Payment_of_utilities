package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.ApplicationController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Vty. ds,jhf ajhv
 * @author ya
 *
 */
public class MainFrame extends JFrame{
	/**
	 * Конструктор
	 */
	public MainFrame() {
		initialize();
	}

	private void initialize() {
	
	setBounds(100, 100, 344, 147);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(null);
	
	JButton btnNewButton = new JButton("Учет оплаты коммунальных услуг");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		btnShowPaymentAction();
		}

	});
	btnNewButton.setBounds(10, 11, 308, 23);
	getContentPane().add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("Квартиросъемщик");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			btnShowTenaneAction();
		}

	});
	btnNewButton_1.setBounds(10, 45, 308, 23);
	getContentPane().add(btnNewButton_1);
	
	JButton btnNewButton_1_1 = new JButton("Услуги");
	btnNewButton_1_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			btnShowServiceAction();
		}

	});
	btnNewButton_1_1.setBounds(10, 74, 308, 23);
	getContentPane().add(btnNewButton_1_1);
	}
	/**
	 * Открыто форму учета
	 */
	private void btnShowPaymentAction() {
		ApplicationController.mainController.btnPaymentAction();
		
	}	/**
	 * Открыто форму квартирантов
	 */
	private void btnShowTenaneAction() {
		ApplicationController.mainController.btnTenantAction();
		
	}	/**
	 * Открыто форму услуг
	 */
	private void btnShowServiceAction() {
		 ApplicationController.mainController.btnServiceAction();
		
	}

}
