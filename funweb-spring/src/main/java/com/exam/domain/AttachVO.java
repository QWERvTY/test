package com.exam.domain;

import lombok.Data;

@Data
public class AttachVO {
	private String uuid, uploadPath, fileName;
	private String fileType;
	private int bNum;
	private boolean enabled;
	
}
