package lms.struts.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.components.Component;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;
import com.opensymphony.xwork2.util.ValueStack;

public class Pages extends Component {
	private HttpServletRequest request;
	private String pageIndex; // 当前页码
	private String pageCount; // 总页数
	private String styleClass; // 分页的样式
	private String url; // action的路径
	private String urlType; // 路径的类型，主要用于URL重写的扩展
	private String recordCount;// 总条数
	private String theme;
	public Pages(ValueStack vs, HttpServletRequest request) {
		super(vs);
		this.request = request;
	}

	@Override
	public boolean end(Writer writer, String body) {
		boolean result = super.start(writer);
		try {
			// 从ValueStack中取出数值
			Object obj = this.getStack().findValue(pageIndex);
			pageIndex = String.valueOf((Integer) obj);
			obj = this.getStack().findValue(pageCount);
			pageCount = String.valueOf((Integer) obj);
			obj = this.getStack().findValue(recordCount);
			recordCount = String.valueOf((Integer) obj);
			Integer totalInt = Integer.valueOf(pageCount);
			
			// 如果elementTotal为20的位数则总页数要减1
			if (totalInt > 1 && Integer.parseInt(recordCount) % Page.getEveryPage() == 0) {
				totalInt--;
				pageCount = totalInt + "";
			}
			
			StringBuilder str = new StringBuilder();
			Map<String, Object> cont = this.getStack().getContext();
			StrutsRequestWrapper req = (StrutsRequestWrapper) cont.get(StrutsStatics.HTTP_REQUEST);
			if (url == null || "".equals(url)) url = (String) req.getAttribute("javax.servlet.forward.request_uri");
		
			String pageNoStr = "?method=" + request.getParameter("method")+ "&pageSel=true";
			if (request.getParameter("key") != null && !"null".equals(request.getParameter("key"))) {
				pageNoStr += "&key=" + request.getParameter("key");
			}
			pageNoStr += "&pageIndex=";
			if ("dir".equals(urlType)) {// 当url的类型为目录类型时，比如http://localhost:8090/yongtree/page/1
				pageNoStr = "";
				if ("1".equals(pageIndex)) {// 第一页时
					if (url.lastIndexOf("/") != url.length() - 1) {
						if (url.lastIndexOf("1") == url.length() - 1) {// 如果有页码1，则去掉1
							url = url.substring(0, url.length() - 1);
						} else if (url.lastIndexOf("/") != url.length() - 1) {// 如果没有页码1，并且最后不是'/'时，加上'/'
							url = url + "/";
						}
					}
				} else {
					url = url.substring(0, url.lastIndexOf("/") + 1);
				}
			}
			// 下面这段处理主要是用来处理动态查询的参数，并拼接成url
			StringBuffer perUrl = new StringBuffer("");
			if (this.getParameters().size() != 0) {
				Iterator<?> iter = this.getParameters().keySet().iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					Object o = this.getParameters().get(key);
					perUrl.append("&").append(key).append("=").append(o);
				}
			}
			str.append("<div id=\"navPage\" class=\"pagination pagination-centered\"><ul>");
			String generateContent = "";
			if( Integer.parseInt(recordCount)> Page.getEveryPage()){
				if (pageIndex.equals("1")) {
					generateContent = generateFirstPage(pageNoStr);
				}else if(pageIndex.equals(pageCount)){
					generateContent = generateLastPage(pageNoStr);
				}else {
					generateContent = generateOtherPage(pageNoStr);
				}
			}
			str.append(generateContent);
			str.append("</ul></div>");
			writer.write(str.toString());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private String generateFirstPage(String pageNoStr){
		StringBuffer pageContent = new StringBuffer();
		pageContent.append("<li><a style=\"cursor:default;color:gray\" href=\"javascript:void(0)\">首页</a></li>");
		Integer maxPage = Integer.parseInt(pageCount);
		for (int i = 1; i <= maxPage; i++) {
			if(i == 1)
				pageContent.append("<li><a style=\"cursor:default;color:gray\" href=\"javascript:void(0)\">1</a></li>");
			else
				pageContent.append("<li><a href=\""+url+pageNoStr+i+"\">"+i+"</a></li>");
		}
		pageContent.append("<li><a href=\""+url+pageNoStr+pageCount+"\">尾页</a></li>");
		return pageContent.toString();
	}
	private String generateLastPage(String pageNoStr){
		StringBuffer pageContent = new StringBuffer();
		pageContent.append("<li><a href=\""+url+pageNoStr+"1\">首页</a></li>");
		Integer maxPage = Integer.parseInt(pageCount);
		for (int i = 1; i <= maxPage; i++) {
			if(i == maxPage)
				pageContent.append("<li><a style=\"cursor:default;color:gray\" href=\"javascript:void(0)\">"+pageCount+"</a></li>");
			else
				pageContent.append("<li><a href=\""+url+pageNoStr+i+"\">"+i+"</a></li>");
		}
		pageContent.append("<li><a style=\"cursor:default;color:gray\" href=\"javascript:void(0)\">尾页</a></li>");
		return pageContent.toString();
	}
	private String generateOtherPage(String pageNoStr){
		StringBuffer pageContent = new StringBuffer();
		pageContent.append("<li><a href=\""+url+pageNoStr+"1\">首页</a></li>");
		Integer maxPage = Integer.parseInt(pageCount);
		Integer index = Integer.parseInt(pageIndex);
		for (int i = 1; i <= maxPage; i++) {
			if (i == index) 
				pageContent.append("<li><a style=\"cursor:default;color:gray\" href=\"javascript:void(0)\">"+i+"</a></li>");
			else
				pageContent.append("<li><a href=\""+url+pageNoStr+i+"\">"+i+"</a></li>");
		}
		pageContent.append("<li><a href=\""+url+pageNoStr+pageCount+"\">尾页</a></li>");
		
		return pageContent.toString();
	}
	//[start] get and set methods
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}


	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	
	//[end]
}
