package zjx.gen.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import zjx.gen.common.xss.XssHttpServletRequestWrapper;
import zjx.gen.service.SysGeneratorService;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午9:12:58
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;

	/**
	 * 生成代码,多个表用逗号隔开
	 */
	@RequestMapping("/code")
	public String code(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取表名，不进行xss过滤
		HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
		String tables = orgRequest.getParameter("tables");
		String[] tableNames = null;
		if (tables.contains(",")) {
			tableNames = tables.split(",");
			// String[] tableNames = new Gson().fromJson(tables, String[].class);
			// tableNames = JSONArray.parseObject(tables, String[].class);
		} else {
			tableNames = new String[] { tables };
		}
		byte[] data = sysGeneratorService.generatorCode(tableNames);

		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
		return "success";
	}
}
