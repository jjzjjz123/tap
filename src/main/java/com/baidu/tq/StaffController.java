package com.baidu.tq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StaffController {
	@RequestMapping("getstafflist")
	@ResponseBody
	public PageBean getstafflist() {
		PageBean pageBean=new PageBean();
		pageBean.setTotal(100);
		Staff staff1=new Staff("z", "z", "11111111111", "0", "0", "z", "z", null);
		Staff staff2=new Staff("a", "a", "11111111111", "0", "0", "a", "a", null);
		List<Staff> list=new ArrayList<Staff>();
		list.add(staff1);
		list.add(staff2);
		pageBean.setRows(list);
		return pageBean;
	}

}
