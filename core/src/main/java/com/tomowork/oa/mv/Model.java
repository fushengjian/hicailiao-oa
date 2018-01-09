package com.tomowork.oa.mv;

import java.util.Map;

/**
 * Model.
 *
 * @author zlei
 */
public interface Model {

	Map<String, Object> getModel();

	Model addObject(String attributeName, Object attributeValue);

	Model addObject(Object attributeValue);

	Model addAllObjects(Map<String, ?> modelMap);

	void clear();

	boolean isEmpty();

	boolean wasCleared();
}
