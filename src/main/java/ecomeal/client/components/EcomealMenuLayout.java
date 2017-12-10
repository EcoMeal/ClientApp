package ecomeal.client.components;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class EcomealMenuLayout extends HorizontalLayout {

	private CssLayout contentArea = new CssLayout();
	private CssLayout menuArea = new CssLayout();
	
	public EcomealMenuLayout() {
		setSizeFull();
		VerticalLayout vertical = new VerticalLayout();
		menuArea.setPrimaryStyleName(ValoTheme.MENU_ROOT);
		contentArea.setPrimaryStyleName("valo-content");
		contentArea.addStyleName("v-scrollable");
		contentArea.setSizeFull();
		vertical.addComponent(contentArea);
		addComponents(menuArea, vertical);
		setExpandRatio(vertical, 1);
	}
	
	public ComponentContainer getContentArea() {
		return contentArea;
	}
	
	public void addMenu(Component menu) {
		menu.addStyleName(ValoTheme.MENU_PART);
		menuArea.addComponent(menu);
	}
	
}
