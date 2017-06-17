package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.IGoodStyleDao;
import com.impl.GoodStyleServiceImpl;
import com.pojo.GoodInfo;
import com.pojo.GoodStyle;
import com.service.IGoodInfoService;
import com.service.IGoodStyleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

@Controller
@RequestMapping("gc")
public class GoodController {
	
	private IGoodInfoService goodInfoServiceImpl;
	private IGoodStyleService goodStyleServiceImpl;
	/**
	 * ������ƷID��ȡ��Ʒ����
	 * @param goodId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="getGood",method=RequestMethod.GET)
	public void getGoodById(Integer goodId,HttpServletResponse response) throws IOException{
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		GoodInfo goodInfo = goodInfoServiceImpl.findByGoodId(goodId);
		PrintWriter writer = response.getWriter();
		if (goodInfo!=null) {
			JSONObject jsonObject = JSONObject.fromObject(goodInfo);
//			String json = new String(jsonObject.toString().getBytes("iso-8859-1"), "utf-8");
			writer.print(jsonObject.toString());
		}else{
			writer.print("û�и�������");
		}
		writer.flush();
		writer.close();
	}
	/**
	 * ��ȡ������Ʒ����
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="getGoodtype")
	public void getGoodStyle(HttpServletResponse response) throws IOException{
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		List<GoodStyle> types = goodStyleServiceImpl.getAllType();
		PrintWriter writer = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(types);
		writer.println(jsonArray.toString());
	}
	
	public IGoodStyleService getGoodStyleServiceImpl() {
		return goodStyleServiceImpl;
	}
	public void setGoodStyleServiceImpl(IGoodStyleService goodStyleServiceImpl) {
		this.goodStyleServiceImpl = goodStyleServiceImpl;
	}
	public IGoodInfoService getGoodInfoServiceImpl() {
		return goodInfoServiceImpl;
	}
	public void setGoodInfoServiceImpl(IGoodInfoService goodInfoServiceImpl) {
		this.goodInfoServiceImpl = goodInfoServiceImpl;
	}
	
}
