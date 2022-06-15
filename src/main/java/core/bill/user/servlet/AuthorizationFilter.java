package core.bill.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.bill.common.util.CustomUtil;
import core.bill.user.model.GroupResourceDTO;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserRoleDTO;

@WebFilter(
		 urlPatterns = "/faces/bill/*",
	        filterName = "AuthorizationFilter",
	        description = "Filter all request URLs"  
	        //dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}
	        //initParams = @WebInitParam(name = "fileTypes", value = "doc;xls;zip;txt;jpg;png;gif")   
		)
public class AuthorizationFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

	FilterConfig fc;
	int usbInt = 0;
	String url = "";
	String realUrl = "";
	List<UserRoleDTO> userRoleDtos = null;

	boolean flag = false;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		fc = filterConfig;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			// HttpSession session = req.getSession(false);

			url = req.getRequestURL().toString();

			String[] local = url.split(req.getContextPath());
			String sub = local[0] + "/" + req.getContextPath();
			usbInt = sub.length();
			realUrl = "/" + url.substring(usbInt);

			System.out.println(realUrl);

			UserDTO auth = (UserDTO) req.getSession().getAttribute("usr");

			if (auth != null) {

				userRoleDtos = CustomUtil.convertSetToList(auth.getUserRoleDTO());
				flag = isAuthorized(userRoleDtos);

				if (flag) {
					flag = false;
					chain.doFilter(req, response);

				} else {
					res.sendRedirect(req.getContextPath() + "/faces/accessDeny.xhtml");
				}

			} else {

				res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
		}

	}

	// --------------------------------
	public Boolean isAuthorized(List<UserRoleDTO> userRoleDtos) {
		Boolean flag = Boolean.FALSE;

		try {

			if (userRoleDtos != null) {
				for (UserRoleDTO userRoleDto : userRoleDtos) {
					for (GroupResourceDTO groupResourceDTO : userRoleDto.getGroupDTO().getGroupResourceDTOs()) {

						if (groupResourceDTO.getResourceDTO().getUrl().equals(realUrl)) {
							return Boolean.TRUE;
						}
					}

				}

			} else {
				return Boolean.FALSE;
			}

		} catch (Exception e) {
			logger.error(e.toString());
			return flag;
		}

		return flag;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
