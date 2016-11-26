package com.TT.utils;

import java.io.File;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class FileUtil {

	public static void checkDirectory(String path) {
		File file = new File(path);

		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public static XWPFDocument replaceTextInTable(XWPFDocument doc, String var, String value) {
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

	public static XWPFDocument replacePlainText(XWPFDocument doc, String var, String value) {
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
