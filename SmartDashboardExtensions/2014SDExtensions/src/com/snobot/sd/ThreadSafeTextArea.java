package com.snobot.sd;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import edu.wpi.first.smartdashboard.gui.Widget.BindableTableEntry;
import edu.wpi.first.smartdashboard.gui.elements.bindings.StringBindable;
import edu.wpi.first.smartdashboard.robot.Robot;

public class ThreadSafeTextArea extends JTextArea implements StringBindable {

	private static final long serialVersionUID = 5638299125599027822L;
	private String value = null;
	private final StringBindable bindable;

	public ThreadSafeTextArea(final String key, String text) {
		super(text);

		bindable = new BindableTableEntry(Robot.getTable(), key);
		// setStringBinding(this);
	}

	public ThreadSafeTextArea(final String key) {
		this(key, "");
	}

    @Override
	public void setText(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
			public void run() {
				ThreadSafeTextArea.super.setText(text);
			}
		});
	}

	@Override
	public void setBindableValue(String value) {
		this.value = value;
		setText(value);
	}
}

// public static class ThreadSafeTextArea extends JTextArea {
//
// public ThreadSafeTextArea(String text) {
// super(text);
// }
//
// public ThreadSafeTextArea() {
// super();
// }
//
// public void setText(final String text) {
// SwingUtilities.invokeLater(new Runnable() {
// public void run() {
// ThreadSafeTextArea.super.setText(text);
// }
// });
// }
// }
//
// public static abstract class EditorTextArea extends ThreadSafeTextArea {
//
// public EditorTextArea()
// {
// // addActionListener(new ActionListener() {
// // public void actionPerformed(ActionEvent e) {
// // textChanged(getText());
// // }
// // });
// addFocusListener(new FocusListener() {
// public void focusGained(FocusEvent fe) {}
// public void focusLost(FocusEvent fe) {
// textChanged(getText());
// }
// });
// // setHorizontalAlignment(JTextField.LEFT);
// }
//
// protected abstract void textChanged(String text);
// }
//
// public static abstract class TextAreaField extends EditorTextArea implements
// StringBindable {
//
// private String value = null;
//
// protected void textChanged(String newValue) {
// if (!value.equals(newValue)) {
// if (setValue(newValue)) {
// value = newValue;
// } else {
// resetValue();
// }
// }
// }
//
// protected void resetValue() {
// if (value == null) {
// setText("");
// } else {
// setBindableValue(value);
// }
// }
//
// @Override
// public void setBindableValue(String value) {
// this.value = value;
// setText(value);
// }
//
// protected abstract boolean setValue(String value);
// }
//
// public static class BindableTextAreaField extends TextAreaField {
//
// private final StringBindable bindable;
//
// public BindableTextAreaField(StringBindable bindable) {
// this.bindable = bindable;
// }
//
// @Override
// protected boolean setValue(String value) {
// bindable.setBindableValue(value);
// return true;
// }
// }
//
// public class TextAreaValueWidiget extends BindableTextAreaField{
// public TextAreaValueWidiget(final String key){
// super(new BindableTableEntry(Robot.getTable(), key));
// setStringBinding(this);
// }
// }