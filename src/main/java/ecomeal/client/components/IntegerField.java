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
		textField.addStyleName("ecomeal-text");
		textField.setValue("1");
		oldValue = textField.getValue();
		
		plus = new Button("+");
		plus.addStyleName("ecomeal-button");
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
		minus.addStyleName("ecomeal-button");
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
				Integer i = new Integer(text);
				if(i > MAXIMUM) {
					i = MAXIMUM;
				} else if(i < MINIMUM) {
					i = MINIMUM;
				}
				oldValue = i + "";
			} catch(NumberFormatException e) {
				// Nothing
			}
			textField.setValue(oldValue);
		});
		textField.setValueChangeMode(ValueChangeMode.LAZY);
		
		setSpacing(false);
		addComponents(textField, plus, minus);
	}
	
	public int getQuantity() {
		return new Integer(textField.getValue());
	}
	
}
