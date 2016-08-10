package com.base;

import android.util.SparseArray;
import android.view.View;

/**
 * 
 * @author ex-changkongshuai001
 *
 * @date 2015-12-15 下午6:46:28
 */
public class ViewHolder {
	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id)
	{
		// SparseArray类似hashmap，但是它的的key必须是整形，value必须是object类型，当满足这两条时使用SparseArray，因为它的效率比hashmap高
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null)
		{
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null)
		{
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}
}
