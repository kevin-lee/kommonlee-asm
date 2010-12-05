/**
 * 
 */
package org.elixirian.common.asm.analysis;

import java.lang.reflect.Member;
import java.util.Map;

import org.objectweb.asm.Type;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public interface MemberCollector<M extends Member>
{
	void collect(Class<?> theClass, String memberName, Map<M, String[]> memberToParameterNamesMap, Type[] params,
			String[] paramNames);
}