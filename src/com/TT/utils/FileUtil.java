package com.TT.utils;

import java.io.File;

public class FileUtil {

	public static void checkDirectory(String path) {
		File file = new File(path);

		if (!file.exists()) {
			file.mkdir();
		}
	}
	
}
