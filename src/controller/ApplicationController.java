package controller;

import forms.MainFrame;
import forms.PaymentFrame;
import forms.ServiceFrame;
import forms.TenantFrame;
/**
 * Общий контроллер приложения
 * @author yauhe
 *
 */
public class ApplicationController {

	public static MainController mainController = new MainController(new MainFrame());
	public static ServiceController serviceController = new ServiceController(new ServiceFrame());
	public static TenantController tenantController = new TenantController(new TenantFrame());
	public static PaymentController paymentController = new PaymentController (new PaymentFrame());
	
	
	
	public static void main(String[] args) {
		mainController.showFrame();

	}

}
