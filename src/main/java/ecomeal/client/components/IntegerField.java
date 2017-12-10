package ecomeal.client.components;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class IntegerField extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	
	private final int MINIMUM = 1;
	private final int MAXIMUM = 50;
	
	private TextField textField;
	private Button plus;
	private Button minus;
	
	private String oldValue;
	
	public IntegerField() {
		textField = new TextField();
		textField.setValue("1");
		oldValue = textField.getValue();
		
		plus = new Button("+");
		plus.addClickListener(event -> {
			try {
				Integer i = new Integer(textField.getValue()) + 1;
				if(i >= MINIMUM && i <= MAXIMUM) {
					oldValue = i + "";
					textField.setValue(i + "");
				}
			} catch(NumberFormatException e) {
				// Log something
			}
		});
		
		minus = new Button("-");
		minus.addClickListener(event -> {
			try {
				Integer i = new Integer(textField.getValue()) - 1;
				if(i >= MINIMUM && i <= MAXIMUM) {
					oldValue = i + "";
					textField.setValue(i + "");
				}
			} catch(NumberFormatException e) {
				// Log something
			}
		});
		
		textField.addValueChangeListener(event -> {
			String text = event.getSource().getValue();
			try {
				new Integer(text);
				oldValue = text;
			} catch(NumberFormatException e) {
				textField.setValue(oldValue);
			}
		});
		textField.setValueChangeMode(ValueChangeMode.LAZY);
		
		//setStyleName(javaScriptClassName);
		setSpacing(false);
		addComponents(textField, plus, minus);
		//removeSpacings();
	}
	
}
