
package com.tomowork.oa.manage.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tomowork.oa.foundation.domain.Area;
import com.tomowork.oa.foundation.service.AreaService;
import com.tomowork.oa.manage.common.web.BaseController;

/**
 * 区域Controller
 */
@Controller
@RequestMapping (value = "${adminPath}/sys/area")
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;

	@ModelAttribute ("area")
	public Area get(@RequestParam (required = false) Long id) {
		if (id != null) {
			return areaService.getObjById(id);
		} else {
			return new Area();
		}
	}

	@RequiresUser
	@ResponseBody
	@RequestMapping (value = "treeData.do")
	public List<Map<String, Object>> treeData(@RequestParam (required = false) String extId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
//		ManageUser user = UserUtils.getUser();
		List<Area> list = areaService.findAll();
		for (int i = 0; i < list.size(); i++) {
			Area e = list.get(i);
			if (extId == null || (extId != null && !extId.equals(e.getId()))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
//				map.put("pId", !user.isAdmin()&&e.getId().equals(user.getArea().getId())?0:e.getParent()!=null?e.getParent().getId():0);
				map.put("pId", e.getParent() != null ? e.getParent().getId() : 0);
				map.put("name", e.getAreaName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
