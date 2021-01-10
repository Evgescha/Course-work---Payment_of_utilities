package controller;

import javax.swing.JFrame;

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
}
