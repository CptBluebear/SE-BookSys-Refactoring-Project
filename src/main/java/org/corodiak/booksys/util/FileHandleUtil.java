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
 * 첨부파일 처리를 위한 파일 핸들러
 */
public class FileHandleUtil
{
	
	/*
	 * 첨부파일 지정 경로에 저장
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
	 * 첨부파일 고유한 이름으로 변경
	 */
	private static String generateFileName(String originalFilename)
	{
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
		return UUID.randomUUID().toString()+dateFormat.format(date);
	}
}
