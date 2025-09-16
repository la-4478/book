package com.bookshop01.admin.order.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.admin.goods.service.AdminGoodsService;
import com.bookshop01.admin.order.service.AdminOrderService;
import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;
import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.mypage.controller.MyPageController;
import com.bookshop01.mypage.service.MyPageService;
import com.bookshop01.order.vo.OrderVO;

@Controller("adminOrderController")
@RequestMapping(value="/admin/order")
public class AdminOrderControllerImpl extends BaseController  implements AdminOrderController{
	@Autowired
	private AdminOrderService adminOrderService;
	
	@Override
	@RequestMapping(value="/adminOrderMain.do" ,method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView adminOrderMain(@RequestParam Map<String, String> dateMap,
	                                   HttpServletRequest request, HttpServletResponse response)  throws Exception {
	    String viewName=(String)request.getAttribute("viewName");
	    ModelAndView mav = new ModelAndView("/common/layout");
	    mav.addObject("body", "/WEB-INF/views"+ viewName +".jsp");

	    String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
	    String section = dateMap.get("section");
	    String pageNum = dateMap.get("pageNum");
	    String beginDate=null,endDate=null;

	    String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
	    beginDate=tempDate[0];
	    endDate=tempDate[1];
	    dateMap.put("beginDate", beginDate);
	    dateMap.put("endDate", endDate);

	    HashMap<String,Object> condMap=new HashMap<String,Object>();
	    if(section== null) {
	        section = "1";
	    }
	    if(pageNum== null) {
	        pageNum = "1";
	    }
	    int sectionInt = Integer.parseInt(section);
	    int pageNumInt = Integer.parseInt(pageNum);
	    int offset = (sectionInt - 1) * 100 + (pageNumInt - 1) * 10;
	    int limit = 10;
	    condMap.put("offset", offset);
	    condMap.put("limit", limit);

	    condMap.put("beginDate",beginDate);
	    condMap.put("endDate", endDate);

	    List<OrderVO> newOrderList=adminOrderService.listNewOrder(condMap);
	    mav.addObject("newOrderList",newOrderList);
	    
		
		String beginDate1[]=beginDate.split("-");
		String endDate2[]=endDate.split("-");
		mav.addObject("beginYear",beginDate1[0]);
		mav.addObject("beginMonth",beginDate1[1]);
		mav.addObject("beginDay",beginDate1[2]);
		mav.addObject("endYear",endDate2[0]);
		mav.addObject("endMonth",endDate2[1]);
		mav.addObject("endDay",endDate2[2]);
		
		mav.addObject("section", section);
		mav.addObject("pageNum", pageNum);
		return mav;
		
	}
	
	@Override
	@RequestMapping(value="/modifyDeliveryState.do" ,method={RequestMethod.POST})
	public ResponseEntity modifyDeliveryState(@RequestParam Map<String, String> deliveryMap, 
			                        HttpServletRequest request, HttpServletResponse response)  throws Exception {
		adminOrderService.modifyDeliveryState(deliveryMap);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
		
	}
	
	
	@Override
	@RequestMapping(value="/detailOrder.do" ,method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView orderDetail(@RequestParam("order_id") int order_id, 
			                      HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView("/common/layout");
		mav.addObject("body", "WEB-INF/views" + viewName +".jsp");
		Map orderMap =adminOrderService.orderDetail(order_id);
		mav.addObject("orderMap", orderMap);
		return mav;
	}
	
}