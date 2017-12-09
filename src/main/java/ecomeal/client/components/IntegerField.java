package ecomeal.client.components;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.TextField;

public class IntegerField extends TextField {

	private static final long serialVersionUID = 1L;
	private String oldValue;
	
	public IntegerField() {
		oldValue = "1";
		addValueChangeListener(event -> {
			String text = event.getSource().getValue();
			try {
				new Integer(text);
				oldValue = text;
			} catch(NumberFormatException e) {
				setValue(oldValue);
			}
		});
		setValueChangeMode(ValueChangeMode.LAZY);
	}
	
}
