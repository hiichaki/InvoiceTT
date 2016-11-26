package com.TT.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.TT.utils.FileUtil;

public class InvoiceController {

	public static void createInvoice(String name, String date, String invoiceNumber, String to, double total) {
		XWPFDocument doc;
		try {
			doc = new XWPFDocument(OPCPackage.open("D://invoice template.docx"));

			doc = FileUtil.replacePlainText(doc, "${date}", date);
			doc = FileUtil.replacePlainText(doc, "${ref}", invoiceNumber);
			doc = FileUtil.replacePlainText(doc, "${to}", to);
			doc = FileUtil.replaceTextInTable(doc, "${total}", "£" + new DecimalFormat("##.##").format(total));
			doc = FileUtil.replaceTextInTable(doc, "${vat}", "£" + new DecimalFormat("##.##").format(total * 0.2));
			doc = FileUtil.replaceTextInTable(doc, "${grand}", "£" + new DecimalFormat("##.##").format(total + total * 0.2));
			
			String path = "/invoices/";
			FileUtil.checkDirectory(path);
			
			doc.write(new FileOutputStream(path + name + ".docx"));
		} catch (InvalidFormatException | IOException e) {
			System.out.println("fail");
			e.printStackTrace();
		}
	}

	
}
