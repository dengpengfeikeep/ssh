package com.baidu.ssh.util;

import java.io.File;
import java.util.UUID;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class FileUploadUtil {
	public static final String suffix = "_small";

	/**
	 * @Description 返回图片在服务器上的路径
	 * @param file
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(File file, String fileName)
			throws Exception {
		String uuid = UUID.randomUUID().toString();
		// 根据文件全名获得拓展名fileType
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		fileName = uuid + fileType;
		String path = ServletActionContext.getServletContext().getRealPath(
				"/upload");
		File targetFile = new File(path, fileName);
		FileUtils.copyFile(file, targetFile);

		// 缩略图是在文件后面加上_small
		String smallImg = uuid + suffix + fileType;
		File smallTargetFile = new File(path, smallImg);
		// 生成缩略图
		Thumbnails.of(targetFile).scale(0.4f).toFile(smallTargetFile);
		// 这里要加/ssh是因为相对路径与绝对路径的问题(我曹)
		return "/ssh/upload/" + fileName;
	}

	/**
	 * 删除文件
	 * @param picr
	 */
	public static void deleteFile(String pic) {
		String path = ServletActionContext.getServletContext().getRealPath("/")
				+ pic;
		File file = new File(path);
		if (file.exists())
			file.delete();

		path = ServletActionContext.getServletContext().getRealPath("/")
				+ pic.substring(0, pic.indexOf(".")) + FileUploadUtil.suffix
				+ pic.substring(pic.indexOf("."));
		file = new File(path);
		if (file.exists())
			file.delete();
	}
}
