package com.TT.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.TT.controller.InvoiceController;

public class InvoiceFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dateFld;
	private JTextField invoiceNumberFld;
	private JTextField toFld;
	private JTextField totalFld;
	private JLabel lblInvoiceName;
	private JTextField nameFld;

	public InvoiceFrame() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		dateFld = new JTextField();
		dateFld.setBounds(119, 36, 358, 20);
		contentPane.add(dateFld);
		dateFld.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setBounds(10, 39, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNum = new JLabel("Invoice number:");
		lblNum.setBounds(10, 64, 99, 14);
		contentPane.add(lblNum);
		
		invoiceNumberFld = new JTextField();
		invoiceNumberFld.setColumns(10);
		invoiceNumberFld.setBounds(119, 61, 358, 20);
		contentPane.add(invoiceNumberFld);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(10, 92, 46, 14);
		contentPane.add(lblTo);
		
		toFld = new JTextField();
		toFld.setColumns(10);
		toFld.setBounds(119, 89, 358, 20);
		contentPane.add(toFld);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 120, 46, 14);
		contentPane.add(lblTotal);
		
		totalFld = new JTextField();
		totalFld.setColumns(10);
		totalFld.setBounds(119, 117, 358, 20);
		contentPane.add(totalFld);
		
		JButton btnNewButton = new JButton("Create Invoice");
		btnNewButton.addActionListener(e -> {
			InvoiceController.createInvoice(nameFld.getText(), dateFld.getText(), invoiceNumberFld.getText(), toFld.getText(), Double.parseDouble(totalFld.getText()));
			JOptionPane.showMessageDialog(null, "Created!");
		});
		btnNewButton.setBounds(119, 172, 250, 23);
		contentPane.add(btnNewButton);
		
		lblInvoiceName = new JLabel("Invoice name:");
		lblInvoiceName.setBounds(10, 8, 99, 14);
		contentPane.add(lblInvoiceName);
		
		nameFld = new JTextField();
		nameFld.setColumns(10);
		nameFld.setBounds(119, 5, 358, 20);
		contentPane.add(nameFld);
		
		repaint();
		revalidate();
	}
}
