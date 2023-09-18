ackage com.myspring.pro27.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
// 
public class ViewNameInterceptor extends HandlerInterceptorAdapter {
	@Override
	// 而⑦듃濡ㅻ윭 �룄�떖�븯湲� �쟾�뿉 �떎�뻾�릺�뒗 硫붿꽌�뱶 : preHandle
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			// 誘몃━ 酉� �씠由� 媛��졇�삤�뒗 遺�遺�
			String viewName = getViewName(request);
			// request �씤�뒪�꽩�뒪�뿉 �떞湲�. 
			request.setAttribute("viewName", viewName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
		}
		return fileName;
	}
}
