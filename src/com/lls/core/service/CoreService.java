package com.lls.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lls.core.message.resp.Article;
import com.lls.core.message.resp.Music;
import com.lls.core.message.resp.MusicMessage;
import com.lls.core.message.resp.NewsMessage;
import com.lls.core.message.resp.TextMessage;
import com.lls.core.util.MessageUtil;
import com.lls.core.util.TulingApiProcess;

/**
 * 核心服务类
 * 
 */
public class CoreService {
	
	private static Logger log = LoggerFactory.getLogger(CoreService.class);
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		log.info("=====processRequest==========");
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			String result = "";

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");

				// 创建图文消息
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				//newsMessage.setFuncFlag(0);

				List<String> str = new ArrayList<String>();
				List<Article> articleList = new ArrayList<Article>();
				// 单图文消息
				if ("概要".equals(content)) {
					Article article = new Article();
					article.setTitle("欢迎来到芥末花生屋");
					article.setDescription("芥末花生屋主要是用来测试微信公众号开发的，因为是订阅号，所以目前不支持自定义菜单");
					article.setPicUrl("http://pic.qiantucdn.com/58pic/15/15/43/34x58PICbAJ_1024.jpg!/format/webp");
					article.setUrl("http://xiatiaohcx.tunnel.qydev.com/test/index.jsp");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}

				else if ("新闻".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("标准的消息模板四条消息首条");
					article1.setDescription("");
					article1.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488993812754&di=18176a2234f0a6764ecddcd2b2f4e48c&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140505%2F6277662_212301636124_2.jpg");
					article1.setUrl("http://xiatiaohcx.tunnel.qydev.com/test/index.jsp");

					Article article2 = new Article();
					article2.setTitle("标准的消息模板四条消息第二条");
					article2.setDescription("");
					article2.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488993812754&di=18176a2234f0a6764ecddcd2b2f4e48c&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140505%2F6277662_212301636124_2.jpg");
					article2.setUrl("http://xiatiaohcx.tunnel.qydev.com/test/index.jsp");

					Article article3 = new Article();
					article3.setTitle("标准的消息模板四条消息第三条");
					article3.setDescription("");
					article3.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488993812754&di=18176a2234f0a6764ecddcd2b2f4e48c&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140505%2F6277662_212301636124_2.jpg");
					article3.setUrl("http://xiatiaohcx.tunnel.qydev.com/test/index.jsp");
					
					Article article4 = new Article();
					article4.setTitle("标准的消息模板四条消息第四条");
					article4.setDescription("");
					article4.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488993812754&di=18176a2234f0a6764ecddcd2b2f4e48c&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140505%2F6277662_212301636124_2.jpg");
					article4.setUrl("http://xiatiaohcx.tunnel.qydev.com/test/index.jsp");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					articleList.add(article4);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				
				//音乐信息
				else if("听歌".equals(content)){
					Music m = new Music();
					m.setDescription("杨宗纬/张碧晨");
					m.setTitle("凉凉");
					
					m.setMusicUrl("https://y.qq.com/n/yqq/song/001Nl0W80sBSwJ.html");
					m.setHQMusicUrl("https://y.qq.com/n/yqq/song/001Nl0W80sBSwJ.html");
					MusicMessage mm = new MusicMessage();
					mm.setMusic(m);
					mm.setFromUserName(toUserName);
					mm.setToUserName(fromUserName);
					//mm.setFuncFlag(0);
					mm.setCreateTime(new Date().getTime());
					mm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
					respMessage = MessageUtil.musicMessageToXml(mm);
				}else{
					result = new TulingApiProcess().getTulingResult(content);
					TextMessage mm = new TextMessage();
					mm.setContent(result);
					mm.setFromUserName(toUserName);
					mm.setToUserName(fromUserName);
					
					mm.setCreateTime(new Date().getTime());
					mm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					respMessage = MessageUtil.textMessageToXml(mm);
					
					
				}
			}
			// 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
            	textMessage.setContent("您给我发送的是图片消息！");
            	respMessage = MessageUtil.textMessageToXml(textMessage);
            } // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
            	textMessage.setContent("您给我发送的是地理位置消息 ！");
            	respMessage = MessageUtil.textMessageToXml(textMessage);
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
            	textMessage.setContent("您给我发送的是链接消息  ！");
            	respMessage = MessageUtil.textMessageToXml(textMessage);
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
            	textMessage.setContent("您给我发送的是音频消息 ！");
            	respMessage = MessageUtil.textMessageToXml(textMessage);
            }  
			
			 // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { 
            	 // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { 
                	
                	textMessage.setToUserName(fromUserName);
        			textMessage.setFromUserName(toUserName);
        			textMessage.setCreateTime(new Date().getTime());
        			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义			
        			StringBuffer contentMsg = new StringBuffer();  
        			contentMsg.append("欢迎访问芥末花生屋").append("\n");  
        			contentMsg.append("您好，芥末花生屋，请回复数字选择服务：").append("\n\n");  
        			contentMsg.append("新闻").append("\n");  
        			contentMsg.append("概要").append("\n");  
        			contentMsg.append("听歌").append("\n");  
        			//contentMsg.append("4  歌曲点播").append("\n");  
        			//contentMsg.append("5  经典游戏").append("\n");  
        			//contentMsg.append("6  美女电台").append("\n");  
        			//contentMsg.append("7  人脸识别").append("\n"); 
        			//contentMsg.append("8  聊天唠嗑").append("\n");
        			//contentMsg.append("9  电影排行榜").append("\n");
        			//contentMsg.append("10 Q友圈").append("\n\n");  
        			contentMsg.append("点击查看 <a href=\"http://xiatiaohcx.tunnel.qydev.com/test/index.jsp\">帮助手册</a>");  
        			log.info("=====processRequest==========");
        			textMessage.setContent(contentMsg.toString());
        			// 将文本消息对象转换成xml字符串
        			respMessage = MessageUtil.textMessageToXml(textMessage);
                	
                	
                	
                	//textMessage.setContent("谢谢您的关注！ ！");
                	respMessage = MessageUtil.textMessageToXml(textMessage);
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                	TextMessage textMessage2 = new TextMessage();
    				textMessage2.setToUserName(fromUserName);
    				textMessage2.setFromUserName(toUserName);
    				textMessage2.setCreateTime(new Date().getTime());
    				textMessage2.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
    				textMessage2.setFuncFlag(0);
    				String eventKey = requestMap.get("EventKey");
    				if(eventKey.equals("11")){
    					textMessage2.setContent("你点击的是天气预报");
    				}else{
    					textMessage2.setContent("其他自定义菜单");
    				}
    				respMessage = MessageUtil.textMessageToXml(textMessage2);
    				log.debug("==============自定义菜单自定义菜单===============！！");
                }  
            }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
	
	private String processEvent(){
		return "";
	}
}