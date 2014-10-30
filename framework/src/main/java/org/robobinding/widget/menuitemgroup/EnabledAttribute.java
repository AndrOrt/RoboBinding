package org.robobinding.widget.menuitemgroup;

import org.robobinding.viewattribute.property.OneWayPropertyViewAttribute;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public class EnabledAttribute implements OneWayPropertyViewAttribute<MenuItemGroup, Boolean> {
	@Override
	public void updateView(MenuItemGroup view, Boolean newValue) {
		view.setEnabled(newValue);
	}

}
