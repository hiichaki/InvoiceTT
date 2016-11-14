package com.TT.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.TT.utils.FileUtil;

public class InvoiceController {

	public static void createInvoice(String name, String date, String invoiceNumber, String to, double total) {
		XWPFDocument doc;
		try {
			doc = new XWPFDocument(OPCPackage.open("D://invoice template.docx"));

			doc = replace(doc, "${date}", date);
			doc = replace(doc, "${ref}", invoiceNumber);
			doc = replace(doc, "${to}", to);
			doc = replaceTable(doc, "${total}", "£" + new DecimalFormat("##.##").format(total));
			doc = replaceTable(doc, "${vat}", "£" + new DecimalFormat("##.##").format(total * 0.2));
			doc = replaceTable(doc, "${grand}", "£" + new DecimalFormat("##.##").format(total + total * 0.2));
			
			String path = "/invoices/";
			FileUtil.checkDirectory(path);
			
			doc.write(new FileOutputStream(path + name + ".docx"));
		} catch (InvalidFormatException | IOException e) {
			System.out.println("fail");
			e.printStackTrace();
		}
	}

	private static XWPFDocument replaceTable(XWPFDocument doc, String var, String value) {
		for (XWPFTable tbl : doc.getTables()) {
			for (XWPFTableRow row : tbl.getRows()) {
				for (XWPFTableCell cell : row.getTableCells()) {
					for (XWPFParagraph p : cell.getParagraphs()) {
						for (XWPFRun r : p.getRuns()) {
							String text = r.getText(0);
							if (text.contains(var)) {
								text = text.replace(var, value);
								r.setText(text, 0);
							}
						}
					}
				}
			}
		}
		return doc;
	}

	private static XWPFDocument replace(XWPFDocument doc, String var, String value) {
		for (XWPFParagraph p : doc.getParagraphs()) {
			List<XWPFRun> runs = p.getRuns();
			if (runs != null) {
				for (XWPFRun r : runs) {
					String text = r.getText(0);
					if (text != null && text.contains(var)) {
						text = text.replace(var, value);

						r.setText(text, 0);
					}
				}
			}
		}
		return doc;
	}
}
