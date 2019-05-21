package com.exam.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SampleDTOList {
	private List<SampleDTO> list;
	
	public SampleDTOList() {
		list = new ArrayList<SampleDTO>();
	}

	@Override
	public String toString() {
		return "SampleDTOList [list=" + list + "]";
	}

}
