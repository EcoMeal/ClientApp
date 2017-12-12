package ecomeal.client.components;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class EcomealMenuLayout extends VerticalLayout {

	private static final long serialVersionUID = 1105797600667561029L;
	
	private CssLayout contentArea = new CssLayout();
	private HorizontalLayout menuArea = new HorizontalLayout();
	
	public EcomealMenuLayout() {
		setSizeFull();
		setMargin(false);
		VerticalLayout vertical = new VerticalLayout();
		menuArea.setPrimaryStyleName(ValoTheme.MENU_ROOT);
		menuArea.setWidth(100, Unit.PERCENTAGE);
		contentArea.setPrimaryStyleName("valo-content");
		contentArea.addStyleName("v-scrollable");
		contentArea.setSizeFull();
		contentArea.setHeight(null);
        contentArea.setWidth("100%");
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
