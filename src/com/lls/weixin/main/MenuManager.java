package com.lls.weixin.main;
import com.lls.weixin.pojo.Button;  
import com.lls.weixin.pojo.CommonButton;  
import com.lls.weixin.pojo.ComplexButton;  
import com.lls.weixin.pojo.Menu;  
import com.lls.weixin.util.WeixinUtil;  

import net.sf.json.JSONObject;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
/** 
 * 菜单管理器类 
 *  
 */  
public class MenuManager {  
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);  
  
    public static void main(String[] args) {  
        // 第三方用户唯一凭证  
        String appId = "wxf976ad982d58b4dc";  
        // 第三方用户唯一凭证密钥  
        String appSecret = "b3d3101943546633b463cac6d4a16ad6";  
  
        // 调用接口获取access_token  
       // AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
       // if (null != at) {  
            // 调用接口创建菜单
        	String token = "weixin";
        	//System.out.println(at.getToken());
        	//createMenu(token);
        	deleteMenu(token);
        	createMenu(token);
      //  }  
    }  
  
    /** 
     * 组装菜单数据 
     *  
     * @return 
     */  
    private static Menu getMenu() {  
        CommonButton btn11 = new CommonButton();  
        btn11.setName("天气预报");  
        btn11.setType("click");  
        btn11.setKey("11");  
  
        CommonButton btn12 = new CommonButton();  
        btn12.setName("公交查询");  
        btn12.setType("click");  
        btn12.setKey("12");  
  
        CommonButton btn13 = new CommonButton();  
        btn13.setName("周边搜索");  
        btn13.setType("click");  
        btn13.setKey("13");  
  
        CommonButton btn14 = new CommonButton();  
        btn14.setName("历史上的今天");  
        btn14.setType("click");  
        btn14.setKey("14");  
  
        CommonButton btn21 = new CommonButton();  
        btn21.setName("歌曲点播");  
        btn21.setType("click");  
        btn21.setKey("21");  
  
        CommonButton btn22 = new CommonButton();  
        btn22.setName("经典游戏");  
        btn22.setType("click");  
        btn22.setKey("22");  
  
        CommonButton btn23 = new CommonButton();  
        btn23.setName("美女电台");  
        btn23.setType("click");  
        btn23.setKey("23");  
  
        CommonButton btn24 = new CommonButton();  
        btn24.setName("人脸识别");  
        btn24.setType("click");  
        btn24.setKey("24");  
  
        CommonButton btn25 = new CommonButton();  
        btn25.setName("聊天唠嗑");  
        btn25.setType("click");  
        btn25.setKey("25");  
  
        CommonButton btn31 = new CommonButton();  
        btn31.setName("Q友圈");  
        btn31.setType("click");  
        btn31.setKey("31");  
  
        CommonButton btn32 = new CommonButton();  
        btn32.setName("电影排行榜");  
        btn32.setType("click");  
        btn32.setKey("32");  
  
        CommonButton btn33 = new CommonButton();  
        btn33.setName("幽默笑话");  
        btn33.setType("click");  
        btn33.setKey("33"); 
        
        CommonButton btn34 = new CommonButton();  
        btn34.setName("公司主页");  
        btn34.setType("view");  
        btn34.setUrl("http://llsweixin.duapp.com");
  
        ComplexButton mainBtn1 = new ComplexButton();  
        mainBtn1.setName("生活助手");  
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });  
  
        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("休闲驿站");  
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });  
  
        ComplexButton mainBtn3 = new ComplexButton();  
        mainBtn3.setName("更多体验");  
        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33,btn34 });  
  
        /** 
         * 每个一级菜单都有二级菜单项<br> 
         *  
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
         */  
        Menu menu = new Menu();  
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
  
        return menu;  
    }  
    
    
    public static void deleteMenu(String token){
    	String url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    	 // 拼装创建菜单的url  
        String deleteUrl = url.replace("ACCESS_TOKEN", token); 
        JSONObject  jsonObject = WeixinUtil.httpRequest(deleteUrl, "GET", "");
        if (null != jsonObject) {  
            if (0 != jsonObject.getInt("errcode")) {  
                //jsonObject.getInt("errcode"); 
                log.error("删除菜单失败 errcode:{}"+jsonObject.getInt("errcode")+" errmsg:{}"+jsonObject.getString("errmsg"));  
            }  
        }  
    }
    
    public static void createMenu(String token){
    	   int result = WeixinUtil.createMenu(getMenu(), token);  
    	   
           // 判断菜单创建结果  
           if (0 == result)  
               log.info("菜单创建成功！");  
           else  
               log.info("菜单创建失败，错误码：" + result);  
    }
}  
