package com.mg.mobile.app.ui.tab;

import android.os.Bundle;
import android.widget.TabHost;

public class BasicTab
{
	private TabHost.TabSpec tabSpec;
	private Class<?> tabClass;
	private Bundle argments;

	public BasicTab(TabHost.TabSpec tabSpec,Class<?> tabClass,Bundle argments)
	{
		this.tabSpec = tabSpec;
		this.tabClass = tabClass;
		this.argments = argments;
	}

	public TabHost.TabSpec getTabSpec()
	{
		return tabSpec;
	}

	public Class<?> getTabClass()
	{
		return tabClass;
	}

	public Bundle getArgments()
	{
		return argments;
	}
}
