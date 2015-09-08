package org.robobinding.property;

import org.robobinding.itempresentationmodel.RefreshableItemPresentationModel;
import org.robobinding.itempresentationmodel.ViewTypeSelectionContext;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
class DataSetPropertyValueModelWrapper extends PropertyWrapper implements DataSetPropertyValueModel {
	private final DataSetPropertyValueModel valueModel;

	public DataSetPropertyValueModelWrapper(DataSetPropertyValueModel dataSetPropertyValueModel) {
		super(dataSetPropertyValueModel);
		this.valueModel = dataSetPropertyValueModel;
	}

	@Override
	public int size() {
		return valueModel.size();
	}

	@Override
	public Object getItem(int position) {
		return valueModel.getItem(position);
	}

	@Override
	public RefreshableItemPresentationModel newRefreshableItemPresentationModel(int itemViewType) {
		return valueModel.newRefreshableItemPresentationModel(itemViewType);
	}
	
	@Override
	public int selectViewType(ViewTypeSelectionContext<Object> context) {
		return valueModel.selectViewType(context);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		valueModel.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		valueModel.removePropertyChangeListener(listener);
	}
}
