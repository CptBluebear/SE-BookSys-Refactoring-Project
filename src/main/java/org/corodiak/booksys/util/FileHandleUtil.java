package org.corodiak.booksys.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/*
 * ÷������ ó���� ���� ���� �ڵ鷯
 */
public class FileHandleUtil
{
	
	/*
	 * ÷������ ���� ��ο� ����
	 */
	public static String saveFile(MultipartFile multipartFile, String saveDir)
	{
		try
		{
			String filename = generateFileName(multipartFile.getOriginalFilename()) + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			File file = new File(saveDir, filename);
			
			multipartFile.transferTo(file);
			return filename;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * ÷������ ������ �̸����� ����
	 */
	private static String generateFileName(String originalFilename)
	{
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
		return UUID.randomUUID().toString()+dateFormat.format(date);
	}
}
