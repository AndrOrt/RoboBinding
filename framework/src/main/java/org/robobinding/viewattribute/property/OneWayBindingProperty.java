package org.robobinding.viewattribute.property;

import org.robobinding.attribute.ValueModelAttribute;
import org.robobinding.presentationmodel.PresentationModelAdapter;
import org.robobinding.property.PropertyChangeListener;
import org.robobinding.property.ValueModel;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public class OneWayBindingProperty extends AbstractBindingProperty {
	private final OneWayPropertyViewAttribute<Object, Object> viewAttribute;
	
	@SuppressWarnings("unchecked")
	public OneWayBindingProperty(Object view, 
			OneWayPropertyViewAttribute<?, ?> viewAttribute, 
			ValueModelAttribute attribute) {
		super(view, attribute, isAlwaysPreInitializingView(viewAttribute));
		
		this.viewAttribute = (OneWayPropertyViewAttribute<Object, Object>)viewAttribute;
	}

	@Override
	public void performBind(PresentationModelAdapter presentationModelAdapter) {
		final ValueModel<Object> valueModel = getPropertyValueModel(presentationModelAdapter);
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
			@Override
			public void propertyChanged() {
				updateView(valueModel);
			}
		};
		PropertyChangeListenerInUiThread inUiThread = new PropertyChangeListenerInUiThread(propertyChangeListener);
		valueModel.addPropertyChangeListener(inUiThread);
	}

	@Override
	public ValueModel<Object> getPropertyValueModel(PresentationModelAdapter presentationModelAdapter) {
		return presentationModelAdapter.getReadOnlyPropertyValueModel(attribute.getPropertyName());
	}

	@Override
	protected void updateView(ValueModel<Object> valueModel) {
		viewAttribute.updateView(view, valueModel.getValue());
	}
}