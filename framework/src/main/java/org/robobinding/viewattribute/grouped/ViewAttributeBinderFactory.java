package org.robobinding.viewattribute.grouped;

import org.robobinding.attribute.PropertyAttributeParser;
import org.robobinding.attribute.ValueModelAttribute;
import org.robobinding.viewattribute.event.EventViewAttributeBinder;
import org.robobinding.viewattribute.event.EventViewAttributeBinderFactory;
import org.robobinding.viewattribute.event.EventViewAttributeFactory;
import org.robobinding.viewattribute.property.AbstractMultiTypePropertyViewAttributeBinderFactory;
import org.robobinding.viewattribute.property.AbstractPropertyViewAttributeBinderFactory;
import org.robobinding.viewattribute.property.MultiTypePropertyViewAttributeBinder;
import org.robobinding.viewattribute.property.OneWayMultiTypePropertyViewAttribute;
import org.robobinding.viewattribute.property.OneWayMultiTypePropertyViewAttributeBinderFactory;
import org.robobinding.viewattribute.property.OneWayMultiTypePropertyViewAttributeFactory;
import org.robobinding.viewattribute.property.OneWayPropertyViewAttribute;
import org.robobinding.viewattribute.property.OneWayPropertyViewAttributeBinderFactory;
import org.robobinding.viewattribute.property.OneWayPropertyViewAttributeFactory;
import org.robobinding.viewattribute.property.PropertyViewAttributeBinder;
import org.robobinding.viewattribute.property.TwoWayMultiTypePropertyViewAttribute;
import org.robobinding.viewattribute.property.TwoWayMultiTypePropertyViewAttributeBinderFactory;
import org.robobinding.viewattribute.property.TwoWayMultiTypePropertyViewAttributeFactory;
import org.robobinding.viewattribute.property.TwoWayPropertyViewAttribute;
import org.robobinding.viewattribute.property.TwoWayPropertyViewAttributeBinderFactory;
import org.robobinding.viewattribute.property.TwoWayPropertyViewAttributeFactory;
import org.robobinding.widgetaddon.ViewAddOnInjector;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public class ViewAttributeBinderFactory {
	private final PropertyAttributeParser propertyAttributeParser;
	private final GroupAttributesResolver groupAttributesResolver;
	private final ViewAddOnInjector viewAddOnInjector;

	public ViewAttributeBinderFactory(PropertyAttributeParser propertyAttributeParser, 
			GroupAttributesResolver resolvedGroupAttributesFactory, ViewAddOnInjector viewListenersInjector) {
		this.propertyAttributeParser = propertyAttributeParser;
		this.groupAttributesResolver = resolvedGroupAttributesFactory;
		this.viewAddOnInjector = viewListenersInjector;
	}
	
	public AbstractPropertyViewAttributeBinderFactory binderFactoryFor(OneWayPropertyViewAttributeFactory<?> factory) {
		return new OneWayPropertyViewAttributeBinderFactory(factory, viewAddOnInjector, propertyAttributeParser);
	}
	
	public AbstractPropertyViewAttributeBinderFactory binderFactoryFor(TwoWayPropertyViewAttributeFactory<?> factory) {
		return new TwoWayPropertyViewAttributeBinderFactory(factory, viewAddOnInjector, propertyAttributeParser);
	}
	
	public AbstractMultiTypePropertyViewAttributeBinderFactory binderFactoryFor(OneWayMultiTypePropertyViewAttributeFactory<?> factory) {
		return new OneWayMultiTypePropertyViewAttributeBinderFactory(factory, viewAddOnInjector, propertyAttributeParser);
	}
	
	public AbstractMultiTypePropertyViewAttributeBinderFactory binderFactoryFor(TwoWayMultiTypePropertyViewAttributeFactory<?> factory) {
		return new TwoWayMultiTypePropertyViewAttributeBinderFactory(factory, viewAddOnInjector, propertyAttributeParser);
	}
	
	public EventViewAttributeBinderFactory binderFactoryFor(EventViewAttributeFactory<?> factory) {
		return new EventViewAttributeBinderFactory(viewAddOnInjector, factory);
	}
	
	public GroupedViewAttributeBinderFactory binderFactoryFor(GroupedViewAttributeFactory<?> factory) {
		return new GroupedViewAttributeBinderFactory(factory, groupAttributesResolver, this);
	}

	public PropertyViewAttributeBinder binderFor(final OneWayPropertyViewAttribute<?, ?> viewAttribute,
			ValueModelAttribute attribute) {
		return binderFor(new OneWayPropertyViewAttributeFactory<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public OneWayPropertyViewAttribute<Object, ?> create() {
				return (OneWayPropertyViewAttribute<Object, ?>)viewAttribute;
			}
		}, attribute);
	}

	public PropertyViewAttributeBinder binderFor(OneWayPropertyViewAttributeFactory<?> factory,
			ValueModelAttribute attribute) {
		AbstractPropertyViewAttributeBinderFactory binderFactory = binderFactoryFor(factory);
		return binderFactory.create(binderFactory, attribute);
	}

	public PropertyViewAttributeBinder binderFor(final TwoWayPropertyViewAttribute<?, ?, ?> viewAttribute,
			ValueModelAttribute attribute) {
		return binderFor(new TwoWayPropertyViewAttributeFactory<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public TwoWayPropertyViewAttribute<Object, ?, ?> create() {
				return (TwoWayPropertyViewAttribute<Object, ?, ?>)viewAttribute;
			}
		}, attribute);
	}

	public PropertyViewAttributeBinder binderFor(TwoWayPropertyViewAttributeFactory<?> factory,
			ValueModelAttribute attribute) {
		AbstractPropertyViewAttributeBinderFactory binderFactory = binderFactoryFor(factory);
		return binderFactory.create(binderFactory, attribute);
	}

	public MultiTypePropertyViewAttributeBinder binderFor(final OneWayMultiTypePropertyViewAttribute<?> viewAttribute,
			ValueModelAttribute attribute) {
		return binderFor(new OneWayMultiTypePropertyViewAttributeFactory<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public OneWayMultiTypePropertyViewAttribute<Object> create() {
				return (OneWayMultiTypePropertyViewAttribute<Object>)viewAttribute;
			}
		}, attribute);
	}

	public MultiTypePropertyViewAttributeBinder binderFor(
			OneWayMultiTypePropertyViewAttributeFactory<?> factory, ValueModelAttribute attribute) {
		AbstractMultiTypePropertyViewAttributeBinderFactory binderFactory = binderFactoryFor(factory);
		return binderFactory.create(binderFactory, attribute);
	}

	public MultiTypePropertyViewAttributeBinder binderFor(final TwoWayMultiTypePropertyViewAttribute<?> viewAttribute,
			ValueModelAttribute attribute) {
		return binderFor(new TwoWayMultiTypePropertyViewAttributeFactory<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public TwoWayMultiTypePropertyViewAttribute<Object> create() {
				return (TwoWayMultiTypePropertyViewAttribute<Object>)viewAttribute;
			}
		}, attribute);
	}

	public MultiTypePropertyViewAttributeBinder binderFor(
			TwoWayMultiTypePropertyViewAttributeFactory<?> factory, ValueModelAttribute attribute) {
		AbstractMultiTypePropertyViewAttributeBinderFactory binderFactory = binderFactoryFor(factory);
		return binderFactory.create(binderFactory, attribute);
	}

	public EventViewAttributeBinder binderFor(EventViewAttributeFactory<?> factory, String attributeName,
			String attributeValue) {
		EventViewAttributeBinderFactory binderFactory = binderFactoryFor(factory);
		return binderFactory.create(binderFactory, attributeName, attributeValue);
	}
}
