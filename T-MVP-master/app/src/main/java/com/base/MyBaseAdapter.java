package com.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 
 * @author ex-changkongshuai001
 *
 * @date 2015-11-6 下午3:02:13
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
	
	protected Context mContext;
	// 数据源
	protected List<T> mDatas;
	protected LayoutInflater mLayoutInflater;
	// listview item的布局文件
	protected int mItemId;
	/**
	 * 将position提供出去,当特殊条目有变化的时候可以使用该position
	 */
	private int mPosition;

	public MyBaseAdapter(Context context, List<T> datas, int itemLayoutId)
	{
		this.mContext = context;
		this.mDatas = datas;
		this.mItemId = itemLayoutId;
		mLayoutInflater = LayoutInflater.from(context);
	}

	/**
	 * 
	 * Tile:onDateChange(litview的数据变化时调用) void
	 */
	public void onDateChange(List<T> apk_list)
	{
		this.mDatas = apk_list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		if(mDatas==null){
			return 0;
		}
		return mDatas.size();
	}

	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	/**
	 * 当列表里的每一项显示到界面时，都会调用这个方法一次
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		mPosition = position;
		if (convertView == null)
		{
			convertView = mLayoutInflater.inflate(mItemId, parent, false);
		}
		convert(convertView, parent, getItem(position));
		return convertView;
	}

	/**
	 * 查询item对应子项并进行处理。 Tile:convert
	 * 
	 * @param convertView
	 *            当前item
	 * @param parent
	 * @param t
	 *            对应数据类型 void
	 */
	public abstract void convert(View convertView, ViewGroup parent, T t);

	/**
	 * @return	获取条目的position
	 */
	public int getPosition() {
		return mPosition;
	}
}
