package cn.smbms.controller;


import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.BillService;
import cn.smbms.service.ProviderSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private ProviderSerivce providerSerivce;
    /**
     * 查询所有供应商的数据
     * 需要实现分页,模糊查找,和连表查询供应商
     * @param request
     * @return modelAndView
     */


       @RequestMapping(value = "list",method = RequestMethod.GET)
      public ModelAndView list(@RequestParam(value = "queryProductName",required = false)String                        queryProductName,
                               @RequestParam(value ="queryProviderId",required = false,defaultValue = "0")String queryProviderId,
                               @RequestParam(value ="queryIsPayment",required = false,defaultValue = "0")Integer queryIsPayment, HttpServletRequest request){


           //需要获得模糊查询返回的结果
           //判断是否为空,即选择或没有选择供应商名称
           Integer providerId = null;
           //如果不等于0 获取它的值
        if (!queryProviderId.equals("0")){
            //将它转换为int类型
            providerId=Integer.parseInt(queryProviderId);
        }
        if (queryIsPayment==0){
            queryIsPayment=null;
        }
            //获取商品的(联查)
       List<Bill> billList=billService.selectByNameOrPro(queryProductName,providerId,                                      queryIsPayment);
           System.out.println("哈哈哈哈哈哈哈哈哈哈哈"+queryProductName);
           for (Bill b: billList
                ) {
               System.out.println(b.getProviderName()+"*************************************"+b.getProductName());
           }
                        //获得供应商表
           List<Provider> providerlist=  providerSerivce.list();
            request.setAttribute("billList",billList);//所有的商品信息
            request.setAttribute("providerList",providerlist);//所有的供应商信息
           request.setAttribute("queryProviderId",providerId);
           request.setAttribute("queryProductName",queryProductName);
           request.setAttribute("queryIsPayment",queryIsPayment);
           ModelAndView modelAndView=new ModelAndView("billlist");
           return modelAndView;
       }





}
